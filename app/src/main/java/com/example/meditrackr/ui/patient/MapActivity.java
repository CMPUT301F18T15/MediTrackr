/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */
package com.example.meditrackr.ui.patient;

//imports
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.CustomInfoWindowAdapter;
import com.example.meditrackr.adapters.PlaceAutocompleteAdapter;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.PlaceInfo;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.models.record.Geolocation;
import com.example.meditrackr.models.record.Record;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Skryt on Nov 13, 2018
 */

// taken from coding with mitch youtube series
// this class works and don't touch it


// Class handles map activity
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener {
    // OnConnectionFailedListener provides callbacks for events that result in a
    // failed attempt to connect the client to service

    // When connection to client failed
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    // When map GoogleMaps is available for viewing
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toasty.info(this, "Map is ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        // If location permission is granted get device location
        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // End execution if precise and approximate location permission is denied
                return;
            }
            // Enable my-location layer and disable the my-location button
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            placeMarkers();
            init();
        }
    }

    // Indicators and request codes
    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));


    // Initialize widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps, mInfo, mPlacePicker;

    // Initialize variables
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private PlaceInfo mPlace;
    private Marker mMarker;


    // Creates map activity view objects based on layouts in XML
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        mSearchText = (AutoCompleteTextView) findViewById(R.id.input_search);
        mGps = (ImageView) findViewById(R.id.ic_gps);
        mGps = (ImageView) findViewById(R.id.ic_gps);
        mInfo = (ImageView) findViewById(R.id.place_info);

        // Get location permissions
        getLocationPermission();


    }

    // Initialize map activity
    private void init() {
        Log.d(TAG, "init: initializing");

        // Create new GoogleApiClient to configure google play services integration
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        mSearchText.setOnItemClickListener(mAutocompleteClickListener);

        // Adapter that handles autocomplete requests from Google geo data client
        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient,
                LAT_LNG_BOUNDS, null);

        mSearchText.setAdapter(mPlaceAutocompleteAdapter);

        // Interface definition for a callback to be invokedwhen an action is performed on the editor
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                // Define what to do for certain actionIds or key events
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {

                    // Execute our method for searching
                    geoLocate();
                }

                return false;
            }
        });

        // OnClickListener for gps icon
        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked gps icon");
                getDeviceLocation();
            }
        });

        // OnClickListener for place info
        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked place info");
                try { // If click when info window is shown close window
                    if (mMarker.isInfoWindowShown()) {
                        mMarker.hideInfoWindow();
                    } else { // If click when window is closed, open window
                        Log.d(TAG, "onClick: place info: " + mPlace.toString());
                        mMarker.showInfoWindow();
                    }
                } catch (NullPointerException e) { // Throw exception when null was clicked
                    Log.e(TAG, "onClick: NullPointerException: " + e.getMessage());
                }
            }
        });

        hideSoftKeyboard();
    }


    // Method for searching location
    private void geoLocate() {
        Log.d(TAG, "geoLocate: geolocating");

        String searchString = mSearchText.getText().toString();

        // Geocoder handles location search
        Geocoder geocoder = new Geocoder(MapActivity.this);
        List<Address> list = new ArrayList<>();
        try { // Set geocoder address as an array list
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) { // Throw exception when input output error caught
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }

        // If list is not empty and a location was found
        if (list.size() > 0) {
            Address address = list.get(0);

            Log.d(TAG, "geoLocate: found a location: " + address.toString());
            //Toasty.info(this, address.toString(), Toast.LENGTH_SHORT).show();

            // Move view to display location on map
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
                    address.getAddressLine(0));
        }
    }

    // Method gets device location
    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        // Create a new instance of FusedLocationProviderClient for use in a non-activity context
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            // If location permission was granted
            if (mLocationPermissionsGranted) {

                // Get lastest location
                final Task location = mFusedLocationProviderClient.getLastLocation();
                // Listener is called when following task is completed
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) { // If location successfully found
                            Log.d(TAG, "onComplete: found location!");
                            // Set location
                            Location currentLocation = (Location) task.getResult();

                            // Move map to display current location
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM,
                                    "My Location");

                        } else { // Location does not exist or could not be found indicate so
                            Log.d(TAG, "onComplete: current location is null");
                            Toasty.error(MapActivity.this, "Unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) { // Throw exception when security manager catches a security violation
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    // Method moves map to display current location
    private void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
        // Modify map's camera to current location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        // Hide keyboard
        hideSoftKeyboard();
    }

    // Method initialize google maps
    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // Acquires google maps
        mapFragment.getMapAsync(MapActivity.this);
    }

    // Method gets location permission
    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        // If permission is granted to access precise location
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // If permission is granted to access approximate location
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // If both above hold true then location permission is granted
                mLocationPermissionsGranted = true;
                // Initialize google maps
                initMap();
            } else {
                // Else if approximate location permission is denied, request for permission again
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else { // Else if precise location permission is denied, request for permission again
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    // Returns the result of request permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        // Always set permission granted to false before getting permission
        // or else permission would always seem to be granted even when its not
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            // Check if permission is granted or denied
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        // If grantResults do not match up with permission granted code then permission is denied
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    // If grantResults match with permission granted code then permission is granted
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    // Initialize our map
                    initMap();
                }
            }
        }
    }

    // Hides keyboard
    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /*
        --------------------------- google places API autocomplete suggestions -----------------
     */

    // Autocompletes location search using keyword prediction from google's api
    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            hideSoftKeyboard();

            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(i);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };


    // Gets location info from location found
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.d(TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
                places.release();
                return;
            }
            final Place place = places.get(0);

            try {
                mPlace = new PlaceInfo();
                mPlace.setName(place.getName().toString());
                Log.d(TAG, "onResult: name: " + place.getName());
                mPlace.setAddress(place.getAddress().toString());
                Log.d(TAG, "onResult: address: " + place.getAddress());
                mPlace.setId(place.getId());
                Log.d(TAG, "onResult: id:" + place.getId());
                mPlace.setLatlng(place.getLatLng());
                Log.d(TAG, "onResult: latlng: " + place.getLatLng());
                mPlace.setRating(place.getRating());
                Log.d(TAG, "onResult: rating: " + place.getRating());
                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                Log.d(TAG, "onResult: phone number: " + place.getPhoneNumber());
                mPlace.setWebsiteUri(place.getWebsiteUri());
                Log.d(TAG, "onResult: website uri: " + place.getWebsiteUri());

                Log.d(TAG, "onResult: place: " + mPlace.toString());
            } catch (NullPointerException e) {
                Log.e(TAG, "onResult: NullPointerException: " + e.getMessage());
            }

            // Move camera to show location on map
            moveCamera(new LatLng(place.getViewport().getCenter().latitude,
                    place.getViewport().getCenter().longitude), DEFAULT_ZOOM, mPlace.getName());

            places.release();
        }
    };

    // Place markers on map according to user info
    public void placeMarkers() {
        Profile profile = LazyLoadingManager.getProfile();
        if(!profile.getisCareProvider()) { // If account belongs to a patient
            Patient patient = LazyLoadingManager.getPatient();
            for (int i = 0; i < patient.getProblems().getSize(); i++) { // Set a marker on map for each problem
                Log.d("MAPMARKER", "problemlist size" + patient.getProblems().getSize());
                for (int j = 0; j < patient.getProblem(i).getRecords().getSize(); j++) {
                    Geolocation geolocation = patient.getProblem(i).getRecords().getRecord(j).getGeoLocation();
                    Record record = patient.getProblem(i).getRecords().getRecord(j);

                    // Initialize custom window for google maps
                    mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapActivity.this));

                    try { // Include date and description in a snippet
                        String snippet =
                                "Record #: " + j + "\n" +
                                        "Date: " + record.getDate() + "\n" +
                                        "Description: " + record.getDescription() + "\n";

                        // For each marker set location, set the location title, and snippet
                        MarkerOptions options = new MarkerOptions()
                                .position(new LatLng(geolocation.getLatitude(), geolocation.getLongitude()))
                                .title(patient.getProblem(i).getDescription())
                                .snippet(snippet);

                        // Add patient problem markers on map
                        mMarker = mMap.addMarker(options);

                    } catch (NullPointerException e) { // Throw exception if map has nothing to show
                        Log.e(TAG, "moveCamera: NullPointerException: " + e.getMessage());
                    }

                    // Hide keyboard
                    hideSoftKeyboard();
                }
            }
        }else { // Else if there are no marker do not add markers
            Log.d("Adding no materkers", "adding no markers");
        }else {
            Log.d("Adding no markers", "adding no markers");
        }

    }
}





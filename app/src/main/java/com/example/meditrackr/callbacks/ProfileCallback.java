package com.example.meditrackr.callbacks;

import com.example.meditrackr.models.Profile;

/**
 * Created by Skryt on Oct 31, 2018
 */

// callback for elasticsearch, onFailure and onSuccess
public interface ProfileCallback {
    void onFailure(String reason);
    void onSuccess(Profile profile, String id);
}

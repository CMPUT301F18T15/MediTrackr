/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
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
package com.example.meditrackr.controllers;

//imports
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * this class can check to see if there is a network available and whether we are online or offline
 * this class can use isNetWorkAvailable to see if there is a network that we can connect to.
 * if there is a network then it will return true otherwise return false
 *
 * this class can use isOnline to check to see if we are online or not.
 * after it checks if we are online then it will return true if we are online or it will return
 * false if we are offline
 *
 * @author Orest Cokan
 * @version 2.0 Nov 14, 2018
 */

// Class connects app to internet
public class NetworkCheckController {

    /**
     * Checks if internet access is available.
     *
     * @author Orest Cokan
     * @return boolean
     */
    public boolean isNetWorkAvaliable(Context context){ // Connects to network
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo(); // Gets network connection
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    /**
     * Checks if internet access is available. Do not call from main thread.
     *
     * @author Orest Cokan
     * @return whether internet access is available
     */
    // Connects to server proxy
    public static boolean isOnline() {
        try {
            int timeoutMs = 1500;
            Socket socket = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53); //Creates proxy info

            // Connect to IP address
            socket.connect(sockaddr, timeoutMs);
            socket.close();

            return true;
        } catch (IOException e) { return false; } // Throws exception if cannot connect to server proxy
    }
}

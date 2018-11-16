package com.example.meditrackr.controllers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Skryt on Nov 13, 2018
 */


public class NetworkCheckController {
    public boolean isNetWorkAvaliable(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Checks if internet access is available. Do not call from main thread.
     * @return whether internet access is available
     */

    public static boolean isOnline() {
        try {
            int timeoutMs = 1500;
            Socket socket = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            socket.connect(sockaddr, timeoutMs);
            socket.close();

            return true;
        } catch (IOException e) { return false; }
    }
}
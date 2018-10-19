package com.example.meditrackr.callbacks;

import com.example.meditrackr.Profile;

public interface ProfileCallback {
    void onFailure(String reason);
    void onSuccess(Profile profile, String id);
}

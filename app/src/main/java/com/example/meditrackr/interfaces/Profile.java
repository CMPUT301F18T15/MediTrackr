package com.example.meditrackr.interfaces;

/**
 * Crated by Skryt on Nov 19, 2018
 */

public interface Profile {
    String getUsername();
    void setUsername(String username);

    String getEmail();
    void setEmaiL(String email);

    String getPhone();
    void setPhone(String phone);

    void setisCareProvider(boolean isCareProvider);

    void getisCareProvider();
}

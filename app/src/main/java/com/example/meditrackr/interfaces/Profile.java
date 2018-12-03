/*--------------------------------------------------------------------------
 * FILE: Profile.java
 *
 * PURPOSE: Functions related to profile information.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/

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

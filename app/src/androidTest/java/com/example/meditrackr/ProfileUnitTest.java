package com.example.meditrackr;

import com.example.meditrackr.models.Profile;
import com.example.meditrackr.models.Record;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ProfileUnitTest {
    /**
     * Unit Tests for Profile
     * Trivial getters & setters testing omitted.
     */
     private Profile profile;


     // Initialize profile
     @Before
     private void initPUnitTest() {
         final String Username = '';
         final Date Email = '';
         final String Phone = '';
         profile = new profile(Username, Email, Phone);
    }

    // Username should not have spaces
    @Test
    public boolean UsernameTest(){
      final String usrName = 'test name';
      profile.setUsername(usrName);

      for(i in Profile.username){
        if(i == ' '){
          system.out.print("Can't have spaces in username" )
        }
      }
    }

    // check to see if email adress is in proper form
    //can't have a space in an email adress
    @Test
    public void EmailSpaceTest(){
    final String WithSpace = 'test email@gmail.com';
    profile.setEmail(WithSpace);
    for(i in Profile.email){
      if(i == ' '){
        system.out.print("Can't have spaces in email" )
      }
    }
    }

  //email adress must have @ and a '.com' at end
  @Test
  public void EmailTest(){
  final String BadEmail = 'testemailgmail';
  profile.setEmail(BadEmail);

  // use this to keep track if there is an @
  count = 0;
  for(i in Profile.email){
   
    // email must have a @
    if(i == '@')
      count = 1;
  }

  // this is if there is no @
  if(count == 0)
    system.out.print("Email adress not valid (no @ detected)" )

  // To DO check if last 4 chars is .com if sodo nothing

  if email.endsWith(".com")

  else
  system.out.print("Email adress not valid (must end in .com)" )
  }




    //make sure phone is in proper form
    @Test
    public boolean PhoneTest(){
      final String BadPhone = 'a123 45678';
      profile.setPhone(BadPhone);
      for(i in profile.phone){
        // no spaces allowed
        if(i == ' ')
          system.out.print("Can't have spaces in Phone Number" )

        //no letters allowed
        if(i.isLetter)
          system.out.print("Can't have letters in Phone Number" )
        }

        // make sure its only 10 digits long
        len = profile.phone.length()
        if(len == 0)
            system.out.print("Please enter Phone Number" )
        else if(len != 10)
          system.out.print("Phone Number must be only 10 digits long. no need to add () or -" )

    }

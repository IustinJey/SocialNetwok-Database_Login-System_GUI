package com.example.guiex1.data;

import com.example.guiex1.domain.User;

import java.time.LocalDateTime;

public class UserData {
    public static User connectedUser;
    public static User selectedFriend;
    public static LocalDateTime friendsSince;
    public static Long selectedUser2;
    public static String firstName;
    public static String lastName;

    public static void clearDataUser()
    {
        firstName = null;
        lastName = null;
        connectedUser = null;
        selectedUser2 = null;
    }
}

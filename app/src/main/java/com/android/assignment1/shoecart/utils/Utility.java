package com.android.assignment1.shoecart.utils;

import android.content.Context;

import com.android.assignment1.shoecart.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private static final String SYMBOL_PATTERN = "[^a-z0-9 ]";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean validatePassword(String password) {
        return password.length() >= 5;
    }

    public static boolean validateIsPasswordEmpty(String password) {
        return password.length() == 0;
    }

    public static boolean validateUserName(String username) {
        return username.length() > 3;
    }

    public static boolean validateSymbolsInUserName(String username) {
        Pattern p = Pattern.compile(SYMBOL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(username);
        return m.find();
    }

    public static boolean validateSpaceInUserName(String username) {
        return username.contains(" ");
    }

    public static boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String getAppNameString(Context context) {
        if (context != null)
            return context.getResources().getString(R.string.app_name);
        return "";
    }
}

package com.android.assignment1.shoecart.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.fragments.HomeFragment;
import com.android.assignment1.shoecart.models.User;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class
 */
public class Utility {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private static final String SYMBOL_PATTERN = "[^a-z0-9 ]";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * validatePassword method
     *
     * @param password
     * @return
     */
    public static boolean validatePassword(String password) {
        return password.length() >= 5;
    }

    /**
     * validateIsPasswordEmpty method
     *
     * @param password
     * @return
     */
    public static boolean validateIsPasswordEmpty(String password) {
        return password.length() == 0;
    }

    /**
     * validateIsNameEmpty method
     *
     * @param name
     * @return
     */
    public static boolean validateIsNameEmpty(String name) {
        return name.length() == 0;
    }

    /**
     * validateIsTextAreaEmpty method
     *
     * @param textArea
     * @return
     */
    public static boolean validateIsTextAreaEmpty(String textArea) {
        return textArea.length() == 0;
    }

    /**
     * validateUserName method
     *
     * @param username
     * @return
     */
    public static boolean validateUserName(String username) {
        return username.length() > 3;
    }

    /**
     * validateSymbolsInUserName method
     *
     * @param username
     * @return
     */
    public static boolean validateSymbolsInUserName(String username) {
        Pattern p = Pattern.compile(SYMBOL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(username);
        return m.find();
    }

    /**
     * validateSpaceInUserName method
     *
     * @param username
     * @return
     */
    public static boolean validateSpaceInUserName(String username) {
        return username.contains(" ");
    }

    /**
     * validateEmail method
     *
     * @param email
     * @return
     */
    public static boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * getAppNameString method
     *
     * @param context
     * @return
     */
    public static String getAppNameString(Context context) {
        if (context != null)
            return context.getResources().getString(R.string.app_name);
        return "";
    }

    /**
     * capitalizeFirstLetter method
     *
     * @param input
     * @return
     */
    public static String capitalizeFirstLetter(final String input) {
        if (!Character.isUpperCase(input.charAt(0)))
            return Character.toUpperCase(input.charAt(0)) + input.substring(1);
        else return input;
    }

    /**
     * getImageResourceFromName method
     *
     * @param imageName
     * @param context
     * @return
     */
    public static int getImageResourceFromName(String imageName, Context context) {
        String imageNameWithoutExtension = imageName.substring(0, imageName.lastIndexOf('.'));
        int resourceId = context.getResources().getIdentifier(imageNameWithoutExtension, "drawable", context.getPackageName());

        return resourceId;
    }

    /**
     * storeUser method
     *
     * @param user
     * @param context
     */
    //    storing employee inside shared preferences
    public static void storeUser(User user, Context context) {

        SharedPreferences sp = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("user", new Gson().toJson(user));
        editor.commit();

    }

    /**
     * getUser method
     *
     * @param context
     * @return
     */
    //    getting data from shared preferences
    public static User getUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        Type listType = new TypeToken<ArrayList<Employee>>(){}.getType();
        Log.e("TAG", new Gson().fromJson(sp.getString("user", ""), User.class) + "");
        return new Gson().fromJson(sp.getString("user", ""), User.class);
    }

    /**
     * showStyledAlertDialog method
     *
     * @param context
     * @return
     */
    public static TextView showStyledAlertDialog(Context context) {
        TextView titleTextView = new TextView(context);
        titleTextView.setText(getAppNameString(context));
        titleTextView.setGravity(Gravity.LEFT);
        // add some leftpadding to the title matching the left padding of the message
        titleTextView.setPadding(60, 40, 0, 0);
        titleTextView.setMaxLines(1);
        titleTextView.setShadowLayer(20, 4, 3, ContextCompat.getColor(context, R.color.colorAccent));
        titleTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        titleTextView.setTypeface(null, Typeface.BOLD);
        return titleTextView;
    }

    /**
     * callHomeFragment method
     *
     * @param fragmentManager
     */
    public static void callHomeFragment(FragmentManager fragmentManager) {
        // Create a new instance of HomeFragment
        HomeFragment homeFragment = new HomeFragment();


        // Begin a FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the current fragment with HomeFragment
        fragmentTransaction.replace(R.id.frames, homeFragment);

        // Add this transaction to the back stack
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}

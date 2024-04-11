package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PURCHASE_HISTORY = "purchase_history";
    public static final String COLUMN_SHIPPING_ADDRESS_1 = "shipping_address_1";
    public static final String COLUMN_SHIPPING_ADDRESS_2 = "shipping_address_2";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_PINCODE = "pincode";

    // User fields...
    private int userId;
    private String name;
    private String email;
    private String password;
    private String username;
    private String purchaseHistory;
    private String shippingAddress1;
    private String shippingAddress2;
    private String city;
    private String province;
    private String pincode;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT UNIQUE,"
                    + COLUMN_PASSWORD + " TEXT,"
                    + COLUMN_USERNAME + " TEXT,"
                    + COLUMN_PURCHASE_HISTORY + " TEXT,"
                    + COLUMN_SHIPPING_ADDRESS_1 + " TEXT,"
                    + COLUMN_SHIPPING_ADDRESS_2 + " TEXT,"
                    + COLUMN_CITY + " TEXT,"
                    + COLUMN_PROVINCE + " TEXT,"
                    + COLUMN_PINCODE + " TEXT"
                    + ")";

    public User() {
    }

    public User(int userId, String name, String email, String password, String username, String purchaseHistory, String shippingAddress1, String shippingAddress2, String city, String province, String pincode) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.purchaseHistory = purchaseHistory;
        this.shippingAddress1 = shippingAddress1;
        this.shippingAddress2 = shippingAddress2;
        this.city = city;
        this.province = province;
        this.pincode = pincode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(String purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    protected User(Parcel in) {
        userId = in.readInt();
        name = in.readString();
        email = in.readString();
        password = in.readString();
        username = in.readString();
        purchaseHistory = in.readString();
        shippingAddress1 = in.readString();
        shippingAddress2 = in.readString();
        city = in.readString();
        province = in.readString();
        pincode = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(username);
        parcel.writeString(purchaseHistory);
        parcel.writeString(shippingAddress1);
        parcel.writeString(shippingAddress2);
        parcel.writeString(city);
        parcel.writeString(province);
        parcel.writeString(pincode);
    }
}
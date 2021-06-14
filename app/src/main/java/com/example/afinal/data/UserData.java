package com.example.afinal.data;

import android.media.Image;

import java.util.ArrayList;

public class UserData {
    String username;
    Image profilePicture;
    ArrayList<Review> yourReviews;
    ArrayList<RestaurantData> favouriteRestaurantData;
    boolean isCreated = false;

    public UserData(String username) {
        if(isCreated) return;
        this.username = username;
        yourReviews = new ArrayList<>();
        favouriteRestaurantData = new ArrayList<>();
        isCreated = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public ArrayList<Review> getYourReviews() {
        return yourReviews;
    }

    public void setYourReviews(ArrayList<Review> yourReviews) {
        this.yourReviews = yourReviews;
    }

    public ArrayList<RestaurantData> getFavouriteRestaurants() {
        return favouriteRestaurantData;
    }

    public void setFavouriteRestaurants(ArrayList<RestaurantData> favouriteRestaurantData) {
        this.favouriteRestaurantData = favouriteRestaurantData;
    }
}

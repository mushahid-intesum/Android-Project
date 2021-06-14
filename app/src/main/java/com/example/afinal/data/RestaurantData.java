package com.example.afinal.data;

import android.location.Location;
import android.media.Rating;

import java.util.ArrayList;

public class RestaurantData {
    public String restaurantName;
    public String restaurantLocation;
//    public Rating averageRating;
    public ArrayList<Review> restaurantReviews;
    boolean isCreated;
    public RestaurantData(String restaurantName, String location) {
        if(isCreated) return;
        this.restaurantName = restaurantName;
        this.restaurantLocation = location;
        restaurantReviews = new ArrayList<>();
        isCreated = true;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }


//    public Rating getAverageRating() {
//        return averageRating;
//    }

//    public void setAverageRating(Rating averageRating) {
//        this.averageRating = averageRating;
//    }

    public ArrayList<Review> getRestaurantReviews() {
        return restaurantReviews;
    }
}

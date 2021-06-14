package com.example.afinal.data;

import android.media.Rating;

public class Review {
//    public UserData userData;
    private String reviewTitle;
    public String reviewText;
    Rating rating;
    String date;
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Review(String reviewTitle, String reviewText, String date) {
        this.reviewTitle = reviewTitle;
        this.reviewText = reviewText;
        this.date = date;
    }

    public String getTitle() {
        return reviewTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getDate() {
        return date;
    }
}

package com.example.sarthiithetuitionfinder;

public class SearchModal {
    String heading;
    String Rating;

    public  SearchModal() {}

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public SearchModal(String heading, String rating) {
        this.heading = heading;
        Rating = rating;
    }
}

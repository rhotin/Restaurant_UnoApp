package com.roman.restaurantunoapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by roman on 11/5/2015.
 */
public class RestaurantObject implements Parcelable {
    String restName;
    String restDay1;
    String restStartTime1;
    String restEndTime1;
    String restDay2;
    String restStartTime2;
    String restEndTime2;
    String restDay3;
    String restStartTime3;
    String restEndTime3;
    String restDay4;
    String restStartTime4;
    String restEndTime4;
    String restDay5;
    String restStartTime5;
    String restEndTime5;
    String restDay6;
    String restStartTime6;
    String restEndTime6;
    String restDay7;
    String restStartTime7;
    String restEndTime7;
    String restPhone;
    double restLat;
    double restLong;

    RestaurantObject(String rest_Name, String rest_Day1, String rest_StartTime1, String rest_EndTime1,
                     String rest_Day2, String rest_StartTime2, String rest_EndTime2,
                     String rest_Day3, String rest_StartTime3, String rest_EndTime3,
                     String rest_Day4, String rest_StartTime4, String rest_EndTime4,
                     String rest_Day5, String rest_StartTime5, String rest_EndTime5,
                     String rest_Day6, String rest_StartTime6, String rest_EndTime6,
                     String rest_Day7, String rest_StartTime7, String rest_EndTime7,
                     String rest_Phone, double rest_Lat, double rest_Long) {
        this.restName = rest_Name;

        this.restDay1 = rest_Day1;
        this.restStartTime1 = rest_StartTime1;
        this.restEndTime1 = rest_EndTime1;

        this.restDay2 = rest_Day2;
        this.restStartTime2 = rest_StartTime2;
        this.restEndTime2 = rest_EndTime2;

        this.restDay3 = rest_Day3;
        this.restStartTime3 = rest_StartTime3;
        this.restEndTime3 = rest_EndTime3;

        this.restDay4 = rest_Day4;
        this.restStartTime4 = rest_StartTime4;
        this.restEndTime4 = rest_EndTime4;

        this.restDay5 = rest_Day5;
        this.restStartTime5 = rest_StartTime5;
        this.restEndTime5 = rest_EndTime5;

        this.restDay6 = rest_Day6;
        this.restStartTime6 = rest_StartTime6;
        this.restEndTime6 = rest_EndTime6;

        this.restDay7 = rest_Day7;
        this.restStartTime7 = rest_StartTime7;
        this.restEndTime7 = rest_EndTime7;

        this.restPhone = rest_Phone;
        this.restLat = rest_Lat;
        this.restLong = rest_Long;
    }

    protected RestaurantObject(Parcel in) {
        restName = in.readString();

        restDay1 = in.readString();
        restStartTime1 = in.readString();
        restEndTime1 = in.readString();

        restDay2 = in.readString();
        restStartTime2 = in.readString();
        restEndTime2 = in.readString();

        restDay3 = in.readString();
        restStartTime3 = in.readString();
        restEndTime3 = in.readString();

        restDay4 = in.readString();
        restStartTime4 = in.readString();
        restEndTime4 = in.readString();

        restDay5 = in.readString();
        restStartTime5 = in.readString();
        restEndTime5 = in.readString();

        restDay6 = in.readString();
        restStartTime6 = in.readString();
        restEndTime6 = in.readString();

        restDay7 = in.readString();
        restStartTime7 = in.readString();
        restEndTime7 = in.readString();

        restPhone = in.readString();
        restLat = in.readDouble();
        restLong = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(restName);

        dest.writeString(restDay1);
        dest.writeString(restStartTime1);
        dest.writeString(restEndTime1);

        dest.writeString(restDay2);
        dest.writeString(restStartTime2);
        dest.writeString(restEndTime2);

        dest.writeString(restDay3);
        dest.writeString(restStartTime3);
        dest.writeString(restEndTime3);

        dest.writeString(restDay4);
        dest.writeString(restStartTime4);
        dest.writeString(restEndTime4);

        dest.writeString(restDay5);
        dest.writeString(restStartTime5);
        dest.writeString(restEndTime5);

        dest.writeString(restDay6);
        dest.writeString(restStartTime6);
        dest.writeString(restEndTime6);

        dest.writeString(restDay7);
        dest.writeString(restStartTime7);
        dest.writeString(restEndTime7);

        dest.writeString(restPhone);
        dest.writeDouble(restLat);
        dest.writeDouble(restLong);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RestaurantObject> CREATOR = new Parcelable.Creator<RestaurantObject>() {
        @Override
        public RestaurantObject createFromParcel(Parcel in) {
            return new RestaurantObject(in);
        }

        @Override
        public RestaurantObject[] newArray(int size) {
            return new RestaurantObject[size];
        }
    };

}

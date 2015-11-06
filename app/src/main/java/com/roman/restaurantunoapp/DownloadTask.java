package com.roman.restaurantunoapp;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by roman on 11/5/2015.
 */
public class DownloadTask extends AsyncTask<Void, Integer, Void> {

    ArrayList<RestaurantObject> restaurnatsArrayList = new ArrayList<>();
    Communicator context;

    DownloadTask(Context c) {
        this.context = (Communicator) c;
    }

    @Override
    protected Void doInBackground(Void... params) {
        URL theUrl = null;

        String rest_Name;
        String rest_Day1;
        String rest_StartTime1;
        String rest_EndTime1;
        String rest_Day2;
        String rest_StartTime2;
        String rest_EndTime2;
        String rest_Day3;
        String rest_StartTime3;
        String rest_EndTime3;
        String rest_Day4;
        String rest_StartTime4;
        String rest_EndTime4;
        String rest_Day5;
        String rest_StartTime5;
        String rest_EndTime5;
        String rest_Day6;
        String rest_StartTime6;
        String rest_EndTime6;
        String rest_Day7;
        String rest_StartTime7;
        String rest_EndTime7;
        String rest_Phone;
        double rest_Lat;
        double rest_Long;

        try {
            theUrl = new URL(RestaurantListActivity.UNOAPP_URL);
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(theUrl.openConnection().getInputStream(), "UTF-8"));
            String rest_json = reader.readLine();

            JSONObject rest_object = new JSONObject(rest_json);
            JSONArray data_arr = rest_object.getJSONObject("data").getJSONArray("companies");

            int totalRestaurants = data_arr.length();
            for (int i = 0; i < totalRestaurants; i++) {
                publishProgress((int) (((i + 1.0) / totalRestaurants) * 100.0));

                rest_Name = data_arr.getJSONObject(i).getString("company_name");
                rest_Day1 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("1").getString("day");
                rest_StartTime1 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("1").getString("st");
                rest_EndTime1 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("1").getString("et");

                rest_Day2 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("2").getString("day");
                rest_StartTime2 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("2").getString("st");
                rest_EndTime2 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("2").getString("et");

                rest_Day3 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("3").getString("day");
                rest_StartTime3 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("3").getString("st");
                rest_EndTime3 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("3").getString("et");

                rest_Day4 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("4").getString("day");
                rest_StartTime4 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("4").getString("st");
                rest_EndTime4 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("4").getString("et");

                rest_Day5 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("5").getString("day");
                rest_StartTime5 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("5").getString("st");
                rest_EndTime5 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("5").getString("et");

                rest_Day6 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("6").getString("day");
                rest_StartTime6 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("6").getString("st");
                rest_EndTime6 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("6").getString("et");

                rest_Day7 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("7").getString("day");
                rest_StartTime7 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("7").getString("st");
                rest_EndTime7 = data_arr.getJSONObject(i).getJSONObject("hour").getJSONObject("7").getString("et");
                rest_Phone = data_arr.getJSONObject(i).getString("phone");
                rest_Lat = data_arr.getJSONObject(i).getDouble("latitude");
                rest_Long = data_arr.getJSONObject(i).getDouble("longitude");

                RestaurantObject obj = new RestaurantObject(rest_Name, rest_Day1, rest_StartTime1,
                        rest_EndTime1, rest_Day2, rest_StartTime2, rest_EndTime2,
                        rest_Day3, rest_StartTime3, rest_EndTime3, rest_Day4, rest_StartTime4, rest_EndTime4,
                        rest_Day5, rest_StartTime5, rest_EndTime5, rest_Day6, rest_StartTime6, rest_EndTime6,
                        rest_Day7, rest_StartTime7, rest_EndTime7,
                        rest_Phone, rest_Lat, rest_Long);
                restaurnatsArrayList.add(obj);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        context.updateProgressTo(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        context.updateUI(restaurnatsArrayList);
    }

    interface Communicator {
        public void updateProgressTo(int progress);

        public void updateUI(ArrayList<RestaurantObject> photosArrayList);
    }
}

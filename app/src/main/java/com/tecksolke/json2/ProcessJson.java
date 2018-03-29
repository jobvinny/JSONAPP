package com.tecksolke.json2;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by techguy on 2/9/18.
 */

public class ProcessJson extends AsyncTask<String, String, String> {

    //set accessing items
    String Urllink = "http://10.42.0.1:8000/api/data";
    HttpURLConnection con = null;
    InputStream stream = null;
    BufferedReader reader = null;

    //set string for data
    String data = "";
    String datapassed = "";
    String singlepassed = "";


    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(Urllink);
            con = (HttpURLConnection) url.openConnection();
            //con.setRequestMethod("GET");
            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                MainActivity.spinner.setVisibility(View.VISIBLE);
            }
            stream = con.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            String line = "";

            //loop through the array of json results
            while (line != null) {
                line = reader.readLine();
                data = data + line;
            }

            //read json objects from the array
            //JSONArray JA = new JSONArray(data);
//            JSONObject objectname = new JSONObject();
//            JSONArray JA = objectname.getJSONArray("dummy");
                JSONObject JO = new JSONObject(data);
                singlepassed = "ID:" + JO.get("id") + "\n" +
                        "Name:" + JO.get("name") + "\n" +
                        "Age:" + JO.get("age") + "\n"+
                        "Created:" + JO.get("created_at") + "\n"+
                        "Updated:" + JO.get("updated_at") + "\n";

                datapassed = datapassed + singlepassed + "\n";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                con.disconnect();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MainActivity.bjson.setVisibility(View.GONE);
        MainActivity.spinner.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //set this to hiding mode
        MainActivity.bjson.setVisibility(View.GONE);
        MainActivity.spinner.setVisibility(View.GONE);
        MainActivity.tjson.setVisibility(View.VISIBLE);

        //set content in this textview
        MainActivity.tjson.setText(this.datapassed);
    }
}

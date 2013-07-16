package luc.edu.client.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JSONParser {

    private InputStream is = null;
    private JSONObject jObj = null;
    private String json = "";
    private int status ;
    private ArrayList<String> list = new ArrayList<String>();

    // constructor
    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    public ArrayList<String> makeHttpRequest(String url, String method,
                                  String ... params) throws JSONException {

        try {
            //check for request method
            if (method == "GET") {
                DefaultHttpClient httpClient = new DefaultHttpClient();

                HttpGet request = new HttpGet(url.toString());
                request.setHeader("Accept", "application/json");

                HttpResponse httpResponse = httpClient.execute(request);
                HttpEntity httpEntity = httpResponse.getEntity();
                status = httpResponse.getStatusLine().getStatusCode();
                json = EntityUtils.toString(httpEntity);
            }

        }  catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return JSON String


        // convert to Arraylist
        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i=0;i<len;i++){
                list.add(jsonArray.get(i).toString());
            }
        }
        return list;

    }
}
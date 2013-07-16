package luc.edu.client;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONException;

import java.util.ArrayList;

import luc.edu.client.util.JSONParser;


/**
 * Created by shell on 7/14/13.
 */
public class LoadItemActivity extends AsyncTask<String,String,ArrayList<String>>{

    private Activity activity;
    private JSONParser jsonParser = new JSONParser();
    private ProgressDialog pDialog;
    public ArrayList<String> json;
    LoadItemActivity(Activity activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(this.activity);
        pDialog.setMessage("Loading items ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }


    @Override
    protected ArrayList<String> doInBackground(String... params) {
         String base_url = params[0];
        try {
            json = jsonParser.makeHttpRequest(base_url, "GET", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(ArrayList<String> json) {
        // dismiss the dialog after getting all tracks
        pDialog.dismiss();
//        AlertDialogManager alert = new AlertDialogManager();
//        alert.showAlertDialog(this.activity,"title",json.toString(),false);
//        TextView tx = (TextView)this.activity.findViewById(R.id.jsonList);
//        tx.setText(json.toString());


        // updating UI from Background Thread
    }

}

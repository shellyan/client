package luc.edu.client;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by shell on 7/25/13.
 */


public  class TabClass {



    public static class TabSubClass extends SherlockListFragment {


        private  final String base_URL = "http://pervasive-dev.cs.luc.edu:8080/sensor-proxy-restful/";
        private   String current_URL = base_URL;
        private  final String base_dir = "root";
        private  String current_dir = base_dir;
        private   ArrayList<String> array = new ArrayList<String>();


        @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadItemToAdapter(base_URL);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
//            Log.i("FragmentList", "Item clicked: " + id);
        String item = array.get(position);
        current_URL = current_URL + item + "/";
        current_dir = item;
        String tag = MainActivity.mTabHost.getCurrentTabTag();
        MainActivity.mTabsAdapter.updateTabTitle(tag,current_dir);

        if(!loadItemToAdapter(current_URL))
            //if found nothing, go back to root
            current_URL=base_URL;
    }


    public boolean loadItemToAdapter(String url){
        LoadItemActivity loadItemActivity = (LoadItemActivity) new LoadItemActivity(getActivity()).execute(current_URL);
        try {
            array = loadItemActivity.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (array.size()==0){
            array.clear();
            array.add("None");
            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, array));
            return false;
        }

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, array));
        return true;



    }




}}







package luc.edu.client;

/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;

/**
 * Demonstration of using ListFragment to show a list of items
 * from a canned array.
 */
//public class FragmentListArraySupport extends SherlockFragmentActivity{
public class FragmentListArraySupport extends SherlockFragmentActivity{

    private static TabHost mTabHost;
    private static config.TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(SampleList.THEME); //Used for theme switching in samples
        super.onCreate(savedInstanceState);
//        ViewGroup parant = (ViewGroup)findViewById(R.layout.activity_main);
//        View view = getLayoutInflater().inflate(R.layout.tab_navigation,parant);
//        setContentView(view);
//        mTabHost = ((config)this.getApplication()).getmTabHost();
//        mTabsAdapter = ((config)this.getApplication()).getmTabsAdapter();

        // Create the list fragment and add it as our sole content.
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            ArrayListFragment list = new ArrayListFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
        }

    }



    public static class ArrayListFragment extends SherlockListFragment {


        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);


            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, config.array));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);

        }
    }




}




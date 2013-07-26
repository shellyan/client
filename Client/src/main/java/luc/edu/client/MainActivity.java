package luc.edu.client;
/*
 * Copyright (C) 2011 The Android Open Source Project
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
import android.support.v4.view.ViewPager;
import android.widget.TabHost;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import luc.edu.client.util.AlertDialogManager;

/**
 * Demonstrates combining a TabHost with a ViewPager to implement a tab UI
 * that switches between tabs and also allows the user to perform horizontal
 * flicks to move between the tabs.
 */






public class MainActivity extends SherlockFragmentActivity {
    public static int THEME = R.style.Theme_Sherlock;
    public static TabHost mTabHost;
    ViewPager mViewPager;
    public static config.TabsAdapter mTabsAdapter;
    ActionMode mMode;
    AlertDialogManager alert = new AlertDialogManager();
    int tabs = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(SampleList.THEME); //Used for theme switching in samples
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mTabsAdapter = new config.TabsAdapter(this, mTabHost, mViewPager);

        mTabsAdapter.addTab(mTabHost.newTabSpec(String.valueOf(tabs++)).setIndicator("root"),
                TabClass.TabSubClass.class, null);
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tab", mTabHost.getCurrentTabTag());
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Used to put dark icons on light action bar
        boolean isLight = MainActivity.THEME == R.style.Theme_Sherlock_Light;

        menu.add("Add")
                .setIcon(isLight ? android.R.drawable.ic_menu_add : android.R.drawable.ic_menu_add)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        menu.add("Edit")
                .setIcon(isLight ? R.drawable.ic_compose_inverse : R.drawable.ic_compose)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

//        menu.add("Refresh")
//                .setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
//                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }




    private final class AnActionModeOfEpicProportions implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            //Used to put dark icons on light action bar
            boolean isLight = MainActivity.THEME == R.style.Theme_Sherlock_Light;

            menu.add("Save")
                    .setIcon(isLight ? R.drawable.ic_compose_inverse : R.drawable.ic_compose)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            menu.add("Search")
                    .setIcon(isLight ? R.drawable.ic_search_inverse : R.drawable.ic_search)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

            menu.add("Delete")
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);


            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getTitle()=="Delete"){
                if (mTabsAdapter.getCount() > 0) {
                    mTabsAdapter.removeTab(mTabHost.getCurrentTabTag());
                    Toast.makeText(MainActivity.this,"Tab:"+ mTabHost.getCurrentTabTag()+"is closed.", Toast.LENGTH_SHORT).show();
                }
            }
            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if (item.getTitle() =="Add"){
            mTabsAdapter.addTab(mTabHost.newTabSpec(String.valueOf(tabs++)).setIndicator("root"),TabClass.TabSubClass.class
                    , null);
            return true;
        }
        if (item.getTitle() == "Edit"){
            mMode = startActionMode(new AnActionModeOfEpicProportions());
            return true;
        }

        return true;

    }

}


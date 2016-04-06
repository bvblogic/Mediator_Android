package com.uprayzner.mediator.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.uprayzner.mediator.R;
import com.uprayzner.mediator.fragments.InfoFragment;
import com.uprayzner.mediator.fragments.LobbyFragment;
import com.uprayzner.mediator.fragments.PlanFragment;

/**
 * Copyright 2016 U.Prayzner
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class ContentActivity extends EventActivity {
    public static final int FRAGMENT_CONTAINER = R.id.fl_container;
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        iniUI();
        prepareBottomView();
        setListeners();
        setChildFragment(0);
    }

    private void iniUI() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setUseElevation(true);
    }

    private void prepareBottomView() {
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.bottom_navigation_bg));
        bottomNavigation.setAccentColor(getResources().getColor(R.color.cardContentTextColorWhite));
        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.addItem(new AHBottomNavigationItem(R.string.tab_title, R.drawable.ic_cloud_24dp, R.color.cardContentTextColorWhite));
        bottomNavigation.addItem(new AHBottomNavigationItem(R.string.tab_plan_title, R.drawable.ic_place_24dp, R.color.cardContentTextColorWhite));
        bottomNavigation.addItem(new AHBottomNavigationItem(R.string.tab_info_title, R.drawable.ic_error_outline_24dp, R.color.cardContentTextColorWhite));
    }

    private void setListeners() {
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                if (wasSelected) {
                    return;
                }
                setChildFragment(position);
            }
        });
    }

    private void setChildFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0: {
                fragment = new LobbyFragment();
                break;
            }
            case 1: {
                fragment = new PlanFragment();
                break;
            }
            case 2: {
                fragment = new InfoFragment();
                break;
            }
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(FRAGMENT_CONTAINER, fragment)
                .commitAllowingStateLoss();
    }
}

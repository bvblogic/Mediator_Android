package com.uprayzner.mediator.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
public abstract class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void addFragment(@IdRes int container, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(container, fragment)
                .addToBackStack(fragment.hashCode() + "")
                .commitAllowingStateLoss();
    }


    public void share(Fragment des, View sharedView, @StringRes int transitionName) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(ContentActivity.FRAGMENT_CONTAINER, des)
                .addToBackStack(des.hashCode() + "")
                .addSharedElement(sharedView, getString(transitionName))
                .commit();
    }
}

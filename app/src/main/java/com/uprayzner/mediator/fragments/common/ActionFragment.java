package com.uprayzner.mediator.fragments.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public abstract class ActionFragment extends Fragment {

    @LayoutRes
    protected abstract int getLayoutRes();

    protected View prepareUI(View root) {
        return root;
    }

    @StyleRes
    protected int getStyle() {
        return -1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater inflater;
        int style = getStyle();
        if (style != -1) {
            inflater = getCustomInflater(style);
        } else {
            inflater = i;
        }
        View root = inflater.inflate(getLayoutRes(), null, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareUI(getView());
    }

    private LayoutInflater getCustomInflater(@StyleRes int style) {
        final Context contextWrapper = new ContextThemeWrapper(getActivity(), style);
        return LayoutInflater.from(contextWrapper);

    }
}

package com.uprayzner.mediator.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.enterprayz.controller.DataController;
import com.enterprayz.controller.beans.interfaces.IPlanCard;
import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.enterprayz.controller.modules.interfaces.base.CardCallback;
import com.qozix.tileview.TileView;
import com.uprayzner.mediator.R;
import com.uprayzner.mediator.adapters.PlanStandsListAdapter;
import com.uprayzner.mediator.fragments.common.MapFragment;
import com.uprayzner.mediator.utils.HorizontalSpacesItemDecoration;
import com.uprayzner.mediator.views.CenteringRecyclerView;
import com.uprayzner.mediator.views.MapPin.MapPinView;

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
public class PlanFragment extends MapFragment implements PlanStandsListAdapter.PlanStandAdapterClickListener {
    private CenteringRecyclerView rvStandsList;
    private TileView tileView;
    private IPlanCard data;
    private PlanStandsListAdapter adapter;
    private boolean wasIni;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_plan;
    }

    @Override
    public TileView getTileView() {
        return tileView;
    }

    @Override
    protected View prepareUI(View root) {
        iniUI(root);
        return super.prepareUI(root);
    }

    @Override
    public void onDestroyView() {
        cardCardCallback.unregister();
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardCardCallback.register();
        loadData();
    }

    @Override
    public void onChoiceMapItem(MapPinView mapPinView, boolean isChecked, boolean fromTouch) {
        rvStandsList.center(mapPinView.getPosition());
    }

    @Override
    public void onListItemClick(int pos, IPlanStandCard card) {
        saveHint();
    }

    private void iniUI(View root) {
        tileView = (TileView) root.findViewById(R.id.map);
        rvStandsList = (CenteringRecyclerView) root.findViewById(R.id.rv_stands_list);
    }

    private void iniList(IPlanCard<IPlanStandCard> data) {
        if (adapter == null) {
            adapter = new PlanStandsListAdapter(data.getPlanStandCard(), this);
            rvStandsList.setAdapter(adapter);
            rvStandsList.addItemDecoration(new HorizontalSpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.stand_card_margin)));
            rvStandsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            rvStandsList.setHasFixedSize(true);
        } else {
            adapter.update(data.getPlanStandCard());
        }

    }

    private void loadData() {
        DataController.getInstance().plan().getPlanCard(true, true).withCallback(cardCardCallback);
    }

    private final SimpleTarget<Bitmap> bitmapTarget = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            if (getActivity() == null) {
                return;
            }
            setupMapOverlay(bitmap, data);
        }
    };

    private CardCallback<IPlanCard> cardCardCallback = new CardCallback<IPlanCard>() {
        @Override
        public void onSuccess(boolean preload, IPlanCard data) {
            PlanFragment.this.data = data;
            if (!wasIni) {
                Glide.with(getActivity())
                        .load(R.drawable.map)
                        .asBitmap()
                        .into(bitmapTarget);
                wasIni = true;
            }
            iniList(data);
        }

        @Override
        public void onError(Throwable error) {

        }
    };


}

package com.uprayzner.mediator.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.enterprayz.controller.beans.interfaces.IPlanStandCard;
import com.uprayzner.mediator.R;

import java.util.ArrayList;
import java.util.List;

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
public class PlanStandsListAdapter extends RecyclerView.Adapter<PlanStandsListAdapter.StandViewHolder> {
    private List<IPlanStandCard> cards;
    private final PlanStandAdapterClickListener listener;

    public PlanStandsListAdapter(List<IPlanStandCard> cards, PlanStandAdapterClickListener listener) {
        this.cards = new ArrayList<>(cards);
        this.listener = listener;
    }

    public void update(List<IPlanStandCard> cards) {
        this.cards = new ArrayList<>(cards);
        notifyDataSetChanged();
    }

    private IPlanStandCard getItem(int pos) {
        return cards.get(pos);
    }

    @SuppressLint("InflateParams")
    @Override
    public PlanStandsListAdapter.StandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StandViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_stand_item, null, false));
    }

    @Override
    public void onBindViewHolder(final StandViewHolder holder, final int position) {
        final IPlanStandCard card = getItem(position);

        GradientDrawable drawable = (GradientDrawable) holder.header.getBackground();
        drawable.setColor(card.getColor());

        holder.title.setText(card.getTitle());
        setProgressColor(card.getColor(), holder.progressBar);
        holder.logo.setBackgroundColor(
                Color.argb(
                        25,
                        Color.red(card.getColor()),
                        Color.green(card.getColor()),
                        Color.blue(card.getColor())));

        Glide.with(holder.logo.getContext())
                .load(card.getImageUrl())
                .crossFade()
                .fitCenter()
                .override(200, 300)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.progressBar.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                holder.progressBar.setVisibility(View.VISIBLE);
                            }
                        }, 50);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.progressBar.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                holder.progressBar.setVisibility(View.GONE);
                            }
                        }, 50);
                        return false;
                    }
                })
                .into(holder.logo);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListItemClick(position, card);
            }
        });
    }

    private void setProgressColor(int color, ProgressBar bar) {
        bar.getIndeterminateDrawable().setColorFilter(color,
                android.graphics.PorterDuff.Mode.MULTIPLY);

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public interface PlanStandAdapterClickListener {
        void onListItemClick(int pos, IPlanStandCard card);
    }

    class StandViewHolder extends RecyclerView.ViewHolder {
        public final ContentLoadingProgressBar progressBar;
        public final View container;
        public final ImageView header;
        public final ImageView logo;
        public final TextView title;

        public StandViewHolder(View itemView) {
            super(itemView);
            progressBar = (ContentLoadingProgressBar) itemView.findViewById(R.id.progress_bar);
            container = itemView.findViewById(R.id.ll_container);
            header = (ImageView) itemView.findViewById(R.id.iv_header);
            logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}

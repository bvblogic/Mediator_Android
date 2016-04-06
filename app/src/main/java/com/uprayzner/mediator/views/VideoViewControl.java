package com.uprayzner.mediator.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.uprayzner.mediator.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

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
public class VideoViewControl extends LinearLayout {
    public enum State {
        PLAY,
        PAUSE
    }

    private View btnPlay;
    private DiscreteSeekBar seekBar;
    private State state = State.PAUSE;

    private VideoViewControlListener listener;
    private long maxPostion;

    public VideoViewControl(Context context) {
        super(context);
        ini();
    }

    public VideoViewControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public VideoViewControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }

    public void changeState(State state, boolean notify) {
        this.state = state;
        switch (state) {
            case PLAY: {

                break;
            }
            case PAUSE: {

                break;
            }
        }
        if (notify && listener != null) {
            listener.onChangeState(state);
        }
    }

    public void setupLength(int videoLength) {
        seekBar.setMax(videoLength);
    }

    public void changePosition(int currentPos) {
        seekBar.setProgress(currentPos);
    }

    private void ini() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_video_controls, this, true);
        if (isInEditMode()) {
            return;
        }
        iniUI();
        setListeners();
    }

    private void iniUI() {
        btnPlay = findViewById(R.id.ll_play);
        seekBar = (DiscreteSeekBar) findViewById(R.id.seek_bar);
    }

    private void setListeners() {
        btnPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state.equals(State.PAUSE)) {
                    changeState(State.PLAY, true);
                } else {
                    changeState(State.PAUSE, true);
                }
            }
        });
        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                if (fromUser && listener != null) {
                    listener.onChangePosition(value);
                }
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });
    }

    public interface VideoViewControlListener {
        void onChangeState(State state);

        void onChangePosition(int currentPosition);
    }
}

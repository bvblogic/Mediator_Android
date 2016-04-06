package com.uprayzner.mediator.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.TextView;

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
public class MultilineEllipsizeTextView extends TextView {
    private String lessText;
    private boolean was = false;

    public MultilineEllipsizeTextView(Context context) {
        super(context);
    }

    public MultilineEllipsizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultilineEllipsizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setEllipsizeText(CharSequence text) {
        if (lessText == null) {
            setText(text);
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ellipsizeText();
                }
            });
        } else {
            setText(lessText);
        }
    }


    private void ellipsizeText() {
        int lineCount = getLineCount();
        int maxLines = getHeight() / getLineHeight();

        if (lineCount <= maxLines) {
            return;
        }


        setMaxLines(maxLines);
        if (maxLines == 0) {
            lessText = getText() != null ? (String) getText() : null;
            return;
        }

        int lineEndIndex = getLayout().getLineEnd(maxLines - 1);
        if (lineEndIndex < 3) {
            return;
        }
        String text = getText().subSequence(0, lineEndIndex - 3) + "...";
        lessText = text;
        setEllipsizeText(text);
    }
}

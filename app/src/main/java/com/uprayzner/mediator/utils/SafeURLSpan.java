package com.uprayzner.mediator.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;
import android.text.Html;
import android.text.Spannable;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Toast;

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
public final class SafeURLSpan extends URLSpan {
    public SafeURLSpan(String url) {
        super(url);
    }

    @Override
    public void onClick(View widget) {
        try {
            final Uri uri = Uri.parse(getURL());
            final Context context = widget.getContext();
            if (uri.toString().contains("@")) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, uri);
                emailIntent.setType("plain/text");
                context.startActivity(emailIntent);
            } else {
                final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (context != null) {
                    intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
                    context.startActivity(intent);
                }
            }
        } catch (android.content.ActivityNotFoundException ignored) {
            Toast.makeText(widget.getContext(), "No supported email client", Toast.LENGTH_LONG).show();
        } catch (Throwable ex) {

        }
    }

    public static CharSequence parseSafeHtml(CharSequence html) {
        return replaceURLSpans(Html.fromHtml(html.toString()));
    }

    public static CharSequence replaceURLSpans(CharSequence text) {
        if (text instanceof Spannable) {
            final Spannable s = (Spannable) text;
            final URLSpan[] spans = s.getSpans(0, s.length(), URLSpan.class);
            if (spans != null && spans.length > 0) {
                for (int i = spans.length - 1; i >= 0; i--) {
                    final URLSpan span = spans[i];
                    final int start = s.getSpanStart(span);
                    final int end = s.getSpanEnd(span);
                    final int flags = s.getSpanFlags(span);
                    s.removeSpan(span);
                    s.setSpan(new SafeURLSpan(span.getURL()), start, end, flags);
                }
            }
        }
        return text;
    }
}
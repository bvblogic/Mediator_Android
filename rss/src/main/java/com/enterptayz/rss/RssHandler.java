package com.enterptayz.rss;

import android.text.Html;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
 * Copyright (C) 2014 Shirwa Mohamed <shirwa99@gmail.com>
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


public class RssHandler extends DefaultHandler {

    private RssItem item;
    private boolean inItem;

    private ArrayList<RssItem> items = new ArrayList<>();

    private String value;
    private StringBuffer buffer;

    @Override
    public void startElement(
            String nameSpaceURI,
            String localName,
            String qName,
            Attributes atts
    ) {

        buffer = new StringBuffer();

        if (localName.equals("item")) {
            item = new RssItem();
            inItem = true;
        }
    }

    public void endElement(
            String uri,
            String localName,
            String qName) {


        value = buffer.toString();
        buffer.setLength(0);

        if (qName.equals("content:encoded") && inItem) {
            item.setContent(value);
        } else {
            value = Html.fromHtml(value).toString();
        }
        if (localName.equals("pubDate") && inItem) {
            try {
                item.setDate(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH).parse(value));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (localName.equals("title") && inItem) {
            item.setTitle(value);
        } else if (localName.equals("link") && inItem) {
            URL url = null;
            try {
                url = new URL(value);
            } catch (MalformedURLException e) {
                Log.e("ERR", "error while creating url from [" + value + "]");
            }
            if (url != null) {
                item.setLink(url);
            }
        } else if (localName.equals("description") && inItem) {
            item.setDescription(value);
        } else if (localName.equals("item")) {
            inItem = false;
            items.add(item);
        }
    }

    public void characters(char[] ch, int start, int end) {
        buffer.append(new String(ch, start, end));
    }

    public ArrayList<RssItem> getRss() {
        return this.items;
    }
}

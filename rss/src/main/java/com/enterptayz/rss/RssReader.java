package com.enterptayz.rss;

import android.os.AsyncTask;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.IOException;
import java.util.List;

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

public class RssReader {
    private String rssUrl;
    private Handler handler;

    public RssReader(String url, Handler handler) {
        rssUrl = url;
        this.handler = handler;
    }

    public void getItems() {
        asyncTask.execute(rssUrl);
    }

    AsyncTask<String, String, List<RssItem>> asyncTask = new AsyncTask<String, String, List<RssItem>>() {

        @Override
        protected void onPostExecute(List<RssItem> list) {
            super.onPostExecute(list);
            if (list != null) {
                handler.onGetItems(list);
            }
        }

        @Override
        protected List<RssItem> doInBackground(String... params) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser saxParser = factory.newSAXParser();
                RssHandler handler = new RssHandler();
                saxParser.parse(rssUrl, handler);
                return handler.getRss();
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    };

    public interface Handler {
        void onGetItems(List<RssItem> list);
    }
}
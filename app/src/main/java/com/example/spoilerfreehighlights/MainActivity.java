/*
 * Copyright (C) 2014 The Android Open Source Project
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
package com.example.spoilerfreehighlights;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static com.example.spoilerfreehighlights.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    public final static String VIDEO_URL = "com.example.spoilerfreehighlights.VIDEO_URL";
    public final static DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void showHighlights(View view) {
        TextView highlights = (TextView) view.findViewById(R.id.highlights);
        if (highlights.getText() != null) {

            String url = highlights.getText().toString();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    }

    @Override
    public void onCompletion(MediaPlayer v) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_listview);
        frameLayout.setVisibility(View.VISIBLE);
        finish();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        GameResultsAdapter mResultsAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public void onStart() {
            super.onStart();
            new HttpRequestTask().execute();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            GameResult[] data = {};

            ArrayList<GameResult> dayResults = new ArrayList<>(Arrays.asList(data));

            mResultsAdapter = new GameResultsAdapter(getActivity(), dayResults);

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            ListView listView = (ListView) rootView.findViewById(R.id.listview_game_results);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (view.findViewById(R.id.tvAwayScore).getVisibility() == View.VISIBLE) {
                        view.findViewById(R.id.tvAwayScore).setVisibility(View.INVISIBLE);
                        view.findViewById(R.id.tvHomeScore).setVisibility(View.INVISIBLE);
                    } else {
                        view.findViewById(R.id.tvAwayScore).setVisibility(View.VISIBLE);
                        view.findViewById(R.id.tvHomeScore).setVisibility(View.VISIBLE);
                    }
                    return true;
                }
            });
            listView.setAdapter(mResultsAdapter);

            return rootView;
        }

        public class GameResultsAdapter extends ArrayAdapter<GameResult> {
            public GameResultsAdapter(Context context, ArrayList<GameResult> gameResults) {
                super(context, 0, gameResults);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                GameResult gameResult = getItem(position);

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_result, parent, false);
                }

                TextView tvHomeTeam = (TextView) convertView.findViewById(R.id.tvHomeTeam);
                TextView tvAwayTeam = (TextView) convertView.findViewById(R.id.tvAwayTeam);
                TextView highlights = (TextView) convertView.findViewById(R.id.highlights);

                TextView tvHomeScore = (TextView) convertView.findViewById(R.id.tvHomeScore);
                TextView tvAwayScore = (TextView) convertView.findViewById(R.id.tvAwayScore);

                tvHomeTeam.setText(gameResult.getHomeTeam());
                tvAwayTeam.setText(gameResult.getAwayTeam());
                highlights.setText(gameResult.getHighlights());

                tvHomeScore.setText(gameResult.getHomePoints() != null ? gameResult.getHomePoints().toString() : "??");
                tvAwayScore.setText(gameResult.getAwayPoints() != null ? gameResult.getAwayPoints().toString() : "??");

                return convertView;
            }
        }

        private class HttpRequestTask extends AsyncTask<Void, Void, GameResult[]> {
            @Override
            protected GameResult[] doInBackground(Void... params) {
                try {
                    Date today = new Date();
                    today.setDate(today.getDate() - 1);
                    final String url = String.format("https://spoiler-free-highlights.herokuapp.com/api/nba/%s", df.format(today));
                    RestTemplate restTemplate = new RestTemplate();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
                    MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
                    jsonMessageConverter.setObjectMapper(objectMapper);
                    restTemplate.getMessageConverters().add(jsonMessageConverter);
                    GameResult[] results = restTemplate.getForObject(url, GameResult[].class);
                    return results;
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage(), e);
                }

                return null;
            }

            protected void onPostExecute(GameResult[] results) {


                if (results != null) {
                    mResultsAdapter.clear();
                    for(GameResult gameResult : results) {
                        mResultsAdapter.add(gameResult);
                    }
                }
            }

        }
    }
}
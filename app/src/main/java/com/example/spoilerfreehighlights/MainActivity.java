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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        GameResultsAdapter mResultsAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            GameResult[] data = {
                    GameResult.of("POR", 114, "OKC", 95, "0021600373",
                            "http://nba.cdn.turner.com/nba/big/video/2016/12/14/819257dd-2671-4592-80c9-e6fca2127a59.nba_1032676_1280x720_3500.mp4",
                            "2016-12-13"),
                    GameResult.of("NOP", 109, "GSW", 113, "0021600371",
                            "http://nba.cdn.turner.com/nba/big/video/2016/12/14/f86fb1fd-f673-40a9-9a59-7620e78901bb.nba_1032483_1280x720_3500.mp4",
                            "2016-12-13"),
                    GameResult.of("ATL", 120, "ORL", 131, "0021600369",
                            "http://nba.cdn.turner.com/nba/big/video/2016/12/13/b273918a-2dd0-40d3-9a04-61b9d5554aac.nba_1032279_1280x720_3500.mp4",
                            "2016-12-13"),
                    GameResult.of("CHI", 94, "MIN", 99, "0021600370",
                            "http://nba.cdn.turner.com/nba/big/video/2016/12/13/13e143eb-b4ab-4732-a9fb-2ab6a27f6da8.nba_1032265_1280x720_3500.mp4",
                            "2016-12-13"),
                    GameResult.of("PHX", 113, "NYK", 111, "0021600372",
                            "http://nba.cdn.turner.com/nba/big/video/2016/12/14/9510b6a8-f09f-4e17-8a2b-691dc115e7ab.nba_1032552_1280x720_3500.mp4",
                            "2016-12-13"),
                    GameResult.of("CLE", 103, "MEM", 86, "0021600368",
                            "http://nba.cdn.turner.com/nba/big/video/2016/12/13/ce1ded81-b11f-42ae-a9e2-259b045c6634.nba_1032192_1280x720_3500.mp4",
                            "2016-12-13")
            };
            ArrayList<GameResult> dayResults = new ArrayList<>(Arrays.asList(data));


            // Now that we have some dummy forecast data, create an ArrayAdapter.
            // The ArrayAdapter will take data from a source (like our dummy forecast) and
            // use it to populate the ListView it's attached to.
            mResultsAdapter = new GameResultsAdapter(getActivity(), dayResults);

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // Get a reference to the ListView, and attach this adapter to it.
            ListView listView = (ListView) rootView.findViewById(R.id.listview_game_results);
            listView.setAdapter(mResultsAdapter);

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//
//            // Will contain the raw JSON response as a string.
//            String resultsJsonStr = null;
//
//            try {
//                // Construct the URL for the OpenWeatherMap query
//                // Possible parameters are avaiable at OWM's forecast API page, at
//                // http://openweathermap.org/API#forecast
//                String baseUrl = "http://spoiler-free-highlights.herokuapp.com/api/nba/2016/12/13";
//                URL url = new URL(baseUrl);
//
//                // Create the request to OpenWeatherMap, and open the connection
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.connect();
//
//                // Read the input stream into a String
//                InputStream inputStream = urlConnection.getInputStream();
//                StringBuffer buffer = new StringBuffer();
//                if (inputStream == null) {
//                    // Nothing to do.
//                    return null;
//                }
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
//                    // But it does make debugging a *lot* easier if you print out the completed
//                    // buffer for debugging.
//                    buffer.append(line + "\n");
//                }
//
//                if (buffer.length() == 0) {
//                    // Stream was empty.  No point in parsing.
//                    return null;
//                }
//                resultsJsonStr = buffer.toString();
//            } catch (IOException e) {
//                Log.e("PlaceholderFragment", "Error ", e);
//                // If the code didn't successfully get the weather data, there's no point in attemping
//                // to parse it.
//                return null;
//            } finally{
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (final IOException e) {
//                        Log.e("PlaceholderFragment", "Error closing stream", e);
//                    }
//                }
//            }

            return rootView;
        }

        public class GameResultsAdapter extends ArrayAdapter<GameResult> {
            public GameResultsAdapter(Context context, ArrayList<GameResult> gameResults) {
                super(context, 0, gameResults);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Get the data item for this position
                GameResult gameResult = getItem(position);
                // Check if an existing view is being reused, otherwise inflate the view
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_result, parent, false);
                }
                // Lookup view for data population
                TextView tvHomeTeam = (TextView) convertView.findViewById(R.id.tvHomeTeam);
                TextView tvAwayTeam = (TextView) convertView.findViewById(R.id.tvAwayTeam);
                // Populate the data into the template view using the data object
                tvHomeTeam.setText(gameResult.getHomeTeam());
                tvAwayTeam.setText(gameResult.getAwayTeam());
                // Return the completed view to render on screen
                return convertView;
            }
        }
    }
}
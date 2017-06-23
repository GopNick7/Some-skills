package com.github.gopnick7.someskills.JSON;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.gopnick7.someskills.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONActivity extends Activity {

    private Button btnParseJSON;
    private TextView txtShowJSONparse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_activity);

        init();

    }

    private void init() {

        btnParseJSON = (Button) findViewById(R.id.btnParseJSON);
        btnParseJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONtask().execute("http://www.vodomer.kharkiv.ua/item.txt");
            }
        });

        txtShowJSONparse = (TextView) findViewById(R.id.txtShowJSONparse);

    }

    private class JSONtask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                String line;
                StringBuffer sb = new StringBuffer();

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            txtShowJSONparse.setText(result);
        }
    }

}

package com.example.networkdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class URLConnectActivity extends AppCompatActivity {

    @BindView(R.id.getConnect)
    TextView getConnect;
    @BindView(R.id.postConnect)
    TextView postConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlconnect);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.getConnect, R.id.postConnect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getConnect:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getRequest();
                    }
                }).start();
                break;
            case R.id.postConnect:
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       posetRequest();
                   }
               }).start();
                break;
        }
    }

    private void getRequest() {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader in = null;
        String urlString = "https://api.apiopen.top/getSongPoetry?page=1&count=20";
        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("accetp", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows N% 5.1; SV1)");
            urlConnection.connect();
            Map<String, List<String>> map = urlConnection.getHeaderFields();
            for (String key : map.keySet()) {
                Log.e("URLConnection", "key: " + key + "  value: " + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Log.e("URLConnection", stringBuffer.toString());
    }

    private void posetRequest() {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer stringBuffer = new StringBuffer();
        String urlString = "https://api.apiopen.top/getSongPoetry";
        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("accetp", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows N% 5.1; SV1)");
//            发送Post请求必须设置下面两行
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            out = new PrintWriter(urlConnection.getOutputStream());
            out.print("page=1&count=20");
            out.flush();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(URLConnectActivity.this,stringBuffer.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

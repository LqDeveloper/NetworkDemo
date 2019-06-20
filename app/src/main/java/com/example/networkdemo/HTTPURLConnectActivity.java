package com.example.networkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HTTPURLConnectActivity extends AppCompatActivity {

    @BindView(R.id.getHttpConnect)
    TextView getHttpConnect;
    @BindView(R.id.postHttpConnect)
    TextView postHttpConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnect);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.getHttpConnect, R.id.postHttpConnect})
    public void onViewClicked(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (view.getId()) {
                    case R.id.getHttpConnect:
                        getRequest("https://api.apiopen.top/getSongPoetry?page=1&count=20");
                        break;
                    case R.id.postHttpConnect:
                        posetRequest("https://api.apiopen.top/getSongPoetry","page=1&count=20");
                        break;
                }
            }
        }).start();
    }


    private void getRequest(String urlStr) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String line;
        String response;
        StringBuffer stringBuffer;
        try {
            URL url = new URL(urlStr);
            httpURLConnection = (HttpURLConnection) url.openConnection();
//            设置对象参数
//            设置请求为GET请求
            httpURLConnection.setRequestMethod("GET");
//            使用输入流
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(false);
//             设置连接超时时间
            httpURLConnection.setConnectTimeout(10000);
//              设置读超时时间
            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.connect();
//            连接后创建一个流栏
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));

            stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            response = stringBuffer.toString().trim();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(HTTPURLConnectActivity.this, response, Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

    private void posetRequest(String urlStr, String parm) {
        HttpURLConnection httpURLConnection = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String line;
        String response;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL url = new URL(urlStr);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(3000);
            printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            printWriter.print(parm);
            printWriter.flush();

            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            response = stringBuffer.toString().trim();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(HTTPURLConnectActivity.this, response, Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }

            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}

package com.example.networkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.urlConnect)
    TextView urlConnectTextView;
    @BindView(R.id.httpUrlConnect)
    TextView httpUrlConnect;
    @BindView(R.id.okhttp)
    TextView okhttp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.urlConnect, R.id.httpUrlConnect, R.id.okhttp})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.urlConnect:
                intent = new Intent(MainActivity.this, URLConnectActivity.class);
                startActivity(intent);
                break;
            case R.id.httpUrlConnect:
                intent = new Intent(MainActivity.this, HTTPURLConnectActivity.class);
                startActivity(intent);
                break;
            case R.id.okhttp:
                intent = new Intent(MainActivity.this, HTTPURLConnectActivity.class);
                startActivity(intent);
                break;
        }
    }
}

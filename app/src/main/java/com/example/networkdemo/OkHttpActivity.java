package com.example.networkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OkHttpActivity extends AppCompatActivity {

    @BindView(R.id.getConnect)
    TextView getConnect;
    @BindView(R.id.postConnect)
    TextView postConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.getConnect, R.id.postConnect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getConnect:
                break;
            case R.id.postConnect:
                break;
        }
    }

    private void getRequest(String url) {
          new Thread(new Runnable() {
              @Override
              public void run() {

              }
          });
    }
    private void getAsyncRequest(String url) {



    }


    private void posetRequest(String urlStr, String parm) {

    }
    private void posetAsyncRequest(String urlStr, String parm) {

    }
}

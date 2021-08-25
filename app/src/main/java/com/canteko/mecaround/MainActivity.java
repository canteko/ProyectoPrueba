package com.canteko.mecaround;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    ImageView logotipo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logotipo = (ImageView) findViewById(R.id.idLogo);
        RequestOptions cropOption = new RequestOptions().fitCenter();
        Glide.with(this)
                .load("https://account.splashtrack.com/Content/V2/img/user-login-bg.png")
                .apply(cropOption)
                .into(logotipo);
    }
}
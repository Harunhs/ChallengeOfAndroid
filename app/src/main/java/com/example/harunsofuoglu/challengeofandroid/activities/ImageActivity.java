package com.example.harunsofuoglu.challengeofandroid.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harunsofuoglu.challengeofandroid.R;
import com.example.harunsofuoglu.challengeofandroid.models.DetailView;
import com.example.harunsofuoglu.challengeofandroid.network.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harunsofuoglu on 5.03.2018.
 */

public class ImageActivity extends AppCompatActivity {




    @BindView(R.id.titleOfImage)
    TextView textView;
    @BindView(R.id.imageDetail)
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);


        DetailView d  = getIntent().getExtras().getParcelable("DetailViewTitle");

        Picasso.with(getBaseContext()).load(d.getUrl()).into(imageView);


        textView.setText(d.getTitle());

    }





}

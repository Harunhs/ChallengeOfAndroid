package com.example.harunsofuoglu.challengeofandroid.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.harunsofuoglu.challengeofandroid.adapters.DetailViewAdapter;
import com.example.harunsofuoglu.challengeofandroid.models.DetailView;
import com.example.harunsofuoglu.challengeofandroid.R;
import com.example.harunsofuoglu.challengeofandroid.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harunsofuoglu on 3.03.2018.
 */

public class DetailActivity extends AppCompatActivity {




    @BindView(R.id.RecyclerView)
    public RecyclerView detailRecyclerView;

    private DetailViewAdapter adapter;



    private ProgressDialog progressDialog;

    private DetailViewAdapter.OnItemClickListener mListener;

    public ArrayList<DetailView> mDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        getDetails();

    }




    public void getDetails(){
        ApiClient.createService(getBaseContext())
                .getPhotos()
                .enqueue(new Callback<List<DetailView>>() {
                    @Override
                    public void onResponse(Call<List<DetailView>> call, Response<List<DetailView>> response) {

                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }

                        final ArrayList<DetailView> mDetails = new ArrayList<>();

                        for(int i=0;i<response.body().size();i++){
                            mDetails.add(response.body().get(i));
                        }

                        int orientationNumber = getResources().getConfiguration().orientation;

                        if(orientationNumber==1) {


                            detailRecyclerView = findViewById(R.id.detailRecyclerView);
                            detailRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                        }else if(orientationNumber == 2){

                            detailRecyclerView = findViewById(R.id.detailRecyclerView);
                            detailRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

                        }
                        adapter = new DetailViewAdapter(DetailActivity.this,mDetails );



                        mListener = new DetailViewAdapter.OnItemClickListener() {
                            @Override
                            public void OnItemClick(DetailView detailView, int position) {

                                DetailView d = mDetails.get(position);

                                startActivity(d);

                            }
                        };


                        detailRecyclerView.setAdapter(adapter);
                        adapter.setListener(mListener);

                    }

                    @Override
                    public void onFailure(Call<List<DetailView>> call, Throwable t) {

                    }
                });


    }

    public void startActivity(DetailView d){
        Intent intent = new Intent(DetailActivity.this, ImageActivity.class);
        intent.putExtra("DetailViewTitle", d);
        startActivity(intent);

    }



}

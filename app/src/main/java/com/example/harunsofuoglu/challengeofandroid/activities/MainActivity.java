package com.example.harunsofuoglu.challengeofandroid.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.harunsofuoglu.challengeofandroid.adapters.RecyclerAdapter;
import com.example.harunsofuoglu.challengeofandroid.models.Person;
import com.example.harunsofuoglu.challengeofandroid.R;
import com.example.harunsofuoglu.challengeofandroid.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.RecyclerView)
    public RecyclerView recyclerView;

    private RecyclerAdapter adapter;

    private ProgressDialog progressDialog;

    private RecyclerAdapter.OnItemClickListener mListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        getUsers();


    }




    public void getUsers() {
        ApiClient.createService(getBaseContext())
                .getUsers()
                .enqueue(new retrofit2.Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {

                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }


                ArrayList<Person> mPersons =new ArrayList<>();

                for (int i = 0; i < response.body().size(); i++) {
                    mPersons.add(response.body().get(i));

                }

                recyclerView = findViewById(R.id.RecyclerView);


                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                adapter = new RecyclerAdapter(MainActivity.this,mPersons );
                mListener = new RecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(Person person, int position) {

                        startDetailActivity();
                    }
                };



                recyclerView.setAdapter(adapter);
                adapter.setListener(mListener);

            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {

                Toast.makeText(getBaseContext(), "HATA", Toast.LENGTH_LONG);
            }
        });
    }



    private void startDetailActivity() {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        startActivity(intent);

    }

}

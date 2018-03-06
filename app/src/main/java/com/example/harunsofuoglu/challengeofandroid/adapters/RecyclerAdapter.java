package com.example.harunsofuoglu.challengeofandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.harunsofuoglu.challengeofandroid.models.Person;
import com.example.harunsofuoglu.challengeofandroid.R;

import java.util.ArrayList;



/**
 * Created by harunsofuoglu on 26.02.2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void OnItemClick(Person person,int position);
    }

    private Context mContext;
    private ArrayList<Person> mPersons;

    private OnItemClickListener mListener;

    public void setListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }



    public RecyclerAdapter(Context context, ArrayList<Person> mPersons) {
        this.mContext = mContext;
        if (mPersons == null || mPersons.isEmpty()) {
            this.mPersons = new ArrayList<>();
        } else {
            this.mPersons = mPersons;
        }
    }


    private Context getContext(){
        return mContext;

    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View personView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_view,parent,false);

        return new ViewHolder(personView);



    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Person person =mPersons.get(position);



        TextView textView2 = holder.mTitleUnique;
        textView2.setText(person.getTitle());



        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnItemClick(person,position);
                }
            });
        }


    }



    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {


        TextView    mTitleUnique;


        public ViewHolder(View itemView) {

            super(itemView);


            mTitleUnique =  itemView.findViewById(R.id.title_unique);

        }


    }
}
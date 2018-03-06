package com.example.harunsofuoglu.challengeofandroid.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harunsofuoglu.challengeofandroid.models.DetailView;
import com.example.harunsofuoglu.challengeofandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by harunsofuoglu on 3.03.2018.
 */

public class DetailViewAdapter extends RecyclerView.Adapter<DetailViewAdapter.DetailViewHolder> {


    public interface OnItemClickListener{
        void OnItemClick(DetailView detailView, int position);
    }

    private Context mContext;
    private ArrayList<DetailView> mDetails;

    private DetailViewAdapter.OnItemClickListener mListener;

    public void setListener(DetailViewAdapter.OnItemClickListener mListener) {
        this.mListener = mListener;
    }



    public DetailViewAdapter(Context context, ArrayList<DetailView> mDetails) {
        this.mContext = mContext;

        if (mDetails == null || mDetails.isEmpty()) {
            this.mDetails = new ArrayList<>();
        } else {
            this.mDetails = mDetails;
        }
    }


    private Context getContext(){
        return mContext;

    }


    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View detailView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_view,parent,false);

        return new DetailViewHolder(detailView);
    }



    @Override
    public void onBindViewHolder(DetailViewHolder holder, final int position) {

        final DetailView detailView =mDetails.get(position);


        Picasso.with(holder.itemView.getContext()).load(detailView.getUrl()).into(holder.thumbnailImage);

        TextView textView = holder.titleDetail;
        textView.setText(detailView.getTitle());



        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnItemClick(detailView,position);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return mDetails.size();
    }



    /*
    düzenlenecek...
     */
    public class DetailViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView thumbnailImage;
        TextView titleDetail;


        public DetailViewHolder(View itemView) {

            super(itemView);

            thumbnailImage = itemView.findViewById(R.id.thumbnailImage);
            titleDetail = itemView.findViewById(R.id.titleOfDetail);
             cardView = itemView.findViewById(R.id.cardViewOne);
        }


    }



}

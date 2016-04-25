package com.kree.keehoo.leasegreendao2;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kree.keehoo.leasegreendao2.model.Lease;

import java.util.List;

/**
 * Created by keehoo on 20.04.2016. @ 19:42
 */


public class MainActivityListAdapter extends
                        RecyclerView.Adapter<MainActivityListAdapter.ViewHolder> {

    private List<Lease> mData;
    private MainActivity mainActivity;


    public MainActivityListAdapter(List<Lease> mData, MainActivity mainActivity) {
        this.mData = mData;
        this.mainActivity = mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item, null);

        // create ViewHolder

        ViewHolder viewHolder = new MainActivityListAdapter.ViewHolder(itemLayoutView, mainActivity);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewItem.setText(mData
                .get(position)
                .getItem());
        holder.textViewPersonName.setText(mData
                .get(position)
                .getPerson()
                .getName());
        holder.setLeaseId( mData.get(position).getId());
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        public long leaseId;
        public MainActivity mainActivity;
        public TextView textViewItem;
        public TextView textViewPersonName;
        public View viewParent;

        public ViewHolder(View itemLayoutView, MainActivity mainActivity) {
            super(itemLayoutView);
            this.mainActivity = mainActivity;
            textViewItem = (TextView) itemLayoutView.findViewById(R.id.activity_main_item_item);
            textViewPersonName = (TextView) itemLayoutView.findViewById(R.id.activity_main_item_person_name);
            viewParent = itemLayoutView.findViewById(R.id.activity_main_item_parent);
            viewParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mainActivity, LeaseActivity.class);
            intent.putExtra("lease_id", leaseId);
            mainActivity.startActivityForResult(intent, 1);


        }
        public void setLeaseId(long leaseId) {
            this.leaseId = leaseId;
        }
    }
}

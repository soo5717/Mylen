package com.example.mylen.feature.notice.main;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;

import java.util.ArrayList;

public class NoticeMainAdapter extends RecyclerView.Adapter<NoticeMainAdapter.ViewHolder>{

    private ArrayList<NoitceMainItem> mList;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView message;
        protected TextView msg_date;

        public ViewHolder(View view){
            super(view);

            this.message = view.findViewById(R.id.tv_message);
            this.msg_date = view.findViewById(R.id.tv_msg_date);
        }
    }

    public NoticeMainAdapter(ArrayList<NoitceMainItem> list){
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_activity_notice, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.message.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        holder.msg_date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        holder.message.setGravity(Gravity.CENTER);
        holder.msg_date.setGravity(Gravity.CENTER);

        holder.message.setText(mList.get(position).getMessage());
        holder.msg_date.setText(mList.get(position).getMsg_date());
    }


    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}

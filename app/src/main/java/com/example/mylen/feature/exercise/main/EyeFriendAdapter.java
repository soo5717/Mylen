package com.example.mylen.feature.exercise.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;

import java.util.ArrayList;

//각 아이템들을 담아둠
public class EyeFriendAdapter extends RecyclerView.Adapter<EyeFriendAdapter.ViewHolder> {
    Context context;

    private ArrayList<EyeMainFriendItem> items = new ArrayList<EyeMainFriendItem>();

    //생성자
   public EyeFriendAdapter(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public int getItemCount() {
        return items.size();
    }

    //viewholder 생성 시에 호출
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_activity_eyemain_friend, parent, false);
        //결정
        return  new ViewHolder(itemView);
   }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EyeMainFriendItem item = items.get(position);

        //viewholder의 view에 data 설정
        holder.setItem(item);
    }

    public void addItem(EyeMainFriendItem item){
        items.add(item);
    }

    public void addItems(ArrayList<EyeMainFriendItem> items){
       this.items = items;
    }

    public EyeMainFriendItem getItem(int position){
       return  items.get(position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_rank;
        TextView tv_user_name;
        TextView tv_user_email;
        TextView tv_point;
        //ImageView iv_picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_rank = (TextView)itemView.findViewById(R.id.tv_rank);
            tv_user_name = (TextView)itemView.findViewById(R.id.tv_user_name);
            tv_user_email = (TextView)itemView.findViewById(R.id.tv_user_email);
            tv_point = (TextView)itemView.findViewById(R.id.tv_point);
         //   iv_picture = (ImageView)itemView.findViewById(R.id.iv_picture);
        }

        public void setItem(EyeMainFriendItem item){
            tv_rank.setText(item.getRank());
            tv_user_name.setText(item.getName());
            tv_user_email.setText(item.getEmail());
            tv_point.setText(item.getPoint());
          //  iv_picture.setText(item.getPicture());

        }

    }
}

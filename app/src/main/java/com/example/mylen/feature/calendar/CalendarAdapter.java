package com.example.mylen.feature.calendar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;
import com.example.mylen.feature.eye.main.EyeFriendAdapter;
import com.example.mylen.feature.eye.main.EyeMainFriendItem;
import com.example.mylen.feature.notice.main.NoticeMain;
import com.example.mylen.feature.others.NavigationDrawer;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

//각 아이템들을 담아둠
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    Context context;

    private ArrayList<CalendarItems> items = new ArrayList<CalendarItems>();

    //생성자
    public CalendarAdapter(Context context){
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
        View itemView = inflater.inflate(R.layout.item_activity_calendar, parent, false);
        //결정
        return  new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalendarItems item = items.get(position);

        //viewholder의 view에 data 설정
        holder.setItem(item);
    }

    public void addItem(CalendarItems item){
        items.add(item);
    }

    public void deleteItem(int index) {items.remove(index);}

    public void addItems(ArrayList<CalendarItems> items){
        this.items = items;
    }

    public CalendarItems getItem(int position){
        return  items.get(position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView calendar_comment;
        TextView calendar_date;
        TextView calendar_dday;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar_comment = (TextView)itemView.findViewById(R.id.calendar_comment);
            calendar_date = (TextView)itemView.findViewById(R.id.calendar_date);
            calendar_dday = (TextView)itemView.findViewById(R.id.calendar_dday);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CalendarDate.class);
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void setItem(CalendarItems item){
            calendar_comment.setText(item.getComment());
            calendar_date.setText(item.getDate());
            calendar_dday.setText(item.getDday());
        }

    }
}

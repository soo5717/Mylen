package com.example.mylen.feature.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;
import com.example.mylen.feature.eye.main.EyeFriendAdapter;
import com.example.mylen.feature.eye.main.EyeMainFriendItem;

import java.util.ArrayList;
import java.util.Calendar;

//각 아이템들을 담아둠
public class CalendarDateAdapter extends RecyclerView.Adapter<CalendarDateAdapter.ViewHolder> {
    Context context;

    private ArrayList<CalendarDateItems> items = new ArrayList<CalendarDateItems>();

    //생성자
    public CalendarDateAdapter(Context context){
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
        View itemView = inflater.inflate(R.layout.item_activity_calendar_date, parent, false);
        //결정
        return  new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalendarDateItems item = items.get(position);

        //viewholder의 view에 data 설정
        holder.setItem(item);
    }

    public void addDateItem(CalendarDateItems item){
        items.add(item);
    }

    public void deleteDateItem(int index) {items.remove(index);}

    public void addDateItems(ArrayList<CalendarDateItems> items){
        this.items = items;
    }

    public CalendarDateItems getDateItem(int position){
        return  items.get(position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView calendar_date_date;
        TextView calendar_date_start;
        TextView calendar_date_end;
        TextView calendar_date_hours;
        TextView to;
        TextView from;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            to = (TextView) itemView.findViewById(R.id.to);
            from = (TextView) itemView.findViewById(R.id.from);
            calendar_date_date = (TextView)itemView.findViewById(R.id.calendar_date_date);
            calendar_date_start = (TextView)itemView.findViewById(R.id.calendar_date_start);
            calendar_date_end = (TextView)itemView.findViewById(R.id.calendar_date_end);
            calendar_date_hours = (TextView)itemView.findViewById(R.id.calendar_date_hours);
        }

        public void setItem(CalendarDateItems item){
            calendar_date_date.setText(item.getDateDate());
            calendar_date_start.setText(item.getStart());
            calendar_date_end.setText(item.getEnd());
            calendar_date_hours.setText(item.gethours());
            to.setText("to");
            from.setText("from");
        }

    }
}

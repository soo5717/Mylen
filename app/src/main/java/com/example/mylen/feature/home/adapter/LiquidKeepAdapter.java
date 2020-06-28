package com.example.mylen.feature.home.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;
import com.example.mylen.data.liquid.LiquidResponse;
import com.example.mylen.feature.home.add.AddLens1Activity;
import com.example.mylen.feature.home.add.AddLiquid1Activity;
import com.example.mylen.feature.home.search.SearchLensActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class LiquidKeepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private ArrayList<LiquidResponse.LiquidInfo> liquidInfos;

    //아이템 뷰 저장하는 뷰 홀더 클래스
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_brand, tv_name, tv_d_day, tv_exp_date;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            tv_brand = itemView.findViewById(R.id.tv_brand);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_d_day = itemView.findViewById(R.id.tv_d_day);
            tv_exp_date = itemView.findViewById(R.id.tv_exp_date);
        }
    }

    //생성자에서 데이터 리스트 객체를 전달 받음
    public LiquidKeepAdapter(Context mContext, ArrayList<LiquidResponse.LiquidInfo> liquidInfos) {
        this.mContext = mContext;
        this.liquidInfos = liquidInfos;
    }

    //아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_main_liquid_keep, parent, false);
        return new ViewHolder(view);
    }

    //Position 에 해당하는 데이터를 뷰홀더 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (LiquidKeepAdapter.ViewHolder) holder;
        viewHolder.tv_brand.setText(liquidInfos.get(position).getBrand());
        viewHolder.tv_name.setText(liquidInfos.get(position).getName());
        viewHolder.tv_d_day.setText(getDDay(liquidInfos.get(position).getExpDate()));
        viewHolder.tv_exp_date.setText(liquidInfos.get(position).getExpDate().substring(0, 10));

        //아이템 클릭 리스너 : 선택 다이얼로그
        viewHolder.itemView.setOnClickListener(v -> {
            //다이어로그 구현
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setItems(R.array.liquid_keep, (dialog, which) -> {
                Intent intent;
                switch (which){
                    case 0: //세척액 사용 => 수정해야 함
                        intent = new Intent(v.getContext(), AddLens1Activity.class);
                        mContext.startActivity(intent);
                        break;
                    case 1: //세척액 수정 => 수정해야 함
                        intent = new Intent(v.getContext(), AddLiquid1Activity.class);
                        mContext.startActivity(intent);
                        break;
                    case 2: //세척액 삭제 => 수정해야 함
                        break;
                }
            });
           //수정 삭제 다이얼로그
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return liquidInfos.size();
    }

    //D-Day 계산
    private String getDDay(String d_day){
        try{
            int year, month, day;
            String arr[] = d_day.split("-");

            Calendar todayCal = Calendar.getInstance(); //현재 날짜 가져오기
            Calendar ddayCal = Calendar.getInstance(); //디데이 초기화

            year = Integer.parseInt(arr[0]);
            month = Integer.parseInt(arr[1]) -1;
            day = Integer.parseInt(arr[2].substring(0,2));

            ddayCal.set(year, month, day);

            //밀리 초 -> 초로 변환
            long today = todayCal.getTimeInMillis()/(24*60*60*1000);
            long dday = ddayCal.getTimeInMillis()/(24*60*60*1000);

            long count = dday - today;

            d_day = "D-"+Long.toString(count);
            Log.d("디데이 확인", d_day);
            return d_day;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
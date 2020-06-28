package com.example.mylen.feature.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;
import com.example.mylen.data.lens.LensOpenResponse;

import java.util.ArrayList;
import java.util.Calendar;

public class LensOpenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private ArrayList<LensOpenResponse.LensInfo> lensInfos;

    // 커스텀 리스너 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }
    // 리스너 객체 참조를 저장하는 변수
    private LiquidKeepAdapter.OnItemClickListener mListener = null;
    public void setOnItemClickListener(LiquidKeepAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    //아이템 뷰 저장하는 뷰 홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_brand, tv_name, tv_d_day, tv_case_d_day;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION)
                    mListener.onItemClick(v, pos);
            });

            //뷰 객체에 대한 참조
            tv_brand = itemView.findViewById(R.id.tv_brand);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_d_day = itemView.findViewById(R.id.tv_d_day);
            tv_case_d_day = itemView.findViewById(R.id.tv_case_d_day);
        }
    }

    //생성자에서 데이터 리스트 객체를 전달 받음
    public LensOpenAdapter(Context mContext, ArrayList<LensOpenResponse.LensInfo> lensInfos) {
        this.mContext = mContext;
        this.lensInfos = lensInfos;
    }

    //아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_main_lens_open, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (LensOpenAdapter.ViewHolder) holder;
        viewHolder.tv_brand.setText(lensInfos.get(position).getBrand());
        viewHolder.tv_name.setText(lensInfos.get(position).getName());
        viewHolder.tv_d_day.setText(getDDay(lensInfos.get(position).getExpDate()));
        viewHolder.tv_case_d_day.setText(getCaseDDay(lensInfos.get(position).getCaseDate()));
    }

    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return lensInfos.size();
    }

    //렌즈 아이디 리턴
    @Override
    public long getItemId(int position) {
        return lensInfos.get(position).getId();
    }

    //D-Day 계산
    private String getDDay(String d_day){
        try{
            int year, month, day;
            String[] arr = d_day.split("-");

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

            d_day = "D-"+count;
            Log.d("디데이 확인", d_day);
            return d_day;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    //D-Day 계산
    private String getCaseDDay(String d_day){
        try{
            int year, month, day;
            String[] arr = d_day.split("-");

            Calendar todayCal = Calendar.getInstance(); //현재 날짜 가져오기
            Calendar ddayCal = Calendar.getInstance(); //디데이 초기화

            year = Integer.parseInt(arr[0]);
            month = Integer.parseInt(arr[1]) -1;
            day = Integer.parseInt(arr[2].substring(0,2));

            ddayCal.set(year, month, day);

            //밀리 초 -> 초로 변환
            long today = todayCal.getTimeInMillis()/(24*60*60*1000);
            long dday = ddayCal.getTimeInMillis()/(24*60*60*1000)+14;

            long count = dday - today;

            d_day = "케이스 교체 D-"+count;
            Log.d("디데이 확인", d_day);
            return d_day;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

}

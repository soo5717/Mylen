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
import com.example.mylen.data.lens.LensKeepResponse;

import java.util.ArrayList;
import java.util.Calendar;

public class LensKeepAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private ArrayList<LensKeepResponse.LensInfo> lensInfos;


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
        TextView tv_brand, tv_name, tv_d_day, tv_quantity, tv_wear_date, tv_exp_date;

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
            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            tv_wear_date = itemView.findViewById(R.id.tv_wear_date);
            tv_exp_date = itemView.findViewById(R.id.tv_exp_date);
        }
    }


    //생성자에서 데이터 리스트 객체를 전달 받음
    public LensKeepAdpater(Context mContext, ArrayList<LensKeepResponse.LensInfo> lensInfos) {
        this.mContext = mContext;
        this.lensInfos = lensInfos;
    }

    //아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_main_lens_keep, parent, false);
        return new ViewHolder(view);
    }

    //Position 에 해당하는 데이터를 뷰홀더 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (LensKeepAdpater.ViewHolder) holder;
        viewHolder.tv_brand.setText(lensInfos.get(position).getBrand());
        viewHolder.tv_name.setText(lensInfos.get(position).getName());
        viewHolder.tv_d_day.setText(getDDay(lensInfos.get(position).getExpDate()));
        viewHolder.tv_quantity.setText(lensInfos.get(position).getQuantity()+"ea");
        viewHolder.tv_wear_date.setText(getWearDate(lensInfos.get(position).getWearDate()));
        viewHolder.tv_exp_date.setText(lensInfos.get(position).getExpDate().substring(0, 10));

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

    //날짜 형식 변경
    private String getWearDate(String wear_date){
        switch(wear_date){
            case "1":
                wear_date = "1 day";
                break;
            case "14":
                wear_date = "2 week";
                break;
            case "30":
                wear_date = "1 month";
                break;
            case "60":
                wear_date = "2 month";
                break;
            case "90":
                wear_date = "3 month";
                break;
            case "180":
                wear_date = "6 month";
                break;
            case "365":
                wear_date = "1 year";
                break;
            default:
                wear_date = wear_date+" day";
        }
        return wear_date;
    }

}

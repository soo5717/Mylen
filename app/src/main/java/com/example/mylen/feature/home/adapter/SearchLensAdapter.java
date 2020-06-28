package com.example.mylen.feature.home.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylen.R;
import com.example.mylen.data.lens.SearchResponse;
import com.example.mylen.feature.home.add.AddLens3Activity;

import java.util.ArrayList;

public class SearchLensAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<SearchResponse.SearchInfo> searchInfos;
    private Context mContext;

    //아이템 뷰 저장하는 뷰 홀더 클래스
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_brand, tv_name, tv_type, tv_quantity, tv_wear_date;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            tv_brand = itemView.findViewById(R.id.tv_brand);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            tv_wear_date = itemView.findViewById(R.id.tv_wear_date);
        }
    }

    //생성자에서 데이터 리스트 객체를 전달 받음
    public SearchLensAdapter(Context mContext, ArrayList<SearchResponse.SearchInfo> searchInfos) {
        this.mContext = mContext;
        this.searchInfos = searchInfos;
    }

    //아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_search_lens, parent, false);
        return new ViewHolder(view);
    }

    //Position 에 해당하는 데이터를 뷰홀더 아이템뷰에 표시
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (SearchLensAdapter.ViewHolder) holder;
        viewHolder.tv_brand.setText(searchInfos.get(position).getBrand());
        viewHolder.tv_name.setText(searchInfos.get(position).getName());
        viewHolder.tv_type.setText(searchInfos.get(position).getType()+"렌즈");
        viewHolder.tv_quantity.setText(searchInfos.get(position).getQuantity()+"ea");
        viewHolder.tv_wear_date.setText(getWearDate(searchInfos.get(position).getWearDate()));

        //아이템 클릭 리스너
        viewHolder.itemView.setOnClickListener(v -> {
            //렌즈 등록3 페이지로 브랜드, 제품명, 수량, 착용 기한 전달
            Intent intent = new Intent(v.getContext(), AddLens3Activity.class);
            intent.putExtra("lensBrand", searchInfos.get(position).getBrand());
            intent.putExtra("lensName", searchInfos.get(position).getName());
            intent.putExtra("lensQuantity", searchInfos.get(position).getQuantity());
            intent.putExtra("lensWearDate", searchInfos.get(position).getWearDate());
            mContext.startActivity(intent);
        });
    }

    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return searchInfos.size();
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

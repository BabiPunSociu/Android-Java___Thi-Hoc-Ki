package com.example.hoangminhtuan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Adapter_201200391 extends BaseAdapter implements Filterable {
    private List<Taxi_hoangminhtuan> data=new ArrayList<>();
    private List<Taxi_hoangminhtuan> dataBackUp;
    // Ngữ cảnh của ứng dụng
    private Activity context;
    //Đối tượng phân tích layout
    private LayoutInflater inflater;
    public int count=0;

    public Adapter_201200391() {
    }
    public Adapter_201200391(List<Taxi_hoangminhtuan> data, Activity context){
        this.data = data;
        this.context=context;
        this.inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup parent) {
        if(v==null)
            v = inflater.inflate(R.layout.phantu,null);
        TextView soXe=v.findViewById(R.id.tvSoXe);
        soXe.setText("Số xe"+data.get(i).getSoXe());
        TextView quangDuong=v.findViewById(R.id.tvQuangDuong);
        quangDuong.setText("QUãng đường:"+data.get(i).getQuangDuong());
        TextView tong=v.findViewById(R.id.tvTong);

        double tongTien=data.get(i).getQuangDuong()*data.get(i).getDonGia()*(1-(double)data.get(i).getKhuyenMai()/100);
        tong.setText("Tổng:"+tongTien);

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.out.println(data.get(i).toString());

                for(int x=0;x<data.size();x++){
                    if(data.get(i).tong()>data.get(x).tong()){
                        count++;
                    }
                }
                Toast.makeText(v.getContext(),""+ count, Toast.LENGTH_SHORT).show();
//                Intent in =new Intent(v.getContext(),MainActivity2.class);
//                Bundle b=new Bundle();
//                b.putString("soXe",data.get(i).getSoXe());
//                b.putDouble("quangDuong",data.get(i).getQuangDuong());
//                b.putInt("donGia",data.get(i).getDonGia());
//                b.putInt("khuyenMai",data.get(i).getKhuyenMai());
//                in.putExtras(b);
//                v.getContext().startActivity(in);
               return false;
            }
        });
        //chuyển dữ liệu
//        FloatingActionButton floatingActionButton=v.findViewById(R.id.them);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in =new Intent(v.getContext(),MainActivity2.class);
//                Bundle b=new Bundle();
//                b.putString("soXe",data.get(i).getSoXe());
//                b.putDouble("quangDuong",data.get(i).getQuangDuong());
//                b.putInt("donGia",data.get(i).getDonGia());
//                b.putInt("khuyenMai",data.get(i).getKhuyenMai());
//                in.putExtras(b);
//                v.getContext().startActivity(in);
//            }
//        });
        return v;
    }
    @Override
    public Filter getFilter() {
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults fr = new FilterResults();
                if(dataBackUp==null){
                    dataBackUp=new ArrayList<>(data);
                }
                if(charSequence==null||charSequence.length()==0){
                    fr.count=dataBackUp.size();
                    fr.values=dataBackUp;
                }
                else {
                    List<Taxi_hoangminhtuan> newData=new ArrayList<>();
                    for(Taxi_hoangminhtuan c:dataBackUp){
                        if(c.tong()<Double.parseDouble(charSequence.toString())){
                            newData.add(c);
                        }
                    }
                    fr.count=newData.size();
                    fr.values=newData;
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data=new ArrayList<Taxi_hoangminhtuan>();
                List<Taxi_hoangminhtuan> tmp = (List<Taxi_hoangminhtuan>) filterResults.values;
                for(Taxi_hoangminhtuan c: tmp)
                    data.add(c);
                notifyDataSetChanged();
            }
        };
        return f;
    }
}



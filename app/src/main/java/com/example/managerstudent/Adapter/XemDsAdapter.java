package com.example.managerstudent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerstudent.Model.Lop;
import com.example.managerstudent.R;
import java.util.List;

public class XemDsAdapter extends RecyclerView.Adapter<XemDsAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<Lop> arrClass;

    public XemDsAdapter( Context context, int layout, List<Lop> arrClass) {
        this.context = context;
        this.layout = layout;
        this.arrClass = arrClass;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lop aClass = arrClass.get(position);
        holder.txtTenLop.setText(aClass.getTenLop());
        holder.txtMaLop.setText(aClass.getMaLop()+"");

    }

    @Override
    public int getItemCount() {
        return arrClass.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaLop,txtTenLop;
        ImageView imgsua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLop    = itemView.findViewById(R.id.txtmalop);
            txtTenLop   = itemView.findViewById(R.id.txttenlop);
            imgsua      = itemView.findViewById(R.id.imgSua);
        }
    }
}

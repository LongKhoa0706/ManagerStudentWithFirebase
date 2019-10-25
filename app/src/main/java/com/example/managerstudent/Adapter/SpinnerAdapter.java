package com.example.managerstudent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.managerstudent.Model.Lop;
import com.example.managerstudent.Model.Student;
import com.example.managerstudent.R;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private List<Lop> arrLop;
    private TextView textView;

    public SpinnerAdapter(int layout, Context context, List<Lop> arrLop) {
        this.layout = layout;
        this.context = context;
        this.arrLop = arrLop;
    }

    @Override
    public int getCount() {
        return arrLop.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(layout, viewGroup, false);
        textView = view.findViewById(R.id.txtchonlop);
        Lop lop = arrLop.get(i);
        textView.setText(lop.getTenLop());
        return view;
    }
}

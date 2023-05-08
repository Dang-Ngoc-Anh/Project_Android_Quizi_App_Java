package com.example.quizi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quizi.R;
import com.example.quizi.model.History;
import com.example.quizi.model.Topic;

import java.util.List;

public class AdapterHistory extends BaseAdapter {
    private Context context;
    private List<History> historyList;
    private int layout;

    public void setFilterList(List<History> filterList){
        this.historyList = filterList;
        notifyDataSetChanged();
    }

    public AdapterHistory(Context context, int layout , List<History> historyList) {
        this.context = context;
        this.historyList = historyList;
        this.layout = layout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tvHistory;
        TextView tvCountWrong;
        TextView tvCountCorrect;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.tvHistory = convertView.findViewById(R.id.tvHistory);
            viewHolder.tvCountWrong = convertView.findViewById(R.id.tvCountWrong);
            viewHolder.tvCountCorrect = convertView.findViewById(R.id.tvCountCorrect);
            convertView.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) convertView.getTag();
        History history = historyList.get(position);
        viewHolder.tvHistory.setText("Thi lần: " + history.getLan());
        viewHolder.tvCountWrong.setText("Sai: " + history.getCountWrong());
        viewHolder.tvCountCorrect.setText("Đúng: " + history.getCountCorrect());
        notifyDataSetChanged();
        return convertView;
    }
}

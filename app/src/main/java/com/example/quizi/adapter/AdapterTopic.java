package com.example.quizi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizi.R;
import com.example.quizi.model.Topic;

import java.util.List;

public class AdapterTopic extends BaseAdapter {
    private Context context;
    private List<Topic> topicList;
    private int layout;

    public void setFilterList(List<Topic> filterList){
        this.topicList = filterList;
        notifyDataSetChanged();
    }

    public AdapterTopic(Context context, int layout , List<Topic> topicList) {
        this.context = context;
        this.topicList = topicList;
        this.layout = layout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Topic> getQuestionList() {
        return topicList;
    }

    public void setQuestionList(List<Topic> questionList) {
        this.topicList = topicList;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return topicList.size();
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
        TextView tvTopics;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.tvTopics = convertView.findViewById(R.id.tvTopic);
            convertView.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) convertView.getTag();
            Topic theme = topicList.get(position);
            viewHolder.tvTopics.setText("Đề số " + theme.getTopic());
           notifyDataSetChanged();
        return convertView;
    }
}

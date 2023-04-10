package com.example.quizi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizi.R;
import com.example.quizi.model.Question;
import com.example.quizi.model.Topic;

import java.util.List;

public class AdapterQuestion extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Question> questionList;

    public void setFilterList(List<Question> filterList){
        this.questionList = filterList;
        notifyDataSetChanged();
    }
    public AdapterQuestion(Context context, int layout, List<Question> questionList) {
        this.context = context;
        this.layout = layout;
        this.questionList = questionList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<Question> getList() {
        return questionList;
    }

    public void setList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public int getCount() {
        return questionList.size();
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
        Question topic = questionList.get(position);
        viewHolder.tvTopics.setText("Câu số " + topic.getNumbers());

        notifyDataSetChanged();
        return convertView;
    }
}

package com.zksg.kudoud.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zksg.kudoud.beans.PageBean;

import java.util.ArrayList;
import java.util.List;



public class BaseAblistViewAdapter<T> extends android.widget.BaseAdapter {



    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> data = new ArrayList<T>();
    protected PageBean pageBean;
    public BaseAblistViewAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        pageBean=new PageBean();
    }


    public void setData(List<T> d){
        data = d;
    }

    public void reset(List<T> d) {
        if (d== null)return;
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();
    }

    public List<T> getData(){
        return data;
    }

    public void add(T  d) {
        if (d == null) return;
        if (data == null) data = new ArrayList<>();
        data.add(d);
        notifyDataSetChanged();
    }

    public void addAllAt(int position, List<T> d) {
        if (d == null) return;
        if (data == null) data = new ArrayList<>();
        data.addAll(position,d);
        notifyDataSetChanged();
    }
    public void addAt(int position,T d) {
        if (d == null) return;
        if (data == null) data = new ArrayList<>();
        data.add(position,d);
        notifyDataSetChanged();
    }

    public void addAll(List<T> d) {
        if (d == null) return;
        if (data == null) data = new ArrayList<>();
        data.addAll(d);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (data == null) return;
        data.remove(position);
        notifyDataSetChanged();
    }



    public void clearAll(){
        data.clear();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    public PageBean getPageBean() {
        return pageBean;
    }
}

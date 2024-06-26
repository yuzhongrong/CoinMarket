package com.kunminx.architecture.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataBindingAdapter<M, B extends ViewDataBinding> extends ListAdapter<M, RecyclerView.ViewHolder> {

    protected Context mContext;

    private OnItemClickListener<M> mOnItemClickListener;

    private OnItemClickViewListener<M> mOnItemClickViewListener;
    private OnItemLongClickListener<M> mOnItemLongClickListener;

    private int clickViewId;

    public void setOnItemClickListener(OnItemClickListener<M> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemClickViewListener(OnItemClickViewListener<M> onItemClickViewListener,int viewId) {
        this.mOnItemClickViewListener = onItemClickViewListener;
        this.clickViewId=viewId;

    }

    public void setOnItemLongClickListener(OnItemLongClickListener<M> onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public BaseDataBindingAdapter(Context context, @NonNull DiffUtil.ItemCallback<M> diffCallback) {
        super(diffCallback);
        this.mContext = context;
    }

    @Override
    public void submitList(@Nullable List<M> list) {
        super.submitList(list, () -> {
            super.submitList(list == null ? new ArrayList<>() : new ArrayList<>(list));
        });
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(this.mContext), this.getLayoutResId(viewType), parent, false);
        BaseBindingViewHolder holder = new BaseBindingViewHolder(binding.getRoot());
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                int position = holder.getAdapterPosition();
                mOnItemClickListener.onItemClick(getItem(position), position);
            }

        });
        if(mOnItemClickViewListener!=null){
            holder.itemView.findViewById(clickViewId).setOnClickListener(v->{
                int position = holder.getAdapterPosition();
                mOnItemClickViewListener.onItemClick(getItem(position), position,v);

            });

        }


        holder.itemView.setOnLongClickListener(v -> {
            if (mOnItemLongClickListener != null) {
                int position = holder.getAdapterPosition();
                mOnItemLongClickListener.onItemLongClick(getItem(position), position);
                return true;
            }
            return false;
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        B binding = DataBindingUtil.getBinding(holder.itemView);
        this.onBindItem(binding, getItem(position), holder);
        if (binding != null) {
            binding.executePendingBindings();
        }
    }

    protected abstract @LayoutRes
    int getLayoutResId(int viewType);

    /**
     * 注意：
     * RecyclerView 中的数据有位置改变（比如删除）时一般不会重新调用 onBindViewHolder() 方法，除非这个元素不可用。
     * 为了实时获取元素的位置，我们通过 ViewHolder.getBindingAdapterPosition() 方法。
     *
     * @param binding .
     * @param item    .
     * @param holder  .
     */
    protected abstract void onBindItem(B binding, M item, RecyclerView.ViewHolder holder);

    public static class BaseBindingViewHolder extends RecyclerView.ViewHolder {
        BaseBindingViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener<M> {
        void onItemClick(M item, int position);

    }

    public interface OnItemClickViewListener<M> {
        public int resId=0;
        void onItemClick(M item, int position,View viewId);


    }



    public interface OnItemLongClickListener<M> {
        void onItemLongClick(M item, int position);
    }
}
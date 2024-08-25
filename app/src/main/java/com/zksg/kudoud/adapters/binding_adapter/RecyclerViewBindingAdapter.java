package com.zksg.kudoud.adapters.binding_adapter;

import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.decorations.SpacesItemDecoration;
import com.zksg.kudoud.anims.FadeInItemAnimator;

import java.util.List;

public class RecyclerViewBindingAdapter {


    @BindingAdapter(value = {"default_linearAdapter", "cannotScrollVertically","itemspace"}, requireAll = false)
    public static void default_initRecyclerViewWithLinearLayoutManager(RecyclerView recyclerView, RecyclerView.Adapter adapter, boolean can,int itemspace) {

        Log.d("---->default_linearAdapter", "default_initRecyclerViewWithLinearLayoutManager: 3");
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        }
        if(itemspace>0){ //TODO tip:默认设置垂直方向间距，其实这里可以通过布局来解决 默认值是0
            recyclerView.addItemDecoration(new SpacesItemDecoration(itemspace));
        }
        LinearLayoutManager manager= new LinearLayoutManager(recyclerView.getRootView().getContext()) {
            @Override
            public boolean canScrollVertically() {
                return !can;
            }
        };


        recyclerView.setLayoutManager(manager);
    }



    @BindingAdapter(value = {"linearAdapter", "cannotScrollVertically","itemspace","orientation"}, requireAll = false)
    public static void initRecyclerViewWithLinearLayoutManager(RecyclerView recyclerView, RecyclerView.Adapter adapter, boolean can,int itemspace,int orientation) {

        Log.d("---->linearAdapter", "initRecyclerViewWithLinearLayoutManager: 4");
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        }
        if(itemspace>0){
            recyclerView.addItemDecoration(new SpacesItemDecoration(itemspace));
        }




        LinearLayoutManager manager= new LinearLayoutManager(recyclerView.getRootView().getContext()) {
            @Override
            public boolean canScrollVertically() {
                return !can;
            }
        };

        if(orientation==LinearLayoutManager.VERTICAL){
            manager.setOrientation(LinearLayoutManager.VERTICAL);
        }else{
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        }


        recyclerView.setLayoutManager(manager);
    }



    @BindingAdapter(value = {"linearAdapter", "cannotScrollVertically"}, requireAll = false)
    public static void initRecyclerViewWithLinearLayoutManager(RecyclerView recyclerView, RecyclerView.Adapter adapter, boolean can) {

        Log.d("---->linearAdapter", "initRecyclerViewWithLinearLayoutManager: 2");
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getRootView().getContext()) {
            @Override
            public boolean canScrollVertically() {
                return !can;
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
    }

    @BindingAdapter(value = {"gridAdapter", "spanCount","columnspace"}, requireAll = false)
    public static void initRecyclerViewWithGridLayoutManager(RecyclerView recyclerView, RecyclerView.Adapter adapter, int count,int columnspace) {
        if (count == 0) count = 3;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), count){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        if(columnspace!=0){
            recyclerView.addItemDecoration(new GridSpaceItemDecoration(count,30,columnspace));
        }else{
            recyclerView.addItemDecoration(new GridSpaceItemDecoration(count,30,35));
        }
    }

    @BindingAdapter(value = {"submitList"}, requireAll = false)
    public static void initRecyclerViewData(RecyclerView recyclerView, List data) {
        Log.d("---->submitList", "initRecyclerViewData: ");
        if (data != null) {
            if (recyclerView.getAdapter() instanceof ListAdapter) {
                ((ListAdapter) recyclerView.getAdapter()).submitList(data);
            } else if (recyclerView.getAdapter() instanceof BaseQuickAdapter) {
                ((BaseQuickAdapter) recyclerView.getAdapter()).replaceData(data);
            }else if(recyclerView.getAdapter() instanceof SimpleDataBindingAdapter){
                ((SimpleDataBindingAdapter) recyclerView.getAdapter()).submitList(data);
            }
        }
    }




    @BindingAdapter(value = {"addList"}, requireAll = false)
    public static void initRecyclerAddData(RecyclerView recyclerView, List data) {
        if (data != null) {
            if (recyclerView.getAdapter() instanceof ListAdapter) {
                ((ListAdapter) recyclerView.getAdapter()).submitList(data);
            } else if (recyclerView.getAdapter() instanceof BaseQuickAdapter) {
                ((BaseQuickAdapter) recyclerView.getAdapter()).addData(data);
            }
        }
    }

    @BindingAdapter(value = {"onChildAttachStateChangeListener"}, requireAll = false)
    public static void addOnChildAttachStateChangeListener(RecyclerView recyclerView, RecyclerView.OnChildAttachStateChangeListener listener) {
        recyclerView.addOnChildAttachStateChangeListener(listener);
    }

//    @BindingAdapter(value = {"adapter", "submitList", "autoScrollToTopWhenInsert", "autoScrollToBottomWhenInsert"}, requireAll = false)
//    public static void bindList(RecyclerView recyclerView, SimpleDataBindingAdapter adapter, List list,
//                                boolean autoScrollToTopWhenInsert, boolean autoScrollToBottomWhenInsert) {
//
//        if (recyclerView != null && list != null) {
//
//            if (recyclerView.getAdapter() == null) {
//
//                recyclerView.setLayoutManager(new LinearLayoutManager(Utils.getApp()));
//
//                recyclerView.setAdapter(adapter);
//
//                adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//                    @Override
//                    public void onItemRangeInserted(int positionStart, int itemCount) {
//                        if (autoScrollToTopWhenInsert) {
//                            recyclerView.scrollToPosition(0);
//                        } else if (autoScrollToBottomWhenInsert) {
//                            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount());
//                        }
//                    }
//                });
//            }
//
//            adapter.submitList(list);
//        }
//    }


    @BindingAdapter(value = {"recycleview_anims_fade"}, requireAll = false)
    public static void recycleview_anims_fade(RecyclerView rv, boolean value) {
        if(rv==null)return;
        if(value){
            rv.setItemAnimator(new FadeInItemAnimator());
        }

    }

}

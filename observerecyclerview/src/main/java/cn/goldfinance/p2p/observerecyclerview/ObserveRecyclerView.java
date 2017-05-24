package cn.goldfinance.p2p.observerecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ray_L_Pain on 2017/5/24.
 */

public class ObserveRecyclerView extends RecyclerView {

    private View emptyView;

    private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    private void checkIfEmpty(){
        if (emptyView != null && getAdapter() != null){
            boolean isEmpty = getAdapter().getItemCount() == 0;
            emptyView.setVisibility(isEmpty ? VISIBLE : GONE);
            setVisibility(isEmpty ? GONE : VISIBLE);
        }
    }

    public ObserveRecyclerView(Context context) {
        super(context);
    }

    public ObserveRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ObserveRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        Adapter oldAdapter = getAdapter();
        if (oldAdapter != null){
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null){
            adapter.registerAdapterDataObserver(observer);
        }
        checkIfEmpty();
    }

    public void setEmptyView(View emptyView){
        this.emptyView = emptyView;
        ((ViewGroup)this.getRootView()).addView(emptyView);
    }
}
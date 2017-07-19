package hu.ait.android.todo.swipeanddrag;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Éva on 6/21/2016.
 */
public interface TodoTouchHelperAdapter {
    void onItemDismiss(int position, RecyclerView recyclerView);

    void onItemMove(int fromPosition, int toPosition);
}

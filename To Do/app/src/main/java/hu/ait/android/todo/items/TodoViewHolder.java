package hu.ait.android.todo.items;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import hu.ait.android.todo.R;

/**
 * Created by Éva on 6/21/2016.
 */
public class TodoViewHolder extends RecyclerView.ViewHolder {

    public final TextView todoTitleTextView;
    public final CheckBox isDoneCheckbox;

    public TodoViewHolder(View itemView) {
        super(itemView);
        todoTitleTextView = (TextView) itemView.findViewById(R.id.todoTitle);
        isDoneCheckbox = (CheckBox) itemView.findViewById(R.id.isDone);
    }
}

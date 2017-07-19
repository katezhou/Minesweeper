package hu.ait.android.todo.items;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.ait.android.todo.R;
import hu.ait.android.todo.model.Todo;
import hu.ait.android.todo.swipeanddrag.TodoTouchHelperAdapter;

/**
 * Created by Éva on 6/21/2016.
 */
public class TodosAdapter extends RecyclerView.Adapter<TodoViewHolder> implements TodoTouchHelperAdapter {

    List<Todo> todosList = new ArrayList<>();

    public TodosAdapter() {
        for (int i = 0; i < 20; i++) {
            todosList.add(new Todo("todo nr. " + i, false));
        }
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_row, parent, false);
        return new TodoViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo actualTodo = todosList.get(position);
        holder.todoTitleTextView.setText(actualTodo.getTitle());
        holder.isDoneCheckbox.setChecked(actualTodo.isDone());
        holder.itemView.setTag(actualTodo);
    }

    @Override
    public int getItemCount() {
        return todosList.size();
    }

    @Override
    public void onItemDismiss(final int position, RecyclerView recyclerView) {
        final Todo todoToBeRemoved = todosList.get(position);
        todosList.remove(position);
        notifyItemRemoved(position);

        showUndoSnackbar(position, recyclerView, todoToBeRemoved);
    }

    private void showUndoSnackbar(final int position, RecyclerView recyclerView, final Todo todoToBeRemoved) {
        Snackbar snackbar = Snackbar
                .make(recyclerView, R.string.item_deleted, Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        todosList.add(position, todoToBeRemoved);
                        notifyItemInserted(position);
                    }
                });

        snackbar.show();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(todosList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(todosList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }
}

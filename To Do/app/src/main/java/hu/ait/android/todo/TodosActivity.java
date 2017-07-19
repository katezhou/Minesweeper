package hu.ait.android.todo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import hu.ait.android.todo.items.TodosAdapter;
import hu.ait.android.todo.swipeanddrag.TodoItemTouchHelperCallback;

public class TodosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        initTodosList();

        startItemAdderActivityWhenAddButtonClicked();
    }

    private void initTodosList() {
        RecyclerView todosRecyclerView = (RecyclerView) findViewById(R.id.todos);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        todosRecyclerView.setLayoutManager(layoutManager);

        TodosAdapter todosAdapter = new TodosAdapter();
        todosRecyclerView.setAdapter(todosAdapter);

        ItemTouchHelper.Callback callback =
                new TodoItemTouchHelperCallback(todosAdapter, todosRecyclerView);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(todosRecyclerView);
    }

    private void startItemAdderActivityWhenAddButtonClicked() {
        FloatingActionButton(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity( new Intent(TodosActivity.this, TodoadderActivity));
            }
        }
    }

    @Override
    protected void onActivityResult(int )

}

package hu.ait.android.todo.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Éva on 6/21/2016.
 */
public class Todo extends SugarRecord implements Serializable{

    private String title;
    private boolean isDone;

    public Todo(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}

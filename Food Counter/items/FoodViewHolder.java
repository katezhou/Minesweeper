package hu.ait.android.finalproject.items;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hu.ait.android.finalproject.R;

/**
 * Created by zhou_xiaoquan on 7/5/16.
 */
public class FoodViewHolder extends RecyclerView.ViewHolder {

    public final TextView foodName;
    public Button details;
    public Button delete;
    private final Context context;
    public View itemView;

    public FoodViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        foodName = (TextView) itemView.findViewById(R.id.FoodName);
        details = (Button) itemView.findViewById(R.id.Details);
        delete = (Button) itemView.findViewById(R.id.btnRemove);
        this.itemView = itemView;
    }

    public Button getDelete() {
        return delete;
    }
}

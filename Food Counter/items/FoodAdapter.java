package hu.ait.android.finalproject.items;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.finalproject.CalendarActivity;
import hu.ait.android.finalproject.DetailsActivity;
import hu.ait.android.finalproject.MainActivity;
import hu.ait.android.finalproject.R;

/**
 * Created by zhou_xiaoquan on 6/28/16.
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    List<foodInfo> foodList = new ArrayList<>();
    public static final String FINFO_KEY = "foodInfo";

    public FoodAdapter() {
        List<foodInfo> initFoodList = foodInfo.listAll(foodInfo.class);
        for (int i = 0; i < initFoodList.size(); i++) {
            if (initFoodList.get(i).getDateID() != MainActivity.object.getDateID()) {
                Log.d("testing food list ", "" + initFoodList.get(i).getDateID());
                Log.d("testing main object ", "" + MainActivity.object.getDateID());
            } else {
                foodList.add(initFoodList.get(i));
            }
        }
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_row, parent, false);
        return new FoodViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final FoodViewHolder holder, final int position) {
        final foodInfo foodInfo = foodList.get(holder.getAdapterPosition());
        holder.foodName.setText(foodInfo.getFoodName());
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent();
                myIntent.putExtra(FINFO_KEY, foodList.get(holder.getAdapterPosition()));
//                myIntent.putExtra("Name", foodInfo.getFoodName());
                myIntent.setClass(holder.itemView.getContext(), DetailsActivity.class);
                holder.itemView.getContext().startActivity(myIntent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final foodInfo foodInfodelete = foodList.get(holder.getAdapterPosition());
                foodInfodelete.delete();
                foodList.remove(foodInfo);
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void addItem(foodInfo foodInfo) {
        foodList.add(foodInfo);
        foodInfo.save();
        notifyDataSetChanged();
    }
}

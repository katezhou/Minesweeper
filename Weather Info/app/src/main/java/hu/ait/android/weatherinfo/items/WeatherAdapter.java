package hu.ait.android.weatherinfo.items;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.ait.android.weatherinfo.R;
import hu.ait.android.weatherinfo.model.weatherInfo;
import hu.ait.android.weatherinfo.swipeanddrag.DetailsActivity;
import hu.ait.android.weatherinfo.swipeanddrag.MainActivity;

/**
 * Created by zhou_xiaoquan on 6/28/16.
 */
public class WeatherAdapter  extends RecyclerView.Adapter<WeatherViewHolder>  {

    List<weatherInfo> weatherList = new ArrayList<>();
    public static final String WINFO_KEY = "weatherInfo";

    public WeatherAdapter() {
        weatherList = weatherInfo.listAll(weatherInfo.class);
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_row, parent, false);
        return new WeatherViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final WeatherViewHolder holder, final int position) {
        final weatherInfo weatherInfo = weatherList.get(holder.getAdapterPosition());
        holder.cityName.setText(weatherInfo.getCityName());
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent();
                myIntent.putExtra(WINFO_KEY, weatherList.get(holder.getAdapterPosition()));
                myIntent.setClass(holder.itemView.getContext(), DetailsActivity.class);
                holder.itemView.getContext().startActivity(myIntent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final weatherInfo weatherInfodelete = weatherList.get(holder.getAdapterPosition());
                weatherInfodelete.delete();
                weatherList.remove(weatherInfo);
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void addItem(weatherInfo weatherInfo){
        weatherInfo.save();
        weatherList.add(weatherInfo);
        notifyDataSetChanged();
    }
}

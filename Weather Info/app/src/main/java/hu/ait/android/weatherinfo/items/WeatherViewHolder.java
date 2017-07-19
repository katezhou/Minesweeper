package hu.ait.android.weatherinfo.items;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hu.ait.android.weatherinfo.R;
import hu.ait.android.weatherinfo.swipeanddrag.DetailsActivity;

/**
 * Created by zhou_xiaoquan on 6/28/16.
 */
public class WeatherViewHolder extends RecyclerView.ViewHolder {

    public final TextView cityName;
    public Button details;
    public Button delete;
    private final Context context;
    public View itemView;

    public WeatherViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        cityName = (TextView) itemView.findViewById(R.id.CityName);
        details = (Button) itemView.findViewById(R.id.Details);
        delete = (Button) itemView.findViewById(R.id.btnRemove);
        this.itemView = itemView;
    }

    public Button getDelete() {
        return delete;
    }
}

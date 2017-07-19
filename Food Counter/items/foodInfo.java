package hu.ait.android.finalproject.items;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by zhou_xiaoquan on 7/5/16.
 */
public class foodInfo extends SugarRecord implements Serializable {

    public String foodName;
    private int dateID;

    public foodInfo () {

    }

    public foodInfo(String foodName, calendarItem calendar) {
        this.foodName = foodName;
        this.dateID = calendar.getDateID();
    }

    public String getFoodName() {
        return foodName;
    }

    public int getDateID() {
        return dateID;
    }

    public void setDateID(int dateID) {

        this.dateID = dateID;
    }
}

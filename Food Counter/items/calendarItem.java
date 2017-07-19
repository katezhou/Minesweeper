package hu.ait.android.finalproject.items;

import java.io.Serializable;

/**
 * Created by zhou_xiaoquan on 7/5/16.
 */
public class calendarItem implements Serializable {

    int date;
    int month;
    int year;
    int dateID;

    public int getDateID() {
        return dateID;
    }

    @Override
    public String toString() {
        return "calendarItem{" +
                "date=" + date +
                ", month=" + month +
                ", year=" + year +
                ", dateID=" + dateID +
                '}';
    }

    public calendarItem(int date, int month, int year) {
        this.month = month;
        this.date = date;
        this.year = year;
        this.dateID = year * 10000 + month * 100 + date;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        int m = month;
        String monthStr = "";
        if(m == 1){
            monthStr = "January";
        }else if(m == 2){
            monthStr  = "February";
        }else if(m == 3){
            monthStr  = "March";
        }else if(m == 4){
            monthStr  = "April";
        }else if(m == 5){
            monthStr  = "May";
        }else if(m == 6){
            monthStr  = "June";
        }else if(m == 7){
            monthStr  = "July";
        }else if(m == 8){
            monthStr  = "August";
        }else if(m == 9){
            monthStr  = "September";
        }else if(m == 10){
            monthStr  = "October";
        }else if(m == 11){
            monthStr  = "November";
        }else if(m == 12){
            monthStr  = "December";
        }
        return monthStr;
    }

    public int getDate() {
        return date;
    }

    public int getYear() {
        return year;
    }
}

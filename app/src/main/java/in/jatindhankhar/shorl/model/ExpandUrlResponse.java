package in.jatindhankhar.shorl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jatin on 12/29/16.
 */

public class ExpandUrlResponse  extends  HistoryItem{



    @SerializedName("analytics")
    private Analytics analytics;


    /*public HistoryItem getHistoryItem() {
        //return super
        return super.getClass().cast(HistoryItem);
    }*/

/*    public void setHistoryItem(HistoryItem historyItem) {
        this.historyItem = historyItem;
    } */

    public Analytics getAnalytics() {
        return analytics;
    }

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }
}
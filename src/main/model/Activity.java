package main.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity {
    private long activityId;
    private long clientId;
    private double amount;
    private Date activityDate;
    private double commission;

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getActivityDate() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-mm-dd");
        ft.format(activityDate);

        return ft.format(activityDate);

    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public Activity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;

        Activity activity = (Activity) o;

        if (activityId != activity.activityId) return false;
        if (Double.compare(activity.amount, amount) != 0) return false;
        if (clientId != activity.clientId) return false;
        if (Double.compare(activity.commission, commission) != 0) return false;
        if (activityDate != null ? !activityDate.equals(activity.activityDate) : activity.activityDate != null)
            return false;
        if (description != null ? !description.equals(activity.description) : activity.description != null)
            return false;

        return true;
    }

    public Activity(long activityId, long clientId, double amount, Date activityDate, double commission, String description) {
        this.activityId = activityId;
        this.clientId = clientId;
        this.amount = amount;
        this.activityDate = activityDate;
        this.commission = commission;
        this.description = description;
    }
}

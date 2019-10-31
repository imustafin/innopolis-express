package mfq.com.refooddelivery2.models;

public class FoodleDrive {

    String mFees;
    String mTime;
    boolean isSelected;

    public FoodleDrive(String fees, String time) {
        mFees = fees;
        mTime = time;
    }

    public String getFees() {
        return mFees;
    }

    public void setFees(String fees) {
        mFees = fees;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

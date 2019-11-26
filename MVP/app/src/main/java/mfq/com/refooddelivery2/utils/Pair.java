package mfq.com.refooddelivery2.utils;

public class Pair<L,R> {

    private L leftValue;
    private R rightValue;

    public Pair(L leftValue, R rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public Pair() {
    }

    public L getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(L leftValue) {
        this.leftValue = leftValue;
    }

    public R getRightValue() {
        return rightValue;
    }

    public void setRightValue(R rightValue) {
        this.rightValue = rightValue;
    }
}

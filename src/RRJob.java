public class RRJob {

    private final String pId;
    private final int arrivalT;
    private final int burstT;
    private int remainingBT;

    public RRJob(String pId,  int arrivalT, int burstT) {
        this.pId = pId;
        this.arrivalT = arrivalT;
        this.burstT = burstT;
        remainingBT = burstT;
    }

    public String getPId() {
        return pId;
    }

    public int getArrivalT() {
        return arrivalT;
    }

    public int getBurstT() {
        return burstT;
    }

    public int getRemainingBT() {
        return remainingBT;
    }

    public void setRemainingBT(int remainingBT) {
        this.remainingBT = remainingBT;
    }

    @Override
    public String toString() {
        return "RRJob{" +
                "pId='" + pId + '\'' +
                ", arrivalT=" + arrivalT +
                ", burstT=" + burstT +
                ", remainingBT=" + remainingBT +
                '}';
    }
}

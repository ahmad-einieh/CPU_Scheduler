public class RRProc {

    private final String pId;
    private final int arrivalT;
    private final int burstT;
    private int remainingT;

    public RRProc(String pId, int arrivalT, int burstT) {
        this.pId = pId;
        this.arrivalT = arrivalT;
        this.burstT = burstT;
        remainingT = burstT;
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

    public int getRemainingT() {
        return remainingT;
    }

    public void setRemainingT(int remainingT) {
        this.remainingT = remainingT;
    }

    @Override
    public String toString() {
        return "RR Process{" +
                "pId='" + pId + '\'' +
                ", arrivalT=" + arrivalT +
                ", burstT=" + burstT +
                ", remainingT=" + remainingT +
                '}';
    }
}

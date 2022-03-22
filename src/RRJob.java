public class RRJob {

    private final String pId;
    private int burstT;
    private final int arrivalT;

    public RRJob(String pId, int burstT, int arrivalT) {
        this.pId = pId;
        this.burstT = burstT;
        this.arrivalT = arrivalT;
    }

    public String getPId() {
        return pId;
    }

    public void setBurstT(int burstT) {
        this.burstT = burstT;
    }

    public int getBurstT() {
        return burstT;
    }

    public int getArrivalT() {
        return arrivalT;
    }

    @Override
    public String toString() {
        return "RRJob{" +
                "pId='" + pId + '\'' +
                ", burstT=" + burstT +
                ", arrivalT=" + arrivalT +
                '}';
    }
}

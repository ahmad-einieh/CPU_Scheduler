public class PriorityJob {

    private final String pId;
    private final int arrivalT;
    private final int priority;
    private final int burstT;

    public PriorityJob(String pId, int arrivalT, int priority, int burstT) {
        this.pId = pId;
        this.arrivalT = arrivalT;
        this.priority = priority;
        this.burstT = burstT;
    }

    public String getPId() {
        return pId;
    }

    public int getArrivalT() {
        return arrivalT;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurstT() {
        return burstT;
    }

    @Override
    public String toString() {
        return "PriorityJob{" +
                "pId='" + pId + '\'' +
                ", arrivalT=" + arrivalT +
                ", priority=" + priority +
                ", burstT=" + burstT +
                '}';
    }
}

public class PriorityJob {

    private final String pId;
    private final int priority;
    private final int burstT;
    private final int arrivalT;

    public PriorityJob(String pId, int priority, int burstT, int arrivalT) {
        this.pId = pId;
        this.priority = priority;
        this.burstT = burstT;
        this.arrivalT = arrivalT;
    }

    public String getPId() {
        return pId;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurstT() {
        return burstT;
    }

    public int getArrivalT() {
        return arrivalT;
    }

    @Override
    public String toString() {
        return "PriorityJob{" +
                "pId='" + pId + '\'' +
                ", priority=" + priority +
                ", burstT=" + burstT +
                ", arrivalT=" + arrivalT +
                '}';
    }
}

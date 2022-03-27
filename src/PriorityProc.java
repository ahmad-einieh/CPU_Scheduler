public class PriorityProc {

    private final String pId;
    private final int arrivalT;
    private final int priority;
    private final int burstT;
    private boolean done;

    public PriorityProc(String pId, int arrivalT, int priority, int burstT) {
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Priority Process{" +
                "pId='" + pId + '\'' +
                ", arrivalT=" + arrivalT +
                ", priority=" + priority +
                ", burstT=" + burstT +
                '}';
    }
}

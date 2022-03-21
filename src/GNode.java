public class GNode {
    String PID;
    int burstTime;
    int remainTime;
    int arriveTime;

    public GNode(String PID, int burstTime,int arriveTime) {
        this.PID = PID;
        this.remainTime = this.burstTime = burstTime;
        this.arriveTime = arriveTime;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    @Override
    public String toString() {
        return "GNode{" +
                "PID='" + PID + '\'' +
                ", burstTime=" + burstTime +
                ", remainTime=" + remainTime +
                ", arriveTime=" + arriveTime +
                '}';
    }
}

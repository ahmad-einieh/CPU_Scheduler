public class GNode {
    String PID;
    int burstTime;
    int remainTime;

    public GNode(String PID, int burstTime) {
        this.PID = PID;
        this.remainTime = this.burstTime = burstTime;
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
}

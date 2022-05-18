import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class SRTF {

    public static void main(String[] args) {
        new SRTF();
    }

    public SRTF() {
        LinkedList<PCB> processes = getProcesses();
        LinkedList<Job> timeline = schedule(processes);
        Main.output(processes, timeline, "SRTF");
    }

    private LinkedList<PCB> getProcesses() {
        LinkedList<PCB> processes = new LinkedList<>();

        File file = new File("testdata1 for SRTF.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));

            while (true) {
                String line = br.readLine();
                if (line.equals("//PID,AT,BT\t(AT: Arrival Time, BT: Burst Time)"))
                    break;

                String[] info = line.split(",");
                processes.add(new PCB(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]), 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processes;
    }

    private LinkedList<Job> schedule(LinkedList<PCB> processes) {
        LinkedList<Job> timeline = new LinkedList<>();

        int clock = 0;
        while (!checkCompleted(processes)) {
            PCB proc = getMin(processes, clock);
            while (proc == null)
                proc = getMin(processes, ++clock);

            timeline.add(new Job(proc.getPId(), clock, clock + 1));
            proc.setRemainingT(proc.getRemainingT() - 1);

            clock++;

            if (proc.getRemainingT() == 0) {
                proc.setWaitingT(clock - proc.getArrivalT() - proc.getBurstT());
                proc.setTurnaroundT(clock);
            }
        }

        return timeline;
    }

    private PCB getMin(List<PCB> processes, int clock) {
        PCB minBurstProc = null;
        int minBurst = Integer.MAX_VALUE;
        for (PCB p : processes) {
            if (!p.isCompleted() && p.getArrivalT() <= clock && p.getRemainingT() < minBurst) {
                minBurst = p.getRemainingT();
                minBurstProc = p;
            }
        }
        return minBurstProc;
    }

    private boolean checkCompleted(LinkedList<PCB> parr) {
        for (PCB p : parr) {
            if (!p.isCompleted())
                return false;
        }
        return true;
    }

}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RR {

    public static void main(String[] args) {
        new RR(1);
    }

    public RR(int quantum) {
        List<PCB> processes = getProcesses();
        List<Job> timeline = schedule(processes, quantum);
        Main.output(processes, timeline, "RR");
    }

    private List<PCB> getProcesses() {
        List<PCB> processes = new LinkedList<>();

        File file = new File("testdata21 for RR.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            int arrivalT = 0;
            while (true) {
                String pId = br.readLine();

                if (pId == null)
                    break;

                int burstTime = Integer.parseInt(br.readLine());

                processes.add(new PCB(pId, arrivalT++, burstTime, 0));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processes;
    }

    private List<Job> schedule(List<PCB> processes, int quantum) {
        List<Job> timeline = new LinkedList<>();
        Queue<PCB> queue = new LinkedList<>(processes);
        int time = 0;

        while (!queue.isEmpty()) {
            PCB proc = queue.poll();
            int finishT;

            if (proc.getRemainingT() > quantum) {
                finishT = time + quantum;
                proc.setRemainingT(proc.getRemainingT() - quantum);
                queue.add(proc);
            }
            else if (proc.getRemainingT() == quantum) {
                finishT = time + quantum;
                proc.setWaitingT(finishT - proc.getBurstT());
                proc.setTurnaroundT(finishT);
            }
            else {
                finishT = time + proc.getRemainingT();
                proc.setWaitingT(finishT - proc.getBurstT());
                proc.setTurnaroundT(finishT);
            }

            timeline.add(new Job(proc.getPId(), time, finishT));
            time = finishT;
        }

        return timeline;
    }

}

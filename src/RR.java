import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

public class RR {

    public RR(int quantum) {
        LinkedList<PCB> processes = getProcesses();
        LinkedList<Job> timeline = schedule(processes, quantum);
        Main.output(processes, timeline, "RR");
    }

    private LinkedList<PCB> getProcesses() {
        LinkedList<PCB> processes = new LinkedList<>();

        File file = new File("src/testdata21 for RR.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));

            int arrivalT = 0;
            while (true) {
                String pId = br.readLine();

                if (pId == null)
                    break;

                int burstTime = Integer.parseInt(br.readLine());

                processes.add(new PCB(pId, arrivalT++, burstTime, 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processes;
    }

    private LinkedList<Job> schedule(LinkedList<PCB> processes, int quantum) {
        LinkedList<Job> timeline = new LinkedList<>();
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
                proc.setCompletionT(finishT);
            }
            else {
                finishT = time + proc.getRemainingT();
                proc.setWaitingT(finishT - proc.getBurstT());
                proc.setCompletionT(finishT);
            }

            timeline.add(new Job(proc.getPId(), time, finishT));
            time = finishT;
        }

        return timeline;
    }

}

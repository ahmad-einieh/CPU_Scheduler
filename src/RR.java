import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

public class RR {

    private final int QUANTUM;
    private final LinkedList<PCB> processes = new LinkedList<>();
    private final LinkedList<Job> timeline = new LinkedList<>();

    public RR(int quantum) {
        QUANTUM = quantum;
        getProcesses();
        schedule();
        Main.output(processes, timeline, "RR");
    }

    private void getProcesses() {
        File file = new File("src/job1.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine();  // the start line which we don't want
            int arrivalT = 0;
            while (true) {
                String pId = br.readLine();
                if (pId.equals("[End of job.txt]"))
                    break;
                int burstTime = Integer.parseInt(br.readLine());

                processes.add(new PCB(pId, burstTime, arrivalT++, 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void schedule() {
        Queue<PCB> queue = new LinkedList<>(processes);
        int time = 0;

        while (!queue.isEmpty()) {
            PCB proc = queue.poll();
            int finishT;

            if (proc.getRemainingT() > QUANTUM) {
                finishT = time + QUANTUM;
                proc.setRemainingT(proc.getRemainingT() - QUANTUM);
                queue.add(proc);
            }
            else if (proc.getRemainingT() == QUANTUM) {
                finishT = time + QUANTUM;
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
    }

}

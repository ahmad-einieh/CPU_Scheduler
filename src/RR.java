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

    private void output() {
        int fullL = 1;
        LinkedList<Integer> ls = new LinkedList<>();
        for (Job job : timeline) {
            int l = job.getEndT() - job.getStartT();
            ls.add(l);
            fullL += 2 * l + job.getPId().length() + 1;
        }

        System.out.println("\n\n/////////////////// RR OUTPUT ////////////////////\n");
        for (int i = 0; i < fullL; i++)
            System.out.print("-");
        System.out.print("\n|");

        for (int i = 0; i < timeline.size(); i++) {
            for (int j = 0; j < ls.get(i); j++)
                System.out.print(" ");

            System.out.print(timeline.get(i).getPId());

            for (int j = 0; j < ls.get(i); j++)
                System.out.print(" ");

            System.out.print("|");
        }

        System.out.println();
        for (int i = 0; i < fullL; i++)
            System.out.print("-");

        int margin = 0;
        System.out.print("\n0");
        for (int i = 0; i < timeline.size(); i++) {
            int times = ls.get(i) * 2 + timeline.get(i).getPId().length() - margin;
            for (int j = 0; j < times; j++)
                System.out.print(" ");

            int endT = timeline.get(i).getEndT();
            System.out.print(endT);

            if (endT > 9)
                margin = String.valueOf(endT).length()-1;
        }

        System.out.println("\n");
        System.out.println("PID\t\t\tWaiting Time\t\tCompletion Time");
        for (PCB proc : processes)
            System.out.println(proc.getPId() + "\t\t\t" + proc.getWaitingT() + "\t\t\t\t\t" + proc.getCompletionT());
        System.out.println();

        int sumWT = 0, sumCT = 0;
        for (PCB process : processes) {
            sumWT += process.getWaitingT();
            sumCT += process.getCompletionT();
        }

        System.out.printf("Average Waiting Time: %.2f%n", 1.0 * sumWT / processes.size());
        System.out.printf("Average Completion Time: %.2f%n", 1.0 * sumCT / processes.size());
    }

    public static void main(String[] args) {
        new RR(5);
    }
}

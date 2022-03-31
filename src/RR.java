import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RR {

    //private final int QUANTUM;
    private final LinkedList<RRProc> processes = new LinkedList<>();
    private final LinkedList<Job> timeline = new LinkedList<>();

    public RR(int quantum) {
        //QUANTUM = quantum;
        getProcesses();
        schedule(quantum);
        //output();
    }

    private static boolean checkCompleted(LinkedList<RRProc> parr) {
        for (RRProc p : parr) {
            if (!p.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    private void getProcesses() {
        int arrivalTime = 0;

        File file = new File("src/job1.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine();  // the start line which we don't want
            while (true) {
                String pId = br.readLine();
                if (pId.equals("[End of job.txt]"))
                    break;
                int burstTime = Integer.parseInt(br.readLine());

                processes.add(new RRProc(pId, arrivalTime++, burstTime));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void schedule(int quantum) {

        LinkedList<RRProc> queue = new LinkedList<>();
        LinkedList<RRProc> ppp = new LinkedList<>();
        int clock = 0;

        while (!checkCompleted(processes)) {
            RRProc fp = null;
            System.out.println(queue);
            if (queue.size() > 0) {
                fp = queue.poll();
                fp.setFinishTime(clock);
                ppp.add(fp);
                fp.reduceRemainingTime(quantum);

                if (fp.getRemainingT() == 0) {
                    fp.setCompleteTime(clock);
                }
            }

            for (RRProc p : processes) {
                if (!p.isDispatched() && p.getArrivalT() <= clock) {
                    p.setDispatched();
                    queue.add(p);
                }
            }

            if (fp != null && !fp.isCompleted()) {
                queue.add(fp);
            }

            clock++;

        }

        for (int i = 0; i < ppp.size() * 2; i++)
            System.out.print("----");
        System.out.print("\n| ");

        for (int i = 0; i < ppp.size(); i++) {
            System.out.print(ppp.get(i).getPId());
            System.out.print(" | ");
        }

        System.out.println();
        for (int i = 0; i < ppp.size() * 2; i++)
            System.out.print("----");
        System.out.println();




        System.out.println("PID\tCompletion_Time");
        for (RRProc p : processes) {
            System.out.print(p.getPId());
            System.out.print("\t");
            System.out.print(p.getCompleteTime());
            System.out.print("   " + (p.getCompleteTime() - p.getArrivalT()));
            System.out.print("   " + ((p.getCompleteTime() - p.getArrivalT()) - p.getBurstT()));
            System.out.println();
        }
    }

    private void display() {
        System.out.println("RR Processes are:");
        for (RRProc process : processes)
            System.out.println(process);
    }

    /*private void output() {
        System.out.println("/////////////////// RR OUTPUT ////////////////////");

    }*/

    public static void main(String[] args) {
        RR r = new RR(1);
    }
}

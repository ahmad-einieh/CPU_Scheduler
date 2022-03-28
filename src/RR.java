import java.io.*;
import java.util.LinkedList;

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

        File file = new File("src/test3.txt");
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
            //processes.get(processes.size() - 1).setArrivalT(processes.get(processes.size() - 1).getArrivalT() + 1);
            //display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void schedule(int q) {
        //processes.get(processes.size()-1).inAT();
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        PrintWriter out = new PrintWriter(osw);
        LinkedList<RRProc> queue = new LinkedList<>();
        int clock = 0;
        //int quantum = q;

        while (!checkCompleted(processes)) {
            // Pop first process in the queue
            RRProc fp = null;
            if (queue.size() > 0) {
                fp = queue.poll();
                fp.reduceRemainingTime(q);

                if (fp.getRemainingT() == 0) {
                    fp.setCompleteTime(clock);
                }
            }

            // Check for new arrivals
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

        out.println("PID\tCompletion Time");

        for (RRProc p : processes) {
            out.print(p.getPId());
            out.print("\t");
            out.print(p.getCompleteTime());
            out.print("   " + (p.getCompleteTime() - p.getArrivalT()));
            out.print("   " + ((p.getCompleteTime() - p.getArrivalT()) - p.getBurstT()));
            out.println();
            //out.println(" units");
        }

        out.close();
    }

    private void display() {
        System.out.println("RR Processes are:");
        for (RRProc process : processes)
            System.out.println(process);
    }

    /*private void output() {
        System.out.println("/////////////////// RR OUTPUT ////////////////////");

        //TODO
    }*/

    public static void main(String[] args) {
        RR r = new RR(1);
    }
}

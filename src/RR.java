import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class RR {

    private final int QUANTUM;
    private final LinkedList<RRProc> processes = new LinkedList<>();
    private final LinkedList<Job> timeline = new LinkedList<>();

    public RR(int quantum) {
        QUANTUM = quantum;
        getProcesses();
        schedule(quantum);
        //output();
    }

    private static boolean checkCompleted(LinkedList<RRProc> parr) {
        for (RRProc p : parr) {
            if (!p.isCompleted())
                return false;
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

    private void schedule2() {

    }

    private void schedule(int quantum) {
        LinkedList<RRProc> queue = new LinkedList<>();
        ArrayList<RRProc> ppp = new ArrayList<>();

        int clock = 0;

        while (!checkCompleted(processes)) {
            RRProc fp = null;
            if (queue.size() > 0) {
                fp = queue.poll();
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
        for (int i = 0; i <= ppp.size() * quantum; i+=quantum) {
            if (i < ppp.size() && ppp.get(i).getPId().length() > 4) {
                if (i >= 10 && i < 100) {
                    System.out.print(i + "      ");
                } else if (i >= 100 && i < 1000) {
                    System.out.print(i + "     ");
                } else if (i >= 1000) {
                    System.out.print(i + "    ");
                } else {
                    System.out.print(i + "       ");
                }
            } else {
                if (i >= 10 && i < 100) {
                    System.out.print(i + "     ");
                } else if (i >= 100 && i < 1000) {
                    System.out.print(i + "    ");
                } else if (i >= 1000) {
                    System.out.print(i + "   ");
                } else {
                    System.out.print(i + "      ");
                }
            }

        }


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

    private void output() {
        int fullL = 0;
        LinkedList<Integer> ls = new LinkedList<>();
        for (Job job : timeline) {
            int l = job.getEndT() - job.getStartT();
            ls.add(l);
            fullL += l;
        }

        System.out.println("\n/////////////////// RR OUTPUT ////////////////////\n");
        for (int i = 0; i < fullL; i++)
            System.out.print("---");
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
            System.out.print("---");

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
        for (RRProc proc : processes)
            System.out.println(proc.getPId() + "\t\t\t" + proc.getWaitingT() + "\t\t\t\t\t" + proc.getCompletionT());
        System.out.println();

        int sumWT = 0, sumCT = 0;
        for (RRProc process : processes) {
            sumWT += process.getWaitingT();
            sumCT += process.getCompletionT();
        }

        System.out.printf("Average Waiting Time: %.2f%n", 1.0 * sumWT / processes.size());
        System.out.printf("Average Completion Time: %.2f%n", 1.0 * sumCT / processes.size());
    }

    public static void main(String[] args) {
        RR r = new RR(1);
    }
}

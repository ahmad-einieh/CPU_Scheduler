import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class SRTF {

    private final LinkedList<SRTFProc> processes = new LinkedList<>();
    //private final LinkedList<Job> timeline = new LinkedList<>();

    public SRTF() {
        getProcesses();
        schedule();
        //output();
    }

    private static boolean checkCompleted(LinkedList<SRTFProc> parr) {
        for (SRTFProc p : parr) {
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

                processes.add(new SRTFProc(pId, arrivalTime++, burstTime));
            }
            //display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void schedule() {
        ArrayList<SRTFProc> ppp = new ArrayList<>();

        int clock = 0;

        while (!checkCompleted(processes)) {

            SRTFProc minBurstProc = null;
            int minBurst = Integer.MAX_VALUE;

            for (SRTFProc p : processes) {
                if (!p.isCompleted() && p.getArrivalT() <= clock && p.getRemainingT() < minBurst) {
                    minBurst = p.getRemainingT();
                    minBurstProc = p;
                }
            }

            clock++;

            if (minBurstProc != null) {
                ppp.add(minBurstProc);
                minBurstProc.reduceRemainingT(1);

                if (minBurstProc.getRemainingT() == 0) {
                    minBurstProc.setCompleteTime(clock);
                }
            }
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
        for (int i = 0; i <= ppp.size(); i++) {
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
        int averageCompleteTime = 0;
        int averageWaitingTime = 0;
        //out.println("/////////////////// SRTF OUTPUT ////////////////////");
        System.out.println("PID\t   Completion_Time\t   Waiting_Time");
        for (SRTFProc p : processes) {
            averageCompleteTime += p.getCompleteTime();
            System.out.print(p.getPId());
            System.out.print("\t\t");
            System.out.print(p.getCompleteTime());
            System.out.print("\t\t\t\t");
            //out.print(p.getArrivalT()+"\t");
            var x = (p.getCompleteTime() - p.getArrivalT()) - p.getBurstT();
            averageWaitingTime += x;
            System.out.println(x);
        }

        System.out.printf("\nAverage Waiting Time: %.2f%n", 1.0 * averageWaitingTime / processes.size());
        System.out.printf("Average Completion Time: %.2f%n", 1.0 * averageCompleteTime / processes.size());
    }

    public static void main(String[] args) {
        new SRTF();
    }
}

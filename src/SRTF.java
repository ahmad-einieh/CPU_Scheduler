import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class SRTF {

    private final LinkedList<SRTFProc> processes = new LinkedList<>();
    //private final LinkedList<Job> timeline = new LinkedList<>();

    public SRTF() {
        getProcesses();
        //display();
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
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        PrintWriter out = new PrintWriter(osw);
        ArrayList<SRTFProc> ppp = new ArrayList<>();

        int clock = 0;
        //int quantum = 1;

        while (!checkCompleted(processes)) {
            // Detect shortest burst process which
            // has not completed
            SRTFProc minBurstProc = null;
            int minBurst = Integer.MAX_VALUE;
            //System.out.println(minBurst);

            for (SRTFProc p : processes) {
                if (!p.isCompleted() && p.getArrivalT() <= clock && p.getRemainingT() < minBurst) {
                    minBurst = p.getRemainingT();
                    minBurstProc = p;
                }
            }

            clock = clock + 1;

            if (minBurstProc != null) {
                ppp.add(minBurstProc);
                //System.out.println(minBurstProc.getPId());
                /*for (int i = 0; i < ppp.size(); i++) {
                    System.out.print(" |"+i+"| ");
                    System.out.print(ppp.get(i).getPId());
                    *//*if (i == ppp.size() - 1) {
                        System.out.print(" |"+i+"| ");
                        System.out.print(ppp.get(i).getPId());
                    } else {
                        System.out.print(" |"+i+"| ");
                        System.out.print(ppp.get(i).getPId() *//**//*+ " -> "*//**//*);
                    }*//*
                }
                System.out.print(" |"+ppp.size()+"| ");
                System.out.println();*/
                minBurstProc.reduceRemainingT(1);

                if (minBurstProc.getRemainingT() == 0) {
                    minBurstProc.setCompleteTime(clock);
                }
            }
        }

        /*for (int i = 0; i < ppp.size(); i++) {
            System.out.print(" |"+i+"| ");
            System.out.print(ppp.get(i).getPId());
        }
        System.out.print(" |"+ppp.size()+"| ");*/
        for (int i = 0; i <= ppp.size(); i++)
            System.out.print("-----");
        System.out.print("\n| ");

        for (int i = 0; i < ppp.size(); i++) {
            System.out.print(ppp.get(i).getPId());
            System.out.print(" | ");
        }

        System.out.println();
        for (int i = 0; i <= ppp.size(); i++)
            System.out.print("-----");
        System.out.println();
        for (int i = 0; i <= ppp.size(); i++) {
            if (i >= 10) {
                System.out.print(i + "     ");
            } else if (i >= 100) {
                System.out.print(i + "    ");
            } else if (i >= 1000) {
                System.out.print(i + "   ");
            } else {
                System.out.print(i + "      ");
            }
            /*for (int j =0 ;j<7;j++)
                System.out.print(" ");*/
        }

        System.out.println();
        int averageCompleteTime = 0;
        int averageWaitingTime = 0;
        //out.println("/////////////////// SRTF OUTPUT ////////////////////");
        out.println("PID\t   Completion_Time\t   Waiting_Time");

        for (SRTFProc p : processes) {
            averageCompleteTime += p.getCompleteTime();
            out.print(p.getPId());
            out.print("\t\t");
            out.print(p.getCompleteTime());
            out.print("\t\t\t\t");
            //out.print(p.getArrivalT()+"\t");
            var x = (p.getCompleteTime() - p.getArrivalT()) - p.getBurstT();
            averageWaitingTime += x;
            out.println(x);
            //out.println(" units");
        }
        out.println("average complete time = " + (double) averageCompleteTime / processes.size());
        out.println("average waiting time = " + (double) averageWaitingTime / processes.size());

        out.close();
    }


    private void display() {
        System.out.println("SRTF Processes are:");
        for (SRTFProc process : processes)
            System.out.println(process);
    }

    public static void main(String[] args) {
        SRTF srt = new SRTF();
    }
}

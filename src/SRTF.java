import javax.swing.*;
import java.io.*;
import java.util.*;

public class SRTF {

    private final LinkedList<PCB2> processes = new LinkedList<>();
    //private final LinkedList<Job> timeline = new LinkedList<>();

    public SRTF() {
        getProcesses();
        schedule();
        //Main.output(processes, timeline, "SRTF");
    }

    private static boolean checkCompleted(LinkedList<PCB2> parr) {
        for (PCB2 p : parr) {
            if (!p.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    private void getProcesses() {

        File file = new File("src/testdata1 for SRTF.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            while (true) {
                String line = br.readLine();
                if (line.equals("//PID,AT,BT\t(AT: Arrival Time, BT: Burst Time)"))
                    break;
                String[] info = line.split(",");
                processes.add(new PCB2(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getProcesses2() {
        int arrivalTime = 0;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(new JFrame());
        File inputFile = fileChooser.getSelectedFile();
        System.out.println("Selected file : " + inputFile.getAbsolutePath());

        Scanner sc = new Scanner(System.in);
        System.out.print("enter type of text file: 1 to new type : ");
        var x = sc.nextInt();
        if (x == 1) {
            File file = new File(String.valueOf(inputFile));
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file));
                while (true) {
                    String line = br.readLine();
                    if (line.equals("//PID,AT,BT\t(AT: Arrival Time, BT: Burst Time)"))
                        break;
                    String[] info = line.split(",");
                    processes.add(new PCB2(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2])));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
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

                    processes.add(new PCB2(pId, arrivalTime++, burstTime));
                }
                //display();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private void schedule() {
        ArrayList<PCB2> ppp = new ArrayList<>();

        int clock = 0;

        while (!checkCompleted(processes)) {

            PCB2 minBurstProc = null;
            int minBurst = Integer.MAX_VALUE;

            for (PCB2 p : processes) {
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

        System.out.println("\n/////////////////// SRTF OUTPUT ////////////////////\n");

        for (int i = 0; i <= ppp.size() ; i++)
            System.out.print("----");
        System.out.print("\n| ");

        for (int i = 0; i < ppp.size(); i++) {
            System.out.print(ppp.get(i).getPId());
            System.out.print(" | ");
        }

        System.out.println();
        for (int i = 0; i <= ppp.size(); i++)
            System.out.print("----");
        System.out.println();
        for (int i = 0; i <= ppp.size(); i++) {
            if (i < ppp.size() && ppp.get(i).getPId().length() > 4) {
                if (i >= 10 && i < 100) {
                    System.out.print(i + "   ");
                } else if (i >= 100 && i < 1000) {
                    System.out.print(i + "  ");
                } else if (i >= 1000) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(i + "    ");
                }
            } else {
                if (i >= 10 && i < 100) {
                    System.out.print(i + "  ");
                } else if (i >= 100 && i < 1000) {
                    System.out.print(i + " ");
                } else if (i >= 1000) {
                    System.out.print(i + "");
                } else {
                    System.out.print(i + "   ");
                }
            }

        }

        System.out.println("\n");
        int averageCompleteTime = 0;
        int averageWaitingTime = 0;
        System.out.println("PID\t   Completion_Time\t   Waiting_Time");
        for (PCB2 p : processes) {
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

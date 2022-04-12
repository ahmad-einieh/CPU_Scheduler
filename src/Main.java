import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter Quantum Size: ");
        Scanner sc = new Scanner(System.in);
        int quantum = sc.nextInt();

        System.out.print("\n\n/////////////////// SRTF OUTPUT ////////////////////\n\n");
        new SRTF();
        new Priority();
        new RR(quantum);
    }

    public static void output(LinkedList<PCB> processes, LinkedList<Job> timeline, String algorithm) {
        int fullL = 1;
        LinkedList<Integer> ls = new LinkedList<>();
        for (Job job : timeline) {
            int l = job.getEndT() - job.getStartT();
            ls.add(l);
            fullL += 2 * l + job.getPId().length() + 1;
        }

        System.out.printf("\n\n/////////////////// %s OUTPUT ////////////////////\n\n", algorithm);
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
}

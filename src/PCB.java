import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PCB {
    Queue queue;

    public PCB() {
        this.queue = new LinkedList();
    }

    public void getData() {

        int count = 0;
        //Queue<GNode> queue1 = new LinkedList<>();


        File file = new File(
                "D:\\projects\\CPU_Scheduler\\src\\job1.txt");

        BufferedReader br
                = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            br.skip(20);
            while ((st = br.readLine()) != null) {
                if (st.equals("[End of job.txt]")) {
                } else {
                    //System.out.println(st);
                    queue.add(new GNode(st, Integer.parseInt(br.readLine()), count));
                    count++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(queue1.size());


    }

    public void SRTF() {
       //System.out.println(queue.peek());
        for (var i : queue){
            //if ()
        }
    }

    public static void main(String[] args) {
        PCB g = new PCB();
        g.getData();
        System.out.println(g.queue.size());
        g.SRTF();
    }
}

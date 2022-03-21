import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Scanner;

public class PCB<T> {
    Queue<T> queue;

    public void getData(){
        File file = new File("C:\\Users\\pankaj\\Desktop\\test.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
    }


}

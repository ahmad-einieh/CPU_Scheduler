import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter Quantum Size: ");
        Scanner sc = new Scanner(System.in);
        int quantum = sc.nextInt();

        new SRTF();
        new Priority();
        new RR(quantum);
    }
}

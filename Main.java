import java.util.Scanner;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int r = 0;
        int c = 0;
        int genTime = 0;
        System.out.println("Welcome to Conway's Game of Life.");
        while(r==0) {
            try {
                System.out.print("How many rows: ");
                r = Integer.parseInt(cin.nextLine());
            } catch(NumberFormatException e) { System.out.println("Try Again"); }
        }
        while(c==0) {
            try {
                System.out.print("How many columns: ");
                c = Integer.parseInt(cin.nextLine());
            } catch(NumberFormatException e) { System.out.println("Try Again"); }
        }
        while(genTime==0) {
            try {
                System.out.print("Update time in milliseconds: ");
                genTime = Integer.parseInt(cin.nextLine());
            } catch(NumberFormatException e) { System.out.println("Try Again"); }
        }
        cin.close();
        JFrame frame = new JFrame("Conway's Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SimPanel simPanel = new SimPanel(r, c, genTime);
        frame.add(simPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
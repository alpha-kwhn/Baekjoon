import java.util.*;
import java.io.*;

public class Baek_1673 {
    static int answer;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextInt()) {
            int coupon = sc.nextInt();
            int goal = sc.nextInt();
            answer = coupon;

            int stamp = coupon % goal;
            int chance = coupon / goal;

            while(chance > 0) {
                answer += chance;
                stamp += chance;
                chance = stamp / goal;
                stamp = stamp % goal;
            }

            System.out.println(answer);
            answer = 0;
        }
    }
}

import java.io.*;
import java.util.*;
public class Baek_1834 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long answer = 0;
        long number = 1;

        while(number < N) {
            answer += ((N * number) + number);
            number++;
        }

        System.out.println(answer);
    }
}

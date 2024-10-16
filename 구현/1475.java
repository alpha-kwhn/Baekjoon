import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();

        // 9+9, 6+6 -> 1세트로 가능함
        char[] c = number.toCharArray();
        int[] used = new int[10];

        for(char ch : c) {
            if(ch == '6' || ch == '9') {
                if(used[6] < used[9])
                    used[6]++;
                else
                    used[9]++;
            } else
                used[ch-'0']++;
        }

        System.out.println(Arrays.stream(used).max().getAsInt());


    }
}

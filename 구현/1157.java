import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp = br.readLine();

        tmp = tmp.toUpperCase();
        int answer = 0;
        String str = "";

        int[] alpha = new int[26];

        char[] arr = tmp.toCharArray();
        for(char c : arr) {
            alpha[c - 65]++;
        }

        for(int i=0; i<26; i++) {
            if(answer < alpha[i]) {
                str = String.valueOf((char)(i+65)).toUpperCase();
                answer = alpha[i];
            } else if(answer == alpha[i]) {
                str = "?";
            }
        }

        System.out.println(str);

    }
}

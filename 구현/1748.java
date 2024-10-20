import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_1748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = String.valueOf(N);

        if(str.length() == 1)
            System.out.println(N);
        else {
            int answer = 9;
            for (int i = 2; i < str.length(); i++)
                answer += (int) (9 * (Math.pow(10, i-1))) * i;
            answer += (int)((N - Math.pow(10, str.length()-1) + 1) * str.length());
            System.out.println(answer);
        }
    }
}

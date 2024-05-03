import java.util.*;
import java.io.*;
public class Baek_5361 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            Double result = (A * 350.34) + (B * 230.90) + (C * 190.55) + (D * 125.30) + (E *180.90);
            String answer = String.format("%.2f", result);
            System.out.println("$"+answer);
        }
    }
}

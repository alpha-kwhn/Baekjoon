import java.io.*;
import java.util.*;
public class Baek_1010 {
    static long [][] factorial;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        factorial = new long[31][31];
        factorial[0][0] = 1;

        for(int i=1; i<31; i++) {
            for(int j=0; j<=i; j++) {
                if(j == 0 || j == i)
                    factorial[i][j] = 1;
                else
                    factorial[i][j] = factorial[i-1][j-1] + factorial[i-1][j];
            }
        }

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int west = Integer.parseInt(st.nextToken());
            int east = Integer.parseInt(st.nextToken());

            System.out.println(factorial[east][west]);

        }
    }
}

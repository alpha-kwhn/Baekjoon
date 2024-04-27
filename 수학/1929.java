import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean []isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for(int i=2; i<=Math.sqrt(N); i++)
            for (int j = i * i; j <= N; j += i)
                isPrime[j] = false;

        for(int i=M; i<=N; i++)
            if(isPrime[i])
                System.out.println(i);
    }
}

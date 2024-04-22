import java.io.*;
import java.util.*;
public class Baek_2012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        long answer = 0;

        for(int i=0; i<N; i++) {
            int rank = Integer.parseInt(br.readLine());
            arr[i] = rank;
        }

        Arrays.sort(arr);
        boolean[] used = new boolean[500_001];
        used[0] = true;

        int ranked = 1;
        for(int i=0; i<arr.length; i++) {
            for(int j=ranked; j<=500_000; j++) {
                if(!used[j]) {
                    used[j] = true;
                    answer += Math.abs(j - arr[i]);
                    ranked = j+1;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}

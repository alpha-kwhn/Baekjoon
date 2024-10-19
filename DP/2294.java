import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
public class Baek_2294 {
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int[] lis = new int[k+1];
        Arrays.fill(lis, 10000001);
        lis[0] = 0;

        for(int i=0; i<n; i++) {
            for(int j=arr[i]; j<=k; j++) {
                lis[j] = Math.min(lis[j], lis[j-arr[i]]+1);
            }
        }

        System.out.println(lis[k] = lis[k] == 10000001 ? -1 : lis[k]);
    }
}

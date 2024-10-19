import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
public class Baek_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int[][] answer = new int[n][k+1];
        for(int i=0; i<n; i++)
            answer[i][0] = 1;

        for(int i=0; i<n; i++) {
            for (int j = 0; j <= k; j++) {
                if(i > 0) {
                    if(j >= arr[i])
                        answer[i][j] = answer[i - 1][j] + answer[i][j - arr[i]];
                    else
                        answer[i][j] = answer[i-1][j];
                } else {
                    if (j % arr[i] == 0)
                        answer[0][j] = 1;
                }
            }
        }

        System.out.println(answer[n-1][k]);

    }
}

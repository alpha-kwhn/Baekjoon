import java.util.*;
import java.io.*;
public class Baek_1365 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int [] line = new int[N+1];
        int [] answer = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++)
            line[i] = Integer.parseInt(st.nextToken());

        int count = 0;
        for(int i=1; i<N+1; i++) {
            if(line[i] > answer[count])
                answer[++count] = line[i];
            else {
                int left = 0;
                int right = count;
                int mid;

                while (left < right) {
                    mid = (left + right) / 2;
                    if (answer[mid] < line[i])
                        left = mid + 1;
                    else
                        right = mid;
                }
                answer[left] = line[i];
            }
        }
        System.out.println(N - count);
    }
}

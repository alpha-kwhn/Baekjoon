import java.util.*;
import java.io.*;

public class Baek_2352 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int [] connect = new int[N+1];
        int [] answer = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++)
            connect[i] = Integer.parseInt(st.nextToken());

        answer[1] = connect[1];

        int count = 1;
        for(int i=2; i<=N; i++) {
            if(connect[i] > answer[count])
                answer[++count] = connect[i];
            else {
                int left = 0;
                int right = count;
                int mid;

                while (left < right) {
                    mid = (left + right) / 2;
                    if (answer[mid] > connect[i])
                        right = mid;
                    else if (answer[mid] < connect[i])
                        left = mid + 1;
                }
                answer[left] = connect[i];
            }
        }

        System.out.println(count);
    }
}

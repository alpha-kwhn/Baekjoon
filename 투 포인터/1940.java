import java.util.*;
import java.io.*;
public class Baek_1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        int [] cloth = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            cloth[i+1] =num;
        }

        Arrays.sort(cloth);

        int pt1 = 1;
        int pt2 = cloth.length-1;
        int answer = 0;

        while(pt1 < pt2) {
            if(cloth[pt1] + cloth[pt2] > M)
                pt2--;
            else if(cloth[pt1] + cloth[pt2] < M)
                pt1++;
            else {
                answer++;
                pt1++;
                pt2--;
            }
        }

        System.out.println(answer);
    }
}

import java.io.*;
import java.util.*;
public class Baek_2531 {
    static int N; // 접시 수
    static int d; // 가짓 수
    static int k; // 연속 섭취 접시 수
    static int c; // 쿠폰 번호
    static int[] belt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        belt = new int[N];

        for(int i=0; i<N; i++)
            belt[i] = Integer.parseInt(br.readLine());

        int answer = -1;
        for(int i=0; i<N; i++) {
            boolean [] dish = new boolean[3001];
            int cnt = 0;
            boolean found = false;
            for(int j=0; j<k; j++) {
                int val;

                if(i+j >= N)
                    val = belt[(i+j)-N];
                else
                    val = belt[i+j];

                if(!dish[val]) {
                    dish[val] = true;
                    cnt++;
                    if(val == c && !found)
                        found = true;
                }
            }
            if(found)
                answer = Math.max(answer, cnt);
            else
                answer = Math.max(answer, cnt+1);

        }
        System.out.println(answer);
    }
}

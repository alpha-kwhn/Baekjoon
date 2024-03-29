import java.io.*;
import java.util.*;
public class Baek_23843 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int [] consent = new int[M];
        int answer = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            queue.add(Integer.parseInt(st.nextToken()));

        int space = 0;
        while(!queue.isEmpty()) {
            if(space < M) {
                consent[space] = queue.poll();
                space++;
            } else {
                int cnt = 0;
                int spent = consent[M-1];
                answer += spent;
                for(int i=M-1; i>=0; i--) {
                    consent[i] -= spent;
                    if(consent[i] == 0)
                        ++cnt;
                }
                space = M - cnt;
            }
        }

        answer += consent[0];
        System.out.println(answer);
    }
}

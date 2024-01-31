import java.util.*;
import java.io.*;
public class Baek_2559 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [] temperature = new int[N];
        int target = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            temperature[i] = Integer.parseInt(st.nextToken());
            target += temperature[i];
            if(i >= K-1) {
                queue.add(target);
                target -= temperature[i-K+1];
            }
        }
        System.out.println(queue.poll());
    }
}

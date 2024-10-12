import java.util.*;
import java.io.*;
public class Baek_1268 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] maze = new int[N][5];
        int maxi = 0;
        int idx = 0;

        StringTokenizer st;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++) {
                int num = Integer.parseInt(st.nextToken());
                maze[i][j] = num;
            }
        }

        for(int i=0; i<N; i++) {
            Set<Integer> sets = new HashSet<>();
            for(int j=0; j<5; j++) {
                int val = maze[i][j];
                for(int k=0; k<N; k++) {
                    if (i != k && maze[k][j] == val) {
                        sets.add(k);
                    }
                }
            }
            if(sets.size() > maxi) {
                idx = i;
                maxi = sets.size();
            }
        }

        System.out.println(idx+1);
    }
}

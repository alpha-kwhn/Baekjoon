import java.util.*;
import java.io.*;
public class Baek_14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int [] maze = new int[N];
        ArrayList<Integer> answer = new ArrayList<>();
        int [][] total = new int[N][2];

        for(int i=0; i<N; i++)
            maze[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            if(answer.isEmpty()) {
                answer.add(maze[i]);
                total[i][0] = i+1;
                total[i][1] = maze[i];
                continue;
            }

            if(maze[i] > answer.get(answer.size()-1)) {
                answer.add(maze[i]);
                total[i][0] = answer.size();
                total[i][1] = maze[i];
            } else {
                int left = 0;
                int right = answer.size()-1;
                int mid;

                while(left<=right) {
                    mid = (left + right) / 2;
                    if(answer.get(mid) < maze[i])
                        left = mid + 1;
                    else if(answer.get(mid) > maze[i])
                        right = mid - 1;
                    else {
                        left = mid;
                        break;
                    }
                }
                answer.set(left, maze[i]);
                total[i][0] = left+1;
                total[i][1] = maze[i];
            }
            // System.out.println(total[i][0] + " " + total[i][1]);
        }

        int lens = answer.size();
        ArrayList<Integer> totals = new ArrayList<>();

        for(int i=N-1; i>=0; i--) {
            if(lens == total[i][0]) {
                totals.add(total[i][1]);
                lens--;
            }
        }

        System.out.println(totals.size());
        for(int i=totals.size()-1; i>=0; i--)
            System.out.print(totals.get(i) + " ");
    }
}

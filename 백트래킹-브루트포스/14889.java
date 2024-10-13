import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_14889 {
    static int N;
    static int[][] team;
    static int answer = Integer.MAX_VALUE;
    public static void combination(int start, int limit, int cnt, ArrayList<Integer> lis) {
        if(cnt == limit) {
            int total = 0;
            for(int i=0; i<lis.size()-1; i++) {
                for(int j=i+1; j<lis.size(); j++) {
                    if(!Objects.equals(lis.get(i), lis.get(j))) {
                        total += (team[lis.get(i)][lis.get(j)] + team[lis.get(j)][lis.get(i)]);
                    }
                }
            }

            ArrayList<Integer> ars = new ArrayList<>();
            for(int i=0; i<N; i++)
                if(!lis.contains(i))
                    ars.add(i);

            int entire = 0;
            for(int i=0; i<ars.size()-1; i++) {
                for(int j=i+1; j<ars.size(); j++) {
                    if(!Objects.equals(ars.get(i), ars.get(j))) {
                        entire += (team[ars.get(i)][ars.get(j)] + team[ars.get(j)][ars.get(i)]);
                    }
                }
            }

            answer = Math.min(answer, Math.abs(total - entire));
            return;
        }

        for(int i=start; i<N; i++) {
            lis.add(i);
            combination(i+1, limit, cnt+1, lis);
            lis.remove(cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        team = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int num = Integer.parseInt(st.nextToken());
                team[i][j] = num;
            }
        }

        combination(0, N/2, 0, new ArrayList<>());

        System.out.println(answer);

    }
}

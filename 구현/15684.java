import java.util.*;
import java.io.*;

public class Baek_15684 {
    static int [][] maze;
    static int N;
    static int M;
    static int H;
    static int [][] copyMaze(int [][] map) {
        int [][] target = new int[H+1][N+1];
        for(int i=1; i<map.length; i++) {
            int [] tmp = map[i];
            target[i] = Arrays.copyOf(tmp, tmp.length);
        }
        return target;
    }
    static boolean simulate(int [][] map) {
        for(int i=1; i<=N; i++) {
            int cur = i;
            for(int j=1; j<=H; j++) {
                if(map[j][cur] == 1)
                    cur += 1;
                else if(map[j][cur] == -1)
                    cur -= 1;
            }
            if(cur != i)
                return false;
        }
        return true;
    }
    static void combination(int r, int depth, int start_a, int [][] test) {
        if(depth == r) {
            if(simulate(test)) {
                System.out.println(r);
                System.exit(0);
            }
        } else {
            for(int i=start_a; i<=H; i++) {
                for(int j=1; j<N; j++) {
                    if(test[i][j] == 0 && test[i][j+1] == 0) {
                        test[i][j] = 1;
                        test[i][j+1] = -1;
                        combination(r, depth+1, i, test);
                        test[i][j] = 0;
                        test[i][j+1] = 0;
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로 선의 개수
        M = Integer.parseInt(st.nextToken()); // 가로 선의 개수
        H = Integer.parseInt(st.nextToken()); // 사다리 높이
        maze = new int[H+1][N+1];

        // 최초 사다리 맵 상태 기록
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            maze[a][b] = 1;
            maze[a][b+1] = -1;
        }

        for (int i = 0; i < 4; i++)
            combination(i, 0, 1, Arrays.copyOf(maze, maze.length));

        System.out.println(-1);
    }
}

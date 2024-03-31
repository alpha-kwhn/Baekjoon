import java.util.*;
import java.io.*;
public class Baek_15683 {
    static int N;
    static int M;
    static int[][] maze;
    static int answer = Integer.MAX_VALUE;
    static class CCTV {
        int x;
        int y;
        int type;
        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
    static ArrayList<CCTV> lis = new ArrayList<>();
    public static void simulation(int depth, int[][] maps) {
        if(depth == lis.size()) {
            int cnt = 0;
            for(int i=0; i<N; i++)
                for(int j=0; j<M; j++)
                    if(maps[i][j] == 0)
                        cnt++;
            answer = Math.min(answer, cnt);
            return;
        }

        CCTV tv = lis.get(depth);
        int [][] test;
        if(tv.type == 1) {
            test = copyMaze(maps);
            upCheck(tv.x, tv.y, test);
            simulation(depth+1, test);

            test = copyMaze(maps);
            downCheck(tv.x, tv.y, test);
            simulation(depth+1, test);

            test = copyMaze(maps);
            leftCheck(tv.x, tv.y, test);
            simulation(depth+1, test);

            test = copyMaze(maps);
            rightCheck(tv.x, tv.y, test);
            simulation(depth+1, test);
        } else if(tv.type == 2) {
            test = copyMaze(maps);
            upCheck(tv.x, tv.y, test);
            downCheck(tv.x, tv.y, test);
            simulation(depth+1, test);

            test = copyMaze(maps);
            leftCheck(tv.x, tv.y, test);
            rightCheck(tv.x, tv.y, test);
            simulation(depth+1, test);
        } else if(tv.type == 3) {
            test = copyMaze(maps);
            upCheck(tv.x, tv.y, test);
            leftCheck(tv.x, tv.y, test);
            simulation(depth+1, test);

            test = copyMaze(maps);
            upCheck(tv.x, tv.y, test);
            rightCheck(tv.x, tv.y, test);
            simulation(depth+1, test);

            test = copyMaze(maps);
            leftCheck(tv.x, tv.y, test);
            downCheck(tv.x, tv.y, test);
            simulation(depth+1, test);

            test = copyMaze(maps);
            rightCheck(tv.x, tv.y, test);
            downCheck(tv.x, tv.y, test);
            simulation(depth+1, test);
        } else if(tv.type == 4) {
            test = copyMaze(maps);
            leftCheck(tv.x, tv.y, test);
            upCheck(tv.x, tv.y, test);
            rightCheck(tv.x, tv.y, test);
            simulation(depth+1, test);

            test = copyMaze(maps);
            leftCheck(tv.x, tv.y, test);
            upCheck(tv.x, tv.y, test);
            downCheck(tv.x, tv.y, test);
            simulation(depth+1, test);

            test = copyMaze(maps);
            leftCheck(tv.x, tv.y, test);
            rightCheck(tv.x, tv.y, test);
            downCheck(tv.x, tv.y, test);
            simulation(depth+1, test);

            test = copyMaze(maps);
            rightCheck(tv.x, tv.y, test);
            upCheck(tv.x, tv.y, test);
            downCheck(tv.x, tv.y, test);
            simulation(depth+1, test);
        } else {
            test = copyMaze(maps);
            leftCheck(tv.x, tv.y, test);
            upCheck(tv.x, tv.y, test);
            downCheck(tv.x, tv.y, test);
            rightCheck(tv.x, tv.y, test);
            simulation(depth+1, test);
        }
    }
    public static int[][] copyMaze(int [][] art) {
        int[][] ars = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                ars[i][j] = art[i][j];
            }
        }
        return ars;
    }
    public static void upCheck(int a, int b, int[][] test) {
        for(int i=a-1; i>=0; i--) {
            if(test[i][b] == 6)
                return;
            if(test[i][b] != 0)
                continue;
            test[i][b] = -1;
        }
    }
    public static void downCheck(int a, int b, int[][] test) {
        for(int i=a+1; i<N; i++) {
            if(test[i][b] == 6)
                return;
            if(test[i][b] != 0)
                continue;
            test[i][b] = -1;
        }
    }
    public static void leftCheck(int a, int b, int[][] test) {
        for(int i=b-1; i>=0; i--) {
            if(test[a][i] == 6)
                return;
            if(test[a][i] != 0)
                continue;
            test[a][i] = -1;
        }
    }
    public static void rightCheck(int a, int b, int[][] test) {
        for(int i=b+1; i<M; i++) {
            if(test[a][i] == 6)
                return;
            if(test[a][i] != 0)
                continue;
            test[a][i] = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int num = Integer.parseInt(st.nextToken());
                maze[i][j] = num;
                if (num >= 1 && num <= 5)
                    lis.add(new CCTV(i, j, num));
            }
        }

        simulation(0, maze);
        System.out.println(answer);
    }
}

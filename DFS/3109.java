import java.util.*;
import java.io.*;

public class Baek_3109 {
    static int R;
    static int C;
    static String [][] maze;
    static int answer = 0;
    static int[] dir_x = {-1, 0, 1};
    static int[] dir_y = {1, 1, 1};
    static boolean DFS(int a, int b) {
        maze[a][b] = "visited";

        if(b == C)
            return true;

        if(a > 1 && maze[a + dir_x[0]][b + dir_y[0]].equals(".")) {
            if(DFS(a + dir_x[0], b + dir_y[0]))
                return true;
        }

        if (maze[a + dir_x[1]][b + dir_y[1]].equals(".")) {
            if(DFS(a + dir_x[1], b + dir_y[1]))
                return true;
        }

        if(a < R && maze[a + dir_x[2]][b + dir_y[2]].equals(".")) {
            if(DFS(a + dir_x[2], b + dir_y[2]))
                return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new String[R+1][C+1];

        for(int i=1; i<=R; i++) {
            String line = br.readLine();
            for(int j=0; j<C; j++)
                maze[i][j+1] = String.valueOf(line.charAt(j));
        }

        for(int i=1; i<=R; i++)
            if(DFS(i, 1))
                answer++;

        System.out.println(answer);
    }
}

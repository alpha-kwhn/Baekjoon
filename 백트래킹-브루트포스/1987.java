import java.util.*;
import java.io.*;

public class Baek_1987 {
    static int R;
    static int C;
    static int [] dir_x = {-1, 0, 1, 0};
    static int [] dir_y = {0, 1, 0, -1};
    static boolean[] alphabet = new boolean[26];
    static int answer = 1;
    static char [][] maze;
    static boolean isOK(int a, int b) {
        return (1 <= a && a <= R && 1 <= b && b <= C);
    }
    static void DFS(int a, int b, int count) {
        if(alphabet[maze[a][b]-'A']) {
            answer = Math.max(answer, count);
            return;
        }

        alphabet[maze[a][b]-'A'] = true;

        for (int i = 0; i < 4; i++) {
            if(isOK(a + dir_x[i], b + dir_y[i]))
                DFS(a + dir_x[i], b + dir_y[i], count+1);
            else
                answer = Math.max(answer, count);
        }

        alphabet[maze[a][b]-'A'] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new char[R+1][C+1];

        for(int i=1; i<=R; i++) {
            String row = br.readLine();
            for(int j=0; j<C; j++) {
                maze[i][j + 1] = row.charAt(j);
            }
        }

        DFS(1, 1, 0);

        System.out.println(answer);
    }
}

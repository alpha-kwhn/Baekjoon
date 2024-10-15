import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;
import java.util.stream.*;

public class Baek_18428 {
    static String[][] maze;
    static int N;
    static int[] dir_x = {-1, 1, 0, 0};
    static int[] dir_y = {0, 0, 1, -1};
    static ArrayList<int[]> teacher = new ArrayList<>();
    static ArrayList<int[]> empty = new ArrayList<>();
    static String answer = "NO";

    public static boolean isOK(int a, int b) {
        return (0<=a && a <N && 0<=b && b<N);
    }

    public static void blocking(int start, int depth, ArrayList<int[]> spots) {
        if(answer.equals("YES"))
            return;

        if(depth == 3) {
            String[][] tmp = new String[N][N];

            for(int i = 0; i < N; i++)
                tmp[i] = Arrays.copyOf(maze[i], N);

            for(int i=0; i<3; i++)
                tmp[spots.get(i)[0]][spots.get(i)[1]] = "O";

            int found = 0;
            for(int[] location : teacher) {
                for(int j=0; j<4; j++) {
                    int dx = location[0] + dir_x[j];
                    int dy = location[1] + dir_y[j];
                    found += DFS(dx, dy, tmp, j);
                }
            }
            answer = (found == 0) ? "YES" : "NO";
            return;
        }

        for(int i=start; i<empty.size(); i++) {
            int[] arr = empty.get(i);
            if (maze[arr[0]][arr[1]].equals("X")) {
                spots.add(new int[]{arr[0], arr[1]});
                blocking(i+1, depth + 1, spots);
                spots.remove(spots.size()-1);
            }
        }
    }

    public static int DFS(int x, int y, String[][] maps, int dir) {
        if(!isOK(x, y) || maps[x][y].equals("O"))
            return 0;

        if(maps[x][y].equals("S"))
            return 1;

        return DFS(x+dir_x[dir], y+dir_y[dir], maps, dir);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        maze = new String[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                String str = st.nextToken();
                maze[i][j] = str;

                if(str.equals("T"))
                    teacher.add(new int[]{i, j});
                if(str.equals("X"))
                    empty.add(new int[]{i, j});
            }
        }

        blocking(0, 0, new ArrayList<>());

        System.out.println(answer);
    }
}

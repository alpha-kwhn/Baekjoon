import java.util.*;
import java.io.*;
public class Baek_4963 {
    static int[] dir_x = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dir_y = {0, 1, 0, -1, -1, 1, -1, 1};
    public static boolean isOK(int a, int b, int w, int h) {
        return (0<=a && a<h && 0<=b && b<w);
    }
    public static void DFS(boolean[][] visit, int[][] maps, int a, int b, int w, int h) {
        for(int i=0; i<8; i++) {
            int dx = a + dir_x[i];
            int dy = b + dir_y[i];
            if(isOK(dx, dy, w, h)) {
                if(!visit[dx][dy] && maps[dx][dy] == 1) {
                    visit[dx][dy] = true;
                    DFS(visit, maps, dx, dy, w, h);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0)
                break;
            else {
                int[][] maze = new int[h][w];
                for(int i=0; i<h; i++) {
                    st = new StringTokenizer(br.readLine());
                    for(int j=0; j<w; j++) {
                        int val = Integer.parseInt(st.nextToken());
                        maze[i][j] = val;
                    }
                }
                boolean[][] visited = new boolean[h][w];
                int count = 0;
                for(int i=0; i<h; i++) {
                    for(int j=0; j<w; j++) {
                        if(maze[i][j] == 1) {
                            if(!visited[i][j]) {
                                visited[i][j] = true;
                                DFS(visited, maze, i, j, w, h);
                                count++;
                            }
                        }
                    }
                }
                System.out.println(count);
            }
        }
    }
}

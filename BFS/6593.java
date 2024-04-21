import java.io.*;
import java.util.*;
public class Baek_6593 {
    static int L; // 층 수
    static int R; // 행
    static int C; // 열
    static int start_z;
    static int start_x;
    static int start_y;
    static int end_z;
    static int end_x;
    static int end_y;
    static int[] dir_x = {-1, 0, 1, 0, 0, 0}; // 북 동 남 서 상 하
    static int[] dir_y = {0, 1, 0, -1, 0, 0};
    static int[] dir_z = {0, 0, 0, 0, -1, 1};
    static char[][][] maze;
    static boolean[][][] visited;
    static class Point {
        int x, y, z, time;
        Point(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }
    public static boolean isOK(int a, int b, int c) {
        return (0<=c && c<L && 0<=a && a<R && 0<=b && b<C);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L == 0 && R == 0 && C == 0)
                break;

            maze = new char[L][R][C];
            visited = new boolean[L][R][C];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String row = br.readLine();
                    maze[i][j] = row.toCharArray();
                    for (int k = 0; k < C; k++) {
                        if (maze[i][j][k] == 'S') {
                            start_z = i;
                            start_x = j;
                            start_y = k;
                        } else if (maze[i][j][k] == 'Z') {
                            end_z = i;
                            end_x = j;
                            end_y = k;
                        }
                    }
                }
                br.readLine();
            }

            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(start_x, start_y, start_z, 0));
            visited[start_z][start_x][start_y] = true;

            int answer = 0;
            while (!queue.isEmpty()) {
                Point pt = queue.poll();
                int z = pt.z;
                int x = pt.x;
                int y = pt.y;
                int time = pt.time;
                for (int i = 0; i < 6; i++) {
                    int dz = z + dir_z[i];
                    int dx = x + dir_x[i];
                    int dy = y + dir_y[i];
                    if (isOK(dx, dy, dz)) {
                        if (!visited[dz][dx][dy]) {
                            if (maze[dz][dx][dy] == '.') {
                                visited[dz][dx][dy] = true;
                                queue.add(new Point(dx, dy, dz, time + 1));
                            } else if (maze[dz][dx][dy] == 'E') {
                                answer = time + 1;
                                queue.clear();
                                break;
                            }
                        }
                    }
                }
            }
            if(answer == 0)
                System.out.println("Trapped!");
            else
                System.out.println("Escaped in " + answer + " minute(s).");
        }
    }
}

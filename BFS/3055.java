import java.io.*;
import java.util.*;
public class Baek_3055 {
    static class Point {
        int x; int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int R;
    static int C;
    public static boolean isOK(int a, int b) {
        return (0<= a && a < R && 0 <= b && b < C);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char [][] maze = new char[R][C];

        int[] dir_x = {-1, 0, 1, 0};
        int[] dir_y = {0, 1, 0, -1};

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        Queue<Point> queue2 = new LinkedList<>();
        boolean[][] visit = new boolean[R][C];

        for(int i=0; i<R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                maze[i][j] = row.charAt(j);
                if (maze[i][j] == 'S') {
                    queue2.add(new Point(i, j));
                    visit[i][j] = true;
                } else if (maze[i][j] == '*') {
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }

        int time = 0;
        boolean found = false;

        while(!queue2.isEmpty()) {
            ArrayList<Point> tmp = new ArrayList<>();

            while(!queue.isEmpty()) {
                Point pt = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int dx = pt.x + dir_x[i];
                    int dy = pt.y + dir_y[i];
                    if (isOK(dx, dy)) {
                        if (maze[dx][dy] != 'X' && maze[dx][dy] != 'D' && !visited[dx][dy]) {
                            maze[dx][dy] = '*';
                            visited[dx][dy] = true;
                            tmp.add(new Point(dx, dy));
                        }
                    }
                }
            }

            queue.addAll(tmp);
            tmp.clear();

            while(!queue2.isEmpty()) {
                Point bv = queue2.poll();
                for (int i = 0; i < 4; i++) {
                    int dx = bv.x + dir_x[i];
                    int dy = bv.y + dir_y[i];
                    if (isOK(dx, dy)) {
                        if(maze[dx][dy] == 'D') {
                            found = true;
                            time++;
                            queue2.clear();
                        } else if (maze[dx][dy] == '.' && !visit[dx][dy]) {
                            visit[dx][dy] = true;
                            tmp.add(new Point(dx, dy));
                        }
                    }
                }
            }

            if(found)
                break;
            else {
                queue2.addAll(tmp);
                tmp.clear();
                time++;
            }
        }

        if(!found)
            System.out.println("KAKTUS");
        else
            System.out.println(time);
    }
}

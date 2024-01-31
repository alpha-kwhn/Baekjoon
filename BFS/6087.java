import java.util.*;
import java.io.*;

public class Baek_6087 {
    static int W;
    static int H;
    static char [][] maze;
    static int [][] mirror;
    static int start_x;
    static int start_y;
    static int final_x;
    static int final_y;
    static PriorityQueue<Spot> queue;
    static boolean [][] visited;
    static int [] dir_x = {-1, 0, 1, 0};
    static int [] dir_y = {0, 1, 0, -1};
    static class Spot implements Comparable<Spot> {
        int h;
        int w;
        int count;
        int direction; // 시계방향 순서대로 0,1,2,3
        Spot(int h, int w, int count, int direction) {
            this.w = w;
            this.h = h;
            this.count = count;
            this.direction = direction;
        }
        @Override
        public int compareTo(Spot spot) {
            return Integer.compare(count, spot.count);
        }
    }
    static boolean isOK(int h, int w) {
        return (0 <= h && h < H && 0 <= w && w < W);
    }
    static boolean isWall(int h, int w) {
        return maze[h][w] == '*';
    }
    static boolean isGoal(int h, int w) {
        return maze[h][w] == 'C';
    }
    static int checkDirection(int direction, int a) {
        if(direction == a) return 0;
        else if(Math.abs(direction - a) == 2) return -1;
        else return 1;
    }
    static void BFS() {
        while(!queue.isEmpty()) {
            Spot target = queue.poll();
            int direction = target.direction;
            if(visited[target.h][target.w]) continue;
            visited[target.h][target.w] = true;
            for (int a = 0; a < 4; a++) {
                int dx = target.h + dir_x[a];
                int dy = target.w + dir_y[a];
                if (isOK(dx, dy) && !isWall(dx, dy)) {
                    if (!isGoal(dx, dy)) {
                        if (checkDirection(direction, a) == 0) {
                            if (mirror[dx][dy] >= target.count) {
                                mirror[dx][dy] = target.count;
                                queue.add(new Spot(dx, dy, target.count, direction));
                            }
                        } else if (checkDirection(direction, a) == 1) {
                            if (mirror[dx][dy] >= target.count + 1) {
                                mirror[dx][dy] = target.count + 1;
                                queue.add(new Spot(dx, dy, target.count + 1, a));
                            }
                        }
                    } else {
                        if (checkDirection(direction, a) == 0) {
                            mirror[dx][dy] = Math.min(mirror[dx][dy], target.count);
                        } else if (checkDirection(direction, a) == 1) {
                            mirror[dx][dy] = Math.min(mirror[dx][dy], target.count+1);
                        }
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> answer = new PriorityQueue<>();

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        maze = new char[H][W];
        mirror = new int[H][W];
        visited = new boolean[H][W];
        queue = new PriorityQueue<>();

        for(int i=0; i<H; i++)
            Arrays.fill(mirror[i], Integer.MAX_VALUE);
        boolean found = false;
        // maze 구성
        for(int i=0; i<H; i++) {
            String row = br.readLine();
            for(int j=0; j<W; j++) {
                maze[i][j] = row.charAt(j);
                if(!found && row.charAt(j) == 'C') {
                    start_x = i;
                    start_y = j;
                    visited[start_x][start_y] = true;
                    found = true;
                } else if(found && row.charAt(j) == 'C') {
                    final_x = i;
                    final_y = j;
                }
            }
        }

        // 시작점 큐에 넣기
        for(int q=0; q<4; q++) {
            if(isOK(start_x+dir_x[q], start_y+dir_y[q])) {
                if(maze[start_x+dir_x[q]][start_y+dir_y[q]] == '.') {
                    queue.add(new Spot(start_x + dir_x[q], start_y + dir_y[q], 0, q));
                    mirror[start_x + dir_x[q]][start_y + dir_y[q]] = 0;
                }
            }
        }

        BFS();
        answer.add(mirror[final_x][final_y]);

        for(int i=0; i<H; i++) {
            Arrays.fill(mirror[i], Integer.MAX_VALUE);
            Arrays.fill(visited[i], false);
        }
        visited[final_x][final_y] = true;

        for(int q=0; q<4; q++) {
            if(isOK(final_x+dir_x[q], final_y+dir_y[q])) {
                if(maze[final_x+dir_x[q]][final_y+dir_y[q]] == '.') {
                    queue.add(new Spot(final_x + dir_x[q], final_y + dir_y[q], 0, q));
                    mirror[final_x + dir_x[q]][final_y + dir_y[q]] = 0;
                }
            }
        }

        BFS();
        answer.add(mirror[start_x][start_y]);

        System.out.println(answer.poll());
    }
}

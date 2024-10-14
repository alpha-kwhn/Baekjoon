import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_3190 {
    static int[][] maze;
    static int N;
    static int[] dir_y = {0, 1, 0, -1};
    static int[] dir_x = {-1, 0, 1, 0};
    static int now_dir = 1; // 상 우 하 좌
    static int time = 1;
    static ArrayList<String> snake = new ArrayList<>();
    static Queue<String> move = new ArrayDeque<>();

    public static boolean isOK(int a, int b) {
        return (0<=a && a<N && 0<=b && b<N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        maze = new int[N][N];

        int apple = Integer.parseInt(br.readLine());
        for(int i=0; i<apple; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            maze[a-1][b-1] = -1;
        }

        int L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            String dir = String.valueOf(st.nextToken());
            move.add(a + "_" + dir);
        }

        maze[0][0] = 1;
        snake.add("0_0");

        int x = 0;
        int y = 0;

        while(true) {
            int dx = x+dir_x[now_dir];
            int dy = y+dir_y[now_dir];

            // 벽 확인
            if(!isOK(dx, dy))
                break;

            // 자신 몸인지 확인
            int next = maze[dx][dy];
            if(next == 1)
                break;

            x = dx;
            y = dy;

            // 머리 이동
            maze[x][y] = 1;
            snake.add(0, x + "_" + y);

            // 빈칸
            if(next == 0) {
                String[] idx = snake.get(snake.size()-1).split("_");
                int a = Integer.parseInt(idx[0]);
                int b = Integer.parseInt(idx[1]);
                maze[a][b] = 0;
                snake.remove(snake.size() - 1);
            }

            // 방향 전환 체크
            if(!move.isEmpty()) {
                String[] idx = move.peek().split("_");
                int change_recent = Integer.parseInt(idx[0]);
                if(time == change_recent) {
                    String rotate = idx[1];
                    move.poll();
                    if(rotate.equals("L"))
                        now_dir = (now_dir+3) % 4;
                    else
                        now_dir = (now_dir+1) % 4;
                }
            }

            time++;
        }

        System.out.println(time);
    }
}

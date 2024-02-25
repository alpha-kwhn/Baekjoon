import java.util.*;
import java.io.*;

public class Baek_2638 {
    static int [][] maze;
    static int [][] air;
    static ArrayList<Cheese> arr = new ArrayList<>();
    static int [] dir_x = {0, 0, 1, -1};
    static int [] dir_y = {1, -1, 0, 0};
    static int time = 0;
    static class Cheese {
        int a;
        int b;
        Cheese(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static boolean isOK(int a, int b) {
        return (0 <= a && a < maze.length && 0 <= b && b < maze[0].length);
    }

    static void judge() {
        Queue<Cheese> queue = new LinkedList<>();
        queue.add(new Cheese(0, 0));

        boolean [][] visited = new boolean[101][101];

        while(!queue.isEmpty()) {
            Cheese target = queue.poll();
            for(int i=0; i<4; i++) {
                int dx = target.a + dir_x[i];
                int dy = target.b + dir_y[i];

                if(isOK(dx, dy) && !visited[dx][dy]) {
                    if(maze[dx][dy] == 0) {
                        visited[dx][dy] = true;
                        queue.add(new Cheese(dx, dy));
                    } else {
                        air[dx][dy] += 1;
                        if(air[dx][dy] >= 2)
                            arr.add(new Cheese(dx, dy));
                    }
                }
            }
        }
    }

    static boolean melting() {
        if(arr.isEmpty())
            return false;
        else {
            while (!arr.isEmpty()) {
                Cheese target = arr.remove(0);
                maze[target.a][target.b] = 0;
            }
            return true;
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        maze = new int[101][101];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++)
                maze[i][j] = Integer.parseInt(st.nextToken());
        }

        // 치즈가 다 녹을 때까지 계속 해서 녹이기 + 시간 증가를 반복
        while(true) {
            air = new int[101][101];
            judge();
            boolean flag = melting();

            if(!flag)
                break;
            else
                time += 1;
        }

        System.out.println(time);
    }
}

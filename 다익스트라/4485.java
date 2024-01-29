import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_4485 {
    static Queue<Spot> container = new LinkedList<>();
    static boolean [][] visited;
    static int [][] cave;
    static int [][] distance;
    static int lengths;
    static int [] dir_x = {0, 0, 1, -1};
    static int [] dir_y = {1, -1, 0, 0};
    private static class Spot {
        int x;
        int y;
        int count;
        Spot(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    static boolean isOK(int a, int b) {
        return (0 <= a && a < lengths && 0 <= b && b < lengths);
    }
    static void BFS() {
        while(!container.isEmpty()) {
            Spot target = container.poll();
            for(int a=0; a<4; a++) {
                int dx = target.x + dir_x[a];
                int dy = target.y + dir_y[a];
                if(isOK(dx, dy)) {
                    if(distance[dx][dy] > target.count + cave[dx][dy]) {
                        distance[dx][dy] = target.count + cave[dx][dy];
                        container.add(new Spot(dx, dy, target.count + cave[dx][dy]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        while(true) {
            num += 1;
            lengths = Integer.parseInt(bf.readLine());
            if(lengths == 0) break;
            cave = new int[lengths][lengths];
            visited = new boolean[lengths][lengths];
            distance = new int[lengths][lengths];
            for(int i=0; i<lengths; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                Arrays.fill(visited[i], false);
                Arrays.fill(distance[i], Integer.MAX_VALUE);
                for(int j=0; j<lengths; j++)
                    cave[i][j] = Integer.parseInt(st.nextToken());
            }
            visited[0][0] = true;
            distance[0][0] = cave[0][0];
            container.add(new Spot(0, 0, cave[0][0]));
            BFS();
            System.out.println("Problem " + num + ": " + distance[lengths-1][lengths-1]);
        }
    }
}

import java.util.*;
import java.io.*;
import java.awt.*;

public class Baek_2665 {
    static int [][] maze;
    static int [][] distance;
    static int size;
    static int[] dir_x = {0, 0, 1, -1};
    static int[] dir_y = {1, -1, 0, 0};
    static Queue<Spot> queue = new LinkedList<>();
    
    public static boolean isOK(int a, int b) {
        return (0 <= a && a < size && 0 <= b && b < size);
    }

    static class Spot {
        Point point;
        int count;
        Spot(Point point, int count) {
            this.point = point;
            this.count = count;
        }
    }

    public static void BFS() {
        while (!queue.isEmpty()) {
            Spot top = queue.poll();
            for (int i = 0; i < 4; i++) {
                int dx = top.point.x + dir_x[i];
                int dy = top.point.y + dir_y[i];
                if (isOK(dx, dy)) {
                    if (maze[dx][dy] == 0) {
                        if (distance[dx][dy] > top.count + 1) {
                            queue.add(new Spot(new Point(dx, dy), top.count + 1));
                            distance[dx][dy] = top.count + 1;
                        }
                    }
                    else {
                        if (distance[dx][dy] > top.count) {
                            queue.add(new Spot(new Point(dx, dy), top.count));
                            distance[dx][dy] = top.count;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        maze = new int[size][size];
        distance = new int[size][size];

        for(int i=0; i<size; i++) {
            String row = br.readLine();
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            for(int j=0; j<size; j++)
                maze[i][j] = Character.getNumericValue(row.charAt(j));
        }

        Spot start = new Spot(new Point(0, 0), maze[0][0] == 0 ? 1 : 0);
        queue.add(start);
        distance[0][0] = start.count;
        BFS();
        System.out.println(distance[size-1][size-1]);
    }
}

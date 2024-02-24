import java.io.*;
import java.util.*;

public class Baek_2573 {
    static ArrayList<Ice> arr = new ArrayList<>();
    static int [][] maze;
    static int [] dir_x = {0, 0, 1, -1};
    static int [] dir_y = {1, -1, 0, 0};
    static int time = 0;
    static class Ice {
        int a;
        int b;
        int height;
        int ratio;
        Ice(int a, int b, int height) {
            this.a = a;
            this.b = b;
            this.height = height;
        }

        Ice(int a, int b, int height, int ratio) {
            this.a = a;
            this.b = b;
            this.height = height;
            this.ratio = ratio;
        }
        public void sink() {
            this.height -= ratio;
            maze[a][b] -= ratio;
        }
    }

    static int seaCount(int a, int b) {
        int count = 0;

        for(int i=0; i<4; i++) {
            if(maze[a+dir_x[i]][b+dir_y[i]] <= 0)
                count += 1;
        }
        return count;
    }

    static void updateSeaCount() {
        for(int i=0; i<arr.size(); i++) {
            Ice target = arr.remove(0);
            Ice newIce = new Ice(target.a, target.b, target.height, seaCount(target.a, target.b));
            arr.add(newIce);
        }
    }

    static int BFS() {
        int island = 0;
        boolean [][] visited = new boolean[maze.length][maze[0].length];
        ArrayList<Ice> test = new ArrayList<>();

        for (Ice tmp : arr) {
            if (!visited[tmp.a][tmp.b]) {
                test.add(tmp);
                visited[tmp.a][tmp.b] = true;
            } else continue;

            while (!test.isEmpty()) {
                Ice target = test.remove(0);

                for (int j = 0; j < 4; j++) {
                    int dx = target.a + dir_x[j];
                    int dy = target.b + dir_y[j];

                    if (maze[dx][dy] > 0 && !visited[dx][dy]) {
                        test.add(new Ice(dx, dy, maze[dx][dy]));
                        visited[dx][dy] = true;
                    }
                }
            }
            island += 1;
        }
        return island;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
                if(maze[i][j] > 0)
                    arr.add(new Ice(i, j, maze[i][j]));
            }
        }

        updateSeaCount();

        boolean isEmpty = false;

        if(BFS() >= 2)
            System.out.println(0);
        else {
            while(!arr.isEmpty()) {
                time += 1;
                ArrayList<Ice> tmp = new ArrayList<>();
                for (Ice ice : arr) {
                    ice.sink();
                    if(ice.height > 0)
                        tmp.add(ice);
                }

                if(!tmp.isEmpty()) {
                    arr = tmp;
                    if(BFS() >= 2)
                        break;
                    else {
                        updateSeaCount();
                    }
                } else {
                    isEmpty = true;
                    break;
                }
            }
        }

        if(isEmpty || arr.isEmpty())
            System.out.println(0);
        else
            System.out.println(time);
    }
}

import java.util.*;
import java.io.*;
public class Baek_16236 {
    static int[][] maze;
    static int start_x;
    static int start_y;
    static int count = 0;
    static int level = 2;
    static int answer = 0;
    static int N;
    static boolean [][] visited;
    static class Spot implements Comparable<Spot> {
        int x;
        int y;
        int level;
        int distance;
        Spot(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
            this.distance = 0;
        }
        public void updateDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(Spot o) {
            if(this.distance < o.distance)
                return -1;
            else if(this.distance > o.distance)
                return 1;
            else {
                if(this.x < o.x)
                    return -1;
                else if(this.x > o.x)
                    return 1;
                else {
                    if(this.y > o.y)
                        return 1;
                    else
                        return -1;
                }
            }
        }
    }
    static ArrayList<int[]> lis = new ArrayList<>();
    public static boolean findFish() {
        PriorityQueue<Spot> queue = new PriorityQueue<>();

        for(int[] fish: lis) {
            if(level > fish[2]) {
                int distance = getCost(fish[0], fish[1]);
                if(distance != -1) {
                    Spot eat = new Spot(fish[0], fish[1], fish[2]);
                    eat.updateDistance(distance);
                    queue.add(eat);
                }
            }
        }

        int removeIdx = 0;
        if(!queue.isEmpty()) {
            Spot fish = queue.peek();
            answer += fish.distance;
            maze[fish.x][fish.y] = 0;
            start_x = fish.x;
            start_y = fish.y;
            ++count;

            if(count == level) {
                count = 0;
                ++level;
            }

            for (int i = 0; i < lis.size(); i++) {
                if (lis.get(i)[0] == queue.peek().x && lis.get(i)[1] == queue.peek().y) {
                    removeIdx = i;
                    break;
                }
            }
            lis.remove(removeIdx);
            return true;
        }

        return false;
    }
    public static boolean isOk(int a, int b) {
        return (0 <= a && a < N && 0 <= b && b < N);
    }
    public static int getCost(int a, int b) {
        int [] dir_x = {-1, 0, 1, 0};
        int [] dir_y = {0, 1, 0, -1};
        boolean [][] temp = new boolean[N][N];
        temp[start_x][start_y] = true;

        Queue<Spot> info = new LinkedList<>();
        info.add(new Spot(start_x, start_y, level));

        while(!info.isEmpty()) {
            Spot now = info.poll();
            for (int i = 0; i < 4; i++) {
                int dx = now.x + dir_x[i];
                int dy = now.y + dir_y[i];

                if(dx == a && dy == b) {
                    visited[dx][dy] = true;
                    return now.distance + 1;
                }

                if (isOk(dx, dy)) {
                    if ((maze[dx][dy] <= level) && !temp[dx][dy]) {
                        Spot adder = new Spot(dx, dy, level);
                        adder.updateDistance(now.distance + 1);
                        info.add(adder);
                        temp[dx][dy] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        maze = new int[N][N];
        visited = new boolean[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 9) {
                    start_x = i;
                    start_y = j;
                } else if(num != 0) {
                    lis.add(new int[]{i, j, num});
                    maze[i][j] = num;
                }
            }
        }

        visited[start_x][start_y] = true;

        if(lis.isEmpty())
            System.out.println(0);
        else if(lis.size() == 1) {
            if(lis.get(0)[2] < 2)
                System.out.println(Math.abs(lis.get(0)[0] - start_x) + Math.abs(lis.get(0)[1] - start_y));
            else
                System.out.println(0);
        } else {
            while (true) {
                boolean result = findFish();
                if (!result)
                    break;
            }
            System.out.println(answer);
        }
    }
}

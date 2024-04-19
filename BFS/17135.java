import java.util.*;
import java.io.*;
public class Baek_17135 {
    static int N; // 행
    static int M; // 열
    static int D; // 공격 제한 거리
    static int[][] maze;
    static int[] dir_x = {0, -1, 0};
    static int[] dir_y = {-1, 0, 1};
    static int answer = 0;
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static ArrayList<int[]> comb = new ArrayList<>();
    static void combination(int start, int depth, boolean[] visited, int[] collect) {
        if(depth == 3) {
            int [] newOne = new int[3];
            for(int i=0; i<3; i++)
                newOne[i] = collect[i];
            comb.add(newOne);
            return;
        }

        for(int i=start; i<M; i++) {
            if(!visited[i]) {
                visited[i] = true;
                collect[depth] = i;
                combination(i+1, depth+1, visited, collect);
                collect[depth] = 0;
                visited[i] = false;
            }
        }
    }
    public static int[][] mazeCopy() {
        int[][] newOne = new int[N+1][M];
        for(int i=0; i<N+1; i++)
            for(int j=0; j<M; j++)
                newOne[i][j] = maze[i][j];
        return newOne;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        maze = new int[N+1][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int num = Integer.parseInt(st.nextToken());
                maze[i][j] = num;
            }
        }

        combination(0, 0, new boolean[M], new int[3]);

        for(int[] arr : comb) {
            // 맵 세팅
            int[][] tmp = mazeCopy();
            // 몬스터 잡은 횟수
            int count = 0;
            // 시뮬레이션
            for(int i=0; i<N; i++) {
                // 시작 x 좌표
                int x = N - i;
                ArrayList<int[]> monster = new ArrayList<>();
                for(int j=0; j<3; j++) {
                    // 시작 y 좌표
                    int y = arr[j];
                    // 이동 거리
                    int distance = 0;
                    // 몬스터 포착 여부
                    boolean found = false;
                    // 몬스터 잡을 때 까지 계속 탐색 진행 (좌 -> 상 -> 우)
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(x, y));
                    // 탐색을 위한 방문 여부 저장 배열
                    boolean[][] visit = new boolean[N+1][M];
                    visit[x][y] = true;

                    while(true) {
                        ArrayList<Point> container = new ArrayList<>();
                        while(!queue.isEmpty()) {
                            Point pt = queue.poll();
                            for (int k = 0; k < 3; k++) {
                                int dx = pt.x + dir_x[k];
                                int dy = pt.y + dir_y[k];
                                if(0<= dx && dx < N-i && 0<=dy && dy<M) {
                                    if(!visit[dx][dy]) {
                                        if(tmp[dx][dy] == 1) {
                                            queue.clear();
                                            found = true;
                                            monster.add(new int[]{dx, dy});
                                            break;
                                        } else {
                                            visit[dx][dy] = true;
                                            container.add(new Point(dx, dy));
                                        }
                                    }
                                }
                            }
                        }
                        ++distance;
                        if(found)
                            break;
                        else {
                            if (distance < D)
                                queue.addAll(container);
                            else
                                break;
                        }
                    }
                }
                for(int[] ars : monster) {
                    if(tmp[ars[0]][ars[1]] == 1) {
                        tmp[ars[0]][ars[1]] = 0;
                        count++;
                    }
                }
            }
            answer = Math.max(count, answer);
        }
        System.out.println(answer);
    }
}

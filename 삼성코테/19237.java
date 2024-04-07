import java.util.*;
import java.io.*;
public class Baek_19237 {
    static int N; // 맵 크기
    static int M; // 상어 개수
    static int K; // 냄새 제거 조건 이동 수
    static int[] dir_x = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dir_y = {0, 0, -1, 1};
    static int answer = 0;
    static int remain;
    static int [][] sharkMaze;
    static int [][] smellMaze;
    static int [][] areaMaze;
    static ArrayList<Shark> shark = new ArrayList<>();
    static class Shark {
        int x, y;
        int num;
        int dir;
        int [][] priority = new int[4][4];
        boolean alive = true;
        Shark(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
        public void setDirection(int a) {
            dir = a;
        }
        public void setPriority(int[][] arr) {
            priority = arr;
        }
        public void dead() {
            alive = false;
            remain--;
        }
    }
    static class Point {
        int x, y, dir;
        Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    public static boolean isOK(int a, int b) {
        return (0<=a && a<N && 0<=b && b<N);
    }
    public static void updateSmell() {
        for(int i=1; i<shark.size(); i++) {
            Shark fish = shark.get(i);

            // 죽은 상어는 스킵
            if(!fish.alive)
                continue;

            int x = fish.x;
            int y = fish.y;
            smellMaze[x][y] = K;
            areaMaze[x][y] = fish.num;
        }
    }
    public static void minusSmell() {
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if (smellMaze[i][j] > 0) {
                    smellMaze[i][j]--;
                    // 냄새가 사라지면 냄새를 뿌린 상어 번호도 없애준다
                    if(smellMaze[i][j] == 0)
                        areaMaze[i][j] = 0;
                }
            }
        }
    }
    public static void search() {
        for(int i=1; i<shark.size(); i++) {
            Shark fish = shark.get(i);

            // 죽은 상어는 스킵
            if(!fish.alive)
                continue;

            int x = fish.x;
            int y = fish.y;
            int cnt = 0;
            ArrayList<Point> pts = new ArrayList<>();

            // 1. 냄새 없는 칸 있는지 확인
            for(int j=0; j<4; j++) {
                int dx = x + dir_x[j];
                int dy = y + dir_y[j];
                if(isOK(dx, dy)) {
                    if(smellMaze[dx][dy] == 0) {
                        cnt++;
                        pts.add(new Point(dx, dy, j));
                    }
                }
            }

            // 2. 냄새 없는 칸이 하나인 경우
            if(cnt == 1)
                placing(pts.get(0), fish);
            // 3. 냄새 없는 칸이 여러 개인 경우 or 냄새 없는 칸 없는 경우 -> 우선 순위 기반 위치 선택 필요
            else
                priority_placing(fish, cnt);
        }
    }
    public static void priority_placing(Shark fish, int cnt) {
        // 상어의 우선 순위 정보 + 방향 불러 오기
        int dir = fish.dir;
        int[][] priority = fish.priority;
        int x = fish.x;
        int y = fish.y;

        for (int i = 0; i < priority[dir].length; i++) {
            int num = priority[dir][i]; // 우선 순위 해당 방향값
            int dx = x + dir_x[num];
            int dy = y + dir_y[num];
            // 타당한 이동방향 + 냄새 없는 장소 인지 확인
            if (isOK(dx, dy))
                // 냄새가 없는 방향이 여러 곳인 경우
                if(cnt > 1) {
                    if (smellMaze[dx][dy] == 0) {
                        // 냄새가 없는 장소라면 해당 방향으로 이동
                        placing(new Point(dx, dy, num), fish);
                        break;
                    }
                } else {
                    if(areaMaze[dx][dy] == fish.num) {
                        // 상어 자신의 냄새가 남아 있는 구역이라면 그곳으로 이동
                        placing(new Point(dx, dy, num), fish);
                        break;
                    }
                }
        }
    }
    public static void placing(Point pts, Shark fish) {
        // 냄새 없는 칸에 다른 상어 있는 경우
        if(sharkMaze[pts.x][pts.y] > 0) {
            // 자기가 더 강한 경우
            if(fish.num < sharkMaze[pts.x][pts.y]) {
                // 기존 좌표 빈공간 처리
                sharkMaze[fish.x][fish.y] = 0;

                // 새로 이동한 좌표 값 저장
                fish.x = pts.x;
                fish.y = pts.y;
                fish.setDirection(pts.dir);
                shark.set(fish.num, fish);

                // 기존 위치에 있던 상어 죽이기
                Shark shk = shark.get(sharkMaze[pts.x][pts.y]);
                shk.dead();
                shark.set(sharkMaze[pts.x][pts.y], shk);

                // 상어 위치 좌표 정보 수정
                sharkMaze[pts.x][pts.y] = fish.num;
            } else { // 자기가 더 약한 경우
                // 기존 좌표 빈공간 처리
                sharkMaze[fish.x][fish.y] = 0;

                // 상어 죽이기
                fish.dead();
                shark.set(fish.num, fish);
            }
        } else { // 냄새 없는 칸에 다른 상어 없는 빈 공간
            // 기존 좌표 빈공간 처리
            sharkMaze[fish.x][fish.y] = 0;

            // 새로 이동한 좌표 값 저장
            fish.x = pts.x;
            fish.y = pts.y;
            fish.setDirection(pts.dir);
            shark.set(fish.num, fish);

            // 상어 위치 좌표 정보 수정
            sharkMaze[pts.x][pts.y] = fish.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sharkMaze = new int[N][N];
        smellMaze = new int[N][N];
        areaMaze = new int[N][N];

        shark.add(new Shark(-1, -1, -1));

        remain = M;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num > 0) {
                    shark.add(new Shark(i, j, num));
                    sharkMaze[i][j] = num;
                    areaMaze[i][j] = num;
                    smellMaze[i][j] = K;
                }
            }
        }

        // 상어 위치 값 오름 차순 정렬
        shark.sort((e1, e2) -> {
            if (e1.num > e2.num)
                return 1;
            else if (e1.num < e2.num)
                return -1;
            return 0;
        });

        // 상어 초기 방향 설정
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++) {
            int dir = Integer.parseInt(st.nextToken());
            Shark fish = shark.get(i);
            fish.setDirection(dir-1);
            shark.set(i, fish);
        }

        // 상어 우선 순위 정보
        for(int i=1; i<=M; i++) {
            Shark fish = shark.get(i);
            int[][] info = new int[4][4];
            for(int j=0; j<4; j++) {
                st = new StringTokenizer(br.readLine());
                info[j][0] = Integer.parseInt(st.nextToken()) - 1;
                info[j][1] = Integer.parseInt(st.nextToken()) - 1;
                info[j][2] = Integer.parseInt(st.nextToken()) - 1;
                info[j][3] = Integer.parseInt(st.nextToken()) - 1;
            }
            fish.setPriority(info);
            shark.set(i, fish);
        }

        while(answer <= 1000 && remain > 1) {
            answer++;
            search();
            minusSmell();
            updateSmell();
        }

        if(answer > 1000)
            System.out.println(-1);
        else
            System.out.println(answer);
    }
}

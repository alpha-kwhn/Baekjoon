import java.util.*;
import java.io.*;
public class Baek_17144 {
    static int R; //행
    static int C; //열
    static int T; // 측정시간
    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static int machine_up_x = -1;
    static int machine_down_x = -1;
    static int answer = 0;
    static int[][] maze; // 맵
    public static boolean isOK(int a, int b) {
        return(0<=a && a<R && 0<=b && b<C);
    }
    public static void spread() {
        // 확산 후 값 저장할 곳 + 공기청정기 위치 표시
        int [][] newOne = new int[R][C];
        newOne[machine_up_x][0] = -1;
        newOne[machine_down_x][0] = -1;

        // 미세 먼지 있는 곳 탐색 + 먼지 확산 실행
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                // 기계좌표면 넘어감
                if(maze[i][j] == -1)
                    continue;
                if(maze[i][j] > 0) {
                    int mount = maze[i][j];
                    int minus = (int) Math.floor((double) mount / 5);
                    for(int p=0; p<4; p++) {
                        int dx = i + dir_x[p];
                        int dy = j + dir_y[p];
                        if(isOK(dx, dy)) {
                            // 공기청정기 없는 확산가능한 칸
                            if(newOne[dx][dy] != -1) {
                                newOne[dx][dy] += minus;
                                mount -= minus;
                            }
                        }
                    }
                    newOne[i][j] += mount;
                }
            }
        }
        refresh(newOne);
    }
    public static void refresh(int [][] target) {
        int[][] newOne = copyMaze(target);

        int [] change_x = {-1, 0, 1};
        int [] change_y = {0, -1, 0};
        int [] move_x = {0, -1, 0, 1};
        int [] move_y = {1, 0, -1, 0};
        int change = 0;

        int start_x = machine_up_x;
        int start_y = 1;

        newOne[machine_up_x][1] = 0;
        newOne[machine_down_x][1] = 0;

        while(true) {
            int x = start_x;
            int y = start_y;
            int dx = start_x + move_x[change];
            int dy = start_y + move_y[change];

            // 값이 이동될 위치 정하기
            if(!isOK(dx, dy)) {
                // 회전
                start_x += change_x[change];
                start_y += change_y[change];
                change++;
            } else {
                start_x += move_x[change];
                start_y += move_y[change];
            }

            // 만약 값이 이동될 위치가 기계라면 종료
            if(newOne[start_x][start_y] == -1) {
                answer -= target[x][y];
                break;
            }

            // 값이 이동될 위치가 기계가 아닌 경우
            newOne[start_x][start_y] = target[x][y];
        }

        // 아랫 방향 순환
        int[] down_change_x = {1, 0, -1};
        int[] down_change_y = {0, -1, 0};
        int[] down_move_x = {0, 1, 0, -1};
        int[] down_move_y = {1, 0, -1, 0};

        start_x = machine_down_x;
        start_y = 1;
        change = 0;

        while(true) {
            int x = start_x;
            int y = start_y;
            int dx = start_x + down_move_x[change];
            int dy = start_y + down_move_y[change];

            // 값이 이동될 위치 정하기
            if(!isOK(dx, dy)) {
                // 회전
                start_x += down_change_x[change];
                start_y += down_change_y[change];
                change++;
            } else {
                start_x += down_move_x[change];
                start_y += down_move_y[change];
            }

            // 만약 값이 이동될 위치가 기계라면 종료
            if(newOne[start_x][start_y] == -1) {
                answer -= target[x][y];
                break;
            }

            // 값이 이동될 위치가 기계가 아닌 경우
            newOne[start_x][start_y] = target[x][y];
        }

        maze = copyMaze(newOne);
    }
    public static int[][] copyMaze(int [][] target) {
        int [][] newOne = new int[R][C];
        for(int i=0; i<R; i++)
            for(int j=0; j<C; j++)
                newOne[i][j] = target[i][j];
        return newOne;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        maze = new int[R][C];

        // 좌표 정보 입력
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                int num = Integer.parseInt(st.nextToken());
                maze[i][j] = num;
                // 공기청정기 위치는 고정이므로 미리 행 위치 static 으로 저장 + 전체 미세먼지 개수 측정
                if(num == -1) {
                    if(machine_up_x == -1)
                        machine_up_x = i;
                    else
                        machine_down_x = i;
                } else if(num > 0)
                    answer += num;
            }
        }

        for(int i=0; i<T; i++) {
            spread();
        }
        int result = 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(maze[i][j] > 0) {
                    result += maze[i][j];
                }
            }
        }

        System.out.println(result);
    }
}

import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_1913 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int position = Integer.parseInt(br.readLine());
        int[][] maze = new int[N][N];
        int [] dir_x = {-1, 0, 1, 0};
        int [] dir_y = {0, 1, 0, -1};
        int x = N / 2;
        int y = N / 2;
        int step = 0;
        int number = 1;
        int gap = 1;
        int [] answer = new int[2];

        maze[x][y] = number++;
        if(position == 1)
            answer = new int[]{x+1, y+1};
        while(number < N*N) {
            if(step % 4 == 0) {
                for(int i=0; i<gap; i++) {
                    x += dir_x[step % 4];
                    if(number == position) {
                        answer[0] = x+1;
                        answer[1] = y+1;
                    }
                    maze[x][y] = number++;
                    if(number == N*N) break;
                }
            } else if(step % 4 == 1) {
                for(int i=0; i<gap; i++) {
                    y += dir_y[step % 4];
                    if(number == position) {
                        answer[0] = x+1;
                        answer[1] = y+1;
                    }
                    maze[x][y] = number++;
                    if(number == N*N) break;
                }
            } else if(step % 4 == 2) {
                gap++;
                for(int i=0; i<gap; i++) {
                    x += dir_x[step % 4];
                    if(number == position) {
                        answer[0] = x+1;
                        answer[1] = y+1;
                    }
                    maze[x][y] = number++;
                    if(number == N*N) break;
                }
            } else if(step % 4 == 3) {
                for(int i=0; i<gap; i++) {
                    y += dir_y[step % 4];
                    if(number == position) {
                        answer[0] = x+1;
                        answer[1] = y+1;
                    }
                    maze[x][y] = number++;
                    if(number == N*N) break;
                }
                gap++;
            }
            step++;
        }
        maze[0][0] = N*N;
        if(position == N*N)
            answer = new int[]{1, 1};

        for(int i=0; i<N; i++) {
            for (Integer val : maze[i])
                System.out.print(val + " ");
            System.out.println();
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}

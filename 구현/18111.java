import java.io.*;
import java.util.StringTokenizer;

public class Baek_18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 제거 2초, 뿌리기 1초

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;
        int answer = Integer.MAX_VALUE;
        int level = 0;

        int[][] land = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int num = Integer.parseInt(st.nextToken());
                land[i][j] = num;
                if(mini > land[i][j]) mini = land[i][j];
                if(maxi < land[i][j]) maxi = land[i][j];
            }
        }

        for(int i=mini; i<=maxi; i++) {
            int block = B;
            int time = 0;
            for(int a=0; a<N; a++) {
                for(int b=0; b<M; b++) {
                    if(land[a][b] > i) {
                        block += Math.abs(land[a][b] - i);
                        time += (2 * Math.abs(land[a][b] - i));
                    } else {
                        block -= Math.abs(land[a][b] - i);
                        time += (Math.abs(land[a][b] - i));
                    }
                }
            }
            if(block >= 0) {
                if(time < answer) {
                    answer = time;
                    level = i;
                } else if(time == answer)
                    level = Math.max(level, i);
            }
        }

        System.out.println(answer + " " + level);
    }
}

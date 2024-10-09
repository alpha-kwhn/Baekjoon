
import java.util.*;
import java.io.*;
public class Baek_1236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[][] maze = new String[N+1][M+1];

        int answer = 0;

        for(int i=0; i<N; i++) {
            String dot = String.valueOf(br.readLine());
            char[] c = dot.toCharArray();
            for(int j=0; j<M; j++)
                maze[i][j] = String.valueOf(c[j]);
        }

        int found_x= 0;
        int found_y= 0;

        for(int i=0; i<N; i++) {
            boolean found = false;
            for(int j=0; j<M; j++) {
                if(maze[i][j].equals("X")) {
                    found = true;
                    break;
                }
            }
            if(!found)
                found_x++;
        }

        for(int i=0; i<M; i++) {
            boolean found = false;
            for(int j=0; j<N; j++) {
                if(maze[j][i].equals("X")) {
                    found = true;
                    break;
                }
            }
            if(!found)
                found_y++;
        }

        System.out.println(Math.max(found_x, found_y));
    }
}

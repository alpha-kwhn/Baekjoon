import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.*;
public class Baek_2563 {
    static int answer = 0;

    public static boolean isOK(int a, int b) {
        return (1<=a && a<101 && 1<=b && b<101);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] paper = new int[101][101];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            for(int j=a; j<a+10; j++) {
                paper[b][j] = 1;
                paper[b+9][j] = 1;
            }

            for(int j=b; j<b+10; j++) {
                paper[j][a] = 1;
                paper[j][a+9] = 1;
            }

            for(int k=b+1; k<b+9; k++)
                for (int j = a + 1; j <a + 9; j++)
                    paper[k][j] = 1;
        }

        answer += (int) Arrays.stream(paper).flatMapToInt(Arrays::stream).filter(e -> e == 1).count();
        System.out.println(answer);
    }
}

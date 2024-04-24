import java.util.*;
import java.io.*;
public class Baek_19941 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String burger = br.readLine();
        char[] hamburger = burger.toCharArray();
        boolean[] checked = new boolean[N];

        int answer = 0;
        for(int i=0; i<N; i++) {
            char c = hamburger[i];
            if(c == 'P') {
                checked[i] = true;
                for (int j = i - K; j <= i + K; j++) {
                    if(j < 0)
                        continue;
                    if(j >= N)
                        break;
                    if(!checked[j] && hamburger[j] == 'H') {
                        checked[j] = true;
                        answer++;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}

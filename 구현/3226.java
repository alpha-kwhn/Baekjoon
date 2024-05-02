import java.io.*;
import java.util.*;

public class Baek_3226 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String time = st.nextToken();
            String[] HM = time.split(":");
            int cost = Integer.parseInt(st.nextToken());
            int hour = Integer.parseInt(HM[0]);
            int minute = Integer.parseInt(HM[1]);

            if(60 - minute <= cost) {
                cost -= (60 - minute);
                if (hour >= 7 && hour < 19)
                    answer += (10 * (60 - minute));
                else
                    answer += (5 * (60 - minute));

                hour++;

                while (true) {
                    if (cost - 60 >= 0) {
                        if (hour >= 7 && hour < 19)
                            answer += 600;
                        else
                            answer += 300;
                        cost -= 60;
                        hour++;
                    } else
                        break;
                }
            }

            if(hour >= 7 && hour < 19)
                answer += (10 * cost);
            else
                answer += (5 * cost);
        }
        System.out.println(answer);
    }
}

import java.io.*;
import java.util.StringTokenizer;

public class Baek_3758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 팀의 수
            int k = Integer.parseInt(st.nextToken()); // 문제의 수
            int t = Integer.parseInt(st.nextToken()); // 팀 ID
            int m = Integer.parseInt(st.nextToken()); // 로그 엔트리 개수

            int[][] record = new int[n+1][k+1]; // 문제 최고점 정보 저장
            int [] submit = new int[n+1]; // 최종 제출 시간 기록
            int [] point = new int[n+1];
            int [] submit_cnt = new int[n+1];

            for(int j=0; j<m; j++) {
                st = new StringTokenizer(br.readLine());
                int id = Integer.parseInt(st.nextToken()); // ID
                int idx = Integer.parseInt(st.nextToken()); // 문제 번호
                int score = Integer.parseInt(st.nextToken()); // 획득 점수

                submit[id] = j+1;
                submit_cnt[id]++;

                if(record[id][idx] < score) {
                    point[id] = point[id] - record[id][idx] + score;
                    record[id][idx] = score;
                }
            }

            int rank = 0;
            for(int j=1; j<point.length; j++) {
                if(j != t) {
                    if (point[j] == point[t]) {
                        if (submit_cnt[j] < submit_cnt[t])
                            rank++;
                        else if (submit_cnt[j] == submit_cnt[t])
                            if (submit[j] < submit[t])
                                rank++;
                    } else if(point[j] > point[t])
                        rank++;
                }
            }

            System.out.println(rank+1);
        }
    }
}

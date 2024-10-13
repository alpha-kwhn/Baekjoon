import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_2775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테케

        for(int i=0; i<T; i++) {
            int K = Integer.parseInt(br.readLine()); // 층 수
            int n = Integer.parseInt(br.readLine()); // 호
            int[][] house = new int[K+1][n+1]; // 아파트
            for(int j=1; j<=n; j++) {
                if(j == 1)
                    house[0][j] = j;
                else
                    house[0][j] = house[0][j-1] + j;
            }

            if(K == 1) {
                house[1][n] = house[0][n];
                System.out.println(house[1][n]);
            } else {
                for (int j = 1; j < K; j++)
                    for (int k = 1; k <= n; k++)
                        house[j][k] += (house[j - 1][k] + house[j][k-1]);
                house[K][n] = house[K-1][n];
                System.out.println(house[K][n]);
            }
        }
    }
}

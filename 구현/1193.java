import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_1193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        String boonsu = "";

        int test = 0;
        int tmp = 0;
        while(tmp<X) {
            test++;
            tmp = ((test*test) + test) / 2;
        }

        int sum = test+1;
        int idx = ((test-1)*(test-1) + (test-1)) / 2;
        int row = test;

        while(idx < X) {
            if (sum % 2 == 0) {
                for (int i = 1; i <= row; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append((sum - i) + "/" + i);
                    boonsu = sb.toString();
                    idx++;
                    if (idx == X)
                        break;
                }
            } else {
                for (int i = 1; i <= row; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(i + "/" + (sum - i));
                    boonsu = sb.toString();
                    idx++;
                    if (idx == X)
                        break;
                }
            }
        }

        System.out.println(boonsu);
    }
}

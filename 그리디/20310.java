import java.io.*;
import java.util.Arrays;

public class Baek_20310 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        boolean[] lis = new boolean[S.length()];
        Arrays.fill(lis, true);

        int one = 0;
        int zero = 0;

        for(int i=0; i<S.length(); i++) {
            if(S.charAt(i) == '0')
                zero++;
            else
                one++;
        }

        StringBuilder sb = new StringBuilder();

        int able_one = one / 2;
        int able_zero = zero / 2;

        for (int i = S.length()-1; i >= 0; i--) {
            if (S.charAt(i) == '0' && able_zero > 0) {
                lis[i] = false;
                able_zero--;
            }
        }

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '1' && able_one > 0) {
                lis[i] = false;
                able_one--;
            }
        }

        able_one = one / 2;
        able_zero = zero / 2;

        for(int i=0; i<S.length(); i++) {
            if(S.charAt(i) == '0' && able_zero > 0 && lis[i]) {
                sb.append('0');
                able_zero--;
            } else if(S.charAt(i) == '1' && able_one > 0 && lis[i]) {
                sb.append('1');
                able_one--;
            }
        }

        System.out.println(sb);

    }
}

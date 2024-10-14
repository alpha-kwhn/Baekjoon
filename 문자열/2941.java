import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_2941 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String alpha = br.readLine();

        String[] arr = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        for(String s : arr) {
            while(alpha.contains(s))
                alpha = alpha.replace(s, "_");
        }
        int cnt = alpha.length();
        alpha = alpha.replaceAll("_", "");
        cnt -= alpha.length();

        System.out.println(cnt + alpha.length());
    }
}

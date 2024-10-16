import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        char[] c = word.toCharArray();

        int[] tobe = new int[2];
        int start = c[0] - '0';

        for(int i=1; i<c.length; i++) {
            if(c[i]-'0' != start) {
                start = c[i]-'0';
                tobe[c[i] - '0']++;
            }
        }

        System.out.println(Arrays.stream(tobe).max().getAsInt());
    }
}

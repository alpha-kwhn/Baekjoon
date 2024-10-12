import java.util.*;
import java.io.*;
public class Baek_1357 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String a = st.nextToken();
        String b = st.nextToken();

        String first = String.valueOf(Integer.parseInt(rev(a)) + Integer.parseInt(rev(b)));
        String answer = rev(first);

        System.out.println(Integer.parseInt(answer));
    }

    public static String rev(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}

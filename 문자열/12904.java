import java.io.*;

public class Baek_12904 {
    static String b;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        b = br.readLine();
        int idx = b.length() - 1;

        while(!b.isEmpty()) {
            if(a.equals(b)) {
                answer = 1;
                break;
            } else {
                if(b.charAt(idx) == 'A')
                    b = b.substring(0, idx);
                else {
                    StringBuilder sb = new StringBuilder(b);
                    sb.reverse();
                    b = sb.substring(1);
                }
            }
            idx--;
        }

        System.out.println(answer);
    }
}

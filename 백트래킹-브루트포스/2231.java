import java.io.*;
public class Baek_2231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num = 1;
        boolean able = false;

        while(num < N) {
            String tmp = String.valueOf(num);
            int lengths = tmp.length();
            int val = num;

            for(int i=0; i<lengths; i++)
                val += (tmp.charAt(i) - '0');

            if(val == N) {
                System.out.println(num);
                able = true;
                break;
            } else
                num++;
        }
        if(!able)
            System.out.println(0);
    }
}

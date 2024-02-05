import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek_11720 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String M = br.readLine();
        int answer = 0;

        for(int i=0; i<M.length(); i++)
            answer += (M.charAt(i) - '0');

        System.out.print(answer);
    }
}

import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_2023 {
    public static PriorityQueue<Integer> answer = new PriorityQueue<>();
    public static boolean check(int x) {
        for(int i=2; i<=Math.sqrt(x); i++) {
            for(int j=i*i; j<=x; j += i) {
                if(j == x)
                    return false;
            }
        }
        return true;
    }

    public static void backtrack(int r, int depth, String number) {
        if(depth == r) {
            int nums = Integer.parseInt(number);
            answer.add(nums);
            return;
        }

        for(int i=0; i<10; i++) {
            StringBuilder sb = new StringBuilder(number);
            sb.append(i);
            int nu = Integer.parseInt(sb.toString());
            if(check(nu)) {
                backtrack(r, depth+1, sb.toString());
                number = sb.deleteCharAt(depth).toString();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String [] first = {"2", "3", "5", "7"};

        for(int i=0; i<4; i++)
            backtrack(N, 1, first[i]);

        for(Integer ans : answer)
            System.out.println(ans);

    }
}

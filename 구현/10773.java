import java.io.*;
import java.util.*;

public class Baek_10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num > 0)
                stack.add(num);
            else
                stack.pop();
        }

        if(stack.isEmpty())
            System.out.println(0);
        else {
            int ans = 0;
            while(!stack.isEmpty())
                ans += stack.pop();
            System.out.println(ans);
        }
    }
}

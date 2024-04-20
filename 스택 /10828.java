import java.io.*;
import java.util.*;
public class Baek_10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Stack<String> stack = new Stack<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String opt = st.nextToken();
            if(opt.equals("push")) {
                String val = st.nextToken();
                stack.add(val);
            } else if(opt.equals("pop") || opt.equals("top")) {
                if(stack.isEmpty())
                    System.out.println(-1);
                else {
                    System.out.println(stack.peek());
                    if(opt.equals("pop"))
                        stack.pop();
                }
            } else if(opt.equals("size"))
                System.out.println(stack.size());
            else {
                int val = stack.isEmpty() ? 1 : 0;
                System.out.println(val);
            }
        }
    }
}

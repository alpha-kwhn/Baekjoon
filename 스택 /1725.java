import java.util.*;
import java.io.*;
public class Baek_1725 {
    static class Stick {
        long height;
        long idx;
        Stick(long height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long [] arr = new long[N];

        for(int i=0; i<N; i++) {
            long num = Long.parseLong(br.readLine());
            arr[i] = num;
        }

        long answer = 0;
        Stack<Stick> stack = new Stack<>();
        for(int i=0; i<N; i++) {
            while(!stack.isEmpty() && stack.peek().height > arr[i]) {
                Stick top = stack.pop();
                long width = i;
                if(!stack.isEmpty())
                    width -= stack.peek().idx +1;
                long val = width * top.height;
                answer = Math.max(answer, val);
            }
            stack.add(new Stick(arr[i], i));
        }

        while(!stack.isEmpty()) {
            Stick stick = stack.pop();
            long h = stick.height;
            long width = N;
            if(!stack.isEmpty())
                width -= stack.peek().idx +1;
            long val = width * h;
            answer = Math.max(answer, val);
        }

        System.out.println(answer);
    }
}

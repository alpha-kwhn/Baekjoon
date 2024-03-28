import java.io.*;
import java.util.*;
public class Baek_9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<String> queue = new LinkedList<>();
        String[] answer = new String[N];

        for(int i=0; i<N; i++) {
            String target = br.readLine();
            for(int j=0; j<target.length(); j++) {
                String c = String.valueOf(target.charAt(j));
                if(queue.isEmpty())
                    queue.add(c);
                else {
                    if(!c.equals(queue.peek())) {
                        if(queue.peek().equals("("))
                            queue.poll();
                        else
                            queue.add(c);
                    } else
                        queue.add(c);
                }
            }
            if(!queue.isEmpty())
                answer[i] = "NO";
            else
                answer[i] = "YES";
            queue.clear();
        }

        for(String a : answer)
            System.out.println(a);
    }
}

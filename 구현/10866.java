import java.util.*;
import java.io.*;
public class Baek_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Deque<Integer> deque = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int num;

            switch(cmd) {
                case "push_front":
                    num = Integer.parseInt(st.nextToken());
                    deque.addFirst(num);
                    break;
                case "push_back":
                    num = Integer.parseInt(st.nextToken());
                    deque.addLast(num);
                    break;
                case "pop_front":
                    if(!deque.isEmpty())
                        System.out.println(deque.removeFirst());
                    else
                        System.out.println(-1);
                    break;
                case "pop_back":
                    if(!deque.isEmpty())
                        System.out.println(deque.removeLast());
                    else
                        System.out.println(-1);
                    break;
                case "size":
                    System.out.println(deque.size());
                    break;
                case "empty":
                    if(deque.isEmpty())
                        System.out.println(1);
                    else
                        System.out.println(0);
                    break;
                case "front":
                    if(!deque.isEmpty())
                        System.out.println(deque.getFirst());
                    else
                        System.out.println(-1);
                    break;
                case "back":
                    if(!deque.isEmpty())
                        System.out.println(deque.getLast());
                    else
                        System.out.println(-1);
                    break;
            }
        }


    }
}

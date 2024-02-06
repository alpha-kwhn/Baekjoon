import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Baek_5430 {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            String task = br.readLine();
            int num = Integer.parseInt(br.readLine());
            String arr = br.readLine();

            String[] dd = arr.substring(1, arr.length()-1).split(",");

            Deque<String> deque = new LinkedList<String>();

            for(int p=0; p < dd.length; p++)
                deque.addLast(dd[p]);

            boolean direction = true;
            boolean isError = false;

            for(int j=0; j<task.length(); j++) {
                if(task.charAt(j) == 'D') {
                    if(direction) {
                        if(deque.isEmpty()) {
                            isError = true;
                            break;
                        }
                        deque.removeFirst();
                    } else deque.removeLast();
                } else if (task.charAt(j) == 'R') {
                    if(direction) direction = false;
                    else direction = true;
                }
            }

            StringBuffer answer = new StringBuffer("");

            if(!deque.isEmpty()) {
                answer.append("[");

                if (direction == true)
                    answer.append(deque.removeFirst());
                else
                    answer.append(deque.removeLast());

                while (!deque.isEmpty()) {
                    if (direction == true) {
                        answer.append(",");
                        answer.append(deque.removeFirst());
                    } else {
                        answer.append(",");
                        answer.append(deque.removeLast());
                    }
                }
                answer.append("]");
                answer.append("\n");
            } else {
                answer.append("[]\n");
            }

            if(!isError) {
                System.out.println(answer);
            } else {
                System.out.println("error");
            }
        }
    }
}

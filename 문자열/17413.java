import java.util.*;
import java.io.*;

public class Baek_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        StringBuilder answer = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        boolean able = true;
        for(int i=0; i<target.length(); i++) {
            String c = String.valueOf(target.charAt(i));
            if(c.equals(">")) {
                sb.append(c);
                answer.append(sb);
                sb = new StringBuilder();
                able = true;
            } else {
                if(c.equals(" ")) {
                    if(able) {
                        answer.append(sb.reverse());
                        answer.append(c);
                        sb = new StringBuilder();
                    } else
                        sb.append(c);
                } else if(c.equals("<")) {
                    answer.append(sb.reverse());
                    answer.append(c);
                    sb = new StringBuilder();
                    able = false;
                } else {
                    sb.append(c);
                }
            }
        }
        String last = sb.toString();
        if(!last.isEmpty())
            answer.append(sb.reverse());

        System.out.println(answer);
    }
}

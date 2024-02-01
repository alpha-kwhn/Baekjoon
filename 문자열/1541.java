import java.io.*;
import java.util.*;

public class Baek_1541 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String question = br.readLine();

        String [] tokens = question.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
        // 비숫자 뒤에 연속하는 숫자, 숫자 뒤에 연속하는 비숫자들을 구분하여 배열에 저장

        List<Integer> answer = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int cur = 0;

        for(String token : tokens) {
            if (!Objects.equals(token, "+") && !Objects.equals(token, "-"))
                sb.append(token);
            else if (Objects.equals(token, "-")) {
                cur += Integer.parseInt(sb.toString());
                answer.add(cur);
                sb.delete(0, sb.length());
                cur = 0;
            } else {
                cur += Integer.parseInt(sb.toString());
                sb.delete(0, sb.length());
            }
        }
        if(sb.length() > 0) {
            cur += Integer.parseInt(sb.toString());
            answer.add(cur);
        }

        int count = answer.get(0);
        for(int i=1; i<answer.size(); i++) {
            count -= answer.get(i);
        }
        System.out.println(count);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_1213 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        String []alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
        "T", "U", "V", "W", "X", "Y", "Z"};

        HashMap<String, Integer> dic = new HashMap<>();

        for(int i=0; i<target.length(); i++) {
            String c = String.valueOf(target.charAt(i));
            if(dic.containsKey(c)) {
                int a = dic.get(c);
                dic.put(c, a+1);
            } else {
                dic.put(c, 1);
            }
        }

        StringBuilder answer = new StringBuilder();
        String center = "";
        boolean able = true;

        for(String alpha : alphabet) {
            if(dic.containsKey(alpha)) {
                int num = dic.get(alpha);
                if(num % 2 == 1) {
                    if(center.isEmpty()) {
                        if(num == 1)
                            center = alpha.repeat(num);
                        else {
                            center = alpha;
                            answer.append(alpha.repeat((num-1) / 2));
                        }
                    } else {
                        able = false;
                        break;
                    }
                } else {
                    String add = alpha.repeat(num / 2);
                    answer.append(add);
                }
            }
        }

        if(!able) {
            System.out.println("I'm Sorry Hansoo");
        } else {
            String ans = answer.toString() + center + answer.reverse();
            System.out.println(ans);
        }
    }
}

import java.io.*;
import java.util.Arrays;

public class Baek_9996 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String pattern = br.readLine();
        String [] words = pattern.split("");

        for(int i=0; i<N; i++) {
            String word = br.readLine();
            String[] tmp = word.split("");

            if(pattern.length()-1 > word.length())
                System.out.println("NE");

            else {
                String able = "DA";

                int left = 0;
                int right = words.length - 1;

                int head = 0;
                int tail = tmp.length - 1;

                while (left < right) {
                    String a = words[left];
                    String b = words[right];

                    String a1 = tmp[head];
                    String b1 = tmp[tail];

                    if (!a.equals("*") && a.equals(a1)) {
                        left++;
                        head++;
                    } else if (!a.equals("*")) {
                        able = "NE";
                        break;
                    }

                    if (!b.equals("*") && b.equals(b1)) {
                        right--;
                        tail--;
                    } else if (!b.equals("*")) {
                        able = "NE";
                        break;
                    }
                }
                System.out.println(able);
            }
        }
    }
}

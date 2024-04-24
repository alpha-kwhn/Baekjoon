import java.io.*;
public class Baek_1522 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        char [] arr = target.toCharArray();

        int a_count = 0;
        int b_count = 0;

        for (char c : arr) {
            if (c == 'a')
                a_count++;
            else
                b_count++;
        }

        String compare_a = "a".repeat(a_count);
        String compare_b = "b".repeat(b_count);

        int answer = Integer.MAX_VALUE;
        for(int i=0; i<=arr.length-compare_a.length(); i++) {
            int count = 0;
            for(int j=0; j<compare_a.length(); j++) {
                if (compare_a.charAt(j) != arr[i+j]) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
        }

        for(int i=0; i<=arr.length-compare_b.length(); i++) {
            int count = 0;
            for(int j=0; j<compare_b.length(); j++) {
                if (compare_b.charAt(j) != arr[i+j]) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
        }

        System.out.println(answer);
    }
}

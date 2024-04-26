import java.io.*;
import java.util.*;
public class Baek_12933 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String duck = br.readLine();
        int[] checked = new int[duck.length()];
        char[] target = {'q', 'u', 'a', 'c', 'k'};

        int answer = 0;
        int cnt = 1;
        int total = 0;
        while(true) {
            ArrayList<Integer> arr = new ArrayList<>();
            boolean found = false;
            int idx = 0;
            for (int i = 0; i < duck.length(); i++) {
                if(duck.charAt(i) == target[idx] && checked[i] == 0) {
                    arr.add(i);
                    idx++;
                    if(idx == 5) {
                        found = true;
                        idx = 0;
                        for (Integer integer : arr) {
                            checked[integer] = cnt;
                            total++;
                            arr = new ArrayList<>();
                        }
                    }
                }
            }

            if(!found)
                break;
            else {
                answer++;
                cnt++;
            }
        }

        if(total < duck.length())
            System.out.println(-1);
        else
            System.out.println(answer);
    }
}

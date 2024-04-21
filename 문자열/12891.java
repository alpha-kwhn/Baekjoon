import java.util.*;
import java.io.*;
public class Baek_12891 {
    static int A;
    static int C;
    static int G;
    static int T;
    static int s_num;
    static int t_num;
    static char[] arr;
    static Map<Character, Integer> map = new HashMap<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s_num = Integer.parseInt(st.nextToken());
        t_num = Integer.parseInt(st.nextToken());

        String target = br.readLine();
        arr = target.toCharArray();

        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map.put('A', A);
        map.put('C', C);
        map.put('T', T);
        map.put('G', G);

        int[] idx = new int[4];
        StringBuilder sb = new StringBuilder(target.substring(0, t_num));
        for(int i=0; i<t_num; i++) {
            if(sb.charAt(i) == 'A')
                idx[0]++;
            else if(sb.charAt(i) == 'C')
                idx[1]++;
            else if(sb.charAt(i) == 'T')
                idx[2]++;
            else
                idx[3]++;
        }

        for(int i=t_num-1; i<arr.length; i++) {
            if(idx[0] >= map.get('A') && idx[1] >= map.get('C') && idx[2] >=map.get('T') && idx[3] >= map.get('G'))
                answer++;
            char c = arr[i-t_num+1];
            if(c == 'A')
                idx[0]--;
            else if(c == 'C')
                idx[1]--;
            else if(c == 'T')
                idx[2]--;
            else
                idx[3]--;

            if(i < arr.length-1) {
                char add = arr[i+1];
                if(add == 'A')
                    idx[0]++;
                else if(add == 'C')
                    idx[1]++;
                else if(add == 'T')
                    idx[2]++;
                else
                    idx[3]++;
            }
        }

        System.out.println(answer);
    }
}

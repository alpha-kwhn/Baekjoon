import java.io.*;
import java.util.*;
public class Baek_1759 {
    static int L;
    static int C;
    static String[] arr;
    static ArrayList<String> answer = new ArrayList<>();
    static String aeiou = "aeiou";
    public static void combination(StringBuilder sb, int depth, int start, boolean[] visited) {
        if(depth == L) {
            String ans = sb.toString();
            int a = 0;
            int b = 0;
            for(int i=0; i<ans.length(); i++) {
                char c = ans.charAt(i);
                if(aeiou.contains(String.valueOf(c)))
                    a++;
                else
                    b++;
            }
            if(a >= 1 && b >= 2)
                answer.add(sb.toString());
            return;
        }

        for(int i=start; i<C; i++) {
            if(!visited[i]) {
                sb.append(arr[i]);
                visited[i] = true;
                combination(sb, depth+1, i+1, visited);
                visited[i] = false;
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new String[C];

        for(int i=0; i<C; i++)
            arr[i] = st.nextToken();

        Arrays.sort(arr);

        combination(new StringBuilder(), 0, 0, new boolean[C]);

        for(String str : answer)
            System.out.println(str);

    }
}

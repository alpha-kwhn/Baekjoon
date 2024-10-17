import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_2529 {
    static ArrayList<String> answer = new ArrayList<>();
    static String[] marks;
    public static void permutation(int r, int depth, ArrayList<Integer> sign, boolean[] visited, int recent) {
        if(depth == r) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<r; i++)
                sb.append(sign.get(i));
            answer.add(sb.toString());
            return;
        }

        for(int i=0; i<10; i++) {
            if(!visited[i]) {
                if(depth >= 1 && marks[depth-1].equals(">") && i < recent) {
                    visited[i] = true;
                    sign.add(i);
                    permutation(r, depth + 1, sign, visited, i);
                    sign.remove(depth);
                    visited[i] = false;
                } else if(depth >= 1 && marks[depth-1].equals("<") && i > recent) {
                    visited[i] = true;
                    sign.add(i);
                    permutation(r, depth + 1, sign, visited, i);
                    sign.remove(depth);
                    visited[i] = false;
                } else if(depth == 0) {
                    visited[i] = true;
                    sign.add(i);
                    permutation(r, depth + 1, sign, visited, i);
                    sign.remove(depth);
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        marks = new String[k];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++)
            marks[i] = st.nextToken();

        permutation(k+1, 0, new ArrayList<>(), new boolean[10], -1);
        Collections.sort(answer);

        System.out.println(answer.get(answer.size()-1));
        System.out.println(answer.get(0));
    }
}

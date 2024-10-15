import java.util.*;
import java.io.*;
public class Baek_11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        b--;
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=1; i<=a; i++)
            arr.add(i);

        while(arr.size() > 1) {
            sb.append(arr.get((idx+b) % arr.size()));
            sb.append(", ");
            idx = (idx+b) % arr.size();
            arr.remove((idx) % arr.size());
        }

        sb.append(arr.get(0));
        sb.append(">");

        System.out.println(sb);
    }
}

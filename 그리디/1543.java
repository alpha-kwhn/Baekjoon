import java.io.*;
public class Baek_1543 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        String find = br.readLine();

        int idx = 0;
        int answer = 0;
        while(idx <= target.length()-find.length()) {
            String search = target.substring(idx, idx+find.length());
            if(!search.equals(find))
                idx++;
            else {
                idx += find.length();
                answer++;
            }
        }
        System.out.println(answer);
    }
}

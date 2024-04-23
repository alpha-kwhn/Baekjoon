import java.io.*;

public class Baek_2607 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String target = br.readLine();
        int[] maps = new int[26];

        for(int i=0; i<target.length(); i++) {
            char c = target.charAt(i);
            maps[c - 'A']++;
        }

        int answer = 0;

        for(int i=0; i<N-1; i++) {
            String search = br.readLine();
            int[] test = maps.clone();

            if(Math.abs(search.length() - target.length()) >= 2)
                continue;

            int count = 0;
            for(int j=0; j<search.length(); j++) {
                char c = search.charAt(j);
                if(test[c - 'A'] > 0) {
                    test[c - 'A']--;
                    count++;
                }
            }

            // 여기서 조건 처리가 문제의 핵심
            if(target.length() + 1 == search.length()) {
                if (count == target.length())
                    answer++;
            } else if(target.length() == search.length()) {
                if (count == target.length() - 1 || count == target.length())
                    answer++;
            } else if(target.length() == search.length()+1) {
                if (count == search.length())
                    answer++;
            }
        }
        System.out.println(answer);
    }
}

import java.util.*;
import java.io.*;
public class Baek_2668 {
    static int N;
    static int answer = 0;
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> maps = new HashMap<>();

        for(int i=1; i<=N; i++) {
            int j = Integer.parseInt(br.readLine());
            maps.put(i, j);
        }

        while(true) {
            ArrayList<Integer> arr = new ArrayList<>(maps.keySet());
            ArrayList<Integer> test = new ArrayList<>(maps.values());
            boolean found = false;

            for (int num : arr) {
                if (!test.contains(num)) {
                    maps.remove(num);
                    found = true;
                }
            }

            if(!found) {
                answer = arr.size();
                Collections.sort(arr);
                result = arr;
                break;
            }
        }

        System.out.println(answer);

        for(int i=0; i<answer; i++)
            System.out.println(result.get(i));
    }
}

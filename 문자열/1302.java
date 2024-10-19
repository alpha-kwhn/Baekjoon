import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_1302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TreeMap<String, Integer> library = new TreeMap<>(Collections.reverseOrder());

        for(int i=0; i<N; i++) {
            String book = br.readLine();
            library.put(book, library.getOrDefault(book, 0) + 1);
        }
        int maxi = 0;
        String key = "";
        for(Map.Entry<String, Integer> entry : library.entrySet()) {
            if(entry.getValue() > maxi) {
                maxi = entry.getValue();
                key = entry.getKey();
            } else if(entry.getValue().equals(maxi)) {
                String[] tmp = new String[2];
                tmp[0] = key;
                tmp[1] = entry.getKey();
                Arrays.sort(tmp);
                key = tmp[0];
            }
        }

        System.out.println(key);
    }
}

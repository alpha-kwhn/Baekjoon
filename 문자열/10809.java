import java.util.*;
import java.io.*;

public class Baek_10809 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        char [] alpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
                    'v', 'w', 'x', 'y', 'z'};
        int [] nums = new int[26];
        Arrays.fill(nums, -1);

        for(int i=0; i<alpha.length; i++)
            nums[i] = target.indexOf(alpha[i]);

        Arrays.stream(nums).forEach(e -> System.out.print(e + " "));
    }
}

import java.util.*;
import java.io.*;

public class Baek_2885 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int[] chocolate = new int[21];

        int size = 0;
        int count = 0;
        int idx = 0;

        for(int i=0; i<=20; i++)
            chocolate[i] = (int)(Math.pow(2, i));

        for(int i=0; i<=20; i++) {
            if (chocolate[i] >= K) {
                size = chocolate[i];
                idx = i;
                break;
            }
        }

        if(size == K)
            System.out.println(size + " " + 0);
        else {
            if(K % 2 == 1)
                System.out.println(size + " " + idx);
            else {
                int maxi = chocolate[--idx];
                int left = K - maxi;
                count = 1;
                while(idx >= 1) {
                    if(chocolate[idx-1] < left) {
                        maxi += chocolate[idx-1];
                        left -= chocolate[idx-1];
                        count++;
                        idx--;
                    } else if(chocolate[idx-1] == left) {
                        count++;
                        break;
                    } else {
                        idx--;
                        count++;
                    }
                }
                System.out.println(size + " " + count);
            }
        }
    }
}

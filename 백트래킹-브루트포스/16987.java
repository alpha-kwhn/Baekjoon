import java.util.*;
import java.io.*;

public class Baek_16987 {
    static int [][] egg;
    static int N;
    static int answer = 0;
    static void permutation(int recent, int [][] test) {
        if(recent == test.length) {
            int zero = 0;
            for (int[] ints : test) {
                if (ints[0] <= 0)
                    zero += 1;
            }
            answer = Math.max(answer, zero);
            return;
        }
        if(test[recent][0] <= 0 || allBreak(Arrays.copyOf(test, test.length), recent))
            permutation(recent+1, test);
        else {
            for (int i = 0; i < test.length; i++) {
                if(i != recent && test[i][0] > 0) {
                    test[i][0] -= test[recent][1];
                    test[recent][0] -= test[i][1];
                    permutation(recent+1, test);
                    test[i][0] += test[recent][1];
                    test[recent][0] += test[i][1];
                }
            }
        }
    }
    static boolean allBreak(int[][] arr, int current) {
        boolean found = true;
        for(int i=0; i<arr.length; i++) {
            if(arr[i][0] > 0 && i != current) {
                found = false;
                break;
            }
        }
        return found;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        egg = new int[N][2];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            egg[i][0] = Integer.parseInt(st.nextToken());
            egg[i][1] = Integer.parseInt(st.nextToken());
        }

        permutation(0, egg);

        System.out.println(answer);
    }
}

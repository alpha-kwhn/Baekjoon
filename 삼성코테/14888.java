import java.io.*;
import java.util.*;

public class Baek_14888 {
    static int N;
    static int mini = Integer.MAX_VALUE;
    static int maxi = Integer.MIN_VALUE;
    static int [] numbers;
    static String [] operator;
    public static void permutation(int depth, int r, boolean [] checked, ArrayList<String> arr) {
        if(depth == r) {
            int sums = numbers[0];
            for(int i=0; i<arr.size(); i++) {
                if(arr.get(i).equals("+"))
                    sums += numbers[i+1];
                else if(arr.get(i).equals("-"))
                    sums -= numbers[i+1];
                else if(arr.get(i).equals("*"))
                    sums *= numbers[i+1];
                else
                    sums /= numbers[i+1];
            }
            if(maxi == Integer.MIN_VALUE && mini == Integer.MAX_VALUE) {
                maxi = sums;
                mini = sums;
            } else {
                if(maxi > sums)
                    mini = Math.min(mini, sums);
                else
                    maxi = sums;
            }
            return;
        }

        for(int i=0; i<N-1; i++) {
            if(!checked[i]) {
                checked[i] = true;
                arr.add(operator[i]);
                permutation(depth+1, r, checked, arr);
                arr.remove(arr.size()-1);
                checked[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        operator = new String[N-1];
        String [] lis = new String[]{"+", "-", "*", "/"};

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<4; i++) {
            int cnt = Integer.parseInt(st.nextToken());
            sb.append(lis[i].repeat(cnt));
        }

        operator = sb.toString().split("");

        permutation(0, N-1, new boolean[N-1], new ArrayList<>());

        System.out.println(maxi);
        System.out.println(mini);
    }
}

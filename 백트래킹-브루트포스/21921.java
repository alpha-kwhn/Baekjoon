import java.io.*;
import java.util.*;
public class Baek_21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int [] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int maxi = 0;
        int next = X;
        int amount = 1;

        for(int i=0; i<X; i++)
            maxi += arr[i];

        int recent = maxi;
        for(int i=1; i<=N-X; i++) {
            recent -= arr[i-1];
            recent += arr[next];
            if(recent > maxi) {
                maxi = recent;
                amount = 1;
            } else if(recent == maxi)
                amount++;
            next = i+X;
        }

        if(maxi > 0) {
            System.out.println(maxi);
            System.out.println(amount);
        } else
            System.out.println("SAD");

    }
}

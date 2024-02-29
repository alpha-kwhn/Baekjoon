import java.util.*;
import java.io.*;
public class Baek_14003 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        long [] arr = new long[N+1];
        ArrayList<Long> answer = new ArrayList<>();
        long [][] results = new long[N+1][2];

        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++)
            arr[i] = Long.parseLong(st.nextToken());

        for(int i=1; i<N+1; i++) {
            int left = -1;
            int right;
            int mid;

            if(answer.isEmpty()) {
                answer.add(arr[i]);
                results[i][0] = i;
                results[i][1] = arr[i];
                continue;
            }

            if(arr[i] > answer.get(answer.size()-1)) {
                answer.add(arr[i]);
                results[i][0] = answer.size();
                results[i][1] = arr[i];
            } else {
                right = answer.size();
                while(left+1<right) {
                    mid = (left + right) / 2;
                    if(answer.get(mid) < arr[i])
                        left = mid;
                    else
                        right = mid;
                }
                answer.set(right, arr[i]);
                results[i][0] = right+1;
                results[i][1] = arr[i];
            }
        }

        ArrayList<Long> total = new ArrayList<>();
        int dd = answer.size();
        for(int i=N; i>0; i--) {
            if(results[i][0] == dd) {
                total.add(results[i][1]);
                dd--;
            }
        }

        System.out.println(total.size());
        for(int i=total.size()-1; i>=0; i--)
            System.out.print(total.get(i) + " ");
    }
}

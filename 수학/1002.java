import java.util.*;
import java.io.*;
public class Baek_1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            if(infinity(x, y, r1, x2, y2, r2))
                System.out.println(-1);
            else
                System.out.println(getInfo(x, y, r1, x2, y2, r2));
        }
    }

    public static boolean infinity(int x1, int y1, int r1, int x2, int y2, int r2) {
        return x1 == x2 && y1 == y2 && r1 == r2;
    }

    public static int getInfo(int x1, int y1, int r1, int x2, int y2, int r2) {
        double distance = (Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2)));

        if(distance + r1 < r2)
            return 0;
        if(distance + r2 < r1)
            return 0;
        if(distance + r1 == r2)
            return 1;
        if(distance + r2 == r1)
            return 1;
        if(r1 >= distance)
            return 2;
        if(r2 >= distance)
            return 2;
        if((r1+r2) == distance)
            return 1;
        else if((r1+r2) > distance)
            return 2;
        else
            return 0;
    }
}

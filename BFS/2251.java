import java.util.*;
import java.io.*;

public class Baek_2251 {
    static int A;
    static int B;
    static int C;
    static HashMap<Integer, Integer> arr = new HashMap<>();
    static int[] lis = new int[3];
    static TreeSet<Integer> answer = new TreeSet<>();
    static boolean [][][] visited;
    static class Water {
        int a;
        int b;
        int c;
        Water(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[C+1][C+1][C+1];

        lis[0] = A;
        lis[1] = B;
        lis[2] = C;

        Queue<Water> queue = new LinkedList<>();
        queue.add(new Water(0, 0, C));

        while(!queue.isEmpty()) {
            Water water = queue.poll();
            int a = water.a;
            int b = water.b;
            int c = water.c;

            if(!visited[a][b][c]) {
                visited[a][b][c] = true;
                if(a == 0)
                    answer.add(c);
                if(a+b <= lis[0]) // b -> a
                    queue.add(new Water(a+b, 0, c));
                else
                    queue.add(new Water(lis[0], b-(lis[0]-a), c));
                if(a+c <= lis[0]) // c -> a
                    queue.add(new Water(a+c, b, 0));
                else
                    queue.add(new Water(lis[0], b, c-(lis[0]-a)));
                if(b+a <= lis[1])
                    queue.add(new Water(0, a+b, c));
                else
                    queue.add(new Water(a-(lis[1]-b), lis[1], c));
                if(b+c <= lis[1])
                    queue.add(new Water(a, b+c, 0));
                else
                    queue.add(new Water(a, lis[1], c-(lis[1]-b)));
                if(c+a <= lis[2])
                    queue.add(new Water(0, b, c+a));
                else
                    queue.add(new Water(a-(lis[2]-c), b, lis[2]));
                if(c+b <= lis[2])
                    queue.add(new Water(a, 0, c+b));
                else
                    queue.add(new Water(a, b-(lis[2]-c), lis[2]));
            }
        }

        for (Integer integer : answer)
            System.out.print(integer + " ");
    }
}

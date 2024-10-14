import java.util.*;
import java.io.*;
public class Baek_4673 {
    static boolean[] arr;

    public static void find(int number) {
        while(number <= 10000) {
            String tmp = String.valueOf(number);
            char[] c = tmp.toCharArray();
            int target = number;
            for(int i=0; i<c.length; i++)
                target += (c[i] - '0');
            if(target > 10000)
                break;
            else {
                arr[target] = true;
                number = target;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        arr = new boolean[10001];

        for(int i=1; i<=10000; i++) {
            if(!arr[i]) {
                System.out.println(i);
                find(i);
            }
        }

    }
}

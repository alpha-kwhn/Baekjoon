import java.io.*;

public class Baek_1515 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();

        int i = 0;
        while(true) {
            i++;
            String test = Integer.toString(i);
            while(!test.isEmpty() && !target.isEmpty()) {
                if(test.charAt(0) == target.charAt(0)) {
                    target = target.substring(1);
                }
                test = test.substring(1);
            }
            if(target.isEmpty()) {
                System.out.println(i);
                break;
            }
        }
    }
}

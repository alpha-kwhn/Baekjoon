import java.io.*;

public class Baek_9086 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        for(int i=0; i<num; i++) {
            String target = br.readLine();
            System.out.println(target.charAt(0) + "" + target.charAt(target.length()-1));
        }
    }
}

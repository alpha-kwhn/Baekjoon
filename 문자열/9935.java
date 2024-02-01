import java.io.*;
public class Baek_9935 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String total = br.readLine();
        String target = br.readLine();

        char [] totalArray = total.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<totalArray.length; i++) {
            sb.append(totalArray[i]);
            if(sb.length() >= target.length()) {
                if(sb.substring(sb.length()-target.length()).equals(target)) {
                    sb.delete(sb.length()-target.length(), sb.length());
                }
            }
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }
}

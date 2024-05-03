import java.io.*;
import java.util.*;
public class Baek_11296 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Map<String, Double> menu = new HashMap<>();
        menu.put("R", 0.45);
        menu.put("G", 0.3);
        menu.put("B", 0.2);
        menu.put("Y", 0.15);
        menu.put("O", 0.1);
        menu.put("W", 0.05);

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            Double price = Double.parseDouble(st.nextToken());
            String color = st.nextToken();
            String coupon = st.nextToken();
            String pay = st.nextToken();

            double discount = menu.get(color);
            price = price * (1-discount);

            if(coupon.equals("C"))
                price = price * (0.95);

            if(pay.equals("C")) {
                String[] tmp = String.valueOf(price).split("\\.");
                tmp[1] = String.format("%.3f", Double.valueOf(tmp[1]));
                if(tmp[1].charAt(1) - '0' == 5) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < tmp[1].length(); j++) {
                        if (j == 2)
                            sb.append("0");
                        else
                            sb.append(tmp[1].charAt(j));
                    }
                    String target = tmp[0] + "." + sb;
                    target += "0".repeat(2-sb.length());
                    System.out.println("$"+target);
                } else {
                    price = Math.round(price * 10) / 10.0;
                    System.out.println("$"+price + "0");
                }
            } else {
                String[] tmp = String.valueOf(price).split("\\.");
                tmp[1] = String.format("%.3f", Double.valueOf(tmp[1]));
                if(tmp[1].charAt(2) - '0' == 5) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < tmp[1].length(); j++) {
                        if (j == 1)
                            sb.append("0");
                        else
                            sb.append(tmp[1].charAt(j));
                    }
                    String target = tmp[0] + "." + sb;
                    target += "0".repeat(2-sb.length());
                    System.out.println("$"+target);
                } else {
                    price = Math.round(price * 100) / 100.0;
                    String[] dd = String.valueOf(price).split("\\.");
                    dd[1] += "0".repeat(2- dd[1].length());
                    StringBuilder sb = new StringBuilder();
                    sb.append(dd[0]);
                    sb.append(".");
                    sb.append(dd[1]);
                    System.out.println("$"+sb);
                }
            }
        }
    }
}

import java.util.*;
import java.io.*;
public class Baek_25329 {
    static class Person implements Comparable<Person> {
        int bill;
        String name;
        Person(String name, int bill) {
            this.bill = bill;
            this.name = name;
        }
        @Override
        public int compareTo(Person ps) {
            if(this.bill != ps.bill)
                return ps.bill - this.bill;
            return this.name.compareTo(ps.name);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Map<String, Integer> dict = new HashMap<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String time = st.nextToken();
            String student = st.nextToken();

            String[] info = time.split(":");

            int hour = Integer.parseInt(info[0]);
            int minute = Integer.parseInt(info[1]);

            if(!dict.containsKey(student))
                dict.put(student, (60 * hour) + minute);
            else
                dict.put(student, dict.get(student) + (60 * hour) + minute);
        }

        PriorityQueue<Person> queue = new PriorityQueue<>();
        ArrayList<String> keys = new ArrayList<>(dict.keySet());

        for(String key : keys) {
            int cost = dict.get(key);
            if(cost <= 100)
                queue.add(new Person(key, 10));
            else {
                cost -= 100;
                int basic = cost / 50;
                if(cost % 50 != 0)
                    basic++;

                queue.add(new Person(key, (basic * 3) + 10));
            }
        }

        while(!queue.isEmpty()) {
            Person person = queue.poll();
            System.out.println(person.name + " " + person.bill);
        }
    }
}

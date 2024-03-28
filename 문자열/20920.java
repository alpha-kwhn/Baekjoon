import java.util.*;
import java.io.*;

public class Baek_20920 {
    static class Word implements Comparable<Word>{
        String word;
        int num;
        int lengths;
        Word(String word, int num, int lengths) {
            this.word = word;
            this.num = num;
            this.lengths = lengths;
        }
        @Override
        public int compareTo(Word o) {
            if(this.num > o.num) {
                return -1;
            } else if(this.num == o.num) {
                if(this.lengths > o.lengths) {
                    return -1;
                } else if(this.lengths == o.lengths) {
                    if(this.word.compareTo(o.word) < 0) {
                        return -1;
                    } else
                        return 1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> dic = new HashMap<>();
        PriorityQueue<Word> queue = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String word = st.nextToken();

            if(word.length() >= M) {
                if (dic.containsKey(word)) {
                    int count = dic.get(word) + 1;
                    dic.put(word, count);
                } else {
                    dic.put(word, 1);
                }
            }
        }

        for(String key : dic.keySet())
            queue.add(new Word(key, dic.get(key), key.length()));

        while(!queue.isEmpty()) {
            String target = queue.poll().word;
            bw.write(target);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}

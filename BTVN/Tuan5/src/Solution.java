import java.io.*;
import java.util.*;

public class Solution {
    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        for (int i = 0; i < h1.size(); i++)
            sum1 += h1.get(i);
        for (int i = 0; i < h2.size(); i++)
            sum2 += h2.get(i);
        for (int i = 0; i < h3.size(); i++)
            sum3 += h3.get(i);
        int i1 = 0, i2 = 0, i3 = 0;
        while (true) {
            if (sum1 == sum2  && sum2 == sum3) {
                break;
            }
            int max = Math.max(sum1, Math.max(sum2, sum3));
            if (max == sum1) {
                if (i1 == h1.size()) {
                    break;
                }
                sum1 -= h1.get(i1);
                i1++;
            } else if (max == sum2) {
                if (i2 == h2.size()) {
                    break;
                }
                sum2 -= h2.get(i2);
                i2++;
            } else {
                if (i3 == h3.size()) {
                    break;
                }
                sum3 -= h3.get(i3);
                i3++;
            }
        }
        return sum1;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(bufferedReader.readLine().trim());
        Stack<String> st = new Stack<>();
        StringBuilder s = new StringBuilder("");
        st.push(s.toString());
        String tmp;
        for (int i = 0; i < q; i++) {
            tmp = bufferedReader.readLine().trim();
            int x = Integer.parseInt(tmp.substring(0, 1));
            if (x == 1) {
                tmp = tmp.substring(2, tmp.length());
                s.append(tmp);
                st.push(s.toString());
            } else if (x == 2) {
                int k = Integer.parseInt(tmp.substring(2, tmp.length()));
                s.delete(s.length() - k, s.length());
                st.push(s.toString());
            } else if (x == 3) {
                int k = Integer.parseInt(tmp.substring(2, tmp.length()));
                System.out.println(s.charAt(k - 1));
            } else {
                st.pop();
                s = new StringBuilder(st.peek());
            }
      //System.out.println("Xau la: " + st.peek().toString() + " " + s);
        }
    }
}

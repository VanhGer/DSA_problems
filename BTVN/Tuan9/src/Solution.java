import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine().trim();
    int cnt1 = 1, cnt0 = 1;
    int le = 1, ri = 0;
    int id = 1;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == 'L') {
        if (le == 1 && id == 1) {
          cnt1 += 1;
        }
        else if (le == 1 && id == 0) {
          if (ri == 0) {
            cnt0 += cnt1 + 1;
            cnt1 = 0;
            le = 0;
          } else {
            cnt0++;
            le = 0;
          }
        }
        else if (le == 0 && id == 0) {
          cnt0++;
        }
        else { // le == 0, id = 1
          if (ri == 1) {
            cnt1 += cnt0 + 1;
            cnt0 = 0;
            le = 1;
          }
          else {
            cnt1++;
            le = 1;
          }
        }
      }
      else{
        if (ri == 1 && id == 1) {
          cnt1++;
        } else if (ri == 0 && id == 0) {
          cnt0++;
        } else if (ri == 1 && id == 0) {
          if (le == 0) {
            cnt0 += cnt1 + 1;
            cnt1 = 0;
            ri = 0;
          } else {
            ri = 0;
            cnt0++;
          }
        } else { // ri = 0, id = 1
          if (le == 1) {
            ri = 1;
            cnt1 += cnt0 + 1;
            cnt0 = 0;
          } else {
            ri = 1;
            cnt1++;
          }
        }
      }
      id = 1 - id;
      //System.out.println(cnt1 + " " + cnt0);
    }
    System.out.println(cnt1 + " " + cnt0);
  }
}
import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

class Result {

    /*
     * Complete the 'roadsAndLibraries' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER c_lib
     *  3. INTEGER c_road
     *  4. 2D_INTEGER_ARRAY cities
     */

    private static void dfs(int u, int p, boolean[] visited, List<Integer>[] adj) {
        visited[u] = true;
        if (adj[u] == null) return;
        for (int i = 0; i < adj[u].size(); i++) {
            int v = adj[u].get(i);
            if (v == p) continue;
            if (!visited[v]) dfs(v, u, visited, adj);
        }
    }

    public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        // Write your code here
        long res = 0;
        List<Integer>[] adj = new List[n + 1];
        for (int i = 0; i < cities.size(); i++) {
            int u = cities.get(i).get(0);
            int v = cities.get(i).get(1);
            if (adj[u] == null) adj[u] = new ArrayList<>();
            if (adj[v] == null) adj[v] = new ArrayList<>();
            adj[u].add(v);
            adj[v].add(u);
        }
        if (c_road >= c_lib) {
            res =(long) n;
            res = res *(long) c_lib;
        } else  {
            boolean[] visited = new boolean[n + 1];
            Arrays.fill(visited, false);
            long cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (! visited[i]) {
                    cnt++;
                    dfs(i, i, visited, adj);
                }
            }
            long tmp = n;
            res += (tmp - cnt) * (long)c_road;
            res += cnt * (long)c_lib;
        }
        return res;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int c_lib = Integer.parseInt(firstMultipleInput[2]);

                int c_road = Integer.parseInt(firstMultipleInput[3]);

                List<List<Integer>> cities = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        cities.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                long result = Result.roadsAndLibraries(n, c_lib, c_road, cities);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}

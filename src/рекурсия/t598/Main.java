package рекурсия.t598;

import java.io.*;
import java.util.*;

/**
 * Задача 598
 * Разбить друзей на группы (в каж. группе максимум 5 людей), так, чтобы была максим. большая группа
 * N до 15
 */
public class Main {

    static private MyScanner in;
    static private PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);

        int n = in.nextInt();
        int[][] friendship = in.next2Ints(n, n);

        Solver solver = new Solver(n, friendship);
        int[] ans = solver.solve();

        Set<Integer> set = new HashSet<>();
        for (int an : ans) {
            set.add(an);
        }

        out.println(set.size());
        for(int a : ans) {
            out.print(a + " ");
        }
        out.println();
    }
}

class Solver {
    private int n;
    private int[][] friendship;

    public Solver(int n, int[][] friendship) {
        this.n = n;
        this.friendship = friendship;
    }

    public int[] solve() {
        for (int len = Math.min(5, n); len > 0; len--) {
            Permutation p = new Permutation(len, n - 1);

            int[] friends = p.next();
            while(friends != null) {
                if (areFriends(friends)) {
                    return buildFriends(friends);
                }
                friends = p.next();
            }
        }

        return null; // never happen
    }

    private int[] buildFriends(int[] friends) {
        int[] ans = new int[n];
        for (int friend : friends) {
            ans[friend] = 1;
        }

        int cnt = 2;
        for (int i = 0; i < n; i++) {
            if (ans[i] == 0) {
                ans[i] = cnt++;
            }
        }

        return ans;
    }

    private boolean areFriends(int[] ind) {
        for (int anInd : ind) {
            for (int anInd1 : ind) {
                if (friendship[anInd][anInd1] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

}

class Permutation {
    private int[] perm;
    private int max;
    private boolean gaveNext = false;
    private int pos = 0;

    public Permutation(int length, int max) {
        this.max = max;

        perm = new int[length];
        for (int i = 0; i < length; i++) {
            perm[i] = i;
        }
    }

    public int[] next() {
        if (!gaveNext) {
            gaveNext = true;
            return perm;
        }

        int k = perm.length;
        for (int i = k - 1; i >= 0; --i)
            if (perm[i] < max - k + i + 1)
            {
                ++perm[i];
                for (int j = i + 1; j < k; ++j)
                    perm[j] = perm[j - 1] + 1;
                return perm;
            }
        return null;
    }
}

class MyScanner implements Closeable {

    public static final String CP1251 = "cp1251";

    private final BufferedReader br;
    private StringTokenizer st;
    private String last;

    public MyScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public MyScanner(String path) throws IOException {
        br = new BufferedReader(new FileReader(path));
    }

    public MyScanner(String path, String decoder) throws IOException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(path), decoder));
    }

    public void close() throws IOException {
        br.close();
    }

    public String next() throws IOException {
        while (st == null || !st.hasMoreElements())
            st = new StringTokenizer(br.readLine());
        last = null;
        return st.nextToken();
    }

    public String next(String delim) throws IOException {
        while (st == null || !st.hasMoreElements())
            st = new StringTokenizer(br.readLine());
        last = null;
        return st.nextToken(delim);
    }

    public String nextLine() throws IOException {
        st = null;
        return (last == null) ? br.readLine() : last;
    }

    public boolean hasNext() {
        if (st != null && st.hasMoreElements())
            return true;

        try {
            while (st == null || !st.hasMoreElements()) {
                last = br.readLine();
                st = new StringTokenizer(last);
            }
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    public String[] nextStrings(int n) throws IOException {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++)
            arr[i] = next();
        return arr;
    }

    public String[] nextLines(int n) throws IOException {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++)
            arr[i] = nextLine();
        return arr;
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public int[] nextInts(int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = nextInt();
        return arr;
    }

    public Integer[] nextIntegers(int n) throws IOException {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = nextInt();
        return arr;
    }

    public int[][] next2Ints(int n, int m) throws IOException {
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                arr[i][j] = nextInt();
        return arr;
    }

    public long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public long[] nextLongs(int n) throws IOException {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++)
            arr[i] = nextLong();
        return arr;
    }

    public long[][] next2Longs(int n, int m) throws IOException {
        long[][] arr = new long[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                arr[i][j] = nextLong();
        return arr;
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next().replace(',', '.'));
    }

    public double[] nextDoubles(int size) throws IOException {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++)
            arr[i] = nextDouble();
        return arr;
    }

    public double[][] next2Doubles(int n, int m) throws IOException {
        double[][] arr = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                arr[i][j] = nextDouble();
        return arr;
    }

    public boolean nextBool() throws IOException {
        String s = next();
        if (s.equalsIgnoreCase("true") || s.equals("1"))
            return true;

        if (s.equalsIgnoreCase("false") || s.equals("0"))
            return false;

        throw new IOException("Boolean expected, String found!");
    }
}

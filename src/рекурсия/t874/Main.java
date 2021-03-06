package рекурсия.t874;

import java.io.*;
import java.util.*;

/**
 * Задача 874
 * Вывести количество возможных окрасов лампочки
 * 1 <= N, K <= 8, 0 <= M <= 10
 */
public class Main {

    static private MyScanner in;
    static private PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);

        int size = in.nextInt();
        int cols = in.nextInt();
        Graph g = new Graph(size, cols);

        int wires = in.nextInt();
        for (int i = 0; i < wires; i++) {
            g.add(in.nextInt(), in.nextInt());
        }

        out.println(g.solve());
    }
}

class Graph {
    private int size, cols;
    private LinkedList<int[]> g;

    public Graph(int size, int cols) {
        this.size = size;
        this.cols = cols;

        g = new LinkedList<>();
    }

    public void add(int from, int to) {
        g.add(new int[] {from - 1, to - 1});
    }

    public int solve() {
        Vars vars = new Vars(size, cols);
        int cnt = 0;

        int[] next = vars.next();
        while(next != null) {
            cnt += check(next) ? 1 : 0;
            next = vars.next();
        }

        return cnt;
    }

    private boolean check(int[] next) {
        for (int[] p : g) {
            if (next[p[0]] == next[p[1]]) {
                return false;
            }
        }
        return true;
    }
}

class Vars {
    private int size, max;
    private int[] vars;

    public Vars(int size, int max) {
        this.size = size;
        this.max = max;

        vars = new int[size];
        Arrays.fill(vars, 1);
        vars[size - 1] = 0;
    }

    public int[] next() {
        boolean hasNext = false;
        for (int i = size - 1; i >= 0; i--) {
            if (vars[i] == max) {
                vars[i] = 1;
            } else {
                vars[i]++;
                hasNext = true;
                break;
            }
        }

        return hasNext ? vars : null;
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

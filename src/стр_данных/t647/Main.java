package стр_данных.t647;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

/**
 * Задача 647
 * Вывести длину поиска каждого запроса
 * N, M до 65535
 */
public class Main {

    static private MyScanner in;
    static private PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);

        int n = in.nextInt();
        int m = in.nextInt();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, n - i);
        }

        SegmentTree<Integer> tree = new SegmentTree<>(n + m, 0, (a, b) -> a + b);
        for (int i = 0; i < n; i++) {
            tree.set(i, 1);
        }

        while(m-- != 0) {
            int q = in.nextInt();
            int pos = map.get(q);
            int zeroes = (n - pos) - tree.get(pos, n - 1);

            out.print(n - pos - zeroes + " ");

            map.put(q, n);
            tree.set(pos, 0);
            tree.set(n, 1);
            n++;
        }
        out.println();
    }
}

class SegmentTree<T> {

    private int size;
    private T[] tree;
    private T initial;
    private BiFunction<T, T, T> function;

    public SegmentTree(int size, T initial, BiFunction<T, T, T> function) {
        this.size = closest2(size);
        this.initial = initial;
        this.function = function;
        tree = (T[]) new Object[this.size * 2];

        Arrays.fill(tree, this.size, tree.length, initial);
        for (int i = this.size - 1; i > 0; i--) {
            tree[i] = function.apply(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    private boolean isLowLevel(int level) {
        return level >= size;
    }

    public T get(int from, int to) {
        return get(0, size - 1, 1, from, to);
    }

    public T get(int lo, int hi, int i, int from, int to) {
        if (from > to)
            return initial;
        if (lo == from && hi == to)
            return tree[i];

        int mid = (lo + hi) / 2;
        return function.apply(get(lo, mid, 2 * i, from, Math.min(to, mid)),
                get(mid + 1, hi, 2 * i + 1, Math.max(from, mid + 1), to));
    }

    public void set(int i, T v) {
        i += size;
        tree[i] = v;
        update(i / 2);
    }

    private void update(int i) {
        while (i != 0) {
            tree[i] = function.apply(tree[2 * i], tree[2 * i + 1]);
            i /= 2;
        }
    }

    private int closest2(int a) {
        return 1 << (int) (Math.log(a - 1) / Math.log(2) + 1.0000000000000007);
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

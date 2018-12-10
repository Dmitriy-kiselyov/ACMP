package графы.t308;

import java.io.*;
import java.util.*;

/**
 * Задача 308
 * Требуется добиться, чтобы в самом большом ведре был заданный объем воды
 * 1000 ≥ B1 > B2 > B3 > 0
 */
public class Main {

    static private MyScanner in;
    static private PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);

        int b1 = in.nextInt();
        int b2 = in.nextInt();
        int b3 = in.nextInt();
        int b1Exp = in.nextInt();

        Solver solver = new Solver(b1, b2, b3, b1Exp);
        int ans = solver.solve();

        out.println(ans == -1 ? "IMPOSSIBLE" : ans);
    }

}

class Solver {
    private int bExp;
    private int[] b;
    private HashMap<String, Integer> variants;
    private Queue<int[]> queue;

    public Solver(int b1, int b2, int b3, int bExp) {
        b = new int[]{b1, b2, b3};
        this.bExp = bExp;
    }

    public int solve() {
        variants = new HashMap<>();
        queue = new LinkedList<>();

        int[] first = new int[] {b[0], 0, 0};
        variants.put(makeKey(first), 0);
        queue.add(first);

        while(!queue.isEmpty()) {
            int[] a = queue.poll();
            if (a[0] == bExp) {
                return variants.get(makeKey(a));
            } else {
                next(a, variants.get(makeKey(a)));
            }
        }

        return -1;
    }

    private void next(int[] a, int step) {
        ArrayList<int[]> next = buildNext(a);

        for(int[] var: next) {
            String key = makeKey(var);
            if (!variants.containsKey(key)) {
                variants.put(key, step + 1);
                queue.add(var);
            }
        }
    }

    private String makeKey(int[] a) {
        return a[0] + "|" + a[1] + "|" + a[2];
    }

    private ArrayList<int[]> buildNext(int[] a) {
        ArrayList<int[]> variants = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i != j) {
                    int canPoar = Math.min(b[j] - a[j], a[i]);
                    if (canPoar != 0) {
                        int[] aa = Arrays.copyOf(a, a.length);
                        aa[i] -= canPoar;
                        aa[j] += canPoar;
                        variants.add(aa);
                    }
                }
            }
        }

        return variants;
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

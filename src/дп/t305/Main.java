package дп.t305;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Задача 305
 * Подсчитать максимальный размер последнего корабля
 * N,M до 200, K до 10
 */
public class Main {

    static private MyScanner in;
    static private PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        boolean[][] f = new boolean[n][m];
        for (boolean[] arr: f) {
            Arrays.fill(arr, true);
        }
        while(k-- != 0) {
            fill(f, in.nextInt() - 1, in.nextInt() - 1, in.nextInt() - 1, in.nextInt() - 1);
        }

        int maxCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (f[i][j]) {
                    int maxRight = lastTrueRight(f, i, j);
                    if (maxRight - j + 1 == 0) {
                        continue;
                    }
                    maxCnt = Math.max(maxCnt, maxRight - j + 1);

                    for (k = i + 1; k < n; k++) {
                        maxRight = Math.min(maxRight, lastTrueRight(f, k, j));
                        if (maxRight - j + 1 == 0) {
                            break;
                        }

                        maxCnt = Math.max(maxCnt, (maxRight - j + 1) * (k - i + 1));
                    }
                }
            }
        }

        out.println(maxCnt);
    }

    static int lastTrueRight(boolean[][] f, int i, int j) {
        boolean[] arr = f[i];
        for (int k = j + 1; k < arr.length; k++) {
            if (!arr[k]) {
                return k - 1;
            }
        }

        return arr.length - 1;
    }

    static void fill(boolean[][] f, int x1, int y1, int x2, int y2) {
        x1 = Math.max(x1 - 1, 0);
        y1 = Math.max(y1 - 1, 0);
        x2 = Math.min(x2 + 1, f.length - 1);
        y2 = Math.min(y2 + 1, f[0].length - 1);

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                f[i][j] = false;
            }
        }
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

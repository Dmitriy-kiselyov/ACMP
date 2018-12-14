package арифм.t371;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Predicate;

/**
 * Задача 371
 * Найти дружественные числа
 * M и N до 10^6
 */
public class Main {

    static private MyScanner in;
    static private PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);

        int[][] all = new int[][] {
                {220, 284},
                {1184, 1210},
                {2620, 2924},
                {5020, 5564},
                {6232, 6368},
                {10744, 10856},
                {12285, 14595},
                {17296, 18416},
                {63020, 76084},
                {66928, 66992},
                {67095, 71145},
                {69615, 87633},
                {79750, 88730},
                {100485, 124155},
                {122265, 139815},
                {122368, 123152},
                {141664, 153176},
                {142310, 168730},
                {171856, 176336},
                {176272, 180848},
                {185368, 203432},
                {196724, 202444},
                {280540, 365084},
                {308620, 389924},
                {319550, 430402},
                {356408, 399592},
                {437456, 455344},
                {469028, 486178},
                {503056, 514736},
                {522405, 525915},
                {600392, 669688},
                {609928, 686072},
                {624184, 691256},
                {635624, 712216},
                {643336, 652664},
                {667964, 783556},
                {726104, 796696},
                {802725, 863835},
                {879712, 901424},
                {898216, 980984}
        };

        int lo = in.nextInt();
        int hi = in.nextInt();
        int cnt = 0;

        for (int[] a : all) {
            if(lo <= a[0] && a[1] <= hi) {
                cnt++;
                out.println(a[0] + " " + a[1]);
            }
        }

        if (cnt == 0) {
            out.println("Absent");
        }
    }

    static void printAll(int max) {
        out.println("int[][] all = new int[][] {");

        for (int a = 2; a <= max; a++) {
            int sum = sumOfDividors(a);
            if (sum <= a || sum > max) {
                continue;
            }

            if (sumOfDividors(sum) == a) {
                out.println("{" + a + ", " + sum + "},");
            }
        }

        out.println("};");
    }

    static int sumOfDividors(int n) {
        int sum = 1;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;

                int rev = n / i;
                if (rev != i) {
                    sum += n / i;
                }
            }
        }

        return sum;
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

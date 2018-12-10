package арифм;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.Predicate;

/**
 * Задача 832
 * Можно ли сыграть в игру
 * N до 1000, 0 <= A,B,C <= max long
 */
public class Main {

    static private MyScanner in;
    static private PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);

        int n = in.nextInt();
        while(n-- != 0) {
            out.println(solve(in.nextLong(), in.nextLong(), in.nextLong()) ? "Yes" : "No");
        }

//        for (int a = 0; a < 10; a++) {
//            for (int b = 0; b < 10; b++) {
//                for (int c = 0; c < 10; c++) {
//                    if (!solve(a, b, c)) {
//                        System.out.println(a + " " + b + " " + c);
//                    }
//                }
//            }
//        }
    }

//    static boolean solve(int a, int b, int c) {
//        int[] arr = new int[] {a, b, c};
//
//        while(true) {
//            if (stop(arr)) {
//                return true;
//            }
//
//            if (isBad(arr) || isBad2(arr) || isBad3(arr)) {
//                return false;
//            }
//
//            int maxI = 0, minI = 0;
//            for (int i = 1; i < 3; i++) {
//                if (arr[i] > arr[maxI]) {
//                    maxI = i;
//                }
//            }
//            for (int i = 1; i < 3; i++) {
//                if (arr[i] <= arr[minI]) {
//                    minI = i;
//                }
//            }
//
//            int restI = 3 - maxI - minI;
//
//            if (restI == minI || minI == maxI || restI == maxI) {
//                System.out.println("ERROR");
//                return false;
//            }
//
//            if (arr[maxI] == 0 || arr[restI] == 0) {
//                return false;
//            }
//
//            arr[minI]++;
//            arr[maxI]--;
//            arr[restI]--;
//        }
//    }
//
//    static boolean stop(int[] arr) {
//        int zeroes = 0;
//        long rest = -1;
//        for (long l : arr) {
//            if (l == 0) {
//                zeroes++;
//            } else {
//                rest = l;
//            }
//        }
//
//        return zeroes == 2 && rest == 1;
//    }
//
//    static boolean isBad(int[] arr) {
//        int zeroes = 0;
//        long rest = -1;
//        for (long l : arr) {
//            if (l == 0) {
//                zeroes++;
//            } else {
//                rest = l;
//            }
//        }
//
//        return zeroes == 2 && rest > 1;
//    }
//
//    static boolean isBad2(int[] arr) {
//        for (int a : arr) {
//            if (a != 1) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    static boolean isBad3(int[] arr) {
//        for (int a : arr) {
//            if (a != 0) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    static boolean solve(long a, long b, long c) {
        long[] arr = new long[] {a, b, c};

        if (every(arr, a1 -> a1 % 2 == 0)) {
            return false;
        };
        if (every(arr, a1 -> a1 % 2 == 1)) {
            return false;
        };

        int zeroes = 0;
        long rest = -1;
        for (long l : arr) {
            if (l == 0) {
                zeroes++;
            } else {
                rest = l;
            }
        }
        if (zeroes == 2 && rest > 1) {
            return false;
        }

        return true;
    }

    static boolean every(long[] arr, Predicate<Long> pred) {
        for (long a : arr) {
            if (!pred.test(a)) {
                return false;
            }
        }
        return true;
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

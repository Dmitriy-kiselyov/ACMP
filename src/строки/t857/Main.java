package строки.t857;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

/**
 * Задача 857
 * Найти количество суперпрефиксов для каждого слова
 * кол-во символов в словах до 10^6, количество символов в префиксах до 10^6
 */
public class Main {

    static private MyScanner in;
    static private PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);

        int n = in.nextInt();
        String[] words = in.nextStrings(n);
        int m = in.nextInt();
        String[] prefixes = in.nextStrings(m);

        HashMap<String, Integer> map = new HashMap<>();
        for (String pref : prefixes) {
            map.put(pref, 0);
        }

        for (String word : words) {
            long hashLeft = 0;
            long hashRight = 0;
            long p = 31;

            long leftP = 1;
            for (int i = 0; i < word.length(); i++) {
                int j = word.length() - i - 1;

                hashLeft += leftP * word.charAt(i);
                leftP *= p;

                hashRight *= p;
                hashRight += word.charAt(j);

                if (hashLeft == hashRight) {
                    String w = word.substring(0, i + 1);

                    Integer cnt = map.get(w);
                    if (cnt != null) {
                        map.put(w, cnt + 1);
                    }
                }
            }
        }

        for (String pref : prefixes) {
            out.println(map.get(pref));
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

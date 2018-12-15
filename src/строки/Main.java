package строки;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Задача 199
 * Сократить дробь из римских чисел
 */
public class Main {

    static private MyScanner in;
    static private PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);

        try {
            String[] nums = in.next().split("/");
            int a = toDec(nums[0]);
            int b = toDec(nums[1]);
            int g = gcd(a, b);
            a /= g;
            b /= g;

            if (b == 1) {
                out.println(toRim(a));
            } else {
                out.println(toRim(a) + "/" + toRim(b));
            }
        } catch (Exception e) {
            out.println("ERROR");
        }
    }

    final static String RIM = "MDCLXVI";
    final static int[] RIM_DEC = new int[] {1000, 500, 100, 50, 10, 5, 1};

    static class Pair {
        String s;
        int n;

        public Pair(String s, int n) {
            this.s = s;
            this.n = n;
        }
    }

    final static Pair[] DEC_RIM = new Pair[] {
            new Pair("M", 1000),
            new Pair("CM", 900),
            new Pair("D", 500),
            new Pair("CD", 400),
            new Pair("C", 100),
            new Pair("XC", 90),
            new Pair("L", 50),
            new Pair("XL", 40),
            new Pair("X", 10),
            new Pair("IX", 9),
            new Pair("V", 5),
            new Pair("I", 1)
    };

    static String toRim(int n) {
        String s = "";
        for (int i = 0; i < DEC_RIM.length; i++) {
            while(n - DEC_RIM[i].n >= 0) {
                s += DEC_RIM[i].s;
                n -= DEC_RIM[i].n;
            }
        }

        return s;
    }

    static int compRim(char a, char b) {
        int ai = RIM.indexOf(a);
        int bi = RIM.indexOf(b);

        return Integer.compare(bi, ai);
    }

    static int toDec(char ch) {
        return RIM_DEC[RIM.indexOf(ch)];
    }

    static int toDec(String rim) {
        int[] s = new int[rim.length()];

        for (int i = 0; i < s.length; i++) {
            char ch = rim.charAt(i);
            if (i == 0) {
                s[i] = toDec(ch);
            } else {
                int comp = compRim(ch, rim.charAt(i - 1));
                if (comp < 0) {
                    s[i] = toDec(ch);
                } else if (comp > 0) {
                    s[i] = toDec(ch);
                    s[i - 1] *= -1;
                } else {
                    s[i] = toDec(ch) + s[i - 1];
                    s[i - 1] = 0;
                }
            }
        }

        int sum = 0;
        for (int a : s) {
            sum += a;
        }
        return sum;
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
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

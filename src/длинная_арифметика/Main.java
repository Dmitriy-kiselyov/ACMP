package длинная_арифметика;

import java.io.*;
import java.util.*;

/**
 * Задача 859
 * Сумма от 1 до N
 * N до 10^100
 */
public class Main {

    static private MyScanner in;
    static private PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);

        BigNumber num = new BigNumber(in.next());
        BigNumber sum = num.mul(num.inc()).div2();

        out.println(sum);
    }

}

class BigNumber {
    private ArrayList<Integer> num;

    public BigNumber(String s) {
        num = new ArrayList<>(s.length());

        for (int i = s.length() - 1; i >= 0; i--) {
            num.add((int)s.charAt(i) - 48);
        }
    }

    private BigNumber(ArrayList<Integer> n) {
        num = n;
    }

    public BigNumber inc() {
        ArrayList<Integer> newNum = new ArrayList<>(num.size() + 1);

        boolean inc = true;
        for (int a: num) {
            int b = a + (inc ? 1 : 0);
            if (b == 10) {
                newNum.add(0);
            } else {
                inc = false;
                newNum.add(b);
            }
        }

        if (inc) {
            newNum.add(1);
        }

        return new BigNumber(newNum);
    }

    public BigNumber div2() {
        ArrayList<Integer> num = reverse(this.num);
        ArrayList<Integer> newNum = new ArrayList<>();

        boolean save = false;
        for (int a : num) {
            if (save) {
                a += 10;
            }

            int next = a / 2;
            save = a % 2 != 0;

            if (next != 0 || newNum.size() > 0) {
                newNum.add(next);
            }
        }

        return new BigNumber(reverse(newNum));
    }

    public BigNumber mul(BigNumber other) {
        ArrayList<Integer> a = num;
        ArrayList<Integer> b = other.num;
        ArrayList<Integer> c = new ArrayList<>(Collections.nCopies(a.size() + b.size(), 0));

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0, carry = 0; j < b.size() || carry != 0; j++) {
                int cur = c.get(i + j) + a.get(i) * (j < b.size() ? b.get(j) : 0) + carry;
                c.set(i + j, cur % 10);
                carry = cur / 10;
            }
        }

        while(c.size() > 1 && c.get(c.size() - 1) == 0) {
            c.remove(c.size() - 1);
        }

        return new BigNumber(c);
    }

    private ArrayList<Integer> reverse(ArrayList<Integer> list) {
        ArrayList<Integer> newNum = new ArrayList<>(list.size());

        for (int i = list.size() - 1; i >= 0; i--) {
            newNum.add(list.get(i));
        }

        return newNum;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = num.size() - 1; i >= 0; i--) {
            s.append(num.get(i));
        }

        return s.toString();
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

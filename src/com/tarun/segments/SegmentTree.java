package com.tarun.segments;

public class SegmentTree {
    private int []ST;
    private int N;

    public SegmentTree(int []arr) {
        build(arr);
    }

    private void build(int []arr) {
        N = arr.length;
        ST = new int[4 * N];
        build(arr, 0, 0, N - 1);
    }

    private void build(int[] arr, int node, int L, int R) {
        if (L == R) {
            ST[node] = arr[L];
        } else {
            int mid = (L + R) / 2;

            build(arr, 2 * node + 1, L, mid);
            build(arr, 2 * node + 2, mid + 1, R);

            ST[node] = ST[2 * node + 1] + ST[2 * node + 2];
        }
    }

    private int query(int node, int ql, int qr, int L, int R) {
       if (qr < L || ql > R) {
           return 0;
       }

       if (L >= ql && R <= qr) {
           return ST[node];
       }

       int mid = (L + R)/2;
       return query(2 * node + 1, ql, qr, L, mid) + query(2 * node + 2, ql, qr, mid + 1, R);
    }

    public int query(int ql, int qr) {
        return query(0, ql, qr, 0, N -1);
    }

    public static void main(String[] args) {
        int []arr = {0, 1, 3, 5, -2, 3};
        SegmentTree st = new SegmentTree(arr);
        System.out.println(st.query(2, 4));
    }
}

package com.tarun.nums;

public class CheckProduct {
    int check(int []A) {
        int negativeCount = 0;
        for (int a : A) {
            if (a == 0) {
                return 0;
            }
            if (a < 0) {
                ++negativeCount;
            }
        }

        if (negativeCount % 2 != 0) {
            return -1;
        }
        return 1;
    }

    public static void main(String []args) {
        int []A = {1, -2, -3, 5, -3, 0};
        CheckProduct cp = new CheckProduct();
        System.out.println(cp.check(A));
    }
}

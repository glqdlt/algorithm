package com.glqdlt.ex;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Date 2019-08-11
 *
 * @author glqdlt
 */
public class Dynamic {

    /**
     * 주어진 값 N 이 주어질 때, 1에서 N까지의 모든 수를 더하시오.
     */
    @Test
    public void numberAllSum() {
        final int N = 10;
        int sum = IntStream.rangeClosed(1, N)
                .reduce(Integer::sum).orElse(0);
        Assert.assertEquals(55, sum);
    }

    @Test
    public void fi() {
        int result;
        int i = 0;
        while (true) {
            result = fibonn(i);
            if (result < 0) {
                break;
            }
            i++;
        }
        Assert.assertEquals(47, i - 1);
        Assert.assertEquals(1836311903, fibonn(i - 1));
    }

    private int fibonn(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n <= 3) {
            return 1;
        }
        int[] temp = {0, 1, 0};
        for (int i = 2; i < n; i++) {
            temp[2] = temp[0] + temp[1];
            temp[0] = temp[1];
            temp[1] = temp[2];
        }
        return temp[2];
    }
}

package com.glqdlt.ex.baekjoon;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Date 2019-08-11
 *
 * @author glqdlt
 */
public class exam1463 {

    /**
     * 다이나믹 프로그래밍에서 중요한 건, 메모제이션(캐싱) 이다.
     * 다이나믹 프로그래밍은 한번 푼 것은 다신 안 푼다가 핵심이다. 즉 O(N) 의 목적을 가진다.
     * 다이나믹 프로그래밍 하면 D[N] 이라는 수식이 자주 등장 하는 데, D는 다이나믹 프로그래밍의 약자이고,
     * 캐싱 데이터를 의미한다.
     * 다이나믹을 벡터나 동적이란 의미로 생각해볼 수도 있는 데, 전혀 상관없다.
     * 알고리즘을 만든 사람이 겉멋으로 지은 거라, 아무 의미 없다.
     * 다이나믹 프로그래밍은 Top - Down(하향식), Bottom Up(상향식) 두 방법을 하더라도 똑같은 결과가 나온다.
     * 상향식이나 하향식의 차이는 N 을 뒤에서 가냐 앞에서 가냐 가 아니라, 관점의 차이이다.
     * 하향식은 전체에서 작은 구조로 접근하는 방법이고, 상향식은 작은 구조에서 전체로 가는 방법이다.
     * 아래 방법은 Bottom Up 이다.
     * 난 개인적으로 빠가라서 그런가 캐싱이란 개념으로 이해할 때에는 이 방법이 이해하기 쉽다.
     * bottom Up 의 포인트는 계산해야할 대상 N 의 최적값(가장 적게 식을 사용한 최소값) 을 찾을 때..
     * 자기 자신의 식 (-1 이나 2 나누기 3 나누기) 하고 난 나머지의 과거 계산 했던 최적값을 합치면 최적값이 나올 것이다.
     * 라고 기대한다.
     * 즉 +1을 해주는 것은, 자기 자신의 수식을 한번 했기 떄문에..
     * 과거에 저장된 최적값에 자기를 한번 거쳐서 갔다고 해서 +1 을 해주는 것이다.
     *
     * 위가 이해가 안 된다면 이렇게 이해하면 된다.
     * N = 이 100일 때, -1 이나 2나누기 3나누기를 적절히 섞엇을 때, 가장 최적값(적게 호출한 횟수)를 찾아라면
     * 3가지의 방법 중에서 가장 최적화된 값을 찾아야 한다.
     * -1을 써봤을 때에는 99의 값이 나온다.
     * 2나누기를 했을 때에는 50의 값이 나온다.
     * 3나누기는 점화식(if문)이 성립되지 않는다.
     *
     * -1의 결과 값인 99라는 값에서 또 최적값을 찾으려면 3을 나누거나 또 2를 나누고 1을 빼던가..
     * 등등의 많은 경우의 수를 다시 계산해야 한다.
     * 2 나누기의 결과 값인 50의 값에서 또 최적값을 찾으며녀 2을 나누던 3을 나누던 아몰랑 어케어케 했을 때
     * 나온 최적의 수를 다시 계산해야 한다.
     * bottom Up의 장점은, N = 100이라는 수를 1에서 부터 가장 최적값을 이미 계산을 하고 100까지 가기 때문에
     * 위의 난제를 하지 않아도 된다.
     * 즉 -1 의 결과 값인 99라는 값이 있을 때, 99의 최적값은 6번의 식을 사용했을 거라는 캐싱 결과가 이미 있다.
     * 그렇다면 99의 최적값 6에다가 -1을 내가 한번했었으니깐 6+1 을 해서 -1 로 했을 시에 100을 -1로 선빵쳐봤을 때
     * 7이라는 최적 결과가 나올 수 있음이 도출 된다.
     * 마찬가지로 2나누기했을 때의 50의 값 역시, 50의 최적값은 6번의 식을 사용했ㅇ르 거라는 캐싱 결과가 이미 있다.
     * 마찬가지로 50의 최적값 6에다가 2를 한번 나누었기에 50이 된거니, 6 + 1 을 해서 100 을 2나누기로 선빵쳐봤을 때
     * 7이라는 최적 결과가 나올 수 있음이 도출 된다.
     * -1 이나 나누기 2나 결국 7 이므로, -1 을 하던 2를 나누던 어쨋든 100의 경우는 최적값은 7이 되는 것이다.
     * 3나누기는 아얘 안 되니 제외하는 것이다.
     * 복합 계산을 해야하는거 아닌가 란 생각이 가득차 있었는 데, 100 -1 을 하면 99 가 나올 때,
     * 99의 최적값에는 이미 3나누기가 포함되어 있다.
     * 자질구레한 거는 생각하지 않아도 된다. 이미 과거에 최적값을 구해놨던 것이기 때문이다.
     */
    @Test
    public void name() {
        int n = 50;
//        배열에 +1 을 해주는 것은.. 배열의 순서는 0부터 시작하기 때문이다. y[0] 은 의미가 없다.
        Integer[] y = new Integer[n + 1];
        Arrays.fill(y, 0);
        for (int i = 2; i <= n; i++) {
            y[i] = y[i - 1] + 1;

            if (i % 2 == 0 && y[i] > y[i / 2] + 1) {
                y[i] = y[i / 2] + 1;
            }

            if (i % 3 == 0 && y[i] > y[i / 3] + 1) {
                y[i] = y[i / 3] + 1;
            }
        }
        Stream.of(y)
                .forEach(System.out::println);

    }

}

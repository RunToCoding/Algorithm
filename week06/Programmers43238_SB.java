package week06;

/*
입국 심사
n명이 입국심사를 위해 줄을 서서 기다리고 있다.
각 입국심사대에 있는 심사관마다 심사하는 데 걸리는 시간이 다르다.

처음에 모든 심사대는 비어있다. 한 심사대에서 동시에 한 명만 심사할 수 있다.
가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있다.
하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그 곳으로 가서 심사를 받을 수도 있다.
모든 사람이 심사 받는 데 걸리는 최소 시간을 구하라.

입국 심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는 데 걸리는 시간 times

1. 검사 범위를 좁히기 위해 최소, 최대 시간을 left, right 로 잡고 중앙 값의 시간에 대해 계싼
2. 중앙 값 시간 동안 검사 가능한 인원 수 계산
    2-1. 인원 수가 n 보다 적으면 left 를 중앙 값 - 1로 변경
    2-2. 인원 수가 n 보다 크거나 같으면 right 를 중앙 값으로 변경
3. left 가 right 보다 작다면 1, 2번 반복
    ( 계산한 인원 수가 n 과 같아도 최소 시간이 아닐 수 있기 때문에 계속 계산!)
 */
public class Programmers43238_SB {
    public static void main(String[] args) {
        int[] n = {6, 6, 6, 3, 6, 6, 11, 5, 3};
        int[][] times = {
                {7, 10}, // 28
                {3, 9}, // 15
                {8, 10}, // 30
                {1000000000, 1000000000, 1000000000}, // 1000000000
                {6, 10}, // 24
                {4, 10}, // 20
                {3, 4, 10}, // 18
                {1, 1, 10}, // 3
                {1, 99, 99} // 3
        };

        for (int idx = 0; idx < n.length; idx++) {
            System.out.println(solution(n[idx], times[idx]));
        }
    }

    private static long solution(int n, int[] times) {
        int timesLen = times.length;
        long left = 1;
        long right = (long) times[timesLen - 1] * n; // 최악의 경우

        while (left <= right) {
            long mid = (left + right) / 2;

            long count = 0; // int 로 하면 통과 X -> 최대 값을 기반으로 구했을 때 count 는 최대 100000 * 1000000000 / 2 이기 때문
            for (int time : times) {
                count += mid / time;
            }

            if (count < n) {
                left = mid + 1;
            } else { // 최소를 찾기 위해 count == mid 여도 진행
                right = mid - 1;
            }
        }

        return left;
    }
}

package week02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
BOJ 1174 줄어드는 수
가장 큰 줄어드는 수는 9876543210 이다.
감소하는 수는 각 숫자가 겹치면 안되기 때문에 한 번 밖에 사용하지 못한다.
그러므로 N이 1024보다 커지는 경우는 무조건 -1이다.
 */
public class BOJ1174_SB {
    static ArrayList<Long> descreasingNumber = new ArrayList<>();

    public static void createDecreasingNumber(long number, int digit) { // 현재 숫자, 자릿수, 줄어드는 수 리스트

        descreasingNumber.add(number);

        for (int i = 0; i < number % 10; i++) {
            createDecreasingNumber(number * 10 + i, digit + 1);
        }

    }

    public static long solution(int num) {

        if (num > 1023) { // 1024보다 큰 경우의 숫자 조합은 나올 수 없음
            return -1;
        }

        descreasingNumber.add(0L);

        for (long i = 1; i < 10; i++) {
            createDecreasingNumber(i, 1);
        }

        Collections.sort(descreasingNumber);

        return descreasingNumber.get(num - 1);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        System.out.println(solution(num));
    }
}

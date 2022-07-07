package week05;

import java.util.Arrays;
import java.util.Scanner;

/*
수 찾기
N개의 정수가 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.
X라는 정수는 M개 주어지며 각각의 X에 대해 순서대로 출력하면 된다.
존재하면 1, 존재하지 않으면 0 출력
입력
5
4 1 5 2 3
5
1 3 7 9 5
출력
1
1
0
0
1
 */
public class BOJ1920_SB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int givenNumSize = input.nextInt();
        int[] givenNums = new int[givenNumSize];
        for (int idx = 0; idx < givenNumSize; idx++) {
            givenNums[idx] = input.nextInt();
        }
        int findNumSize = input.nextInt();
        int[] findNums = new int[findNumSize];
        for (int idx = 0; idx < findNumSize; idx++) {
            findNums[idx] = input.nextInt();
        }

        solution(givenNumSize, givenNums, findNumSize, findNums);
    }

    private static void solution(int givenNumSize, int[] givenNums, int findNumSize, int[] findNums) {
        // 이분 탐색을 위해 정렬
        Arrays.sort(givenNums);

        for (int idx = 0; idx < findNumSize; idx++) {
            int findNumber = findNums[idx]; // 현재 찾으려는 숫자
            int left = 0;
            int right = givenNumSize;
            int mid;
            boolean isFind = false;

            while (left < right) {
                mid = (left + right) / 2;
                if (givenNums[mid] < findNumber) left = mid + 1;
                else if (givenNums[mid] > findNumber) right = mid;
                else {
                    isFind = true;
                    break;
                }
            }

            System.out.println(isFind ? 1 : 0);

        }
    }
}

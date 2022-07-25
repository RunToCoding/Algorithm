package week07;

import java.util.Scanner;

/*
조 짜기
나이 순으로 정렬된 학생들의 점수가 주어진다.
각각의 조가 잘 짜여진 정도는 그 조에 속해있는 가장 높은 점수와 가장 낮은 점수의 차이이다.
조가 잘 짜여진 정도의 최댓값을 구하라
입력
10
2 5 7 1 3 4 8 6 9 3
출력
20
 */
public class BOJ2229_SB {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numberOfStudents = input.nextInt();
        int[] studentScores = new int[numberOfStudents + 1]; // dp 활용을 위해 인덱스 1번부터 채움

        for (int idx = 1; idx <= numberOfStudents; idx++) {
            studentScores[idx] = input.nextInt();
        }

        System.out.println(solution(numberOfStudents, studentScores));
    }

    private static int solution(int numberOfStudents, int[] studentScores) {
        int[] dp = new int[numberOfStudents + 1]; // dp도 숫자의 개수보다 한 개 더 많이 생성

        for (int idx = 1; idx <= numberOfStudents; idx++) {
            int max = 0; // 가능한 최소 점수로 max 지정
            int min = 10000; // 가능한 최대 점수로 min 지정

            for (int i = idx; i > 0; i--) {
                max = Math.max(max, studentScores[i]);
                min = Math.min(min, studentScores[i]);
                dp[idx] = Math.max(dp[idx], max - min + dp[i - 1]); // 지정 구간에서의 max - min 값과 지정 구간 앞 구간의 dp 값을 더함
            }
        }

        return dp[numberOfStudents];
    }

}

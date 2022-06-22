package week03;

import java.util.Scanner;

/*
수익
창업한 지 N일까지 매일 수익을 적어 놓았을 때.
가장 많이 돈을 번 구간을 구하라.
N이 주어진 다음 매일 매일의 수익 P가 주어진다. N이 0이면 입력 종료이다.
앞 구간 + 현재 구간과 현재 구간을 비교하여 더 큰 쪽을 선택한다.
앞 구간 + 현재 구간 => 구간을 계속 이어감
현재구간 => 구간을 새로 시작함
 */
public class BOJ4097_SB {
    public static int solution(int days, int[] dailyProfit) {
        int answer = Integer.MIN_VALUE;
        for (int idx = 1; idx < days; idx++) {
            dailyProfit[idx] = Math.max(dailyProfit[idx - 1] + dailyProfit[idx], dailyProfit[idx]);
            answer = Math.max(answer, dailyProfit[idx]);
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            int days = input.nextInt(); // 기간
            if (days == 0) break; // 0이면 종료

            int[] dailyProfit = new int[days]; // 매일 얻은 수익

            for (int idx = 0; idx < days; idx++) {
                dailyProfit[idx] = input.nextInt();
            }

            System.out.println(solution(days, dailyProfit));
        }
    }
}

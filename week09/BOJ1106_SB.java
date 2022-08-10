package week09;

import java.util.Scanner;

/*
호텔
첫번째 줄에는 늘어야 하는 호텔 고객의 최소 수 C, 홍보할 수 있는 도시 수 N이 주어진다.
두 번째 줄부터는 N개의 줄에 각 도시에서 홍보할 때 드는 비용과 그 비용으로 얻을 수 있는 고객 수가 주어진다.
고객을 적어도 C명 늘이기 위해서 투자해야 하는 돈의 최솟값을 구하라
======
입력
12 2
3 5
1 1
출력
8
 */
public class BOJ1106_SB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int wantCustomerNum = input.nextInt();
        int cityNum = input.nextInt();
        int[][] cityInfo = new int[cityNum][2];

        for (int idx = 0; idx < cityNum; idx++) {
            cityInfo[idx][0] = input.nextInt(); // 비용
            cityInfo[idx][1] = input.nextInt(); // 얻을 수 있는 고객 수
        }

        System.out.println(solution(wantCustomerNum, cityNum, cityInfo));
    }

    private static int solution(int wantCustomerNum, int cityNum, int[][] cityInfo) {
        int[] costPerCustomer = new int[wantCustomerNum];

        for (int idx = 0; idx < wantCustomerNum; idx++) {
            costPerCustomer[idx] = 100000;
            for (int cityIdx = 0; cityIdx < cityNum; cityIdx++) {
                int cost = cityInfo[cityIdx][0];
                int customerNum = cityInfo[cityIdx][1];
                int comparisonCosts = idx - customerNum < 0 ? cost : costPerCustomer[idx - customerNum] + cost;
                costPerCustomer[idx] = Math.min(costPerCustomer[idx], comparisonCosts);
            }
        }

        return costPerCustomer[wantCustomerNum - 1];
    }
}

package week13;

import java.util.Scanner;

/*
회전 초밥
불경기로 영업이 어려워져 다음과 같이 두 가지 행사를 진행
1. 원래 회전 초밥은 손님이 마음대로 초밥을 고르고, 먹은 만큼 식대를 계산하지만,
벨트 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격 제공
2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고,
1번 행사에 참가할 경우 쿠폰에 적혀진 종류의 초밥 하나 무료 제공
벨트 위에 없을 경우, 요리사가 새로 만들어 제공
회전 초밥 음식점의 벨트 상태, 메뉴에 있는 초밥 수, 연속해서 먹은 접시 수, 쿠폰 번호가 주어졌을 때,
손님이 먹을 수 있는 초밥 종휴 수의 최댓값을 구하라.
---
입력
8 30 4 30
7
9
7
30
2
7
9
25
---
출력
5
 */
public class BOJ2531_SB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int dishNums = input.nextInt(); // 접시 수
        int sushiNums = input.nextInt(); // 초밥 종류 수
        int consecutiveDishNums = input.nextInt(); // 연속해서 먹은 접시
        int couponNumber = input.nextInt(); // 쿠폰 번호
        int[] sushiVarieties = new int[dishNums];
        for (int idx = 0; idx < dishNums; idx++) {
            sushiVarieties[idx] = input.nextInt();
        }

        System.out.println(solution(dishNums, sushiNums, consecutiveDishNums, couponNumber, sushiVarieties));
    }

    private static int solution(int dishNums, int sushiNums, int consecutiveDishNums, int couponNumber, int[] sushiVarieties) {
        int count = 0;
        int maxCount;
        int[] logEatSushi = new int[sushiNums + 1];

        // 처음 위치에서 연속해서 스시 먹기
        for (int idx = 0; idx < consecutiveDishNums; idx++) {
            int currentSushi = sushiVarieties[idx];
            if (logEatSushi[currentSushi] == 0) count++;
            logEatSushi[currentSushi]++;
        }
        maxCount = logEatSushi[couponNumber] == 0 ? count + 1 : count;

        // 스시 먹는 위치 이동
        int currentSushiIdx = consecutiveDishNums;
        for (int idx = 0; idx < dishNums; idx++) {
            int ordSushi = sushiVarieties[idx]; // 가장 오래 전에 선택한 스시
            logEatSushi[ordSushi]--;
            if (logEatSushi[ordSushi] == 0) count--; // 현재 먹지 않을 스시이므로 제거

            int currentSushi = sushiVarieties[currentSushiIdx];
            if (logEatSushi[currentSushi] == 0) count++;
            logEatSushi[currentSushi]++;

            if (maxCount <= count) {
                maxCount = logEatSushi[couponNumber] == 0 ? count + 1 : count;
            }

            currentSushiIdx++;
            if (currentSushiIdx == dishNums) currentSushiIdx = 0;
        }

        return maxCount;
    }
}

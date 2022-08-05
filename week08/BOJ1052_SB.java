package week08;

import java.util.Scanner;

/*
물병
N개의 물병이 존재하며, 처음 모든 물병에는 1리터씩 물이 들어있다.
한 번에 K개의 물병을 옮길 수 있을 때, 물병의 물을 적절히 재분배하려고 한다.
먼저 같은 양의 물이 들어있는 물병 두 개를 고른다. 그 다음 한 개의 물병에 다른 한 쪽에 있는 물을 모두 붓는다.
이 방법을 필요한 만큼 계속한다.
N개로 K개를 넘지않는 물병이 불가능할 경우, 1리터의 물이 들어있는 새로운 물병을 살 수 있다.
N=3이고, K=1일 때, 물병 3개를 1개로 만드는 것은 불가능하다.
한 병을 또 다른 병에 부으면 2리터 물병 하나와 1리터 물병 하나가 남는다.
상점에서 한 개의 물병을 사면, 2리터가 들어 있는 물병 2개를 만들 수 있고,
마지막으로 4리터가 들어있는 물병 한 개를 만들 수 있다.
상점에서 사야하는 물병의 최솟값을 출력한다.
 */
public class BOJ1052_SB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int waterBottleNum = input.nextInt(); // n
        int notEmptyWaterBottleNum = input.nextInt(); // k

        System.out.println(solution(waterBottleNum, notEmptyWaterBottleNum));

    }

    private static int solution(int waterBottleNum, int notEmptyWaterBottleNum) {
        int neededWaterBottleNum = 0;

        // K 이하의 물병이 필요할 때까지만 계산
        while (numberOfOne(waterBottleNum) > notEmptyWaterBottleNum) {
            waterBottleNum++;
            neededWaterBottleNum++;
        }

        return neededWaterBottleNum;
    }

    /**
     * 이진수로 변환했을 때 1의 개수를 구하는 함수
     * @param num 확인하려는 숫자
     * @return 이진수로 변환했을 때의 1의 개수
     */
    private static int numberOfOne(int num) {
        int cnt = 0;
        while (num > 0) {
            if (num % 2 == 1) cnt++;
            num /= 2;
        }
        return cnt;
    }
}

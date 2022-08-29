package week09;

import java.util.Arrays;

/*
외벽 점검
레스토랑 구조는 "완전히 동그란 모양"이고 외벽의 총 둘레는 n 미터이며,
외벽의 몇몇 지점은 추위가 심할 경우 손상될 수도 있는 취약한 지점들이 있다.
최소한의 친구들을 투입해 취약 지점을 점검하고 나머지 친구들은 내부 공사를 돕도록 하려 한다.
레스토랑의 정북 방향 지점을 0으로 나타내며,
취약 지점 위치는 정북 방향 지점으로부터 시계방향으로 떨어진 거리로 나타낸다.
친구들은 출발 지점부터 시계 혹은 반시계 방향으로 외벽을 따라서만 이동한다.

외벽 길이 n, 취약 지점 위치 배열 weak, 각 친구가 1시간 동안 이동할 수 있는 거리 배열 dist 가 주어질 때,
취약 지점을 점검하기 위해 보내야 하는 친구 수의 최소값을 return 하라.
모두 투입해도 모두 점검할 수 없는 경우는 -1을 return 하면 된다.
 */
public class Programmers60062_SB {
    public static void main(String[] args) {
        int[] nums = {12, 12};
        int[][] weaks = {
                {1, 5, 6, 10},
                {1, 3, 4, 9, 10}
        };
        int[][] dists = {
                {1, 2, 3, 4},
                {3, 5, 7}
        };

        for (int idx = 0; idx < nums.length; idx++) {
            System.out.println(solution(nums[idx], weaks[idx], dists[idx])); // 2, 1
        }
    }

    private static int solution(int n, int[] weak, int[] dist) {
        int distSize = dist.length;
        Arrays.sort(dist);

        return -1;
    }
}

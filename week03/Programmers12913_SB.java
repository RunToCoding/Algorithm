package week03;

import java.util.Arrays;

/*
땅따먹기
땅따먹기 게임의 땅(land)은 총 N행 4열로 이루어져 있고, 모든 칸에는 점수가 쓰여있다.
1행부터 땅을 빫으며 한 행씩 내려올 때, 각 행의 4칸 중 한 칸만 밟으면서 내려와야 한다.
같은 열은 연속해서 밟을 수 없다.
마지막 행까지 모두 내려왔을 때, 얻을 수 있는 점수의 최대값을 return 하는 solution 함수를 완성하라.
---
무조건 칸은 4개임.
현재 자리와 더할 수 있는 가장 큰 값을 찾아 더해가는 방식으로 진행.
마지막 행에서 max 값을 찾아 출력
 */
public class Programmers12913_SB {
    public static int solution(int[][] land) {
        int landLenM1 = land.length - 1;
        for (int idx = 0; idx < landLenM1; idx++) {
            land[idx + 1][0] += Math.max(Math.max(land[idx][1], land[idx][2]), land[idx][3]);
            land[idx + 1][1] += Math.max(Math.max(land[idx][0], land[idx][2]), land[idx][3]);
            land[idx + 1][2] += Math.max(Math.max(land[idx][0], land[idx][1]), land[idx][3]);
            land[idx + 1][3] += Math.max(Math.max(land[idx][0], land[idx][1]), land[idx][2]);
        }

        return Arrays.stream(land[landLenM1]).max().getAsInt();
    }

    public static void main(String[] args) {
        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};
        System.out.println(solution(land));
    }
}

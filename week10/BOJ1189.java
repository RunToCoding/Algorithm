package week10;

import java.util.Scanner;

/*
컴백홈
현수는 왼쪽 아래점에 있고, 집은 오른쪽 위에 있다.
한수가 집에 돌아갈 수 있는 경우의 수 중 거리가 K인 수를 출력하라.
단, 한 번 지나친 곳은 다시 방문하지 않으며, T로 표시된 부분은 가지 못한다.
 */
public class BOJ1189 {
    private static int count = 0;
    private static final int[][] DISTANCE = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private static int row;
    private static int col;
    private static int k;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        row = input.nextInt();
        col = input.nextInt();
        k = input.nextInt(); // 거리
        input.nextLine(); // 현재 줄의 (\n)을 받아옴

        char[][] matrix = new char[row][col];
        for (int i = 0; i < row; i++) {
            matrix[i] = input.nextLine().toCharArray();
        }

        matrix[row - 1][0] = 'T'; // 현수 위치
        solution(matrix, 1, row - 1, 0); // 현수 위치부터 숫자를 세므로 len 은 1로 초기화
        System.out.println(count);
    }


    private static void solution(char[][] matrix, int len, int curR, int curC) {
        if (len == k) {
            if ((curR == 0) && (curC == col - 1)) {
                count++;
            }
            return;
        }

        for (int[] dist : DISTANCE) {
            int nextR = curR + dist[0];
            int nextC = curC + dist[1];
            if (0 <= nextR && nextR < row && 0 <= nextC && nextC < col && matrix[nextR][nextC] != 'T') {
                matrix[nextR][nextC] = 'T'; // 방문 처리
                solution(matrix, len + 1, nextR, nextC);
                matrix[nextR][nextC] = '.'; // 방문 처리 해제
            }
        }
    }
}

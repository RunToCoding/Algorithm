package week11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
노드 사이의 거리
첫째 줄에 노드의 개수 N과 거리가 궁금한 노드 쌍의 개수 M이 주어진다.
그 다음 N - 1개의 줄에 두 점과 거리를 입력되고, M개의 노드 쌍이 입력된다.
M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.
입력
4 2
2 1 2
4 3 2
1 4 3
1 2
3 2
출력
2
7
 */
public class BOJ1240_SB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[][] nodes = new int[n][n];

        for (int i = 0; i < n - 1; i++) {
            int a = input.nextInt() - 1;
            int b = input.nextInt() - 1;
            int w = input.nextInt();
            nodes[a][b] = nodes[b][a] = w;
        }

        for (int i = 0; i < m; i++) {
            int num1 = input.nextInt() - 1;
            int num2 = input.nextInt() - 1;
            System.out.println(solution(num1, num2, nodes, n));
        }

    }

    private static class Number {
        int num;
        int distance;

        Number(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }
    }

    private static int solution(int num1, int num2, int[][] nodes, int n) {
        int distance = 0;
        Queue<Number> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.add(new Number(num1, 0));

        while (!queue.isEmpty()) {
            Number number = queue.poll();
            visited[number.num] = true;

            if (number.num == num2) {
                distance = number.distance;
                break;
            }

            for (int idx = 0; idx < n; idx++) {
                int wieght = nodes[number.num][idx];
                if (wieght > 0 && !visited[idx]) {
                    queue.add(new Number(idx, number.distance + wieght));
                }
            }

        }
        return distance;
    }
}

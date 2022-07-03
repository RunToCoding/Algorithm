package week04;

import java.util.Arrays;

/*
합승 택시 요금
출발 지점 s, a의 집 위치, b의 집 위치에 대해 두 사람이 귀가하는 데 소요되는 예상 최저 택시요금 계산
모든 위치를 같이 타고 갈 수도 있고, 중간 지점에서 각자 타고 갈 수도 있음.
===
문제가 어려워서 풀이 참고했다.
해당 문제는 "플로이드 워셜 (Floyd Warshall)" 알고리즘을 사용해야 한다.
합승 구간이 일부 겹치며, n이 200으로 매우 적기 때문에
모든 정점 간 거리 계산 후 활용하는 플로이드 워셜 알고리즘을 사용하는 것이 좋다.
그러나 플로이드 워셜은 O(n^3)으로 많은 시간이 걸리기 때문에 이에 유의하여 사용해야 한다.
 */
public class Programmers72413_SB {
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int MAX = 20000000; // 200 * 100000 이상이면 됨
        int answer = MAX;

        // 가장 큰 값으로 채움
        int[][] dist = new int[n + 1][n + 1];
        for (int idx = 0; idx < n + 1; idx++) {
            Arrays.fill(dist[idx], MAX);
        }

        // 초기 간선 정보 입력
        for (int[] fare : fares) {
            int x = fare[0];
            int y = fare[1];
            int w = fare[2];
            dist[x][y] = w;
            dist[y][x] = w;
        }

        // 플로이드 워셜
        // 기준이 되는 거쳐가는 노드 k
        for (int k = 1; k < n + 1; k++) {
            // 출발하는 노드 i
            for (int i = 1; i < n + 1; i++) {
                // 도착하는 노드 j
                for (int j = 1; j < n + 1; j++) {
                    if (i == j) {
                        dist[i][j] = 0; // i와 j가 같을 때는 이동 X
                        continue;
                    }
                    // i에서 k를 거쳤다가 k에서 j까지 가는 거리와 i에서 j까지 가는 거리를 비교 -> 작은 값 저장
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

        // k르 거쳐서 가는 경로 중 최단 거리 찾기
        for (int k = 1; k < n + 1; k++) {
            answer = Math.min(answer, dist[s][k] + dist[k][a] + dist[k][b]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] n = {6, 7, 6};
        int[] s = {4, 3, 4};
        int[] a = {6, 4, 5};
        int[] b = {2, 1, 6};
        int[][][] fares = {
                {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 60}, {2, 3, 22}, {1, 6, 25}},
                {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}},
                {{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}
        };

        for (int idx = 0; idx < n.length; idx++) {
            System.out.println(solution(n[idx], s[idx], a[idx], b[idx], fares[idx]));
        }
    }
}
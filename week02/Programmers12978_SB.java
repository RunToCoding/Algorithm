package week02;

/*
배달
N개의 마을은 1부터 N 까지의 번호가 각각 하나씩 부여됭 ㅓ있다.
각 마을은 양방향으로 통행할 수 있는 도로로 연결되어 있는데,
서로 다른 마을 간에 이동할 때는 이 도로를 지나야 한다.
도로를 지날 때 걸리는 시간은 도보별로 다른다.
N개의 마을 중에서 K 시간 이하로 배달이 가능한 마을에서만 주문을 받으려 한다.
1번 마을에 있는 음식점이 K 이하의 시간에 배달 가능한 마을 개수를 구하라.
 */
public class Programmers12978_SB {
    static boolean[] isDelivery;

    public static void findDelivery(int K, int N, int town, int time, int[][] delivery, boolean[] isVisited) {
        if (K < time) return;

        if (!isDelivery[town]) {
            isDelivery[town] = true;
        }

        for (int idx = 1; idx < N + 1; idx++) {
            if (delivery[town][idx] > 0 && !isVisited[idx]) {
                isVisited[idx] = true;
                findDelivery(K, N, idx, time + delivery[town][idx], delivery, isVisited);
                isVisited[idx] = false;
            }
        }
    }

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;
        isDelivery = new boolean[N + 1];
        int[][] delivery = new int[N + 1][N + 1];
        boolean[] isVisited = new boolean[N + 1];

        // 배달 가능 지역 목록 생성
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int t = r[2];

            if (0 < delivery[a][b] && delivery[a][b] < t) continue;

            delivery[a][b] = t;
            delivery[b][a] = t;
        }

        findDelivery(K, N, 1, 0, delivery, isVisited);

        for (boolean b : isDelivery) {
            if (b) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] N = {5, 6};
        int[][][] road = {
                {{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}},
                {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}
        };
        int[] K = {3, 4};

        for (int idx = 0; idx < N.length; idx++) {
            System.out.println(solution(N[idx], road[idx], K[idx]));
        }
    }
}

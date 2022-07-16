package week06;

import java.util.HashSet;
import java.util.Scanner;

/*
숫자 연결하기
N과 K과 주어질 때 N을 여러 번 써서 K로 나누어떨어지는지 구하려고 한다.
N=10일 때, 한 번 쓰면 10, 두 번 쓰면 1010이다.
나누어 떨어지는 최솟값을 출력하자. 만약 아무리 써도 불가능할 경우 -1을 출력하자.
입력
2 9
출력
9
 */
public class BOJ1323_SB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();

        System.out.println(solution(n, k));
    }

    private static int solution(int n, int k) {
        int answer = 1;
        long remainder = n % k;
        HashSet<Long> remainders = new HashSet<>();

        while (remainder != 0) {
            remainders.add(remainder);
            answer++;

            String number = "" + remainder + n; // 이전 결과 나머지 + n
            remainder = Long.parseLong(number) % k;
            if (remainders.contains(remainder)) {
                answer = -1;
                break;
            }
        }

        return answer;
    }
}

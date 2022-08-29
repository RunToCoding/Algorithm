package week10;

/*
소수 찾기
한자리 숫자가 적힌 종이 조각이 흩어져 있을 때,
흩어진 종이 조각을 붙여 소수를 몇개 만들 수 있는지 알아내려 함
각 종이 조각에 적힌 숫자가 문자열 numbers로 주어질 때
종이 조각으로 만들 수 있는 소수가 몇 개인지 return
 */
public class Programmers42839 {
    public static void main(String[] args) {
        String[] numbersArr = {
                "17", "011"
        };

        for (String numbers : numbersArr ) {
            System.out.println(solution(numbers));
        }
    }

    private static int primeNumberCnt;
    private static final int MAX_SIZE = 10000000; // 숫자 범위는 0 - 9999999
    private static final boolean[] isNotPrime = new boolean[MAX_SIZE];
    private static int solution(String numbers) {
        primeNumberCnt = 0;

        // 1. 소수에 대한 정보가 담긴 배열 생성
        isNotPrime[0] = isNotPrime[1] = true;
        for (int i = 2; i < MAX_SIZE; i++) {
            if (isNotPrime[i]) continue;
            for (int j = i + i; j < MAX_SIZE; j += i) {
                isNotPrime[j] = true;
            }
        }

        // 2. 숫자 생성 후 소수 확인
        char[] number = numbers.toCharArray();
        boolean[] isUsed = new boolean[number.length];
        countPrimeNumber(number, 0, isUsed, "");

        return primeNumberCnt;
    }

    private static void countPrimeNumber(char[] number, int len, boolean[] isUsed, String num) {
        // 처음 만들어진 숫자이며 소수인 경우
        if (num.length() > 0 && !isNotPrime[Integer.parseInt(num)]) {
            primeNumberCnt++;
            isNotPrime[Integer.parseInt(num)] = true; // 중복된 숫자를 사용하지 않도록 true 처리
        }

        for (int idx = 0; idx < number.length; idx++) {
            if (isUsed[idx]) continue;

            isUsed[idx] = true;
            countPrimeNumber(number, len + 1, isUsed, num + number[idx]);
            isUsed[idx] = false;
        }
    }
}

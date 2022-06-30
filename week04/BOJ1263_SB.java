package week04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
시간 관리
하루에 할 일이 총 N개가 있고, 이 일들을 편하게 1번부터 N번까지 차례대로 번호를 붙였다.
시간을 효율적으로 관리하기 위해, 할 일들에 대해 끝내야 할 시간과 걸리는 시간을 적는 명단을 만들었다.
0시부터 활동을 시작할 수 있고, 두 개 이상의 일을 같은 시간에 처리할 수 없다.
마감시간 내에 처리할 수 있는 범위 내에서 최대한 늦게 일을 시작할 수 있는 시간이 몇 시인지 출력하라.
입력 -  첫째 줄에 일의 수 N, 다음 N개의 줄에는 i+1번째 줄에 i번째 일에 대한 Ti(걸리는 시간)와 Si(마감 시간)가 입력됨.
4
3 5
8 14
5 20
1 16
-> 일을 끝내는 시간을 기준으로 내림차순 정렬하고 하루 안에 일을 모두 마칠 수 있는지를 구해야 함. (겹치는 시간도 고려 필요)
0시부터 시작하여도 일을 끝마칠 수 없다면 -1 출력
참고 > 하루는 24시간인데, 24시간을 MAX 로 잡으면 안됨. 문제 어휘가 좀 잘못된 것 같음... :(
 */
public class BOJ1263_SB {
    static class Work {
        int workingTime; // 일 하는 데 걸리는 시간
        int closingTime; // 일 마감 시간

        Work(int workingTime, int closingTime) {
            this.workingTime = workingTime;
            this.closingTime = closingTime;
        }
    }

    public static int solution(int workSize, List<Work> works) {
        // 마감 시간 기준으로 정렬
        works.sort((o1, o2) -> o2.closingTime - o1.closingTime);

        Work work = works.get(0);
        int startTime = work.closingTime - work.workingTime;
        for (int idx = 1; idx < workSize; idx++) {
            work = works.get(idx);

            if (work.closingTime < startTime) {
                startTime = work.closingTime;
            }

            startTime -= work.workingTime;
        }


        return startTime < 0 ? -1 : startTime;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int workSize = input.nextInt(); // 할 일 수
        ArrayList<Work> works = new ArrayList<>();

        for (int idx = 0; idx < workSize; idx++) {
            works.add(new Work(input.nextInt(), input.nextInt()));
        }

        System.out.println(solution(workSize, works));
    }
}

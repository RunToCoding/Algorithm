package week12;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
강의실
N개의 강의실이 있고, 모든 강의의 시작 시간과 끝나는 시간을 알고 있다.
이 때, 최대한 적은 수의 강의실을 사용하여 모든 강의가 이루어지게 하고 싶다.
- 한 강의실에서 동시에 2개 이상의 강의를 진행할 수 없다.
- 한 강의의 종료 시간과 다른 강의의 시작 시간이 겹칠 수 있다.
필요한 최소 강의실 수를 출력하라.
입력
8
6 15 21
7 20 25
1 3 8
3 2 14
8 6 27
2 7 13
4 12 18
5 6 20
출력
5
 */
public class BOJ1374_SB {
    private static class Lecture {
        int lectureNum;
        int startTime;
        int endTime;

        Lecture(int lectureNum, int startTime, int endTime) {
            this.lectureNum = lectureNum;
            this.startTime = startTime;
            this.endTime = endTime;
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfLectures = input.nextInt();

        List<Lecture> lectures = new ArrayList<>();

        for (int idx = 0; idx < numberOfLectures; idx++) {
            lectures.add(new Lecture(input.nextInt(), input.nextInt(), input.nextInt()));
        }

        lectures.sort(((o1, o2) -> o1.startTime == o2.startTime ? o1.endTime - o2.endTime : o1.startTime - o2.startTime));

        PriorityQueue<Integer> lectureEndTime = new PriorityQueue<>();

        int numberOfClassrooms = 0;
        for (Lecture lecture : lectures) {
            while (!lectureEndTime.isEmpty() && lectureEndTime.peek() <= lecture.startTime) lectureEndTime.poll();

            lectureEndTime.add(lecture.endTime);
            numberOfClassrooms = Math.max(numberOfClassrooms, lectureEndTime.size());
        }

        System.out.println(numberOfClassrooms);
    }
}

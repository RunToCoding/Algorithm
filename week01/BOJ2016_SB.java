package week01;

import java.util.Scanner;

/*
미팅 주선하기
미팅 자리에서 각자의 짝을 정하는 방법은 다음과 같다.
1. 태현이가 1번, 나머지 남학생이 2-5번, 여학생이 6-10번이라고 하자.
2. 각자 상대방에 대한 선호도 순위를 매겨서 쓴다.
    두 명 이상의 상대에게 같은 선호도를 적용하면 안되고, 5명 모두에 대한 순위를 매겨야 한다.
3. 6번 여학생부터 가장 좋아하는 남학생에게 프러포즈를 한다. 퇴짜를 놓으면 다음 순의 남학생에게 프러포즈 한다.
4. 남학생은 프러포즈를 받을지 퇴짜를 놓을지 결정하는데, 현재 자신에게 짝이 없으면 무조건 프러포즈를 받아들인다.
    현재 짝이 있지만 더 선호하는 여학생이 프러포즈 하면 기존 여학생에게 퇴짜를 놓는다.
5. 퇴짜 받은 여학생들만 다음 라운드에서 다시 짝짓기에 참여한다.
모든 학생들이 짝을 찾을 때까지 이 과정을 반복하며 마지막에 서로의 짝이 최종 짝이 된다.
태현이의 선호도는 6 7 8 9 10 순서이다.
해당 선호도를 적당히 바꿔 적어서 원래 선호도 리스트 보다 더 좋아하는 여학생과 짝이 될 수 있는지 출력하라.
가능하면 "YES", 불가능하면 "NO"
 */
public class BOJ2016_SB {
    static int[] myPreference = {6, 7, 8, 9, 10}; // 태현의 선호도
    static int[] outputPreference = new int[5];
    static int[] outputScores = new int[5];
    static int originPartner = 0;
    static int changePartner = 0;
    static int cnt = 0;

    // 짝 정하기
    public static void getPartner(int[][] preference, int[][] scores) {
        preference[1] = outputPreference;
        scores[1] = outputScores;
        int[] partners = new int[11]; // 파트너 정보 기록
        int[] findPartner = new int[5]; // 여학생의 파트너 정보 기록

        while (true) {
            boolean isContinue = false;
            for (int i = 6; i < 11; i++) {
                if (partners[i] != 0) continue; // 이미 짝을 지정한 여학생은 짝 선택을 하지 않는다.
                int idx = findPartner[i - 6]; // 여학생이 원하는 남학생 우선순위 (인덱스)
                findPartner[i - 6] = idx + 1; // 여학생이 다시 짝을 찾을 때 사용할 인덱스

                int wantPartner = preference[i][idx]; // 여학생이 원하는 남학생
                if (partners[wantPartner] == 0) { // 아직 짝이 정해지지 않음
                    partners[wantPartner] = i;
                    partners[i] = wantPartner;
                } else {
                    isContinue = true;
                    // 더 선호하는 여학생인지 비교
                    int decidedPartner = partners[wantPartner]; // 남학생과 짝인 여학생 번호
                    if (scores[wantPartner][decidedPartner - 6] > scores[wantPartner][i - 6]) { // 현재 여학생의 선호도가 더 높으면
                        partners[wantPartner] = i;
                        partners[i] = wantPartner;
                        partners[decidedPartner] = 0; // 기존에 짝인 여학생은 새로 지정해야 한다.
                    }
                }
            }
            if (!isContinue) break;
        }

        if (cnt == 0) {
            originPartner = partners[1];
        } else {
            changePartner = partners[1];
        }

    }

    // 태현의 선호도 생성
    public static void setPreference(int len, int[][] preference, int[][] scores, boolean[] used) {
        if (1 < cnt && originPartner > changePartner) {
            return;
        }

        if (len == 5) {
            // 선호도 결과 구하기
            getPartner(preference, scores);
            cnt++;
            return;
        }

        for (int idx = 0; idx < 5; idx++) {
            if (!used[idx]) {
                used[idx] = true;
                int partner = myPreference[idx];
                outputPreference[len] = partner;
                outputScores[partner - 6] = len;
                setPreference(len + 1, preference, scores, used);
                used[idx] = false;
            }
        }
    }

    public static String solution(int[][] preferences, int[][] scores) {
        boolean[] used = new boolean[5]; // 선호도 사용 기록
        cnt = 0;

        // 선호도 계산
        setPreference(0, preferences, scores, used);

        if (originPartner > changePartner) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testcase = input.nextInt();
        int[][] preferences = new int[11][5];
        int[][] scores = new int[11][5]; // 우선순위에 따른 점수

        for (int tc = 0; tc < testcase; tc++) {
            // 선호도 입력
            for (int i = 2; i < 11; i++) {
                for (int j = 0; j < 5; j++) {
                    int partner = input.nextInt();
                    preferences[i][j] = partner;
                    if (partner > 5) partner -= 5;
                    scores[i][partner - 1] = j;
                }
            }
            System.out.println(solution(preferences, scores));
        }

    }
}

/*
1
10 9 6 7 8
8 10 7 9 6
9 7 6 8 10
8 10 6 9 7
2 3 1 4 5
5 1 2 3 4
3 2 1 4 5
2 3 1 5 4
5 3 4 1 2

1
6 8 9 7 10
9 8 7 6 10
8 7 10 9 6
8 9 6 7 10
5 1 4 3 2
5 3 4 1 2
3 5 1 4 2
1 3 4 5 2
5 1 3 2 4
 */
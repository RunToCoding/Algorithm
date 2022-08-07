package week07;

/*
조이스틱
조이스틱으로 알파벳 이름 완성하기
조이스틱 각 방향은 다음과 같다.
- 상 : 다음 알파벳
- 하 : 이전 알파벳 (A에서 아래면 Z)
- 좌 : 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽은 마지막 위치)
- 우 : 커서를 오른쪽으로 이동 (마지막 위치에서 오른쪽은 첫 번째 위치)
문제 설명에 그리디라고 되어 있는데, 그리디가 아닌 것으로 보임. (https://school.programmers.co.kr/questions/30107)
 */
public class Programmers42860_SB {
    public static void main(String[] args) {
        String[] names = {
                "JEROEN", // 56
                "JAN", // 23
                "JAZ", // 11
                "BAACAAAAAAAF", // 13
                "AAAAAAAAAAAA", // 0
                "A", // 0
                "AAAAABBAAAAAAABAAA", // 16
                "AAABBBABA", // 10
                "ABABAAAAABA" // 10
        };

        for (String name : names) {
            System.out.println(solution(name));
        }

    }

    private static int solution(String name) {
        int numberOfNames = name.length();
        boolean[] isChange = new boolean[numberOfNames];
        int moveCount = 0;

        // 문자별 위/아래 이동 횟수 계산
        for (int idx = 0; idx < numberOfNames; idx++) {
            char alphabet = name.charAt(idx);
            if (alphabet == 'A') {
                isChange[idx] = true; // 'A'는 이미 변환이 되었다고 표시
                continue;
            }
            moveCount += Math.min((alphabet - 'A'), ('Z' - alphabet + 1)); // 위 또는 아래로 움직임
        }

        // 문자 변환을 위한 좌우/이동 횟수 계산
        isChange[0] = true; // 첫 번째 문자는 이동이 필요하지 않기 때문에 변환 완료 표시
        return moveCount + moveAllRoutesCount(0, isChange);
    }

    /**
     * 모든 이동 경로에 대해 카운트
     * @param idx       현재 idx
     * @param isChange  변환 여부를 표시한 배열
     * @return          우측 이동과 좌측 이동 중 더 작은 값 반환
     */
    private static int moveAllRoutesCount(int idx, boolean[] isChange) {
        if (isAllTrueExist(isChange)) {
            return 0;
        }

        // 우측 이동
        int rightNextIdx = idx + 1 == isChange.length ? 0 : idx + 1;
        int rightMoveCount = 1;
        while (isChange[rightNextIdx]) {
            if (rightMoveCount == isChange.length) break;
            rightMoveCount++;
            rightNextIdx = rightNextIdx == isChange.length - 1 ? 0 : rightNextIdx + 1;
        }

        isChange[rightNextIdx] = true;
        rightMoveCount += moveAllRoutesCount(rightNextIdx, isChange);
        isChange[rightNextIdx] = false;

        // 좌측 이동
        int leftNextIdx = idx == 0 ? isChange.length - 1 : idx - 1;
        int leftMoveCount = 1;
        while (isChange[leftNextIdx]) {
            if (leftMoveCount == isChange.length) break;
            leftMoveCount++;
            leftNextIdx = leftNextIdx == 0 ? isChange.length - 1 : leftNextIdx - 1;
        }

        isChange[leftNextIdx] = true;
        leftMoveCount += moveAllRoutesCount(leftNextIdx, isChange);
        isChange[leftNextIdx] = false;

        return Math.min(rightMoveCount, leftMoveCount);
    }

    /**
     * 모두 True 인지 확인
     *
     * @param booleans 확인할 배열
     * @return 하나라도 False가 존재하면 false, 아니면 true
     */
    private static boolean isAllTrueExist(boolean[] booleans) {
        for (boolean status : booleans) {
            if (!status) return false;
        }
        return true;
    }


    //=========================================================================================
    // 그리디 방식으로 풀었던 과거 코드
    private static int solution_greedy(String name) {
        int numberOfNames = name.length();
        boolean[] isChange = new boolean[numberOfNames];
        boolean isRun = true;
        int idx = 0;
        int moveCount = 0;
        while (true) {
            char alphabet = name.charAt(idx);

            isChange[idx] = true;
            moveCount += Math.min((alphabet - 'A'), ('Z' - alphabet + 1)); // 위 또는 아래로 움직임

            // 좌우 이동방향 정하기
            int[] direction = {-1, 1}; // 좌우
            int[] nextIdxs = new int[2];
            int[] count = new int[2];
            for (int i = 0; i < direction.length; i++) {
                int trueCount = 0;
                nextIdxs[i] = idx;
                while (true) {
                    if (direction[i] == -1 && nextIdxs[i] == 0) nextIdxs[i] = numberOfNames;
                    if (direction[i] == 1 && nextIdxs[i] == numberOfNames - 1) nextIdxs[i] = -1;

                    nextIdxs[i] += direction[i];

                    if (!isChange[nextIdxs[i]] && name.charAt(nextIdxs[i]) != 'A') break;

                    if (name.charAt(nextIdxs[i]) == 'A')
                        isChange[nextIdxs[i]] = true; // A인 경우 true로 만들어주는 작업 필요

                    trueCount++;

                    if (trueCount >= numberOfNames) {
                        isRun = false;
                        break;
                    }
                }
                count[i] = trueCount + 1;
            }

            if (!isRun) break;

            if (count[0] < count[1]) {
                moveCount += count[0];
                idx = nextIdxs[0];
            } else {
                moveCount += count[1];
                idx = nextIdxs[1];
            }
        }

        return moveCount;
    }
}

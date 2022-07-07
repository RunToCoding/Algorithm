package week05;

import java.util.Arrays;
import java.util.HashSet;

/*
영어 끝말잇기
n명의 사람이 영어 끝말잇기를 하며, 규칙은 아래와 같다.
1. 1번부터 번호 순서대로 한 사람씩 단어를 말한다.
2. 마지막 사람이 단어를 말하면 다시 1번부터 시작해야 한다. => 순회
3. 앞 사람이 말한 단어 마지막 문자로 시작해야 한다. => 문자 비교
4. 이전에 등장했던 단어는 사용할 수 없다. => Set 으로 관리
5. 한 글자 단어는 인정되지 않는다. => 단어 길이 검사 필요 => 단어 길이는 2 이상 50 이하 (해당 경우 주어지지 않음)
가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에 탈락하는지 (순회 횟수)
탈락자가 생기지 않는다면, [0, 0] return
 */
public class Programmers12981_SB {
    public static void main(String[] args) {
        int[] n = {3, 5, 2};
        String[][] words = {
                {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"},
                {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"},
                {"hello", "one", "even", "never", "now", "world", "draw"}
        };

        for (int idx = 0; idx < n.length; idx++) {
            System.out.println(Arrays.toString(solution(n[idx], words[idx])));
        }
    }

    private static int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        int round = 1;
        HashSet<String> useWords = new HashSet<>();
        useWords.add(words[0]); // 첫 번째 사람이 말한 단어를 넣고 시작
        int useWordsNum = useWords.size();

        for (int idx = 1; idx < words.length; idx++) {
            int personNumber = idx % n;
            if (personNumber == 0) round++; // 새로운 라운드 시작

            // 앞 사람이 말한 단어의 마지막 문자로 시작하지 않거나 이전에 사용한 단어
            String preWord = words[idx - 1];
            String curWord = words[idx];
            useWords.add(curWord);
            if (preWord.charAt(preWord.length() - 1) != curWord.charAt(0)
                    || useWordsNum == useWords.size()) {
                answer[0] = personNumber + 1;
                answer[1] = round;
                break;
            }

            useWordsNum = useWords.size();

        }

        return answer;
    }
}

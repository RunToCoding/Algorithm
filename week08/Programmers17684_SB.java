package week08;

import java.util.ArrayList;
import java.util.HashMap;

/*
[3차] 압축
LZW 압축은 다음 과정을 거친다.
1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
5. 단계 2로 돌아간다.
 */
public class Programmers17684_SB {
    private static HashMap<String, Integer> dictionary = new HashMap<>();

    public static void main(String[] args) {
        String[] messages = {"KAKAO", "TOBEORNOTTOBEORTOBEORNOT", "ABABABABABABABAB"};

        for (String msg : messages) {
            System.out.println(solution(msg));
        }
    }

    private static ArrayList<Integer> solution(String msg) {
        dictionaryInitialize(); // 사전 초기화

        ArrayList<Integer> lzwList = new ArrayList<>(); // 압축 리스트

        String currentWord = "";
        int msgLen = msg.length();
        for (int idx = 0; idx < msgLen; idx++) {
            currentWord += String.valueOf(msg.charAt(idx));
            int currentWordNumber = dictionary.get(currentWord);
            if (idx + 1 < msgLen) { // 뒤에 문자가 남은 경우
                String nextWord = currentWord + msg.charAt(idx + 1); // 다음 단어 추가
                if (!dictionaryRegistration(nextWord)) {
                    // 이미 있는 단어
                    continue;
                }
            }

            lzwList.add(currentWordNumber);
            currentWord = ""; // 초기화
        }

        return lzwList;
    }

    /**
     * 사전 등록
     *
     * @param word 등록하려는 단어
     * @return 등록 성공/실패 여부
     */
    private static boolean dictionaryRegistration(String word) {
        if (dictionary.containsKey(word)) return false;
        else {
            dictionary.put(word, dictionary.size() + 1);
            return true;
        }
    }

    /**
     * 사전 초기화
     */
    private static void dictionaryInitialize() {
        dictionary = new HashMap<>(); // 여러 예제 실행 시 dictionary 초기화를 위해 추가
        char alphabet = 'A';
        for (int i = 1; i < 27; i++) {
            dictionary.put(String.valueOf(alphabet), i);
            alphabet++;
        }
    }

}

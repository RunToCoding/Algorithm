package week08;

import java.util.HashMap;
import java.util.ArrayList;

public class Programmers17684_JY {

    public static void main(String[] args) {
        String[] msg = {"A", "KAKAO", "TOBEORNOTTOBEORTOBEORNOT", "ABABABABABABABAB"};
        
        for (String m : msg) {
            System.out.println(solution(m));
            int[] result = solution(m);
            System.out.println(m);
            for (int i : result) System.out.println(i);
        }
    }

    private static int[] solution(String msg) {
        int len = msg.length();
        
        int[] answer = new int[1];

        if (len > 1) {
            //1. ArrayList 선언
            ArrayList<Integer> list = new ArrayList<Integer>();

            //2. dict 초기화
            HashMap<String, Integer> dict = new HashMap<String, Integer>();
            
            int idx = 0;
            int nextIdx = 0;
            int dictIdx = 26;
            
            while (idx < len) {
                nextIdx = idx+1;
                if (nextIdx < len) {
                    while (nextIdx < len && dict.containsKey(msg.substring(idx, nextIdx+1)) == true) {nextIdx++;}
                
                    if (nextIdx < len) {
                        dict.put(msg.substring(idx, nextIdx+1), ++dictIdx);
                    }
                    
                    if (nextIdx<len && nextIdx-idx <= 1) {
                        list.add(msg.charAt(idx) - 64);
                    } else {
                        list.add(dict.get(msg.substring(idx, nextIdx)));
                    }
                } else {

                    list.add(msg.charAt(idx) - 64);
                }
                idx = nextIdx;
            }

            answer = new int[list.size()];
            for (int i=0; i<list.size();i++) answer[i] = list.get(i);
        } else {
            answer[0] = msg.charAt(0) - 64;
        }        
        return answer;
    }
}

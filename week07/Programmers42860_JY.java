package week07;

public class Programmers42860_JY {
    
    public static void main(String[] args) {
        String[] names = {
                "JEROEN", // 56
                "JAN", // 23
        };

        for (String name : names) {
            System.out.println(solution(name));
        }

    }

    private static int solution(String name) {
        int answer = 0;

        int len = name.length();
        char temp ; 
        int move = len-1;
        int idx;
        for (int i=0; i<len; i++) {
            //System.out.print(name.charAt(i)+" , ");
            temp = name.charAt(i);
            answer += Math.min(temp - 'A', 'Z'- temp +1);

            idx = i + 1; //'A' 연속 찾기
            while (idx < len && name.charAt(idx) == 'A') idx++;

            move = Math.min(move, i*2 + len-idx); 
            move = Math.min(move, (len-idx)*2 + i);
        }
        
        return answer+move;
    }
}

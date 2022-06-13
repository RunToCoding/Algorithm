package week01;

import java.util.Stack;

/*
괄호 변환
'('와 ')'로만 이루어진 문자열이 있을 경우, '('의 개수와 ')'의 개수가 같다면 이를 균형잡힌 괄호 문자열이라 한다.
여기에 '('와 ')'의 괄호 짝이 모두 맞을 경우 이를 올바른 문자열이라고 부른다.
균형 잡힌 괄호 문자열이 주어질 때, 올바른 괄호 문자열로 변환한 결과를 return 하라.
규칙
1. 입력이 빈 문자열인 경우, 빈 문자열을 반환한다.
2. 문자열 w를 두 균형잡힌 괄호 문자열 u, v로 분류한다.
   u가 더 이상 분리할 수 없을 때까지 하며, v는 빈 문자열이 될 수 있다.
3. u가 올바른 괄호 문자열이라면 v에 대해 1단계부터 다시 수행한다.
    3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환한다.
4. 문자열 u가 올바른 괄호 문자열이 아니라면 아래 과정을 수행한다.
    4-1. 빈 문자열에 첫 번째 문자로 '('를 붙인다.
    4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙인다.
    4-3. ')'를 다시 붙인다.
    4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙인다.
    4-5. 생성된 문자열을 반환한다.
 */
public class Programmers60058_SB {
    // 올바른 문자열인지 판단
    public static boolean isCorrect(String u) {
        Stack<Character> stack = new Stack<>();
        for (Character c : u.toCharArray()) {
            if (c == '(') {
                stack.add(c);
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }

    public static String solution(String w) {
        // 1. 빈 문자열인 경우, 그대로 반환한다.
        if (w.equals("")) {
            return w;
        }

        StringBuilder answer = new StringBuilder();
        String u = "";
        String v = "";

        // 2. 두 균형잡힌 괄호 문자열 u와 v로 분리
        Stack<Character> stack = new Stack<>();
        for (int idx = 0; idx < w.length(); idx++) {
            if (stack.isEmpty() || stack.peek() == w.charAt(idx)) {
                stack.add(w.charAt(idx));
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    u = w.substring(0, idx + 1);
                    v = w.substring(idx + 1);
                    break;
                }
            }
        }

        // 3. 문자열 u가 올바른 문자열인 경우 v에 대해 1단계부터 수행한 결과를 이어 붙인다.
        if (isCorrect(u)) {
            answer.append(u).append(solution(v));
        } else {
            // 4-1. 첫 번째 문자로 '('를 붙인다.
            answer.append("(");
            // 4-2. v에 대해 1단계부터 수행한 결과를 이어 붙인다.
            answer.append(solution(v));
            // 4-3. ')'를 다시 붙인다.
            answer.append(")");
            // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 붙인다.
            u = u.substring(1, u.length() - 1);
            for (Character c : u.toCharArray()) {
                answer.append(c == '(' ? ")" : "(");
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        String[] inputs = {
                "(()())()",
                ")(",
                "()))((()"
        };

        for (String input : inputs) {
            System.out.println(solution(input));
        }
    }
}

package week11;

import java.util.*;
import java.util.stream.Collectors;

/*
튜플
원소의 개수가 n개이고, 중복되는 원소가 없는 튜플이 주어질 때, 집합 기호를 이용해 표현할 수 있다.
튜플 (2, 1, 3,4)인 경우
{{2}, {2,1}, {3,1,2}, {2, 1, 3, 4}} 이렇게 표현할 수 있다.
숫자는 2, 1, 3, 4 순서로 많이 나왔고 이 순서가 튜플의 순서와 동일하므로
숫자가 나온 횟수를 기준으로 문제를 풀었다!
 */
public class Programmers64065_SB {
    public static void main(String[] args) {
        String[] strings = {
                "{{2},{2,1},{2,1,3},{2,1,3,4}}",    //[2, 1, 3, 4]
                "{{1,2,3},{2,1},{1,2,4,3},{2}}",    //[2, 1, 3, 4]
                "{{20,111},{111}}",    //[111, 20]
                "{{123}}",    //[123]
                "{{4,2,3},{3},{2,3,4,1},{2,3}}", //[3, 2, 4, 1]
        };

        for (String s : strings) {
            System.out.println(solution(s));
            System.out.println("=============");
        }
    }

    private static List<Integer> solution(String s) {
        String[] nums = s.replace("{", "").replace("}", "").split(",");

        Map<String, Integer> countNum = new HashMap<>(); // 문자 개수를 담을 Map
        for (String num : nums) {
            countNum.merge(num, 1, Integer::sum);
        }

        // map sort by value
        countNum = countNum.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        List<Integer> answer = new ArrayList<>();

        countNum.forEach((key, value) -> answer.add(Integer.valueOf(key)));

        return answer;
    }
}

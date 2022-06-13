def solution(p):
    # 1 - 빈문자열 반환
    if not len(p):
        return p
    
    # 2 - 균형잡힌 괄호 문자열 분리(u, v 분할)
    bracket_open, bracket_close = 0, 0
    for i, s in enumerate(p):
        # 괄호 개수 카운트
        if s == '(': bracket_open += 1
        elif s == ')': bracket_close += 1
        
        # 개수가 같을 때(균형잡힌) u, v설정
        print(bracket_open, bracket_close)
        if bracket_open == bracket_close:
            u = p[:i+1]
            v = p[i+1:]
            break
            
    # 3 - u가 올바른 문자열일 때
    if correct_string(u):
        return u + solution(v)
    # 4 - u가 올바른 문자열이 아닐 때
    else:
        return '(' + solution(v) + ')' + u[1:-1].translate(str.maketrans('()', ')('))
    
def correct_string(s):
    # s가 올바른 문자열인지 확인
    stack = []
    for c in s:
        if c == '(':
            stack.append(c)
        else:
            if len(stack) > 0:
                stack.pop()
            else:
                # 여는괄호가 없는데 닫힌괄호가 나올 경우
                return False
            
    # 모두 정상 수행 되었을 경우
    return True
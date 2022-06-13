def correctStringCheck(w):
    stack = []
    len_w = len(w)
    for i in range(len_w):
        print(i, " "+ w[i]+ " : ",stack)
        if i == 0 and w[i] == ")": return False
        elif w[i] == ")":
            if stack and stack[-1] == "(": stack.pop()
            else: return False
        else: stack.append("(")
    else:
        return True

def SplitBalanceString(w):
    if w == "": return ""
    else:
        u, v = "", ""
        open, close = 0, 0
        len_w = len(w)
        for i in range(len_w):
            if w[i] == "(": open+=1
            else: close+=1

            if open !=0 and close !=0 and open==close:
                u = w[:i+1]
                v = w[i+1:]
                break

    return u, v


def solution(p):
    answer = ''
    if correctStringCheck(p): return p
    else:
        if p == "": return ""
        else:
            print("split : ", SplitBalanceString(p))
            u, v = SplitBalanceString(p)
            if correctStringCheck(u):
                answer = u + solution(v)
            else:
                temp = '('
                temp += solution(v)
                temp += ')'
                u = u[1:-1]
                for i in u:
                    if i == "(": temp += ")"
                    else: temp += "("
                answer += temp
    return answer


print(solution(")("))
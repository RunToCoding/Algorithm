# 수익

"""
창업 : n일 - 가장 돈을 많이 번 구간 찾기
(EX) -3 / 4 / 9 / -2 / -5 / 8
"""

import sys

while N := int(input()):
    max_ = int(input())
    before = max_
    for _ in range(1, N):
        current = int(input())

        if before+current < current : before = current
        else: before += current

        max_ = max(max_, before)
        
    print(max_)
    
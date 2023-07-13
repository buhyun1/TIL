import random

def solution(M, N):
    row = []
    column = []

    for i in range(1,M+1):
        row.append(i)
    for i in range(1,N+1):
        column.append(i)

    random.randrange(1,M)
    row[:]
    answer = 0
    return row, column

print(solution(2,5))


def solution(M, N):
    answer = M*N-1
    return answer
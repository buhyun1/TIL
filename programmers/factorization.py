def solution(n):
    box = []

    def re(n, m):
        if n < 2:
            return

        while n % m != 0:
            m += 1

        box.append(m)
        re(n // m, m)

    re(n, 2)
    
    new_list = []
    for v in box:
        if v not in new_list:
            new_list.append(v)
    return new_list

print(solution(120))
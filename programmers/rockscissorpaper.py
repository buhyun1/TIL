
def solution(rsp):
    a = []

    for i in rsp:
        a.append(i)

    for cnt in range(len(a)):
        if a[cnt] == '0':
            a[cnt] = '5'
            continue
        if a[cnt] == '2':
            a[cnt] = '0'
            continue
        if a[cnt] == '5':
            a[cnt] = '2'
            continue
    b = ''.join(a)
    return b

print(solution('502'))
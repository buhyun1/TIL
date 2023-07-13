def solution(my_string, n):
    2 <= len(my_string) <= 5
    2 <= n <= 10
    my_string = my_string.strip('')
    answer= list(my_string) 
    li = []
    for i in answer:
        li.append(i*n)
    l = ''.join(li)
    return l

print(solution('buhyun123',3))

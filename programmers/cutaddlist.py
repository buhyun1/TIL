def solution(my_str, n):
    box = list(my_str)

    new_box = []

    result = [box[i * n:(i + 1) * n] for i in range((len(box) + n - 1) // n )] 
    for i in result :
        word = ''.join(i)
        new_box.append(word)
    return new_box

print(solution("abc1Addfggg4556b",6))

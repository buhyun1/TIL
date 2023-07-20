def solution(numbers):
    number= {0:'zero', 1:'one', 2:'two', 3:'three', 4:'four', 5:'five', 6:'six', 7:'seven', 8:'eight', 9:'nine'}
    for i,v in number.items():
        print(i,v)
        numbers = numbers.replace(v,str(i))
    answer = 0
    return int(numbers)

print(solution("onetwothreefourfivesixseveneightnine"))
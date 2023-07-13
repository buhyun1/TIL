def solution(money):
    cup = money//5500
    change = money - 5500*cup
    answer = [cup, change]
    return answer

print(solution(5500))

print(15000//5500)
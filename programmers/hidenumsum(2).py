def solution(my_string):
    box = list(my_string)

    for i in range(len(box)):
        if box[i].isalpha():
            box[i] = " "

    cleaned_string = ''.join(box)  # 리스트를 문자열로 변환
    print(cleaned_string)
    # 문자열을 공백을 기준으로 분할하여 숫자 합을 계산
    numbers = cleaned_string.split()
    print(numbers)
    total_sum = sum(int(num) for num in numbers if num.isdigit())
    
    return total_sum


print(solution("aAb1B2cC34oOp"))

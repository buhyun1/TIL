def solution(my_string):
    # box = list(my_string)
    # point = []
    # new_box = []
    # for i in box : 
    #     if i != ' ':
    #         new_box.append(i)
    #     if i == '+' or i =='-':
    #         point.append(i)
    #         print(point)
    # body = ''.join(new_box)
    
    # tail = body.replace('+', ' ')
    # tail2 = tail.replace('-', ' ')
    # tail3 = tail2.split()

    # ans = tail3[0]
    # for k in point :
    #     if k == '+':
    #         ans += tail3[]
    # return 
    box = my_string.split()
    ans = int(box[0])
    for i in range(1,len(box)+1) : 
        if i != len(box) :
            print(i)
            if box[i] == '+':    
                ans += int(box[i+1])
                print(ans)
            if box[i] == '-':
                ans -= int(box[i+1])
                print(ans)
    return ans
print(solution("314 + 432 - 11 - 2"))


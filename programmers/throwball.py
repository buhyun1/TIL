def solution(numbers, k):
    box = []
    
    for i in range(1,len(numbers)+1):
        box.append(i)

    if len(box)%2 == 0:
        new_box = box[0::2]
        list_num = k%len(new_box)-1
        return new_box[list_num]
    else :
        
        new_box1 = box[0::2]
        new_box2 = box[1::2]
        new_box3 = new_box1 + new_box2
        list_num = k%len(new_box3)-1
        print(new_box3)
    return new_box3[list_num]

print(solution([1,2,3,4,5,6],5))
# 짝 , 홀로 나누면 된다 
# 홀 [13524][13524] 정렬
# 짝 [131313] 정렬
# 리스트 1,3,1,3,1,3 계속 반복만 한다면?

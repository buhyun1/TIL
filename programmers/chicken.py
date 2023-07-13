service = 0
coupon = 0

def solution(chicken):
    if chicken < 10 :
        return
    
    global service
    service1 = chicken // 10 # 몫
    service += service1

    global coupon
    coupon1 = chicken % 10 # 나머지
    coupon += coupon1

    if service1 == 10 :
        service += 1
        #coupon -= 10

    print(service, coupon)

    solution(service1)
    return service, coupon

print(solution(100))
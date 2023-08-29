class SoccerPlayer(object): # 클래스는 설계도, class예약어 / class이름 / (상속받는객체명)
    def __init__(self, name, position, back_number): # __init__은 객체 초기화 예약 함수, __(맨글링) : 특수 예약 함수
        self.name = name
        self.position = position
        self.back_number = back_number

    def change_back_number(self, new_number):
        print("선수의 등번호를 변경합니다 : From %d to %d"  % \
                (self.back_number, new_number))
        self.back_number = new_number

    def __str__(self):
        return "Hello, My name is %s. My back number is %d" % \
                (self.name, self.back_number)
#######################
# Basic Math          #
#######################

"""
여기서 간단한 수학을 하는 프로그램을 만들것입니다. 
"""


def get_greatest(number_list):
    """
    주어진 리스트에서 가장 큰 숫자를 반환함

        Parameters:
            number_list (list): integer로 값으로만 구성된 list
            ex - [10, 33, 22, 99, 33]

        Returns:
            greatest_number (int): parameter number_list 중 가장 큰 값

        Examples:
            >>> number_list = [39, 54, 32, 11, 99]
            >>> import basic_math as bm
            >>> bm.get_greatest(number_list)
            99
    """
    greatest_number = number_list[0]
    for i in range(0, len(number_list)):
        if greatest_number < number_list[i]:
            greatest_number = number_list[i]
    return greatest_number


def get_smallest(number_list):
    """
    주어진 리스트에서 제일 작은 숫자를 반환함

        Parameters:
            number_list (list): integer로 값으로만 구성된 list
            ex - [10, 33, 22, 99, 33]

        Returns:
            smallest_number (int): parameter number_list 중 가장 작은 값

        Examples:
            >>> number_list = [39, 54, 32, 11, 99]
            >>> import basic_math as bm
            >>> bm.get_smallest(number_list)
            11
    """
    smallest_number = number_list[0]
    for i in range(0, len(number_list)):
        if smallest_number > number_list[i]:
            smallest_number = number_list[i]
    return smallest_number


def get_mean(number_list):
    """
    주어진 리스트 숫자들의 평균을 구함.

        Parameters:
            number_list (list): integer로 값으로만 구성된 list
            ex - [10, 33, 22, 99, 33]

        Returns:
            mean (int): parameter number_list 숫자들의 평균

        Examples:
            >>> number_list = [39, 54, 32, 11, 99]
            >>> import basic_math as bm
            >>> bm.get_mean(number_list)
            47
    """
    total = 0
    for i in number_list:
        total += i
    mean = total / int(len(number_list))
    return mean

def get_median(number_list):
    """
    주어진 리스트 숫자들의 중간값을 구함.

        Parameters:
            number_list (list): integer로 값으로만 구성된 list
            ex - [10, 33, 22, 99, 33]

        Returns:
            median (int): parameter number_list 숫자들의 중간값

        Examples:
            >>> number_list = [39, 54, 32, 11, 99]
            >>> import basic_math as bm
            >>> bm.get_median(number_list)
            39
            >>> number_list2 = [39, 54, 32, 11, 99, 5]
            >>> bm.get_median(number_list2)
            35.5
    """
    #짝수일때
    if (len(number_list) % 2 == 0):   
        save = sorted(number_list)
        median = (save[int(len(number_list)/2)] + save[int((len(number_list)/2)-1)]) / 2 
    #홀수일때
    elif (len(number_list) % 2 == 1):
        save = sorted(number_list) 
        median = save[int((len(number_list)-1)/2)]
    return median

if __name__ == "__main__":
    ex = [10, 33, 22, 99, 33]
    result = get_greatest(ex)
    print(result)

    result1 = get_smallest(ex)
    print(result1)

    result2 = get_mean(ex)
    print(result2)

    result3 = get_median(ex)
    print(result3)
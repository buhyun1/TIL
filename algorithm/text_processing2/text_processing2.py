#######################
# Test Processing II  #
#######################

import string

def digits_to_words(input_string):
    """
    인풋으로 받는 스트링에서 숫자만 추출하여 영어 단어로 변환하여 단어들이 연결된 스트링을 반환함
    아래의 요건들을 충족시켜야함
    * 반환하는 단어들은 영어 소문자여야함
    * 단어들 사이에는 띄어쓰기 한칸이 있음
    * 만약 인풋 스트링에서 숫자가 존재하지 않는 다면, 빈 문자열 (empty string)을 반환함

        Parameters:
            input_string (string): 영어로 된 대문자, 소문자, 띄어쓰기, 문장부호, 숫자로 이루어진 string
            ex - "Zip Code: 19104"

        Returns:
            digit_string (string): 위 요건을 충족시킨 숫자만 영어단어로 추출된 string
            ex - 'one nine one zero four'

        Examples:
            >>> import text_processing2 as tp2
            >>> digits_str1 = "Zip Code: 19104"
            >>> tp2.digits_to_words(digits_str1)
            'one nine one zero four'
            >>> digits_str2 = "Pi is 3.1415..."
            >>> tp2.digits_to_words(digits_str2)
            'three one four one five'
    """
    digit_string = input_string
    for character in string.punctuation:
        digit_string = digit_string.replace(character, '')
    for character in string.ascii_letters:
        digit_string = digit_string.replace(character, '') 
    digit_string = digit_string.replace(' ','')
    empty = []
    for i in digit_string :
        empty.append(i)

    list = ['0','1','2','3','4','5','6','7','8','9']
    list_a = ['zero','one','two','three','four','five','six','seven','eight','nine']
    cnt = 0
    for i in empty :
        if list[int(i)] == i :
            empty.remove(i)
            empty.insert(cnt, list_a[int(i)])
            cnt += 1
    result = ' '.join(empty)

    return result
    

"""
컴퓨터 프로그래밍에 많은 명명 규칙이 있지만, 두 규칙이 특히 흔히 쓰입니다. 
첫번째로는, 변수 이름을 'underscore'로 나눠준다거나, (ex. under_score_variable)
두번째로는, 변수 이름을 대소문자 구별해 구분자 (delimiter)없이 쓰는 경우가 있습니다. 
이 두번째의 경우에는 첫번째 단어는 소문자로, 그 후에 오는 단어들의 첫번째 글자들은 대문자로 쓰입니다 (ex. camelCaseVariable). 
"""


def to_camel_case(input):
    """
    이 문제에서 첫번째 규칙 'underscore variable' 에서 두번째 규칙 'camelcase variable'으로 변환함
    * 앞과 뒤에 여러개의 'underscore'는 무시해도 된
    * 만약 어떤 변수 이름이 underscore로만 이루어 진다면, 빈 문자열만 반환해도 됨

        Parameters:
            underscore_str (string): underscore case를 따른 스트링

        Returns:
            camelcase_str (string): camelcase를 따른 스트링

        Examples:
            >>> import text_processing2 as tp2
            >>> underscore_str1 = "to_camel_case"
            >>> tp2.to_camel_case(underscore_str1)
            "toCamelCase"
            >>> underscore_str2 = "__EXAMPLE__NAME__"
            >>> tp2.to_camel_case(underscore_str2)
            "exampleName"
            >>> underscore_str3 = "alreadyCamel"
            >>> tp2.to_camel_case(underscore_str3)
            "alreadyCamel"
    """
    if '_' in input :
        input = input.strip('_')
        input = input.lower()
        input = input.split('_')
        input = ' '.join(input).split()

        cnt = 1
        input1 = input
        for i in range(1, len(input)) : 
            # print(len(input))
            input1 = input[cnt][0].upper()
            input[cnt] = input[cnt][1:] 
            input[cnt] = input1 + input[cnt] 
            cnt += 1
            
        input = ''.join(input)
        return input
    
    else :
        return input

if __name__ == "__main__" :
    ex = '!hi. wh?at is the weat[h]er lik?e. !@##$^^&*)_+|?"112123456'
    result = digits_to_words(ex)
    print(result)

    el = "__EXAMPLE__NAME____EXAMPLE__NAME__"
    result = to_camel_case(el)
    print(result)
    
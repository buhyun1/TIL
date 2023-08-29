#######################
# Test Processing I   #
#######################


def normalize(input_string):
    """
    인풋으로 받는 스트링에서 정규화된 스트링을 반환함
    아래의 요건들을 충족시켜야함
    * 모든 단어들은 소문자로 되어야함
    * 띄어쓰기는 한칸으로 되어야함
    * 앞뒤 필요없는 띄어쓰기는 제거해야함

    Parameters:
        input_string (string): 영어로 된 대문자, 소문자, 띄어쓰기, 문장부호, 숫자로 이루어진 string
        ex - "This is an example.", "   EXTRA   SPACE   "

        Returns:
            normalized_string (string): 위 요건을 충족시킨 정규회된 string
            ex - 'this is an example.'

        Examples:
            >>> import text_processing as tp
            >>> input_string1 = "This is an example."
            >>> tp.normalize(input_string1)
            'this is an example.'
            >>> input_string2 = "   EXTRA   SPACE   "
            >>> tp.normalize(input_string2)
            'extra space'
    """
    input_data = input_string
    space = input_data.lower()
    normalized_string = " ".join(space.split()) 
    
# Python에서 문자열을 원하는 문자열을 기준으로 배열로 나눠줄 때 사용하는 split()과,
# 배열을 원하는 문자열을 사이에 넣어주며 하나의 문자열로 합쳐줄 때 사용하는 join()을 사용하여
# 중복되는 공백을 하나의 공백으로 만들거나 제거할 수 있습니다.
# 공백을 기준으로 문자열을 배열로 나누고, 다시 하나의 공백으로 합쳐줍니다.

    return normalized_string 

def no_vowels(input_string):
    """
    인풋으로 받는 스트링에서 모든 모음 (a, e, i, o, u)를 제거시킨 스트링을 반환함

        Parameters:
            input_string (string): 영어로 된 대문자, 소문자, 띄어쓰기, 문장부호로 이루어진 string
            ex - "This is an example."

        Returns:
            no_vowel_string (string): 모든 모음 (a, e, i, o, u)를 제거시킨 스트링
            ex - "Ths s n xmpl."

        Examples:
            >>> import text_processing as tp
            >>> input_string1 = "This is an example."
            >>> tp.normalize(input_string1)
            "Ths s n xmpl."
            >>> input_string2 = "We love Python!"
            >>> tp.normalize(input_string2)
            ''W lv Pythn!'
    """
    data = input_string
    for char in data:
        if char in "aeiouAEIOU":
            data = data.replace(char, '')
    no_vowel_string = data

    return no_vowel_string


if __name__ == "__main__":
    input_string = "    This  is an  example.   "
    print(normalize(input_string))

    input_string1 = input()
    result = no_vowels(input_string1)
    print(result)
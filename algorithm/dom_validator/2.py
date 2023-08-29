# <items>
#              <data>
#                  <items name="item1">item1abc</items>
#                  <item name="item2">item2abc</item>
#              </items>
#          </data>
def is_validate_dom(dom_text : str) -> bool:
    """ dom 문서인 str 타입의 데이터를 받은 후 해당 데이터가 올바른 dom 문서인지 아닌지 확인 하는 코드 
        반드시 stack 또는 queue 를 사용하여 구현할 것
    Args:
        dom_text (str) : dom 문서

    Returns:
        is_valudate_dom (bool) : 해당 문서가 올바른 dom인지 확인하는 코드
    
    """
    output = ""
    t = ['<', '>', '/']
    lists = []
    texts = dom_text.strip()
    a = texts.split()
    index = 0
    mod = 0
    for i in a:
        if i[-1] != '>':
            a[index] = i + '>'
        index += 1
    b = "".join(a)
    for i in b:
        if i == '<':
            mod = 1
            continue
        elif i == '>':
            mod = 0
            if output != "":
                lists.append(output)
            output = ""
            if len(lists) > 1:
                if lists[-1] == lists[-2]:
                    lists.pop()
                    lists.pop()
        if mod == 1:
            if i not in t:
                output += i

    if lists == []:
        print(True)
    else:
        print(False)
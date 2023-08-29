
def is_validate_dom(text : str) -> bool:
    """ dom 문서인 str 타입의 데이터를 받은 후 해당 데이터가 올바른 dom 문서인지 아닌지 확인 하는 코드 
        반드시 stack 또는 queue 를 사용하여 구현할 것
    Args:
        dom_text (str) : dom 문서

    Returns:
        is_valudate_dom (bool) : 해당 문서가 올바른 dom인지 확인하는 코드
    
    """
    text = text.replace(' ','')
    text = text.replace('\n','')

    binlist = []
    binstr = ""
    binvar = ""
    for i in text:
    
        if i == '<': 
            binlist.append(binvar)
            binstr = ''
            binstr += i
            binlist = ' '.join(binlist).split()

        elif i == '>':
            binstr += i
            binlist.append(binstr)
            binstr = ''
            # print(binlist)
            
            # if binlist[len(binlist)-1][2:len(binlist[len(binlist)-1])-1] == binlist[len(binlist)-2][1:len(binlist[len(binlist)-1])-1]:
            #   binlist.pop()
            #   binlist.pop()
            # print(binlist)
            # if binlist is not None: #아무것도 없을때
            #   if binlist[-1][2:len(binlist[-1])-1] == binlist[-2][1:len(binlist[-1])-1]:
            #       binlist.pop()
            #       binlist.pop()
            #       print(binlist)

            # else:
            #   pass
        
        else:
            binstr += i
        # print(binlist)

    flist = []
    empty_list = []
    tf = False
    for var in binlist:
        if var[0] == '<' and var[1] != "/":
            open_tag = var.replace('<','').replace('>','')
            flist.append(open_tag)

        elif var[0] == '<' and var[1] == "/":
            closed_tag = var.replace('</','').replace('>','')
            
            # if closed_tag in flist[-1]: # <에 추가 해서 
            if flist[-1].startswith(closed_tag):
                flist.pop()
                tf = True
            else:
                tf = False

    if flist == empty_list and tf == True:
        return True 
    else:
        return False

def main():
    text = """
        <p id="intro">A Computer Science portal for geeks.</p>   
        <p>This example illustrates the <b>getElementsById</b> method.</p>
        <p id="demo"></p>
        """
    test = is_validate_dom(text)
    print(test)

if __name__ == "__main__":
    main()
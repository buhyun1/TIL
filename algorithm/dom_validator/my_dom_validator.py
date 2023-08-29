
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
    for i in text:
    
        if i == '<': 
            binstr = ''
            binstr += i
            binlist = ''.join(binlist).split()

        elif i == '>':
            binstr += i
            binlist.append(binstr)
            binstr = ''
        
        else:
            binstr += i
        # print(binlist)
    print(binlist)

    flist = []
    empty_list = []
    tf = False
    # for var in binlist:
    #     if var[0] == '<' and var[1] != "/":
    #         flist.append(var)
            
    #     elif var[0] == '<' and var[1] == "/":
    #         closed_tag = var.replace('</','').replace('>','')
            
    #         if closed_tag in flist[-1]: # <에 추가 해서 
    #             flist.pop()
    #             tf = True
    #         else:
    #             tf = False
    for var in binlist:
        if var[0] == '<' and var[1] != "/":
            open_tag = var.replace('<','').replace('>','')
            flist.append(open_tag)
            print(flist)

        elif var[0] == '<' and var[1] == "/":
            closed_tag = var.replace('</','').replace('>','')
            
            # if closed_tag in flist[-1]: # <에 추가 해서 
            if flist[-1].startswith(closed_tag):
                flist.pop()
                tf = True
                # print(flist)
            else:
                tf = False

    if flist == empty_list and tf == True:
        return True 
    else:
        return False

def main():
    text = """
    <data>
            <items>
                <item name="item1">item1abc</item>

                <item name="item2">item2abc</item>
            </item>
        </data>
        """
    test = is_validate_dom(text)
    print(test) 

if __name__ == "__main__":
    main()


# <p id="intro">A Computer Science portal for geeks.</p>   
#         <p>This example illustrates the <b>getElementsById</b> method.</p>
#         <p id="demo"></p>

# <employee>
#     <name>Negan</name>
#     <age>40</age>
#     <email>imnegan@twd.com</email>
#     <address>
#     <city>Noida</city>
#     <state>Uttar Pradesh</state>
#     <pin>201301</address></pin>
#     <landmark>Near hill top</landmark>
# </employee>

# <note>  
#   <to>Tove</to>  
#   <from>Jani</from>  
#   <heading>Reminder</heading>  
#   <body>Don't forget me this weekend!</body>  
# </note>  

# <data>
#             <items>
#                 <item name="item1">item1abc</item>
#                 <item name="item2">item2abc</item>
#             </items>
#         </data>

# <items>
#              <data>
#                  <items name="item1">item1abc</items>
#                  <item name="item2">item2abc</item>
#              </items>
#          </data>
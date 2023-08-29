# text = """<employee>
#     <name>Negan</name>
#     <age>40</age>
#     <email>imnegan@twd.com</email>
#     <address>
#       <city>Noida</city>
#       <state>Uttar Pradesh</state>
#       <pin>201301</address></pin>
#       <landmark>Near hill top</landmark>
#   </employee>"""
text ="""
        <data>
          <items>
              <item name="item1">item1abc</item>
              <item name="item2">item2abc</item>
          </items>
        </data>"""
text = text.replace(' ','')
text = text.replace('\n','')

binlist = []
binstr = ""
binvar = ""
for i in text:
  
  if i == '<': 
    binlist.append(binvar) #있어도 되고 없어도 되고
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
    flist.append(var)
  
  elif var[0] == '<' and var[1] == "/":
    closed_tag = var.replace('</','').replace('>','')
    if closed_tag in flist[-1]:
      flist.pop()
      tf = True
    else:
      tf = False

if flist == empty_list and tf == True:
  print(True) 
else:
  print(False) 
    
    



import csv
from collections import defaultdict

f = open("forklist_move.csv","r") # 파일 이름 박아 넣지 않는다
rdr = csv.reader(f)  

list_dict = defaultdict(list) #defaultdict의 list를 사용하겠다.
for i in rdr:
  list_dict[i[1]].append([i[3],i[4],i[2]])
del(list_dict['fork_id'])


for i in list_dict.keys():
  list_dict[i] = sorted(list_dict.get(i), key = lambda x: x[2])
  
print(list_dict)

f.close()

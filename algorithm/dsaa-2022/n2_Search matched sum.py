def search_matched_sum(arr : list, target : int):
  min_index = ()
  for i in range(0,len(arr)):
    for j in range(i+1,len(arr)):
      sum = arr[i] + arr[j]
      if sum == target:
        min_index = (i,j)
        
  return min_index      

def ordered_search_matched_sum(arr : list):
    
  for i in range(len(arr)):
      min_idx = i
      for j in range(i+1, len(arr)):
          if arr[min_idx] > arr[j]:
              min_idx = j

      arr[i], arr[min_idx] = arr[min_idx], arr[i]
  return arr

import timeit
code="""
from random import randint
array = [randint(1, 100) for _ in range(50)]
sort_result = ordered_search_matched_sum(array)
result = search_matched_sum(sort_result, 3)
print(result)
"""
setup="""
def search_matched_sum(arr : list, target : int):
  min_index = ()
  for i in range(0,len(arr)):
    for j in range(i+1,len(arr)):
      sum = arr[i] + arr[j]
      if sum == target:
        min_index = (i,j)
        
  return min_index      

def ordered_search_matched_sum(arr : list):
    
  for i in range(len(arr)):
      min_idx = i
      for j in range(i+1, len(arr)):
          if arr[min_idx] > arr[j]:
              min_idx = j

      arr[i], arr[min_idx] = arr[min_idx], arr[i]
  return arr   
"""
#call the timeit() method 4 times
print(timeit.timeit(stmt = code, setup = setup, number = 10000))

# if __name__ == "__main__":
#   arr = [5, 4, 2, 1]
#   sort_result = ordered_search_matched_sum(arr)
#   result = search_matched_sum(sort_result, 3)
#   print(result) 
# # (2, 3)
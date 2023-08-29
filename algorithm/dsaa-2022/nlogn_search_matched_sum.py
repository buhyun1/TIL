# def search_matched_sum(arr : list, target : int):
#   i, j = 0, len(arr)-1
#   min_index = []
#   while i<j:
#     sum = arr[i] + arr[j]
#     if sum == target:
#         min_index.append(i)
#         min_index.append(j) 
#     elif sum>target:
#         j = -1
#     else:
#         i += 1
        
#   return min_index     

def search_matched_sum(arr : list, target : int):
    low = 0
    high = len(arr)-1
    min_index = ()
    while (low < high) :
        sum = arr[low]+ arr[high]
        if sum == target:
            min_index = (low,high)
        if target < sum:
            high -= 1
        else: 
            low += 1
    
    return min_index

def merge_sort(unsorted_array):
    if len(unsorted_array) > 1:
        mid = len(unsorted_array) // 2 
        left = unsorted_array[:mid]
        right = unsorted_array[mid:] 

        merge_sort(left)
        merge_sort(right)
        print(unsorted_array)

        i = j = k = 0
        
        while i < len(left) and j < len(right):
            if left[i] < right[j]:
                unsorted_array[k] = left[i]
                i += 1
            else:
                unsorted_array[k] = right[j]
                j += 1
            k += 1

        while i < len(left):
            unsorted_array[k] = left[i]
            i += 1
            k += 1

        while j < len(right):
            unsorted_array[k] = right[j]
            j += 1
            k += 1

import timeit
code="""
from random import randint
array = [randint(1, 100) for _ in range(1000)]
sort_result = merge_sort(array)
result = search_matched_sum(sort_result, 40)

print(result)
"""
setup="""
def search_matched_sum(arr : list, target : int):
    low = 0
    high = len(arr)-1
    min_index = []
    while (low <= high) :
        sum = arr[low]+ arr[high]
        if sum == target:
            min_index = (low,high)
        if target < sum:
            high -= 1
        else: 
            low += 1
    
    return min_index 

def merge_sort(unsorted_array):
    if len(unsorted_array) > 1:
        mid = len(unsorted_array) // 2 
        left = unsorted_array[:mid]
        right = unsorted_array[mid:] 

        merge_sort(left)
        merge_sort(right)

        i = j = k = 0
        
        while i < len(left) and j < len(right):
            if left[i] < right[j]:
                unsorted_array[k] = left[i]
                i += 1
            else:
                unsorted_array[k] = right[j]
                j += 1
            k += 1

        while i < len(left):
            unsorted_array[k] = left[i]
            i += 1
            k += 1

        while j < len(right):
            unsorted_array[k] = right[j]
            j += 1
            k += 1
    return unsorted_array
"""

#call the timeit() method 4 times
print(timeit.timeit(stmt = code, setup = setup, number = 1000))
# def partition(arr, low, high):
#     i = (low-1)        
#     pivot = arr[high]  

#     for j in range(low, high):
#         if arr[j] <= pivot:

#             i = i+1
#             arr[i], arr[j] = arr[j], arr[i]

#     arr[i+1], arr[high] = arr[high], arr[i+1]
#     return (i+1)

# def quickSort(arr, low, high):
#     if len(arr) == 1:
#         return arr
#     if low < high:
#         pi = partition(arr, low, high)

#         quickSort(arr, low, pi-1)
#         quickSort(arr, pi+1, high)



def partition(arr, low, high):
    i = (low-1)         
    pivot = arr[high]   
    print(f"pivot : {pivot}")
    print(arr[low:high])

    for j in range(low, high):
        if arr[j] <= pivot:

            i = i+1
            arr[i], arr[j] = arr[j], arr[i]
            print("swap ->" , arr)
        else:
            print("no swap ->" , arr)

    arr[i+1], arr[high] = arr[high], arr[i+1]
    print("---->" , arr)
    return (i+1)

def quickSort(arr, low, high):
    if len(arr) == 1:
        return arr
    if low < high:
        pi = partition(arr, low, high)

        print(f"low : {low}, mid : {pi}")
        quickSort(arr, low, pi-1)
        print(f"mid : {pi} , high : {high}")
        quickSort(arr, pi+1, high)


if __name__ == '__main__':
    from random import randint
    n = 10 
    array = [randint(1, 1000) for _ in range(n)]
    print("Given array is", end="\n")
    print(array)
    quickSort(array, 0, n-1)
    print("Sorted array is: ", end="\n")
    print(array)

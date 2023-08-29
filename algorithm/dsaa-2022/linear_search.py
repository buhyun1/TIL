def search(arr, x):  
    for i in range(len(arr)):
        if arr[i] == x:
            return i
    return -1


def sentinel(lst, target):
    size = len(lst)
    lst.append(target)
    i = 0
    while(lst[i]!= target):
        i += 1
    if(i == size):
        return None
    else:
        return i
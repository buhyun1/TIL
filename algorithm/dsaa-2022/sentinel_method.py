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
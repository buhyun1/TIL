def solution(k, tangerine):
    from collections import Counter
    total, count = 0, Counter(tangerine)
    for index, each in enumerate(sorted(count.values(), reverse=True)):
        total += each
        if total >= k:
            return index + 1
    return k
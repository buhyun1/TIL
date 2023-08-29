maze = {(1,1): {'E': 1, 'W': 0, 'N': 0, 'S': 1}, (2,1): {'E': 1, 'W': 0, 'N': 1, 'S': 0}, (3,1): {'E': 1, 'W': 0, 'N': 0, 'S': 1}, 
(4,1): {'E': 1, 'W': 0, 'N': 1, 'S': 0}, (5,1): {'E': 0, 'W': 0, 'N': 0, 'S': 0}, (1,2): {'E': 1, 'W': 1, 'N': 0, 'S': 0}, 
(2,2): {'E': 0, 'W': 1, 'N': 0, 'S': 1}, (3,2): {'E': 0, 'W': 1, 'N': 1, 'S': 0}, (4,2): {'E': 0, 'W': 1, 'N': 0, 'S': 1}, 
(5,2): {'E': 1, 'W': 0, 'N': 1, 'S': 0}, (1,3): {'E': 1, 'W': 1, 'N': 0, 'S': 0}, (2,3): {'E': 0, 'W': 0, 'N': 0, 'S': 0}, 
(3,3): {'E': 1, 'W': 0, 'N': 0, 'S': 1}, (4,3): {'E': 0, 'W': 0, 'N': 1, 'S': 1}, (5,3): {'E': 0, 'W': 1, 'N': 1, 'S': 0}, 
(1,4): {'E': 1, 'W': 1, 'N': 0, 'S': 0}, (2,4): {'E': 0, 'W': 0, 'N': 0, 'S': 0}, (3,4): {'E': 0, 'W': 1, 'N': 0, 'S': 1}, 
(4,4): {'E': 0, 'W': 0, 'N': 1, 'S': 1}, (5,4): {'E': 1, 'W': 0, 'N': 1, 'S': 0}, (1,5): {'E': 0, 'W': 1, 'N': 0, 'S': 1}, 
(2,5): {'E': 0, 'W': 0, 'N': 1, 'S': 1}, (3,5): {'E': 0, 'W': 0, 'N': 1, 'S': 1}, (4,5): {'E': 0, 'W': 0, 'N': 1, 'S': 1}, 
(5,5): {'E': 0, 'W': 1, 'N': 1, 'S': 0}}

solution = [(5,5), (4,5), (3,5), (2,5), (1,5), (1,4), (1,3), (1,2), (1,1)]

def plus_way(way, path):
    a = []
    for i in path:
        a.append(i)
    way.append(a)
    return way

start = (5,5)
end = (1,1)
point = start
way = []
path = []
a = []
path.append(point)
count = 0
while len(path) > 0:
    if point == end:
        plus_way(way, path)
        path.pop()
        point = path[-1]

    elif maze[point]['E'] == 1:
        maze[point]['E'] = 2
        point = (point[0], point[1]+1)
        path.append(point)
        maze[point]['W'] = 2
    
    elif maze[point]['W'] == 1:
        maze[point]['W'] = 2
        point = (point[0], point[1]-1)
        path.append(point)
        maze[point]['E'] = 2
    
    elif maze[point]['N'] == 1:
        maze[point]['N'] = 2
        point = (point[0]-1, point[1])
        path.append(point)
        maze[point]['S'] = 2

    elif maze[point]['S'] == 1:
        maze[point]['S'] = 2
        point = (point[0]+1, point[1])
        path.append(point)
        maze[point]['N'] = 2

    else:
        path.pop()
        if len(path) > 0:
            point = path[len(path)-1]

print(way)

way.sort(key=lambda x: len(x))
print(way[0])

if way[0] == solution:
    print(True)
else:
    print(False)
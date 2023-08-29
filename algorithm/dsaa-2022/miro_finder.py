
def get_maze_answer(maze : dict) -> list:
    """ 미로에 대한 정보를 dict 타입으로 입력 받아서 해당 미로의 답을 반환한다.
    Args:
        maze (dict) : 미로에 대한 정보를 포함하고 있으며, 해당 정보는 위치 정보와 이동 가능한 방향에 대한 정보를 포함한다.
    Example:
        maze = {(1, 1): {'E': 0, 'W': 0, 'N': 0, 'S': 1},
                (2, 1): {'E': 1, 'W': 0, 'N': 1, 'S': 0},
                (3, 1): {'E': 1, 'W': 0, 'N': 0, 'S': 0},
                (1, 2): {'E': 1, 'W': 0, 'N': 0, 'S': 0},
                (2, 2): {'E': 0, 'W': 1, 'N': 0, 'S': 1},
                (3, 2): {'E': 1, 'W': 1, 'N': 1, 'S': 0},
                (1, 3): {'E': 0, 'W': 1, 'N': 0, 'S': 1},
                (2, 3): {'E': 0, 'W': 0, 'N': 1, 'S': 1},
                (3, 3): {'E': 0, 'W': 1, 'N': 1, 'S': 0}}
    Returns:
        maze_solution (list) : `maze` 데이터를 바탕으로 최적의 이동 솔루션을 list 타입으로 출력한다.
                                list의 값은 공간 위치에 대한 정보를 포함한다.
    Example:
        >>> solution = get_maze_answer(maze)
        >>> solution
            [(3, 3), (3, 2), (2, 2), (2, 1), (1, 1)]
    """

    start = (5,5)
    end = (1,1)
    point = start
    way = []
    path = []
    path.append(point)
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

    # print(way)

    way.sort(key=lambda x: len(x))
    # print(way[0])

    # if way[0] == solution:
    #     print(True)
    # else:
    #     print(False)

    return way[0]

def plus_way(way, path):
    a = []
    for i in path:
        a.append(i)
    way.append(a)
    return way
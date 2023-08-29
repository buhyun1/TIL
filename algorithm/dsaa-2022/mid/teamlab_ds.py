from typing import Any


class Node:  # 알아서 (object) 상속
    """
    A class to represent a Node for an explanation of data structure.
    ...

    Attributes
    ----------
    data : Any
        data that user store on Node instance
    next : Node
        object connected to the next node in a linked list.

    """

    def __init__(self, data: Any = None, next: 'Node' = None) -> None:
        # 1.none : 초기 값이 없다. 2.none :리턴 값이 없다
        """
        Args:
            data (Any, optional): data that user store on Node instance
            next (Node, optional): object connected to the next node in a linked list

        Returns:
            None
        """
        self._data = data
        self._next = None

    @property
    def data(self):
        """ data that user store on Node instance """
        return self._data

    @property  # getter
    def next(self):
        """ An object connected to the next node in a linked list """
        return self._next

    @data.setter  # setter
    def data(self, value: Any) -> None:
        self._data = value

    @next.setter
    def next(self, node: 'Node') -> None:
        self._next = node

    def __str__(self) -> str:  # print(a)
        return_str = f"I have a data : {self._data}\n"  \
            + f"I have a next node : {id(self._next)}"
        return return_str

    def __repr__(self) -> str:  # -> 리턴 형식 뭔지 표시 #a
        return_str = f"Node({self._data})"
        return return_str

    def __add__(self, other) -> None:
        self._next = other


class LinkedListBag(object):  # 질문
    """
    ~~~~~~
    """

    def __init__(self, first_node: Node = None) -> None:
        super().__init__()  # super 상속
        self._head = first_node
        self._tail = first_node

        if first_node is None:
            self._size = 0
        else:
            self._size = self._count()  # O(n) 데이터개수만큼 한번만 연산 시 counter 반환

    # __contain__ in이라는 명령어를 썼을때 작동, 이값이 포함되었는가

    def __contains__(self, target: int):
        cur_node = self._head
        while cur_node is not None:
            if cur_node.data == target:
                return True
            cur_node = cur_node.next
        else:
            return False

    def _count(self) -> int:
        counter = 0
        cur_node = self._head
        while cur_node is not None:
            counter += 1
            cur_node = cur_node.next
        return counter
    # boolean or None 리턴, 불린일 경우 성공 or 실패 정보를 전달

    def append(self, new_node: Node) -> bool:
        try:
            if self._size == 0:
                self._head = new_node
                # initialization을 하지 않았을 경우 초기 노드를 만들어줌, O(n)
                self._tail = new_node
            else:
                # 뉴노드를 가리킴 head만 있을 경우 처음부터 시작이므로 O(1)
                self._tail.next = new_node
                self._tail = new_node  # 뉴노드가 됨
            self._size += 1
            return True
        except Exception as e:
            print(e)
            return False

    def insert(self, new_node: Node, index_number: int) -> bool:
        index_counter = 0
        cur_node = self._head

        # 0번째 노드에 추가시킬 경우, 앞의 노드가 없기에 뉴노드의 다음이 헤드, 뉴노드가 헤드가 됨, 사이즈 +1
        # self._head가 None일 경우, 인덱스 0번에 넣을때 방법은???
        if index_number == 0:
            new_node.next = self._head
            self._head = new_node
            self._size += 1
            return True
        # insert 0번시 처리
        while cur_node is not None:
            if index_number == index_counter:
                pred_node.next = new_node  # 0번째에 추가 안됨, pred_node가 없기 때문
                new_node.next = cur_node
                self._size += 1
                return True
            # pred_node.next = new_node
            else:
                pred_node = cur_node
                cur_node = cur_node.next
                index_counter += 1
        return False

    def remove(self, target_value: int) -> bool:
        cur_node = self._head

        while cur_node is not None:
            if cur_node.data == target_value:
                pred_node.next = cur_node.next
                del(cur_node)
                self._size -= 1
                return True
            else:
                pred_node = cur_node
                cur_node = cur_node.next
        return False  # false or 값이 없다는 error

    # 1. while을 이용한 count(단점 : len을 부를때마다 n번 연산)
    def __len__(self):
        return self._size

    def __repr__(self) -> str:
        cur_node = self._head
        if self._size == 0:
            return None

        return_str = ""
        while cur_node is not None:
            return_str += f"{cur_node.data} -> "
            cur_node = cur_node.next
        return_str += f"End of Linked List"
        return return_str

# 객체 하나 생성 후 헤드의 주소만 던져줌
    def __iter__(self):
        return _BagIterator(self._head)

    @property
    def head(self) -> Node:
        return self._head

# iterable한 형태 -> for value in ~~~ 사용 시 값을 가져옴 하나씩
# iterator을 만들기 위해서는 next가 있어야댐. self를 복사해놓고 다른곳에 값을 사용


class _BagIterator():
    def __init__(self, head_node) -> None:
        self._cur_node = head_node

# 한번 호출시 부름
    def __iter__(self):
        return self

# 값을 하나씩 뽑아오게 함, None이 아닐경우 자기 자신 반환
# for loop 시 한번씩 next
    def __next__(self):
        if self._cur_node is None:
            raise StopIteration
        else:
            node = self._cur_node  # 현재 노드를 저장하고 현재 노드에 다음 노드 대치
            self._cur_node = self._cur_node.next
            return node

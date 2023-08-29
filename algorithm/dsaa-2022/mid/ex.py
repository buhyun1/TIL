from typing import Any  #any 타입 쓸때 import 해두기

class Node:     
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
    
    def __init__(self, data : Any = None, next : 'Node' = None) -> None:
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

    @property
    def next(self):
        """ An object connected to the next node in a linked list """
        return self._next

    @data.setter
    def data(self, value : Any) -> None:
        self._data = value
    
    @next.setter
    def next(self, node : 'Node') -> None:
        self._next = node

    def __str__(self) -> str: #print(a)
        return_str = f"I have a data : {self._data}\n"  \
                    + f"I have a next node : {id(self._next)}"
        return return_str

    def __repr__(self) -> str: #a
        return_str = f"Node({self._data})" 
        return return_str

    def __add__(self, other) -> None:
        self._next = other

class LinkedListBag():
    def __init__(self, first_node : Node  = None) -> None: #node형태로 오고 초기값 0 
        self._head = first_node  
        self._tail = first_node 
        if first_node is not None:
            self._size = 1
        else:
            self._size = 0
        
    def append(self, new_node : Node) -> None:
      try:
        if self._size == 0:
            self._head = new_node
            self._tail = new_node
        else:
            self._tail.next = new_node
            self._tail = new_node
            self._size += 1
        self.size += 1
        return True

      except Exception as e:
        print(e)
        return False

    def insert(self, index_number : int, new_node : Node) -> bool:
        list_index = 0
        cur_node = self._head
        if index_number == 0:
            new_node.next = cur_node
            self._head = new_node
            self._size += 1
            return True

        while cur_node is not None:
            if list_index == index_number:
                pred_node.next = new_node
                new_node.next = cur_node
                self._size += 1
                return True
            list_index += 1
            pred_node = cur_node 
            cur_node = cur_node.next
        return False

    def remove(self, target_value : int) -> bool:
        cur_node = self._head
        pred_node = cur_node 
        while cur_node is not None:
            if cur_node.data == target_value:
                pred_node.next = cur_node.next
                del(cur_node)
                self._size -= 1
                return True
            pred_node = cur_node 
            cur_node = cur_node.next
        return False        

    def __len__(self):
        return self._size
    
    def __contains__(self, target : int): # in 사용가능 
        cur_node = self._head
        while cur_node is not None:
            if cur_node.data == target:
                return True
            cur_node = cur_node.next
        return False
    
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

    def __iter__(self):
        return _BagIterator(self._head)

class _BagIterator():
    def __init__(self, head_node : Node) -> None:
        self._cur_node = head_node
    
    def __iter__ (self):
        return self
    
    def __next__(self):
        if self._cur_node is None:
            raise StopIteration
        else:
            node = self._cur_node
            self._cur_node = self._cur_node.next
            return node


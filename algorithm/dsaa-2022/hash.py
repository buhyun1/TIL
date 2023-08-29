import numpy as np

#separate chaining in python
class Node() :
    
    def __init__(self,value) :
        self.value = value
        self.next = None

class Linked_List() :
  pass            
           
class Hash_table() :
    def __init__(self,size) :
        self.size = size
        self.hashtable = [None]*self.size
        for x in range(self.size) :
            self.hashtable[x] = Linked_List()
        
    def hash(self,key) :
        # Hash function is h(x) = x%10
        return key%10
    
    def insert_key(self,key) :
        index = self.hash(key)
        self.hashtable[index].insert(key)
        
    def search_key(self,key) :
        index = self.hash(key)
        boolean = self.hashtable[index].search(key)
        return boolean
    
    def print_HT(self) :
        print("Hash table is :- \n")
        print("Index \t\tValues\n")
        for x in range(self.size) :
            print(x,end="\t\t")
            self.hashtable[x].print_LL()
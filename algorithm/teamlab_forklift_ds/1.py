class Vector: 
    def __init__(self, *args): 
        self.args = args 
    
    def __add__(self, other): 
        a = [arg1 + arg2 for arg1, arg2 in zip(self.args, other.args)] 
        return self.__class__(*a) 
    
    def __repr__(self): 
        return self.__class__.__name__ + str(self.args) 

v1 = Vector(1, 4, 8, 9) 
v2 = Vector(5, 3, 2, 1)

print(v1) 
print(v2) 
print(v1 + v2) 
print(v1 + v1 + v2 + v2)
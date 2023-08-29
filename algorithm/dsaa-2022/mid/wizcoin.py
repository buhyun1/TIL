import collections.abc
import operator

class WizCoin:  # 1.class 문을 통해 WizCoin이라는 새로운 클래스 정의
    def __init__(self, galleons, sickles, knuts):  # 메소드가 객체에서 호출되면 self 파라미터가 객체에 자동으로 전달된다.
        """galleons, sickles, knuts로 새로운 WizCoin 객체를 생성한다."""
        self.galleons = galleons  # 일반적으로 __init__() 에서 파라미터 이름이 속성 이름과 같다. self가 앞에 붙은 경우 객체의 속성, 안 붙은 경우 파라미터.
        self.sickles = sickles
        self.knuts = knuts
        # 참고로, __init__() 메소드에는 return문이 존재해서는 안 된다

    def value(self):
        """이 WizCoin 객체에 포함된 모든 동전의 가치(크넛 단위)"""
        return (self.galleons * 17 * 29) + (self.sickles * 29) + (self.knuts)

    def weightInGrams(self):
        """그램 단위로 동전의 무게를 반환한다."""
        return (self.galleons * 31.103) + (self.sickles * 11.34) + (self.knuts * 5.0)

    def __repr__(self):
        return f'{self.__class__.__qualname__}({self.galleons}, {self.sickles}, {self.knuts})'

    def __str__(self):
        return f'{self.galleons}g, {self.sickles}s, {self.knuts}k'

    def __add__(self,other):
        if not isinstance(other, WizCoin):
            return NotImplemented
    
        return WizCoin(other.galleons + self.galleons, other.sickles + self.sickles, other.knuts + self.knuts)

    
    def __radd__(self,other):
        return self.__add__(other)

    def __iadd__(self,other):
        if not isinstance(other, WizCoin):
            return NotImplemented
        
        self.galleons += other.galleons
        self.sickles += other.sickles
        self.knuts += other.knuts
        return self

    def _comparisonOperatorHelper(self, operatorFunc, other):
        if isinstance(other, WizCoin):
            return operatorFunc(self.total, other.total)
        elif isinstance(other, (int,float)):
            return operatorFunc(self.total, other)
        elif isinstance(other, collections.abc.Sequence):
            otherValue = (other[0]*17*29) +(other[1]*29) +other[2]
            return operatorFunc(self.total, otherValue)
        elif operatorFunc == operator.eq:
            return False
        else:
            return NotImplemented
    
    def __eq__(self,other):
        return self._comparisonOperatorHelper(operator.eq, other)

    @property
    def total(self):
        return (self.galleons * 17 * 29) + (self.sickles * 29) + (self.knuts)
    def lrotate(self):
        # 현재 트리의 기존 root를 A라고 두자
        A = self.node
        # 기존 root의 right child를 B라고 두자
        B = self.node.right.node
        # B의 left child(위 그림에서 베타)를 T라고 두자
        T = B.left.node
        
        # B를 새로운 root로 지정
        self.node = B
        # A를 root(B)의 새로운 left child로 지정
        B.left.node = A
        # T(위 그림에서 베타)를 A의 새로운 right child로 지정
        A.right.node = T
from ortools.linear_solver import pywraplp 
# C++로 개발되어 python으로 사용할 수 있게 만든 패키지  
import random

def main():
    M = 1000 # big M
    N = 5 # total number
    processing={}

    solver = pywraplp.Solver.CreateSolver('GLOP') 
    # GLOP는 OR-Tools의 linear solver로 이해하면 된다.
    # 주요 OR 도구 선형 최적화 해결사는 Google의 사내 선형 프로그래밍 해결사입니다.
    # Glop 빠르고 메모리 효율이 높으며 통계적으로 안정적
    # 기본 OR 도구 MIP 솔버는 SCIP입니다. / 타사 솔버 SCIP
    infinity=solver.infinity()

    # Variables.
    p = {} # 작업 시간
    for i in range(N):
        p[i] = solver.NumVar(0.0,infinity,'processing[%d]'%i)
        processing[i] = random.randint(0,10)

    c = {} # 완료 시간
    for i in range (N):
        c[i] = solver.NumVar(0.0,infinity,'c[%d]'%i) 
    # 소수점 시작 가능 numvar.
    
    x= {}
    for i in range(N):
        for j in range(N):
            x[i,j] = solver.IntVar(0,1,'x[%d][%d]'%(i,j))
    
    
    # Constraints.
    for i in range (N):
        solver.Add(c[i] >= processing[i])
    
    for i in range (N):
        for j in range (N):
            if i!=j:
                solver.Add(c[i] + processing[j] <= c[j] + M*(1-x[i,j]))
                solver.Add(c[j] + processing[i] <= c[i] + M*x[i,j])

    # Objective.
    objective = solver.Objective()
    for i in range(N): 
        objective.SetCoefficient(c[i] , 1.0)
    objective.SetMinimization()

    status = solver.Solve()

    if status == pywraplp.Solver.OPTIMAL:
        print('Solution:')
        print('Objective value =', solver.Objective().Value())
        for i in range(N):
            print('completion_time =', c[i].solution_value())
        
    else:
        print('The problem does not have an optimal solution.')


if __name__ == '__main__':
    main()

from ortools.linear_solver import pywraplp

solver = pywraplp.Solver.CreateSolver('SCIP')

job=[1,2,3,4,5]
N=len(job)
bigM=1000
infinity=solver.infinity()
#p={}
#for i in range(num_jobs):
#    p[i]=solver.NumVar(0.0,infinity,'processing[%d]'%i)
processing_time=[2.0,5.0,1.0,3.0,5.0]

x={}
for i in range(N):
    for j in range(N):
        x[i,j]=solver.IntVar(0,1,'x[%d][%d]'%(i,j))
        # print(x[i,j])

completion_time={}
for i in range (N):
    completion_time[i]= solver.NumVar(0.0,infinity,'completion_Time[%d]'%i)

for i in range (N):
    solver.Add(completion_time[i]>=processing_time[i])
    for j in range (N):
        if i!=j:
            solver.Add(completion_time[i]+processing_time[j]<=completion_time[j]+bigM*(1-x[i,j]))
            solver.Add(completion_time[j]+processing_time[i]<=completion_time[i]+bigM*x[i,j])

objective = solver.Objective()
for i in range(N):
    objective.SetCoefficient(completion_time[i], 1.0)
objective.SetMinimization()

status = solver.Solve()

if status == pywraplp.Solver.OPTIMAL:
    print('Solution:')
    print('Objective value =', solver.Objective().Value())        
    for i in range (N):
        print('completion_time =', completion_time[i].solution_value())
        for j in range (N):
            print('x[%d][%d] = '%(i,j), x[i,j].solution_value())
else:
    print('The problem does not have an optimal solution.')


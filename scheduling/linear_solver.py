from ortools.linear_solver import pywraplp

solver = pywraplp.Solver.CreateSolver('GLOP')

# declare variable x, with lower bound 0 and no upper bound
x = solver.NumVar(0, solver.infinity(), "x")
# declare variable y, with lower bound 0 and no upper bound
y = solver.NumVar(0, solver.infinity(), "y")
# declare variable z, with lower bound 0 and no upper bound
z = solver.NumVar(0, solver.infinity(), "z")

# add an objective to the solver
objective = solver.Objective()
# add terms to objective such that objective function results from it
objective.SetCoefficient(x, 1)
objective.SetCoefficient(y, 2)
objective.SetCoefficient(z, 3)
# declare problem as an maximization problem
objective.SetMaximization()

# add constraint: 2x + y + z <= 20
constraint = solver.Constraint(-solver.infinity(),20)
constraint.SetCoefficient(x, 2)
constraint.SetCoefficient(y, 1)
constraint.SetCoefficient(z, 1)
# add constraint: x + y + z <= 15
constraint = solver.Constraint(-solver.infinity(),15)
constraint.SetCoefficient(x, 1)
constraint.SetCoefficient(y, 1)
constraint.SetCoefficient(z, 1)
# add constraint: x - y - z >= 0 
constraint = solver.Constraint(0,solver.infinity())
constraint.SetCoefficient(x, 1)
constraint.SetCoefficient(y, -1)
constraint.SetCoefficient(z, -1)

solver.Solve()

print("x_opt: ", x.solution_value())
print("y_opt: ", y.solution_value())
print("z_opt: ", z.solution_value())
print("optimal value: " + str(x.solution_value()+2*y.solution_value()+3*z.solution_value()))
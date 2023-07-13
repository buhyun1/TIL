"""Solve a multiple knapsack problem using a MIP solver."""
from ortools.linear_solver import pywraplp #  최적화 기능을 포함한 라이브러리

# http://google.github.io/ortools/javadoc/com/google/ortools/algorithms/KnapsackSolver.html

# 최대 가치의 상품들을 5개의 상자로 묶는 가장 좋은 방법을 찾는 방법

def main():
    data = {}
    data['weights'] = [
        48, 30, 42, 36, 36, 48, 42, 42, 36, 24, 30, 30, 42, 36, 36
    ]
    data['values'] = [
        10, 30, 25, 50, 35, 30, 15, 40, 30, 35, 45, 10, 20, 30, 25
    ]
    assert len(data['weights']) == len(data['values']) # 반드시 어떤 영역에 속하는 것을 보증하기 위해 사용하는 가정 설정문
    data['num_items'] = len(data['weights'])
    data['all_items'] = range(data['num_items'])

    data['bin_capacities'] = [100, 100, 100, 100, 100]

    data['num_bins'] = len(data['bin_capacities'])
    data['all_bins'] = range(data['num_bins'])

    # Create the mip solver with the SCIP backend.
    solver = pywraplp.Solver.CreateSolver('SCIP')
    if solver is None:
        print('SCIP solver unavailable.')
        return

    # Variables.
    # x[i, b] = 1 if item i is packed in bin b.
    x = {}
    for i in data['all_items']:
        for b in data['all_bins']:
            x[i, b] = solver.BoolVar(f'x_{i}_{b}') # x[(i, j)]은 i 항목이 bin j에 배치되면 1이 되고 그렇지 않으면 0

    # Constraints.
    # Each item is assigned to at most one bin.
    for i in data['all_items']:
        solver.Add(sum(x[i, b] for b in data['all_bins']) <= 1) #각 항목은 최대 한 개의 구간에 놓일 수 있다

    # The amount packed in each bin cannot exceed its capacity.
    for b in data['all_bins']:
        solver.Add(
            sum(x[i, b] * data['weights'][i]
                for i in data['all_items']) <= data['bin_capacities'][b]) #각 구간에 포함되는 총 중량은 용량을 초과할 수 없다

    # Objective.
    # Maximize total value of packed items.
    objective = solver.Objective()
    for i in data['all_items']:
        for b in data['all_bins']:
            objective.SetCoefficient(x[i, b], data['values'][i])
    objective.SetMaximization()

    status = solver.Solve()

    if status == pywraplp.Solver.OPTIMAL: # 출력문
        print(f'Total packed value: {objective.Value()}')
        total_weight = 0
        for b in data['all_bins']:
            print(f'Bin {b}')
            bin_weight = 0
            bin_value = 0
            for i in data['all_items']:
                if x[i, b].solution_value() > 0:
                    print(
                        f"Item {i} weight: {data['weights'][i]} value: {data['values'][i]}"
                    )
                    bin_weight += data['weights'][i]
                    bin_value += data['values'][i]
            print(f'Packed bin weight: {bin_weight}')
            print(f'Packed bin value: {bin_value}\n')
            total_weight += bin_weight
        print(f'Total packed weight: {total_weight}')
    else:
        print('The problem does not have an optimal solution.')


if __name__ == '__main__':
    main()
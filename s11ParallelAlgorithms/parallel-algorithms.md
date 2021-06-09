# Parallel Algorithms

- Parallel algorithms can execute different tasks simultaneously (where as sequential algorithms execute tasks one by one)
- Not all algorithms can be parallelised. Algorithms that can be include finding prime numbers in a range (can make different subsets and execute them on separate threads). Numerical methods usually cannot as the execution of the n+1th step requires the execution of the nth step (eg. Euler method)

## Problems of Parallel Algorithms
1. Communication
  - Efficiency of a parallel algorithm has to take into consideration more than just time and memory complexity, it also must consider inter-thread communication which can slow down the execution ('parallel slowdown')
2. Load Balance 
  - The work of the algorithm must be split evenly amongst the threads

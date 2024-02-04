
import math 
import numpy as np 

#creates the array of coefficietns
A = np.array([[2, 3, -4], [1, -2, 1], [3, 1, -1]])

#creates the array of right hand of the equaiton
B = np.array([5, 0, 8])

#solves the equations
x = np.linalg.solve(A,B)

print(x)
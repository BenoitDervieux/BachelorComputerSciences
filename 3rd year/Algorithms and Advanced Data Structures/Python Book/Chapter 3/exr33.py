import matplotlib.pyplot as plt 
import numpy as np
import math

xpoints = np.array([x for x in range(1, 25)])
ypoints1 = np.array([40*x**2 for x in range(1, 25)])
ypoints2 = np.array([2*x**3 for x in range(1, 25)])

plt.plot(xpoints, ypoints1, color='yellow')
plt.plot(xpoints, ypoints2, color='chartreuse')
plt.show()
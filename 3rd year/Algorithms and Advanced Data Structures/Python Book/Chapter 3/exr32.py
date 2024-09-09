import matplotlib.pyplot as plt 
import numpy as np
import math

xpoints = np.array([x for x in range(1, 100)])
ypoints1 = np.array([2*x**2 for x in range(1, 100)])
ypoints2 = np.array([8*x*np.log2(x) for x in range(1, 100)])

plt.plot(xpoints, ypoints1, color='yellow')
plt.plot(xpoints, ypoints2, color='chartreuse')
plt.show()

ypoints3 = np.array([(1/4) * x - np.log(x) for x in range(1, 100)])

plt.plot(xpoints, ypoints3, color='yellow')
plt.show()
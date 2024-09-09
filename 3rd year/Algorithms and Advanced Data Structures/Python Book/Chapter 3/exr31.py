import matplotlib.pyplot as plt 
import numpy as np
import math

xpoints = np.array([x for x in range(1, 100)])
ypoints1 = np.array([8*x for x in range(1, 100)])
ypoints2 = np.array([np.log2(x) for x in range(1, 100)])
ypoints3 = np.array([2*x**2 for x in range(1, 100)])
ypoints4 = np.array([x**3 for x in range(1,100)])
ypoints5 = np.array([2**x for x in range(1, 100)])

plt.plot(xpoints, ypoints1, color='yellow')
plt.plot(xpoints, ypoints2, color='chartreuse')
plt.plot(xpoints, ypoints3, color='blue')
plt.plot(xpoints, ypoints4, color='red')
plt.plot(xpoints, ypoints5, color='orange')
plt.xscale('log')
plt.yscale('log')
plt.show()
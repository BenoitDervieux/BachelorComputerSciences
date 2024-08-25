import pandas as pd 
import numpy as np 
import os 
import matplotlib.pyplot as plt
from sklearn.manifold import MDS, Isomap, TSNE
import random 
from mpl_toolkits.mplot3d import Axes3D

FIGURESIZE=(8,8)

# # Mnist
# train_data = np.loadtxt('mnist_test.csv', delimiter=',', dtype='float64')
# X = train_data[:,1:]
# X = X / 255
# y = train_data[:,0]

# WineQT
# data = np.loadtxt('WineQT.csv', delimiter=',', dtype='float64', encoding='utf-8-sig', skiprows=1)
# X = data[:, :11] 
# y = data[:, 11]

# Abalone
data = np.loadtxt('abalone_modified.csv', delimiter=',', dtype='float64', encoding='utf-8-sig', skiprows=1)
X = data[:, :8] 
y = data[:, 8]

tsne = TSNE(n_components=2, perplexity=5)
y4 = tsne.fit_transform(X)

plt.figure(figsize=FIGURESIZE)
plt.scatter(y4[:,0], y4[:,1], c=y, edgecolor='none', alpha=0.8, cmap=plt.cm.get_cmap('viridis'))
plt.xlabel("Principal Component 1")
plt.ylabel("Principal Component 2")
plt.colorbar()
plt.show()
print("done")
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt
import numpy as np

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

pca = PCA(n_components=2)
X_projected = pca.fit_transform(X)
x1 = X_projected[:, 0]
x2 = X_projected[:, 1]

plt.scatter(x1, x2, c=y, edgecolor='none', alpha=0.8, cmap=plt.cm.get_cmap('viridis'))
plt.xlabel("Principal Component 1")
plt.ylabel("Principal Component 2")
plt.colorbar()
plt.show()
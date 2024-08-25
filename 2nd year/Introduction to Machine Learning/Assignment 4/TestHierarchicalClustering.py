from sklearn.cluster import AgglomerativeClustering
import numpy as np
import matplotlib.pyplot as plt



# data = np.loadtxt('dist.csv', delimiter=';', dtype='float64', encoding='utf-8-sig')
# X = data[:, :2]

# WineQT
# data = np.loadtxt('WineQT.csv', delimiter=',', dtype='float64', encoding='utf-8-sig', skiprows=1)
# X = data[:, :11] 
# y = data[:, 11]

# data = np.loadtxt('abalone_modified.csv', delimiter=',', dtype='float64', encoding='utf-8-sig', skiprows=1)
# X = data[:, :8] 
# y = data[:, 8]

mnist_data = np.loadtxt('mnist_test.csv', delimiter=',', dtype='float64')
mnist_data = mnist_data[:1000]
X = mnist_data[:,1:]
X = X / 255
y = mnist_data[:,0]

# Perform hierarchical clustering
agg_cluster = AgglomerativeClustering(n_clusters=3)
cluster_labels = agg_cluster.fit_predict(X)

# Print the cluster labels
print("Cluster Labels:", cluster_labels)

plt.figure(figsize=(8, 6))
plt.scatter(X[:, 0], X[:, 1], c=y, cmap='viridis')
plt.title('Hierarchical Clustering Results')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.colorbar(label='Cluster Label')
plt.show()
import numpy as np
import matplotlib.pyplot as plt
import random

class BisectingKMeans:
    
    def __init__(self):
        pass
    
    def split_in_two(self, X):
        m_length = X.shape[0]
        if m_length % 2 == 0:
            m_length = int(m_length/2)
            return X[:m_length], X[m_length:]
        elif m_length % 2 == 1:
            m_length = int(m_length/2)
            return X[:m_length], X[m_length:]
    
    
    def sort_by_sse(self, X):
        centroid = self.calculate_centroids(X)
        return self.calculate_sse(X, centroid)
    
    def calculate_centroids(self, X):
        centroid_coordinates = []
        for i in range(X.shape[1]):
            centroid_coordinates.append(np.sum(X[:,i])/X.shape[0])
        return centroid_coordinates
    
    def calculate_sse(self, X, centroid):
        result = 0
        for i in range(X.shape[0]):
            for j in range(X.shape[1]-1):
                result += (X[i][j] - centroid[j])**2
        return result

    def reassign(self, X1, X2, centroid1, centroid2):
        X = np.vstack((X1, X2))
        split1 = np.empty((0, X.shape[1]))
        split2 = np.empty((0, X.shape[1]))
        for i in range(X.shape[0]):
        # For every elements in X1
            #First we initialize a calculation number
            calculation1 = 0
            calculation2 = 0
            for j in range(X.shape[1] - 1):
                calculation1 += (X[i][j] - centroid1[j])**2
                calculation2 += (X[i][j] - centroid2[j])**2
            if calculation1 > calculation2:
                split1 = np.vstack((split1, X[i]))
            else:
                split2 = np.vstack((split2, X[i]))
        return split2, split1
    
    def bkmeans(self, X, k, iteration, bool):
        list_of_clusters = []
        index_array = np.arange(X.shape[0]).reshape(-1, 1)
        X = np.hstack((X, index_array))
        list_of_clusters.append(X)
        for i in range(k-1):
            list_of_clusters.sort(key=self.sort_by_sse, reverse=True)
            split1, split2 = self.split_in_two(list_of_clusters.pop(0))
            for j in range(iteration):
                centroids1 = self.calculate_centroids(split1)
                centroids2 = self.calculate_centroids(split2)
                split1, split2 = self.reassign(split1, split2, centroids1, centroids2)
            list_of_clusters.append(split1)
            list_of_clusters.append(split2)
        output = np.empty(X.shape[0])
        for i in range(len(list_of_clusters)):
            for j in range(len(list_of_clusters[i])):
                output[int(list_of_clusters[i][j][2])] = i
        if bool is True:
            for i in range(len(list_of_clusters)):
                r = lambda: random.randint(0,255)
                colorToPut = '#%02X%02X%02X' % (r(), r(), r())
                plt.scatter(list_of_clusters[i][:,0], list_of_clusters[i][:,1], color = colorToPut)
                centroid = self.calculate_centroids(list_of_clusters[i])
                plt.scatter(centroid[0], centroid[1], color = '#FF0000', marker='s')
            plt.show()
        return output

    
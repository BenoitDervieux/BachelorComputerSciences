import numpy as np
import matplotlib.pyplot as plt
import random

class KmeansImpl:
    
    def __init__(self):
        self.centroids = None
        pass


    def _create_list_centroids(self, X, centroid):
        list_of_centroids = []
        Xmax = [np.max(X[x]) for x in range(X.shape[1])]
        Xmin = [np.min(X[x]) for x in range(X.shape[1])]
        for i in range(centroid):
            coordinates_centroid = []
            for j in range(X.shape[1]):
                number = random.uniform(Xmax[j], Xmin[j])
                coordinates_centroid.append(number if number != 0 else 1)
            list_of_centroids.append(coordinates_centroid)
        return list_of_centroids
    
    def _add_cluster_column(self, X):
        # Here we add a index/cluster column
        index_array = np.zeros(X.shape[0]).reshape(-1, 1)
        X = np.hstack((X, index_array))
        return X
    
    def _assign_cluster_to_points(self, X, list_of_centroids):
        for _ in range(X.shape[0]):
            index_points =[]
            for i in list_of_centroids:
                result = 0
                for p in range(X.shape[1] - 1):
                    result += (X[_,p] - i[p])**2
                index_points.append(result)
            min_value = min(index_points)
            X[_, (X.shape[1]-1)] = index_points.index(min_value)
        return X
    
    def _recalculate_centroids(self, X, centroids):
        # Here is an empty list of centroids
        list_of_centroid_temp = []
        # We go through all the centroids
        for i in range(centroids):
            # We initiate a list of coordinates
            list_of_coordinates = []
            # We go through all the points
            for j in range(X.shape[0]):
                # If the points has the centroids in its columns
                if X[j, (X.shape[1]-1)] == i:
                    # We create some coordinate list
                    coordinates = []
                    # We go through all the features of X, here there are 
                    for l in range(X.shape[1]-1):
                        # Here we add the 1 coordinate directly
                        coordinates.append(X[j,l])
                    # Here we add the both coordinate
                    list_of_coordinates.append(coordinates)
            # Here there are all the coordinate arrays
            # Now we want calculate the mean of every coordinate for every feature
            coord_to_add = []
            # For every feature minus the cluster
            for t in range(X.shape[1]-1):
                summation = 0
                for u in range(len(list_of_coordinates)):
                    summation += list_of_coordinates[u][t]
                # print(list_of_coordinates)
                divide = len(list_of_coordinates) if len(list_of_coordinates) != 0 else 1
                summation = summation / divide
                coord_to_add.append(summation)
            list_of_centroid_temp.append(coord_to_add)
            self.centroids = list_of_centroid_temp
        return list_of_centroid_temp
    
    def kmeans(self, X, centroids=3, iterations=10, show=True):
        #Here we assigned new centroids randomly choosen
        # We take some random values from the range of values
        list_of_centroids = self._create_list_centroids(X, centroids)

        X = self._add_cluster_column(X)

        for i in range(iterations):
            ##### Here we are going to assign each point to a centroid/cluster
            ##### This should as well be in a loop
            X = self._assign_cluster_to_points(X, list_of_centroids)
            # print(list_of_centroids)
            # Here there is a problem
            list_of_centroids = self._recalculate_centroids(X, centroids)
        
            ## Now we are going to recalculate the centroids
        if show == True:
            for i in range(X.shape[0]):
                plt.scatter(X[:,0], X[:,1], c=X[:, X.shape[1]-1], edgecolor='none', alpha=0.8, cmap='viridis')
                for i in list_of_centroids:
                    plt.scatter(i[0], i[1], color = '#FF0000', marker='s')
            plt.show()
        return X[:, X.shape[1]-1]

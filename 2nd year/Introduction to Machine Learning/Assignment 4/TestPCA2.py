import numpy as np 

class PCA:
    
    def __init__(self, n_components):
        self.n_components = n_components
        self.components = None
        self.mean = None
    
    def fit(self, X):
        # Mean centering
        self.mean = np.mean(X, axis = 0)
        X = X - self.mean
        
        #covariance, this function needs samples as columns
        cov = np.cov(X.T)
        
        #eigenvectors and eigenvalues
        eigenvectors, eigenvalues = np.linalg.eig(cov)
                
        eigenvectors = eigenvectors.T
        
        #sort  eigenvectors
        idxs = np.argsort(eigenvalues)[::-1]
        eigenvalues = eigenvalues[idxs]
        eigenvectors = eigenvectors[idxs]
        
        self.components = eigenvectors[:self.n_components]
    
    def transform(self, X):
        #project the data
        X = X - self.mean
        
        return np.dot(X, self.components.T)
    

if __name__ == "__main__":
    import matplotlib.pyplot as plt
    
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
    
    pca = PCA(2)
    pca.fit(X)
    X_projected = pca.transform(X)
    
    print("Shape of X:", X.shape)
    print("Shape of X_projected:", X_projected.shape)
    
    x1 = X_projected[:, 0]
    x2 = X_projected[:, 1]
    
    plt.scatter(x1, x2, c=y, edgecolor='none', alpha=0.8, cmap=plt.cm.get_cmap('viridis'))
    plt.xlabel("Principal Component 1")
    plt.ylabel("Principal Component 2")
    plt.colorbar()
    plt.show()
    
    
        
    
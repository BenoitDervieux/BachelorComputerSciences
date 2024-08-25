def create_split(seed, X, y):
    np.random.seed(seed)
    indices = np.arange(X.shape[0]) # create array of indices 
    np.random.shuffle(indices)
    split = int(0.8*X.shape[0])
    
    X_train = X[indices[:split]]
    y_train = y[indices[:split]]
    X_test = X[indices[split:]]
    y_test = y[indices[split:]]
    return X_train, X_test, y_train, y_test
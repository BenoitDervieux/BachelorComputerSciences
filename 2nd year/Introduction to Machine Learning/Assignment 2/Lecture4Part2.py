from matplotlib.colors import ListedColormap
import numpy as np   
import matplotlib.pyplot as plt  
import MachineLearningModel as ml
from ForwardSelection import ForwardSelection
from ROCAnalysis import ROCAnalysis

def normalize(X):
    for i in range(X.shape[1]):
        mean_f = np.mean(X[:,i])
        std_f = np.std(X[:,i])
        X[:,i] = (X[:,i] - mean_f)/std_f
    return X, mean_f, std_f

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
    

data = np.genfromtxt('./datasets/heart_disease_cleveland.csv', delimiter=',', skip_header=1)
X = data[:, :-1]  # Features
y = data[:, -1]   # Target variable
X_norm, mu, sigma = normalize(X)

seeds = [0]
# X_subdataset, X_validation, y_subdataset, y_validation = create_split(seeds[0], X_norm, y)
X_subdataset, X_validation, y_subdataset, y_validation = create_split(seeds[0], X_norm, y)

f_select_lr = ForwardSelection(X_subdataset, y_subdataset, ml.LogisticRegression(learning_rate=0.05, num_iterations=3000))
f_select_lr.forward_selection()
print("Logistic Regression:")
print(f_select_lr.selected_features)
print(f_select_lr.best_cost)


f_select_lr.fit()
y_pred = f_select_lr.predict(X_validation[:, f_select_lr.selected_features])
roc = ROCAnalysis(y_pred, y_validation)

print("TP rate:",roc.tp_rate())
print("Fp rate:",roc.fp_rate())
print("Precision:",roc.precision())
print("F-score:",roc.f_score())


lr = ml.LogisticRegression(learning_rate=0.05, num_iterations=3000)
lr.fit(X_subdataset, y_subdataset)
y_pred = lr.predict(X_validation)
roc = ROCAnalysis(y_pred, y_validation)
print()
print("Logitic regression")

print("TP rate:",roc.tp_rate())
print("Fp rate:",roc.fp_rate())
print("Precision:",roc.precision())
print("F-score:",roc.f_score())
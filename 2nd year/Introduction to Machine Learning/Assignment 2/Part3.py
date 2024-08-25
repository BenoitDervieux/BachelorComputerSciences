import numpy as np   
import matplotlib.pyplot as plt  
import MachineLearningModel as ml

data = np.genfromtxt('./datasets/secret_polynomial.csv', delimiter=',')
data = data[1:,]
np.random.shuffle(data)
X = data[:, 0]
y = data[:, 1]
split = int(0.8*X.shape[0])
X_train = X[:split]
Y_train = y[:split]
X_test = X[split:]
Y_test = y[split:]

fig, (ax1, ax2, ax3) = plt.subplots(1, 3, figsize=(15, 5))
fig.suptitle('Horizontally stacked subplots')
ax1.scatter(X, y, color='black', linewidth=1, label="Whole set")
ax1.set_title("Whole set")
ax2.scatter(X_train, Y_train, color='red', linewidth=1, label="Train set")
ax2.set_title("Train set")
ax3.scatter(X_test, Y_test, color='blue', linewidth=1, label="Test set")
ax3.set_title("Test set")
plt.show()


# for i in range(6):
    # model_gradient = ml.RegressionModelNormalEquation(i+1)
    # model_gradient.fit(X_train, Y_train)
    # prediction = model_gradient.predict(X_test)
    # print(prediction)
    
# for i in range(6):
#     model_gradient = ml.RegressionModelNormalEquation(i)
#     model_gradient.fit(X_train, Y_train)
#     y_vals = model_gradient.predict(X)

#     plt.scatter(X, y)
#     plt.plot(X, y_vals, color='red')
#     plt.show()

X_test_refined = np.linspace(X_train.min(), X_train.max(), X_test.shape[0])

fig, axs = plt.subplots(2, 3, figsize=(13, 8))
for i, ax in enumerate(axs.flat):
    model_gradient = ml.RegressionModelNormalEquation(i+1)
    model_gradient.fit(X_train, Y_train)
    y_prediction = model_gradient.predict(X_test_refined)
    ax.scatter(X_test, Y_test)
    ax.plot(X_test_refined, y_prediction, color='red')
    ax.set_title(f'Degree {i+1}')
plt.tight_layout()
plt.show()


fig, axs = plt.subplots(4, 5, figsize=(13, 8))
for i, ax in enumerate(axs.flat):
    np.random.shuffle(data)
    X = data[:, 0]
    y = data[:, 1]
    split = int(0.8*X.shape[0])
    X_train = X[:split]
    Y_train = y[:split]
    X_test = X[split:]
    Y_test = y[split:]
    model_gradient = ml.RegressionModelNormalEquation(4)
    model_gradient.fit(X_train, Y_train)
    X_test_refined = np.linspace(X_train.min(), X_train.max(), X_test.shape[0])
    y_prediction = model_gradient.predict(X_test_refined)
    ax.scatter(X_test, Y_test)
    ax.plot(X_test_refined, y_prediction, color='red')
    ax.set_title(f'Degree {i+1}')
plt.tight_layout()
plt.show()




from matplotlib.colors import ListedColormap
import numpy as np   
import matplotlib.pyplot as plt  
import MachineLearningModel as ml

data = np.genfromtxt('./datasets/banknote_authentication.csv', delimiter=',')
np.random.shuffle(data)
X = data[:,:2]
# print(X)
y = data[:,2]
# print(y)

# X_normalized = (X - X.mean())/X.std()

X_normalized = np.zeros_like(X, dtype=float)

for i in range(X.shape[1]):
    mean_f = np.mean(X[:,i])
    std_f = np.std(X[:, i])
    X_normalized[:,i] = (X[:,i] - mean_f)/std_f


fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(10, 5))
fig.suptitle('Horizontally stacked subplots')
ax1.scatter(X[:,0], X[:,1], c=y, cmap="viridis")
ax1.set_title("Not normalized data")
ax2.scatter(X_normalized[:,0], X_normalized[:,1], c=y, cmap="inferno")
ax2.set_title("Normalized data")
plt.show()

split = int(0.8*X.shape[0])
X_train = X_normalized[:split]
Y_train = y[:split]
X_test = X_normalized[split:]
Y_test = y[split:]

logReg = ml.LogisticRegression(learning_rate=0.01, num_iterations=5000)
logReg.fit(X_train, Y_train)
# print("X_train shape:", X_train.shape)
# print("Y_train shape:", Y_train.shape)
# print("X_test shape:", X_test.shape)
# print("Y_test shape:", Y_test.shape)
print("Evaluation Log:", logReg.evaluate(X_test, Y_test))
print("Beta:", logReg.params)


# plt.show()

NoLogReg = ml.NonLinearLogisticRegression(degree=2, learning_rate=0.01, num_iterations=5000)
NoLogReg.fit(X_train, Y_train)
print("Evaluation No log:",NoLogReg.evaluate(X_test, Y_test))

fig, (ax1, ax2) = plt.subplots(1, 2)
ax1.plot([x for x in range(len(logReg.results))], logReg.results)
ax1.set_title("Linear logistic regression,\ncost:" + str(logReg.cost) + "\nLearning Rate:" + str(logReg.learning_rate) + "\nIterations:" + str(logReg.num_iterations))
ax2.plot([x for x in range(len(NoLogReg.results))], NoLogReg.results)
ax2.set_title("Non linear logistic regression,\ncost:" + str(NoLogReg.cost) + "\nLearning Rate:" + str(NoLogReg.learning_rate) + "\nIterations:" + str(NoLogReg.num_iterations))
plt.show()

def divide_random_20_80(data):
    np.random.shuffle(data)
    X = data[:,:2]
    y = data[:,2]
    for i in range(X.shape[1]):
        mean_f = np.mean(X[:,i])
        std_f = np.std(X[:, i])
        X_normalized[:,i] = (X[:,i] - mean_f)/std_f
    split = int(0.8*X.shape[0])
    X_train = X_normalized[:split]
    Y_train = y[:split]
    X_test = X_normalized[split:]
    Y_test = y[split:]
    return X_train, Y_train, X_test, Y_test

accuracy_logistic = []
accuracy_nonlinear = []

for i in range(20):
    X_train, Y_train, X_test, Y_test = divide_random_20_80(data)
    logReg = ml.LogisticRegression(learning_rate=0.001 * (i + 1) * 2, num_iterations=5000)
    logReg.fit(X_train, Y_train)
    evaluation = logReg.evaluate(X_test, Y_test)
    accuracy_logistic.append(evaluation)
    logReg = ml.NonLinearLogisticRegression(learning_rate=0.001 * (i + 1) * 2, num_iterations=5000)
    logReg.fit(X_train, Y_train)
    evaluation = logReg.evaluate(X_test, Y_test)
    accuracy_nonlinear.append(evaluation)
plt.boxplot([accuracy_logistic, accuracy_nonlinear])
plt.show()



h = 0.01
X_min, X_max = X_normalized[:, 0].min() - 1, X_normalized[:, 0].max() + 1
Y_min, Y_max = X_normalized[:, 1].min() - 1, X_normalized[:, 1].max() + 1
xx, yy = np.meshgrid(np.arange(X_min, X_max, h), np.arange(Y_min, Y_max, h))
x1, x2 = xx.ravel(), yy.ravel()
NoLogReg = ml.NonLinearLogisticRegression(learning_rate=0.01, num_iterations=5000)
XXe = np.c_[x1, x2]
NoLogReg.fit(X_train, Y_train)
p = NoLogReg.predict(XXe)
classes = p > 0.5
clz_mesh = classes.reshape(xx.shape)
cmap_light = ListedColormap(['#FFAAAA', '#AAFFAA', '#AAAAFF'])
cmap_bold = ListedColormap(['#FF0000', '#00FF00', '#0000FF'])

plt.figure(2)
plt.pcolormesh(xx, yy, clz_mesh, cmap=cmap_light)
plt.scatter(X_test[:, 0], X_test[:, 1], c=Y_test, cmap=cmap_bold)
plt.show()

h = 0.01
LogReg = ml.LogisticRegression(learning_rate=0.01, num_iterations=5000)
XXe = np.c_[x1, x2]
LogReg.fit(X_train, Y_train)
p = LogReg.predict(XXe)
classes = p > 0.5
clz_mesh = classes.reshape(xx.shape)
cmap_light = ListedColormap(['#FFAAAA', '#AAFFAA', '#AAAAFF'])
cmap_bold = ListedColormap(['#FF0000', '#00FF00', '#0000FF'])

plt.figure(2)
plt.pcolormesh(xx, yy, clz_mesh, cmap=cmap_light)
plt.scatter(X_test[:, 0], X_test[:, 1], c=Y_test, cmap=cmap_bold)
plt.show()


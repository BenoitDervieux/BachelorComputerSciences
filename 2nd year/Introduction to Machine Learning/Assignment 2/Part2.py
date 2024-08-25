import numpy as np   
import matplotlib.pyplot as plt  
import MachineLearningModel as ml

data = np.genfromtxt('./datasets/housing-boston.csv', delimiter=',')
print(data)
data = data[1:]

Xe = data[:, :2]
y = data[:, 2:3]

# fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(12,6))
# fig.suptitle('Horizontally stacked subplots')
# ax1.scatter(Xe[:, 0], y)
# ax2.scatter(Xe[:, 1], y)
# plt.show()

# n = Xe.shape[0]
# #Xee is just a test matrix of one column of Xe
# Xee = np.c_[np.ones((n,1)),Xe[:,0]]

# j = np.dot(Xee, W) - y
# J = (j.T.dot(j))/n

# print(J)

# cost = (1/n)*np.dot((np.dot(Xee, W) - y).T, (np.dot(Xee, W) - y))
# cost = (1 / n) * np.dot((np.dot(Xee, W) - y).T, (np.dot(Xee, W) - y))
# print("Cost:", cost)

#################################
######### LINEAR REGRESSION ######
#################################
model = ml.RegressionModelNormalEquation(1)
model.fit(Xe, y)
print("Beta:", model.beta)
print("Cost:", model.cost)
X_predict = np.array([[2.31, 6.575]])
prediction = model.predict(X_predict)
print("Prediciton:", prediction)

#Create normalized data
Xe_normalized = np.array(Xe)
## Here we gonna normalize the data
X_indus_mean = np.mean(Xe[:,0])
X_indus_std = np.std(Xe[:,0])
Xe_normalized[:,0] = (Xe_normalized[:,0] - X_indus_mean) / X_indus_std

## Here we gonna normalize the data
X_rm_mean = np.mean(Xe[:,1])
X_rm_std = np.std(Xe[:,1])
Xe_normalized[:,1] = (Xe_normalized[:,1] - X_rm_mean) / X_rm_std

print(Xe_normalized)


model_norm = ml.RegressionModelNormalEquation(1)
model_norm.fit(Xe_normalized, y)
print("Beta:", model_norm.beta)
print("Cost:", model_norm.cost)
X_predict_norm = np.array([[2.31, 6.575]])
X_predict_norm[:,0] = (X_predict[:,0] - X_indus_mean) / X_indus_std
X_predict_norm[:,1] = (X_predict[:,1] - X_rm_mean) / X_rm_std
prediction_norm = model_norm.predict(X_predict_norm)
print("Prediciton:", prediction_norm)

#################################
######### Gradient Descent ######
#################################

# X_gd = np.array(Xe)
# X_gd = np.c_[X_gd,np.ones((X_gd.shape[0], 1))]

# # model = ml.RegressionModelGradientDescent(1)
# # results = model.fit(X_gd, y)
# # X_predict_indus = np.array([[2.31, 6.757]])
# # prediction = model.predict(X_predict_indus)
# # print("Prediction:", prediction)

# Xe_normalized_gd = np.array(Xe_normalized)
# Xe_normalized_gd = np.c_[Xe_normalized_gd,np.ones((Xe_normalized_gd.shape[0], 1))]

# model_normalized = ml.RegressionModelGradientDescent(1)
# results_normalized = model_normalized.fit(Xe_normalized_gd, y)

# # fig, (ax1, ax2) = plt.subplots(1, 2)
# # fig.suptitle('Horizontally stacked subplots')
# # ax1.plot([x for x in range(3000)], results, color='black', linewidth=2, label="Prediction")
# # ax2.plot([x for x in range(3000)], results_normalized, color='red', linewidth=2, label="Prediction")
# # plt.show()


# y_predicted = model_normalized.predict(Xe_normalized)

# plt_figure = np.array(Xe_normalized_gd[:,0])

# cmap = plt.get_cmap('viridis')
# fig = plt.figure(figsize=(8,6))
# m1 = plt.scatter(Xe_normalized_gd[:,0], y, color=cmap(0.9), s=10)
# m2 = plt.scatter(Xe_normalized_gd[:,1], y, color=cmap(0.5), s=10)
# plt.plot(Xe_normalized_gd[:,0], y_predicted, color='black', linewidth=2, label='Prediction')
# plt.show()

#################################
######### Previous Gradient Descent ######
#################################
# # instantiate the model
model_gradient = ml.RegressionModelGradientDescent(1)
# # fit the model with non normalized data
model_gradient.fit(Xe, y)
model_results = model_gradient.cost
# X_predict_indus = np.array([[2.31, 6.757]])
# prediction_gd = model_gradient.predict(X_predict_indus)
# print("Predicion:",prediction_gd)
# model_gradient.evaluate(Xe, y)


model_gradient_normalized = ml.RegressionModelGradientDescent(1)
model_gradient_normalized.fit(Xe_normalized, y)
model_results_normalized = model_gradient_normalized.cost
# model_gradient_normalized.evaluate(Xe_normalized, y)


fig, (ax1, ax2) = plt.subplots(1, 2)
fig.suptitle('Horizontally stacked subplots')
ax1.plot([x for x in range(model_gradient.num_iterations)], model_results, color='black', linewidth=2, label="Prediction")
ax1.set_title("Not normalized data, mse:" + str(model_results[len(model_results)-1]))
ax2.plot([x for x in range(model_gradient_normalized.num_iterations)], model_results_normalized, color='red', linewidth=2, label="Prediction")
ax2.set_title("Normalized data, mse:" + str(model_results_normalized[len(model_results_normalized)-1]))
plt.show()


# pourcent_prediction = (prediction_gd * 100) / prediction_indus
# pourcent_prediction = 100 - pourcent_prediction
# print("Pourcentage de prediction:", pourcent_prediction)
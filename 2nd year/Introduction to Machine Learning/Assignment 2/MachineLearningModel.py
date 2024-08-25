from abc import ABC, abstractmethod
import numpy as np

class MachineLearningModel(ABC):
    """
    Abstract base class for machine learning models.
    """

    @abstractmethod
    def fit(self, X, y):
        """
        Train the model using the given training data.

        Parameters:
        X (array-like): Features of the training data.
        y (array-like): Target variable of the training data.

        Returns:
        None
        """
        pass

    @abstractmethod
    def predict(self, X):
        """
        Make predictions on new data.

        Parameters:
        X (array-like): Features of the new data.

        Returns:
        predictions (array-like): Predicted values.
        """
        pass

    @abstractmethod
    def evaluate(self, X, y):
        """
        Evaluate the model on the given data.

        Parameters:
        X (array-like): Features of the data.
        y (array-like): Target variable of the data.

        Returns:
        score (float): Evaluation score.
        """
        pass

def _polynomial_features(self, X):
    """
        Generate polynomial features from the input features.
        Check the slides for hints on how to implement this one. 
        This method is used by the regression models and must work
        for any degree polynomial
        Parameters:
        X (array-like): Features of the data.

        Returns:
        X_poly (array-like): Polynomial features.
    """
    Xpred = np.array([])
    # iterate for the number of degree
    for k in reversed(range(self.degree + 1)):
        # create an array, 1 row x columns
        add_column = np.array([x**(k) for x in X])
        # reshape 1 column, x rows
        add_column = add_column.reshape(-1, 1)
        # append to the empty array (except if empty then copy)
        if Xpred.size == 0:
            Xpred = add_column
        else:
            Xpred = np.append(add_column, Xpred,axis=1)
    return Xpred
    

class RegressionModelNormalEquation(MachineLearningModel):
    """
    Class for regression models using the Normal Equation for polynomial regression.
    """
    def __init__(self, degree):
        """
        Initialize the model with the specified polynomial degree.

        Parameters:
        degree (int): Degree of the polynomial features.
        """
        #--- Write your code here ---#
        self.degree = degree
        self.beta = None
        self.cost = None
        self.theta = None

    def fit(self, X, y):
        """
        Train the model using the given training data.
        
        Parameters:
        X (array-like): Features of the training data.
        y (array-like): Target variable of the training data.
        
        Returns:
        None
        """
        #--- Write your code here ---#
        ## Ici je comprends qu'il faille que je trouve Beta ou Theta
        n = X.shape[0]
        if (self.degree > 1):
            Xe = _polynomial_features(self, X)
        else:
            Xe = np.c_[np.ones((n,1)), X]
        W = np.dot(np.linalg.inv(np.dot(Xe.T, Xe)), np.dot(Xe.T,y))
        # W = np.linalg.inv(Xe.T.dot(Xe)).dot(Xe.T).dot(y)
        self.beta = W
        self.theta = self.beta
        # Here we will calculate the cost function
        j = np.dot(Xe, W) - y
        J = (j.T.dot(j))/n
        self.cost = J
        

    def predict(self, X):
        """
        Make predictions on new data.

        Parameters:
        X (array-like): Features of the new data.

        Returns:
        predictions (array-like): Predicted values.
        """
        #--- Write your code here ---#
        # instantiate an empty array
        n = X.shape[0]
        if (self.degree > 1):
            Xe = _polynomial_features(self, X)
        else: 
            Xe = np.c_[np.ones((n,1)), X]
        # multiply X by Beta values
        prediction = np.dot(Xe, self.beta)
        # return the predictions in an array
        return prediction
        

    def evaluate(self, X, y):
        """
        Evaluate the model on the given data.

        Parameters:
        X (array-like): Features of the data.
        y (array-like): Target variable of the data.

        Returns:
        score (float): Evaluation score (MSE).
        """
        #--- Write your code here ---#
        X_results = self.predict(X)
        mse = 0
        for x in range(len(X_results)):
            mse += (1/len(X_results)) * (X_results[x] - y[x])**2
        return mse

class RegressionModelGradientDescent(MachineLearningModel):
    """
    Class for regression models using gradient descent optimization.
    """

    def __init__(self, degree, learning_rate=0.001, num_iterations=3000):
        """
        Initialize the model with the specified parameters.

        Parameters:
        degree (int): Degree of the polynomial features.
        learning_rate (float): Learning rate for gradient descent.
        num_iterations (int): Number of iterations for gradient descent.
        """
        #--- Write your code here ---#
        self.degree = degree
        self.learning_rate = learning_rate
        self.num_iterations = num_iterations
        self.cost = None
        self.params = None
        self.theta = None

    def fit(self, X, y):
        """
        Train the model using the given training data.

        Parameters:
        X (array-like): Features of the training data.
        y (array-like): Target variable of the training data.

        Returns:
        None
        """
        #--- Write your code here ---#
        # initialize n and the number of features
        # n is the number of columns and features is the number of elements in Beta
        # Or inside the equation
        n = X.shape[0]
        if (self.degree > 1):
            Xe = _polynomial_features(self, X)
        else:
            Xe = np.c_[np.ones((n,1)), X]
        features = Xe.shape[1]
        # +1 is for the biais
        self.params = np.array([[0] for x in range(features)])
        self.cost = []
        for _ in range(self.num_iterations):
            # Here is the prediction which is basically each paramter of 
            # parameters times the features
            y_pred = np.dot(Xe, self.params)
            dw = (1/n) * np.dot(Xe.T, (y_pred - y))
            self.params = self.params - self.learning_rate * dw
            self.cost.append(self.evaluate(Xe, y))
            self.theta = self.params

        

    def predict(self, X):
        """
        Make predictions on new data.

        Parameters:
        X (array-like): Features of the new data.

        Returns:
        predictions (array-like): Predicted values.
        """
        #--- Write your code here ---#
        # n = X.shape[0]
        # X = np.c_[np.ones((n,1)), X]
        if (X.shape[1] == 1):
            X = _polynomial_features(self, X)
        y_pred = np.dot(X, self.params)
        return y_pred

    def evaluate(self, X, y):
        """
        Evaluate the model on the given data.

        Parameters:
        X (array-like): Features of the data.
        y (array-like): Target variable of the data.

        Returns:
        score (float): Evaluation score (MSE).
        """
        #--- Write your code here ---#
        y_pred = self.predict(X)
        return np.mean((y-y_pred)**2)

    
    
    

class LogisticRegression:
    """
    Logistic Regression model using gradient descent optimization.
    """

    def __init__(self, learning_rate=0.001, num_iterations=3000):
        """
        Initialize the logistic regression model.

        Parameters:
        learning_rate (float): The learning rate for gradient descent.
        num_iterations (int): The number of iterations for gradient descent.
        """
        #--- Write your code here ---#
        self.params = None
        self.learning_rate = learning_rate
        self.num_iterations = num_iterations
        self.cost = None
        self.results = None
        self.theta = None

    def fit(self, X, y):
        """
        Train the logistic regression model using gradient descent.

        Parameters:
        X (array-like): Features of the training data.
        y (array-like): Target variable of the training data.

        Returns:
        None
        """
        #--- Write your code here ---#
        n = X.shape[0]
        features = X.shape[1]
        self.params = np.array([[0] for x in range(features + 1)])
        Xe = np.c_[np.ones((n,1)), X]
        # Here self.biais = 0 but won't put it here
        self.results = []
        for _ in range(self.num_iterations):
            x_time_param = np.dot(Xe, self.params)
            sig_x = self._sigmoid(x_time_param)
            if(len(y.shape) == 1):
                y = y.reshape(-1,1)
            sig_minus_y = sig_x - y
            self.params = self.params - (self.learning_rate / n) * np.dot(Xe.T, sig_minus_y)
            self.cost = self._cost_function(Xe, y)
            self.results.append(self.cost)
            self.theta = self.params[:,0]
           
           

    def predict(self, X):
        """
        Make predictions using the trained logistic regression model.

        Parameters:
        X (array-like): Features of the new data.

        Returns:
        predictions (array-like): Predicted probabilities.
        """
        #--- Write your code here ---#
        n = X.shape[0]
        Xne = np.c_[np.ones((n,1)), X]
        probability = self._sigmoid(np.dot(Xne, self.params))
        predictions = np.where(probability > 0.5, 1, 0)
        return predictions

    def evaluate(self, X, y):
        """
        Evaluate the logistic regression model on the given data.

        Parameters:
        X (array-like): Features of the data.
        y (array-like): Target variable of the data.

        Returns:
        score (float): Evaluation score (e.g., accuracy).
        """
        y_pred = self.predict(X)
        y_rounded = np.round(y_pred).flatten()
        y_reshaped = y.reshape(-1,1).flatten()
        error_count = np.sum(y_reshaped != y_rounded)
        return 1.0 - (error_count/len(y))
    
        

    def _sigmoid(self, z):
        """
        Sigmoid function.

        Parameters:
        z (array-like): Input to the sigmoid function.

        Returns:
        result (array-like): Output of the sigmoid function.
        """
        #--- Write your code here ---#
        return 1 / (1 + np.exp(-z))

    def _cost_function(self, X, y):
        """
        Compute the logistic regression cost function.

        Parameters:
        X (array-like): Features of the data.
        y (array-like): Target variable of the data.

        Returns:
        cost (float): The logistic regression cost.
        """
        #--- Write your code here ---#
        n = X.shape[0]
        z = np.dot(X, self.params)
        gOfX = self._sigmoid(z)
        right_side = np.dot((1 - y).T, np.log(1 - gOfX))
        left_side = np.dot(y.T, np.log(gOfX))
        cost = -1/n * ( left_side + right_side)
        return cost[0][0]
    
class NonLinearLogisticRegression:
    """
    Nonlinear Logistic Regression model using gradient descent optimization.
    It works for 2 features (when creating the variable interactions)
    """

    def __init__(self, degree=2, learning_rate=0.001, num_iterations=3000):
        """
        Initialize the nonlinear logistic regression model.

        Parameters:
        degree (int): Degree of polynomial features.
        learning_rate (float): The learning rate for gradient descent.
        num_iterations (int): The number of iterations for gradient descent.
        """
        #--- Write your code here ---#
        self.degree = degree
        self.learning_rate = learning_rate
        self.num_iterations = num_iterations
        self.params = None
        self.cost = None
        self.results = None
        self.theta = None

    def fit(self, X, y):
        """
        Train the nonlinear logistic regression model using gradient descent.

        Parameters:
        X (array-like): Features of the training data.
        y (array-like): Target variable of the training data.

        Returns:
        None
        """
        #--- Write your code here ---#
        if (self.degree == 2):
            Xe = self.mapFeature(X[:, 0], X[:, 1], self.degree) 
        else:
            Xe = X      
        n, features = Xe.shape
        self.params = np.array([[0] for x in range(features)])
        self.results = []
        for i in range(self.num_iterations):
            x_time_param = np.dot(Xe, self.params)
            sig_x = self._sigmoid(x_time_param)
            if(len(y.shape) == 1):
                y = y.reshape(-1,1)
            sig_minus_y = sig_x - y
            self.params = self.params - self.learning_rate * (1/n) * np.dot(Xe.T, sig_minus_y)
            self.cost = self._cost_function(Xe, y)
            self.results.append(self.cost)
            self.theta = self.params[:,0]
        

    def predict(self, X):
        """
        Make predictions using the trained nonlinear logistic regression model.

        Parameters:
        X (array-like): Features of the new data.

        Returns:
        predictions (array-like): Predicted probabilities.
        """
        #--- Write your code here ---#
        n = X.shape[0]
        if (self.degree == 2):
            Xe = self.mapFeature(X[:, 0], X[:, 1], self.degree)
        else:
            Xe = X
        probability = self._sigmoid(np.dot(Xe, self.params))
        predictions = np.where(probability > 0.5, 1, 0)
        return predictions

    def evaluate(self, X, y):
        """
        Evaluate the nonlinear logistic regression model on the given data.

        Parameters:
        X (array-like): Features of the data.
        y (array-like): Target variable of the data.

        Returns:
        cost (float): The logistic regression cost.
        """
        #--- Write your code here ---#
        # y_pred = self.predict(X)
        # score = np.mean(y_pred == y)
        # return score
        
        y_pred = self.predict(X)
        y_rounded = np.round(y_pred).flatten()
        y_reshaped = y.reshape(-1,1).flatten()
        error_count = np.sum(y_reshaped != y_rounded)
        return 1.0 - (error_count/len(y))

    def _sigmoid(self, z):
        """
        Sigmoid function.

        Parameters:
        z (array-like): Input to the sigmoid function.

        Returns:
        result (array-like): Output of the sigmoid function.
        """
        #--- Write your code here ---#
        return 1 / (1 + np.exp(-z))

    def mapFeature(self, X1, X2, D):
        """
        Map the features to a higher-dimensional space using polynomial features.
        Check the slides to have hints on how to implement this function.
        Parameters:
        X1 (array-like): Feature 1.
        X2 (array-like): Feature 2.
        D (int): Degree of polynomial features.

        Returns:
        X_poly (array-like): Polynomial features.
        """
        #--- Write your code here ---#
        one = np.ones([len(X1), 1])
        Xe = np.c_[one, X1, X2]
        for i in range(2, D+1):
            for j in range(0, i+1):
                Xnew = X1**(i-j)*X2**j
                Xnew = Xnew.reshape(-1, 1)
                Xe = np.append(Xe, Xnew, 1)
        return Xe

    def _cost_function(self, X, y):
        """
        Compute the logistic regression cost function.

        Parameters:
        X (array-like): Features of the data.
        y (array-like): Target variable of the data.

        Returns:
        cost (float): The logistic regression cost.
        """
        #--- Write your code here ---#
        n = X.shape[0]
        z = np.dot(X, self.params)
        gOfX = self._sigmoid(z)
        right_side = np.dot((1 - y).T, np.log(1 - gOfX))
        left_side = np.dot(y.T, np.log(gOfX))
        cost = -1/n * ( left_side + right_side)
        return cost[0][0]

import numpy as np
from ROCAnalysis import ROCAnalysis

class ForwardSelection:
    """
    A class for performing forward feature selection based on maximizing the F-score of a given model.

    Attributes:
        X (array-like): Feature matrix.
        y (array-like): Target labels.
        model (object): Machine learning model with `fit` and `predict` methods.
        selected_features (list): List of selected feature indices.
        best_cost (float): Best F-score achieved during feature selection.
    """

    def __init__(self, X, y, model):
        """
        Initializes the ForwardSelection object.

        Parameters:
            X (array-like): Feature matrix.
            y (array-like): Target labels.
            model (object): Machine learning model with `fit` and `predict` methods.
        """
        #--- Write your code here ---#
        self.X = X
        self.y = y
        self.model = model
        self.best_cost = None
        self.selected_features = []

    def create_split(self, X, y):
        """
        Creates a train-test split of the data.

        Parameters:
            X (array-like): Feature matrix.
            y (array-like): Target labels.

        Returns:
            X_train (array-like): Features for training.
            X_test (array-like): Features for testing.
            y_train (array-like): Target labels for training.
            y_test (array-like): Target labels for testing.3333
        """
        #--- Write your code here ---#
        # data = np.c_[X, y]
        # np.random.shuffle(data)
        # X = data[:,:-1]
        # y = data[:,-1]
        split = int(0.8*X.shape[0])
        X_train = X[:split]
        y_train = y[:split]
        X_test = X[split:]
        y_test = y[split:]
        return X_train, X_test, y_train, y_test

    def train_model_with_features(self, features):
        """
        Trains the model using selected features and evaluates it using ROCAnalysis.

        Parameters:
            features (list): List of feature indices.

        Returns:
            float: F-score obtained by evaluating the model.
        """
        #--- Write your code here ---#
        X_train, X_test, y_train, y_test = self.create_split(features, self.y)
        self.model.fit(X_train, y_train)
        y_pred = self.model.predict(X_test)
        roc = ROCAnalysis(y_pred, y_test)
        score = roc.f_score()
        return score

    def forward_selection(self):
        """
        Performs forward feature selection based on maximizing the F-score.
        """
        # list the numbers from 0 to X.shape[1]
        numbers = [i for i in range(self.X.shape[1])]
        # this will be the selection of our features, first they will be in order and
        # then through the selection we will remove them
        selected = None
        X_to_test = np.array([])
        X_final = np.array([])
        best_f_score = -1
        all_f_score = []
        # We first iterate through all the features to get the best at every step (13, 12, 11 etc)
        for i in range(self.X.shape[1]):
            X_to_test = X_final
            if len(numbers) == 1:
                selected = numbers[0]
            for p in range(len(numbers)):
                if len(X_final) == 0:
                    X_to_test = self.X[:, numbers[p]]
                else :
                    X_to_test = np.c_[X_final, self.X[:,numbers[p]]]
                if len(X_to_test.shape) == 1:
                    X_to_test = X_to_test.reshape(-1, 1)
                f_score = self.train_model_with_features(X_to_test)
                if f_score > best_f_score:
                    best_f_score = f_score
                    if len(numbers) != 1:
                        selected = numbers[p]
            # We basically append them in order of best f_score
            self.selected_features.append(selected)
            all_f_score.append(f_score)
            if len(X_final) == 0:
                X_final = self.X[:, selected]
            else :
                X_final = np.c_[X_final, self.X[:, selected]]
            for z in range(len(numbers)):
                if numbers[z-1] == selected:
                    numbers.remove(numbers[z-1])
            best_f_score = -1
        # print(all_f_score)
        self.best_cost = max(all_f_score)
        max_index = all_f_score.index(max(all_f_score))
        self.selected_features = [self.selected_features[x] for x in range(max_index+1)]
        # print("Selected features:", self.selected_features)

        
                
    def fit(self):
        """
        Fits the model using the selected features.
        """ 
        X_fit = self.X[:, self.selected_features]
        # Here this not sure if I need to split the data or not
        self.model.fit(X_fit, self.y)
        

    def predict(self, X_test):
        """
        Predicts the target labels for the given test features.

        Parameters:
            X_test (array-like): Test features.

        Returns:
            array-like: Predicted target labels.
        """
        #--- Write your code here ---#
        return self.model.predict(X_test)

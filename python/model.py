from pandas import Series, DataFrame
import pandas as pd
import numpy as np
import os
import matplotlib.pylab as plt
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import classification_report, confusion_matrix  
from sklearn import metrics 
from sklearn.externals import joblib


df = pd.read_csv("/Users/parthmaniar/Downloads/convertcsv (2).csv")




feature_cols = ['outlook','temperature','humidity','windy']
X = df[feature_cols]
y = df.play 
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2) 


clf = DecisionTreeClassifier()


print("--OUTLOOK--")
print("1. Sunny")
print("2. Overcast")
print("3. Rainy")

o=int(input("Enter Your Choice  : "))

print("--TEMPERATURE--")
print("1. Hot")
print("2. Mild")
print("3. Cool")

t=int(input("Enter Your Choice  : "))
print("--HUMIDITY--")
print("1. High")
print("2. Normal")
h=int(input("Enter Your Choice  : "))

print("--WINDY--")
print("1. True")
print("2. False")

w=int(input("Enter Your Choice  : "))



c=[[o,t,h,w]]


clf = clf.fit(X_train,y_train)



y_pred = clf.predict(X_test)
pred = clf.predict(c)
joblib.dump(clf, 'my_model.sav')
print("----------------------------------------")
if(pred[0]==0):
    print("ALERT: DONT PLAY")
else:
    print("YOU CAN PLAY")
    
print("----------------------------------------")
print("Accuracy:",metrics.accuracy_score(y_test, y_pred))


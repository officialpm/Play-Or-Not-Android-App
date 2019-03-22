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

m=joblib.load("my_model.sav")


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

pred = m.predict(c)

print("----------------------------------------")
if(pred[0]==0):
    print("ALERT: DONT PLAY")
else:
    print("YOU CAN PLAY")
    
print("----------------------------------------")



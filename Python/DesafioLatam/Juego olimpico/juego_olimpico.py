# -*- coding: utf-8 -*-
"""
Created on Thu May 27 19:12:05 2021

@author: YonathanVasquez
"""

import pandas as pd


### leer  CSV
df = pd.read_csv('athlete_events.csv')


#ejercicio_1
ejercicio_1 = df.shape  # (271116, 15

#ejercicio_2
ejercicio_2 = df['Sport'].value_counts().count()

#ejercicio_3
ejercicio_3 = df['Season'].value_counts() / len(df)

#ejercicio_4
ejercicio_4 = df[df.Season=="Summer"].sort_values('Year').head(1)["City"].unique()

#ejercicio_5
ejercicio_5 = df[df.Season=="Winter"].sort_values('Year').head(1)["City"].unique()

#ejercicio_6
ejercicio_6 = df.groupby('Team').nunique().sort_values(by = "Name", ascending = False)["Name"][:10]

#ejercicio_7
med_ver=df[df['Season']=="Summer"]["Medal"].value_counts()
med_inv=df[df['Season']=="Winter"]["Medal"].value_counts()

porc_ver=df[df['Season']=="Summer"]["Medal"].value_counts()/sum(df[df['Season']=="Summer"]["Medal"].value_counts())
porc_inv=df[df['Season']=="Winter"]["Medal"].value_counts()/sum(df[df['Season']=="Winter"]["Medal"].value_counts())

ejercicio_7=df["Medal"].value_counts()/ sum(df["Medal"].value_counts())



#ejercicio_8
ejercicio_8 = df[(df['Year'] == df['Year'].unique().min()) & (df['Season'] == "Summer") ]["Team"].unique()










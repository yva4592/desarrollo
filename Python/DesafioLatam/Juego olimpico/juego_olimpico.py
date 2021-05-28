# -*- coding: utf-8 -*-
"""
Created on Thu May 27 19:12:05 2021

@author: YonathanVasquez
"""

import pandas as pd
import numpy as np


### leer  CSV
df = pd.read_csv('athlete_events.csv')

ejercicio_1 = df.shape  # (271116, 15

ejercicio_2 = df['Sport'].value_counts().count()

ejercicio_3 = df['Season'].value_counts() / len(df)

ejercicio_4 = df[(df['Year'] == df['Year'].unique().min()) & (df['Season'] == "Summer") ]["City"].unique()

ejercicio_5 = df[(df['Year'] == df['Year'].unique().min()) & (df['Season'] == "Winter") ]["City"].unique()
# comprobar :  df[df['Year'] == df['Year'].unique().min()]["Season"].unique()

ejercicio_6 = df.groupby('Team').nunique().sort_values(by = "Name", ascending = False)["Name"][:10]

ejercicio_7 = df.groupby('Medal').nunique()["Name"] / len(df)

ejercicio_8 = df[(df['Year'] == df['Year'].unique().min()) & (df['Season'] == "Summer") ]["Team"].unique()


"""

df[( df['Year'] == df['Year'].unique().min()) & (df['Season'] == "Summer" ) ]

ejercicio_4 = np.where(( df[   df['Year'] == df['Year'].unique().min()) & (df['Season'] == "Summer") ], df["Team"] ], df["Team"]))


df[(df['Year'] == df['Year'].unique().min()) & (df['Season'] == "Summer") ]["City"]
                       
ejercicio_6 = df[(df['Year'] == df['Year'].unique().min()) & (df['Season'] == "Summer") ]["City"].unique()

df[(df['Year'] == df['Year'].unique().min()) & (df['Season'] == "Summer") ]

for colname, serie in df.iteritems():
    print(colname)
    print(serie)
    break


for i in df:
    print(i)
python3 --version



df[df['Season'] == "Summer" or df['Season'] == "Winter"]



ejercicio_1 = df['Year'].value_counts()

df.groupby('Team').nunique()

df.groupby('Team').nunique().sort_values(by = "Name", ascending == False)




# n 6

df.groupby('Team').nunique()

df.groupby('Team').nunique().sort_values(by = "Name", ascending = False)

df.groupby('Team').nunique().sort_values(by = "Name", ascending = False)["Name"]

df.groupby('Team').nunique().sort_values(by = "Name", ascending = False)["Name"][:10]

"""







# -*- coding: utf-8 -*-
"""
Created on Thu May 27 06:38:47 2021

@author: YonathanVasquez
"""

import numpy as np

velocidad = [4, 4, 7, 7, 8, 9, 10, 10, 10,
11, 11, 12, 12, 12, 12, 13, 13,
13, 13, 14, 14, 14, 14, 15, 15,
15, 16, 16, 17, 17, 17, 18, 18,
18, 18, 19, 19, 19, 20, 20, 20,
20, 20, 22, 23, 24, 24, 24, 24, 25]


distancia = [2, 10, 4, 22, 16, 10, 18, 26, 34,
17, 28, 14, 20, 24, 28, 26, 34, 34,
46, 26, 36, 60, 80, 20, 26, 54, 32,
40, 32, 40, 50, 42, 56, 76, 84, 36,
46, 68, 32, 48, 52, 56, 64, 66, 54,
70, 92, 93, 120, 85]


def promedio(lista):
        suma = 0 
        prom = 0
        retorno = 0
        for i in lista:
            if (type(i)==int or type(i)==float):
                suma+=int(i)
            else:
                suma+=0
        if suma == 0:
            retorno = "No existe promdio por que no son enteros o float"
        else:
            prom = suma /float(len(lista))
            retorno = prom  
        return retorno

asociados = list(zip(velocidad, distancia))


asociados[0][]

asociados[:][1:2]


"""

count_velocidad_bajo_promedio = 0
velocidad = []
for a in range(len(asociados)):
    velocidad.append(asociados[a][0])
    
for i in velocidad:    
    if i < promedio(velocidad):
            print(i)
            count_velocidad_bajo_promedio+=1
print(count_velocidad_bajo_promedio)
print(promedio(velocidad))

"""

################# count_velocidad_bajo_promedio

count_velocidad_bajo_promedio = 0
velocidad = []
for a in range(len(asociados)):
    velocidad.append(asociados[a][0])
    
for i in velocidad:    
    if i < promedio(velocidad):
        count_velocidad_bajo_promedio+=1
print(count_velocidad_bajo_promedio)

################# count_velocidad_bajo_promedio


################# count_velocidad_distancia_bajo_promedio

count_velocidad_bajo_promedio_2 = 0
count_distancia_bajo_promedio = 0
velocidad = []
distancia = []
for a in range(len(asociados)):
    velocidad.append(asociados[a][0])
    distancia.append(asociados[a][1])
    
for i in velocidad:    
    if i < promedio(velocidad):
        count_velocidad_bajo_promedio_2+=1
print(count_velocidad_bajo_promedio_2)


for i in distancia:    
    if i < promedio(distancia):
        count_distancia_bajo_promedio+=1
print(count_distancia_bajo_promedio)


################# count_velocidad_distancia_bajo_promedio



################# count_velocidad_sobre_promedio

count_velocidad_sobre_promedio = 0

velocidad = []

for a in range(len(asociados)):
    velocidad.append(asociados[a][0])
    
    
for i in velocidad:    
    if i > promedio(velocidad):
        count_velocidad_sobre_promedio+=1
print(count_velocidad_sobre_promedio)


################# count_velocidad_sobre_promedio




################# count_velocidad_distancia_bajo_promedio

count_velocidad_sobre_promedio_2 = 0
count_distancia_sobre_promedio = 0
velocidad = []
distancia = []
for a in range(len(asociados)):
    velocidad.append(asociados[a][0])
    distancia.append(asociados[a][1])
    
for i in velocidad:    
    if i > promedio(velocidad):
        count_velocidad_sobre_promedio_2+=1
print(count_velocidad_sobre_promedio_2)


for i in distancia:    
    if i > promedio(distancia):
        count_distancia_sobre_promedio+=1
print(count_distancia_sobre_promedio)


################# count_velocidad_distancia_bajo_promedio
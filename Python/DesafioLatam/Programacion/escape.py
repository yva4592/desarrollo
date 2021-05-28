# -*- coding: utf-8 -*-
"""
Created on Thu May 13 16:43:17 2021

@author: YonathanVasquez
"""

#importa sys
#importa math
import sys
import math

#entrada de gravedad
g = float(sys.argv[1])

#entrada del radio
r = float(sys.argv[2])

# convertir radio de Kms a mts
r = r*1000

#operacion antes de la raiz
operacion = 2*g*r

# se optiene raiz y guarda en variable el resultado de velocidad de escape
velocidad_escape = math.sqrt(operacion)

#se concadena y muestra
print("La velocidad de escape es {} m/s".format(velocidad_escape))
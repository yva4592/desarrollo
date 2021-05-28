# -*- coding: utf-8 -*-
"""
Created on Thu May 13 17:32:43 2021

@author: YonathanVasquez
"""

#importr sys
import sys


#cantidad de usuarios normales
cantidad_usuarios_normales = int(sys.argv[1])

#cantidad de usuarios premiun
cantidad_usuarios_premiun = int(sys.argv[2])

#cantidad de usuarios periodo prueba
cantidad_usuarios_periodo_prueba = int(sys.argv[3])

#ingreso de precio de venta estandar
precio_venta_estandar = int(sys.argv[4])

#ingreso de gastos
gastos = int(sys.argv[5])

#operaciomes de utilidades segun el tipo
utilidad_usuario_normal = precio_venta_estandar*cantidad_usuarios_normales - gastos

utilidad_usuario_premiun = 2*precio_venta_estandar*cantidad_usuarios_premiun - gastos

utilidad_usuario_periodo_prueba = 0*cantidad_usuarios_periodo_prueba - gastos



#se concadena y muestra utilidades de los tipos de usuarios
print("La utilidad total de  Usuario normal es {}".format(utilidad_usuario_normal))

print("La utilidad total de  Usuario premiun es {}".format(utilidad_usuario_premiun))

print("La utilidad total de  Usuario de prueba es {}".format(utilidad_usuario_periodo_prueba))

# -*- coding: utf-8 -*-
"""
Created on Thu May 20 17:23:26 2021

@author: YonathanVasquez

"""

import string

# 1.1 Concatenando letras

def gen(numero_letras):    # llamar: gen(10)
    ############################ Inicio concatenando letras
    
        numero_letras = int(numero_letras)
        palabra = ""    
        for i in range(numero_letras):
            palabra+= string.ascii_lowercase[i]
            return palabra
    
    ############################# Fin concatenando letras
        
        


# 1.2 dibujando letras
# A
# Inicio dibujar O
def letra_o(n):  # llamar: letra_o(5)
    
        n = int(n)
        contain = ""
        for i in range(n):
            contain += "*"  
        print(contain)
        contain_middle = "*"
        for i in range (n - 2):
            contain_middle += " "
        contain_middle += "*\n"
        c = contain_middle * (n - 2)
        print(c.rstrip())
        print(contain)
    
# Fin dibujar O

# Inicio dibujar I  
    
def letra_i(n):
    
        n = int(n)
        contain = ""
        for i in range(n):
            contain += "*"  
        print(contain)
        e = n-1
        d = int(e/2)
        
        contain_middle = ""
        for i in range (d):
            contain_middle += " "
        contain_middle += "*"
        for j in range (d):
            contain_middle += " "
        contain_middle += "\n"
        c = contain_middle * (n - 2)
        print(c.rstrip())
        print(contain)
    
    
# Fin dibujar I  


# Inicio dibujar X

def letra_x(n):
   
    
    
        n = int(n)
        
        if n %2 == 0:
            print(n)
            n+= 1
        else:
            print(n)
            n-=1
        d = int(n/2)
        print(d)
        contain_2 = ""
        for b in range(d):
            
            contain_2 += " "*b + "*" + " "*(n-(2*(b+1))) + "*\n"
        cadena_arriba = contain_2    
        print(cadena_arriba.rstrip())
        
        
        
        contain_3=" "*d + "*" + " "*d+"\n"
        print(contain_3.rstrip())
        
        
        contain_4 = ""
        
        for e in range(d):
            contain_4 += " "*(d-1) + "*" + " "*((n)-(2*d))+ "*\n" 
        print(contain_4)
        

        
 # Inicio dibujar X       
        
        
        
    
    

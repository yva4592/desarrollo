

### definiendo funcion

"""

def multiplicar():
    print(2*2)
    
multiplicar()

"""

#### 

"""

def imprimir_menu():
    print('Menú: ')
    print('1) Opción 1')
    print('2) Opción 2')
    print('3) Opción 3')
    print('4) Salir')
  
    
  
###  funcion con menu   
    
def imprimir_menu():
    print('Menú: Escoja una acción')
    print('-' * 20)
    print('1) Acción 1')
    print('2) Acción 2')
    print('Escribe "Salir" para terminar el programa')
    print()
    
opt_menu = 'cualquier valor'
    
while opt_menu != 'salir' and opt_menu != 'Salir':
    imprimir_menu() # Se llama a la función
    opt_menu = input()
    if opt_menu == '1':
        print('Realizando acción 1')
    elif opt_menu == '2':
        print('Realizando acción 2')
    elif opt_menu == 'salir' or opt_menu == 'Salir':
        print('Saliendo')
    else:
        print('Opción inválida')
    print()
    
 ###  funcion con menu     
    


### funcion con parametros    

def multiplicar_dos_numeros(x,y):
    print(x*y)
    
multiplicar_dos_numeros(2,5)
    
### funcion con parametros    


   

# pasar valores a funcion desde consola

a = int(input())
b = int(input())
multiplicar_dos_numeros(a,b)

# pasar valores a funcion desde consola


 

# funcion con parametro opcional

def multiplicar_opcional(x,y= 1):
    print(x*y)
    
multiplicar_opcional(10)
multiplicar_opcional(10,3)
    
# funcion con parametro opcional


# recorriendo una funcion 

for i in range(1,11):
    for j in range(1,11):
        multiplicar_dos_numeros(i,j)
        
# recorriendo una funcion
        





########## funcion de fahrenheit a celsius y como usar

    
import random

# funcion
def fahrenheit(f):
    celsius = (f + 40) / 1.8 - 40
    print("La temperatura es de {} grados Celsius".format(celsius))
   
#opcion 1    
fahrenheit(random.randint(1, 100))



# opcion 2
far_user = int(input("Ingrese temperatura en Fahrenheit"))
fahrenheit(far_user)

########## funcion de fahrenheit a celsius y como usar





## funcion con return 

a = 20
b = 60



def multiplicar_dos_numeros_2(x,y=1):
    resultado = x * y
    return resultado
    print("holaaaaaa")  # no se ve por que termina en el return
    
valor = multiplicar_dos_numeros_2(a,b)

## funcion con return


# return vacio: No entrega nada
def return_vacio():
    return

return_vacio()
# return vacio: No entrega nada





##  funcion con variables lo cales 
## retorna 2 

def ejemplo_locales(parametro):
        print("este es el parametro", parametro)
        definido_dentro = "esta variable tambien es local"
        return parametro, definido_dentro  # se envia mas de 1 separando por ","


retorno_1, retorno_2 = ejemplo_locales("papa")
print(retorno_1)
print(retorno_2)

parametro    # definida solo en la funcion no el main "consola"
definido_dentro    # definida solo en la funcion no el main "consola"

##  funcion con variables lo cales 
## retorna 2





## ejemplo  de variable local


def aprobado(promedio, nota_aprobacion = 4): #INICIO  ---> socpe o alcanse de la uncion
    if promedio >= nota_aprobacion:
        status = True
    else:
        status = False
    return status  # FIN ---> socpe o alcanse de la uncion

print(status)   # status no esta en el main o "espacio principal"

## ejemplo  de variable local

"""



""" primero ejemplo

# estas variables se definen dentro del ambiente __main__
name = 'Alan Turing'
age = 41

def any_function():
# Esta variable está siendo definda en un ambiente nuevo: el de any_function
    birthplace = 'Londres'
# Como age es una variable global, se puede acceder desde este scope
# Y también se puede acceder a birthplace, porque aunque sea local, se está en su mismo scope
    print("Edad de", age, "años. Residencia: ", birthplace)
# Acá se vuelve al __main__ (se elimina la identación de 4 espacios)
# La varianle birthplace no existe en este espacio.


print("Nombre: ", name)
any_function()

"""
""" primero ejemplo

Se puede ver que, si bien dentro de la función el valor de age fue reescrito (primer print ), al
ser consultada nuevamente, de vuelta en el espacio __main__ (segundo print ), ésta
conserva el valor que se le asignó originalmente.
Esto es porque en Python (a diferencia de otros lenguajes), cuando se ingresa en una
función, se está trabajando en un ambiente nuevo que, aunque posea variables con el
mismo nombre de una global, no afecta las variables 'externas' . Es decir, al utilizar el
nombre de una variable global dentro de un contexto local y asignarle un nuevo valor,
simplemente se está declarando una nueva variable , que funcionará en el scope local.

"""


"""  segundo ejemplo


age = 41

def any_function():
# Se asigna un nuevo valor
    age = 100
    print("En el scope de la función, age tiene el valor de:", age)
    
    
any_function()
print("Luego de ejecutar any_function(), tiene el valor de:",age)



"""

"""  segundo ejemplo

Se puede ver que, si bien dentro de la función el valor de age fue reescrito (primer print ), al
ser consultada nuevamente, de vuelta en el espacio __main__ (segundo print ), ésta
conserva el valor que se le asignó originalmente.
Esto es porque en Python (a diferencia de otros lenguajes), cuando se ingresa en una
función, se está trabajando en un ambiente nuevo que, aunque posea variables con el
mismo nombre de una global, no afecta las variables 'externas' . Es decir, al utilizar el
nombre de una variable global dentro de un contexto local y asignarle un nuevo valor,
simplemente se está declarando una nueva variable , que funcionará en el scope local.

"""

"""  tercero ejemplo


# estas variables se definen dentro del ambiente __main__
name = 'Alan Turing'
age = 41
birthplace = 'Maida Vale, London, England'
gender = "male"

def say_hi():
# Estas variables están siendo defindas en un ambiente nuevo: el de any_function
# Las variables name y age son "variables nuevas",
# que no tienen relación con las variables globales definidas previamente.
    name = 'Ian MacKaye'
    age = 56
    occupation = 'Musician'
    print("{} is a {} and is {} years old. He is {}.".format(name,
    occupation, age, gender))
# name y age tomarán los valores de su scope local
say_hi()
# name y age toman los valores del scope main
print("{} was born in {} and died when he was {} years old. He was {}.".format(name, birthplace, age, gender))


"""

"""  tercero ejemplo


Por consiguiente, podemos afirmar que:
● birthplace y gender son variables globales, cuyo alcance es __main__ ( say_hi
está dentro de __main__ ).
● occupation es una variable local, cuyo alcance es el método say_hi .
● birthplace no existe dentro del método say_hi , pero se podría acceder a ella
desde say_hi , porque es global.
● occupation no existe fuera del método say_hi , y no se puede acceder a ella desde
main , porque es local de say_hi .
● name y age existen dos veces; En un contexto global, cuyo alcance es __main__ . Y en
un contexto local, cuyo alcance es say_hi .
● gender es una variable global.

"""













TRUE == TRUE

TRUE == FALSE

"hello" == "goodbye"

3 == 2


"hello" != "goodbye"

TRUE != TRUE

TRUE != FALSE

"hello" != "goodbye"

3 != 2


3 < 5

3 > 5


#Alphanetical Order

"hello" > "goobbye"


#true coerces to 1
# FALSE coerces to 0

TRUE < FALSE



5 >= 3

3 >= 3



linkedin <- c(16,9,13,5,2,17,14)


linkedin

linkedin > 10



facebook <- c(17,7,5,16,8,13,14)


facebook


facebook <= linkedin




3 == (2 + 1)
"intermediate" != "r"
TRUE != FALSE
"Rchitect" != "rchitect"




TRUE == FALSE

-6*14 != 17 -101


"useR" == "user"




TRUE == 1






(1 + 2) > 4
"dog" < "Cats"
TRUE <= FALSE




# Comparison of numerics
-6 * 5 + 2 >= -10 + 1

# Comparison of character strings
"raining" <=  "raining dogs"

# Comparison of logicals
TRUE > FALSE




# The linkedin and facebook vectors have already been created for you
linkedin <- c(16, 9, 13, 5, 2, 17, 14)
facebook <- c(17, 7, 5, 16, 8, 13, 14)

# Popular days
linkedin  > 15

# Quiet days
linkedin <= 5

# LinkedIn more popular than Facebook

linkedin >  facebook




# The social data has been created for you
linkedin <- c(16, 9, 13, 5, 2, 17, 14)
facebook <- c(17, 7, 5, 16, 8, 13, 14)
views <- matrix(c(linkedin, facebook), nrow = 2, byrow = TRUE)

# When does views equal 13?

views[] == 13



# When is views less than or equal to 14?

views[] <= 14






# OPERADORES --------------------------------------------------------------




TRUE & TRUE


FALSE & TRUE

TRUE & FALSE


FALSE & FALSE




X <- 12

X > 5 & X < 15




xa <- 17

xa > 5 & xa < 15


TRUE | TRUE


FALSE | TRUE

TRUE | FALSE


FALSE | FALSE


Y <- 4

Y < 5 | Y  > 15


Ya <- 14

Ya < 5 | Ya  > 15


!TRUE

!FALSE






!(Ya < 5)

Ya  >= 5



is.numeric(5)


!is.numeric(5)


is.numeric("hello")


!is.numeric("hello")


TRUE & TRUE
FALSE | TRUE
5 <= 5 & 2 < 3
3 < 4 | 7 < 6

# rm (list = ls())


# install.packages("rstudioapi")
# install.packages('xml2')



TRUE & TRUE
FALSE | TRUE
5 <= 5 & 2 < 3
3 < 4 | 7 < 6



linkedin <- c(16, 9, 13, 5, 2, 17, 14)

facebook <- c(17,  7,  5, 16,  8, 13, 14)

views = matrix(c(linkedin, facebook), nrow = 2, byrow = TRUE)

last <- tail(linkedin, 1)

 5 > last | last > 10
 
  15 <  last & last <=  20
  
  
  # linkedin exceeds 10 but facebook below 10
  linkedin > 10 & facebook < 10
  
  # When were one or both visited at least 12 times?
  linkedin >= 12 | facebook >= 12
  
  # When is views between 11 (exclusive) and 14 (inclusive)?
  
  11 < views & views <= 14
  
  
  
  
  
  
  !TRUE
  !(5 > 3)
  !!FALSE
  
  

  x <- 5
  y <- 7
  !(!(x < 4) & !!!(y > 12))
  
  
  
  
  a <- c(1:30)
  median(a)
  mean(a)
  
  
  
  
  
  
  # Select the second column, named day2, from li_df: second
  second <- li_df[,2]
  
  # Build a logical vector, TRUE if value in second is extreme: extremes
  extremes <- 5 > second  |  second > 25
  
  # Count the number of TRUEs in extremes
  sum(extremes)





# CONDICIONES -------------------------------------------------------------

X <- -3
  
if(X < 0)
{
  print("x is a negative number")
}
  
  
  
  y <- 5
  
  if(y < 0)
  {
    print("y is a negative number")
  }
  
  
  x <- -3
  
  if(x < 0){
    print("x is a negative number")
  } else{
    print("x is either a positive number or zero")
  }
  
  
  
  x <- 5
  
  if(x < 0){
    print("x is a negative number")
  } else{
    print("x is either a positive number or zero")
  }
  
  
  
  
  x <- 5
  
  if(x < 0){
    print("x is a negative number")
  } else if(x == 0){
    print("x is zeror")
  } else{
    print("x is a positive number")
  }
  
  
  y <- 6
  
  if(y %% 2 == 0) {
    
    print("divisible by 2")
    
  } else if ( y %% 3 == 0) {
    
    print("divisible by 3")
  
  }else {
    
    print("not divisible by 2 nor by 3...")
  }
  
  
  
  # Variables related to your last day of recordings
  medium <- "LinkedIn"
  num_views <- 14
  
  # Examine the if statement for medium
  if (medium == "LinkedIn") {
    print("Showing LinkedIn information")
  }
  
  # Write the if statement for num_views
  if (num_views > 15) {
    print("You are popular")
  }
  
  
  
  
  # Variables related to your last day of recordings
  medium <- "LinkedIn"
  num_views <- 14
  
  # Control structure for medium
  if (medium == "LinkedIn") {
    print("Showing LinkedIn information")
  } else{
    print("Unknown medium")
  }
  
  
  
  # Control structure for num_views
  if (num_views > 15) {
    print("You're popular!")
  } else{
    print("Try to be more visible!")
  }
  
  
  
  
  # Variables related to your last day of recordings
  medium <- "LinkedIn"
  num_views <- 14
  
  # Control structure for medium
  if (medium == "LinkedIn") {
    print("Showing LinkedIn information")
  } else if (medium == "Facebook") {
    # Add code to print correct string when condition is TRUE
    print("Showing Facebook information")
  } else {
    print("Unknown medium")
  }
  
  # Control structure for num_views
  if (num_views > 15) {
    print("You're popular!")
  } else if (num_views <= 15 & num_views > 10) {
    # Add code to print correct string when condition is TRUE
    print("Your number of views is average")
  } else {
    print("Try to be more visible!")
  }
  
  
  
  if (number < 10) {
    if (number < 5) {
      result <- "extra small"
    } else {
      result <- "small"
    }
  } else if (number < 100) {
    result <- "medium"
  } else {
    result <- "large"
  }
  print(result)
  
  
  
  
  # Variables related to your last day of recordings
  li <- 15
  fb <- 9
  
  # Code the control-flow construct
  if (li >= 15 & fb >= 15) {
    sms <- 2 * (li + fb)
  } else if (li < 10 & fb < 10 ) {
    sms <- 0.5 * (li + fb)
  } else {
    sms <- (li + fb)
  }
  
  # Print the resulting sms to the console
  print(sms)
  
  
  ctr <- 1
  
  while(ctr <= 7){
    print( paste("ctr is set to", ctr))
    ctr <- ctr + 1
    
  }
  
  
  
  
  ctr <- 1
  
  while (ctr <= 7){
    
    if(ctr %% 5 == 0){
      break
    }
    
    print(paste("ctr is set to", ctr))
    ctr <- ctr + 1
    
  }
  
  
  
  
  
  # Initialize the speed variable
  speed <- 64
  
  # Code the while loop
  while (speed > 30 ) {
    # print("Slow down!")
    print(paste("Slow down!", speed))
    speed <- speed -7
  }
  
  # Print out the speed variable
  speed
  
  
  
  

  
  # Initialize the speed variable
  speed <- 64
  
  # Extend/adapt the while loop
  while (speed > 30) {
    print(paste("Your speed is",speed))
    if (speed > 48 ) {
      print("Slow down big time!")
      speed <- speed - 11
    } else {
      print("Slow down!")
      speed <- speed - 6
    }
  }
  
  
  
  
  
  
  # Initialize the speed variable
  speed <- 88
  
  while (speed > 30) {
    print(paste("Your speed is", speed))
    
    # Break the while loop when speed exceeds 80
    if (speed > 80) {
      break
    }
    
    if (speed > 48) {
      print("Slow down big time!")
      speed <- speed - 11
    } else {
      print("Slow down!")
      speed <- speed - 6
    }
  }
  
  
  
  
  
  # Initialize i as 1 
  i <- 1
  
  # Code the while loop
  while (i <= 10) {
    print(3*i)
    if ( 3*i %% 8 == 0 ) {
      print(3*i)
      break
    }
    i <- i + 1
  }
  
  
  
  
  
  cities <- c("New York","Paris",
              "London","Tokyo",
              "Rio de Janeiro","Cape Town") 
  
  
  for(city in cities){
    
    print(city)
  }
  
  
  for(city in cities){
    
    if(nchar(city) == 6){
      break
    }
    print(city)
    
  }
  
  
  
  
  for(city in cities){
    
    if(nchar(city) == 6){
      next
    }
    print(city)
    
  }
  
  
  
  
  
  for(i in 1:length(cities)){
    
    print(paste(cities[i],"esta en la posición",i,"en el vector de ciudades."))
    
  }
  
  
  
  
  for(city in cities){
    
    print(city)
    
  }
  
  
  
  for(i in 1:length(cities)){
    
    print(cities[i])
    
  }
  
  
  
  
  
  
  # The linkedin vector has already been defined for you
  linkedin <- c(16, 9, 13, 5, 2, 17, 14)
  
  # Loop version 1
  for(link in linkedin){
    print(link)
  }
  
  
  
  # Loop version 2
  for(i in 1:length(linkedin)){
    print(linkedin[i])
    
  }
  
  
  
  
  
  # The nyc list is already specified
  nyc <- list(pop = 8405837, 
              boroughs = c("Manhattan", "Bronx", "Brooklyn", "Queens", "Staten Island"), 
              capital = FALSE)
  
  # Loop version 1
  for(link in nyc){
    print(link)
  }
  
  
  
  # Loop version 2
  
  for(i in 1:length(nyc)){
    print(nyc[[i]])
    
  }
  
  
  
  # The tic-tac-toe matrix ttt has already been defined for you
  
  # [,1] [,2] [,3]
  # [1,] "O"  NA   "X" 
  # [2,] NA   "O"  "O" 
  # [3,] "X"  NA   "X" 
  
  a <- c("O",NA,   "X")
  b <- c(NA,   "O",  "O")
  ab <- c("X",  NA,   "X")
  
  ttt = matrix(c(a,b,ab), nrow = 3, byrow = TRUE)
  
  
  
  
  # define the double for loop
  
  for (i in 1:nrow(ttt)) {
    for (j in 1:ncol(ttt)) {
      print(paste("On row",i,"and column",j,"the board contains",ttt[i,j]))
    }
  }
  

  
  
  
  # The linkedin vector has already been defined for you
  linkedin <- c(16, 9, 13, 5, 2, 17, 14)
  
  # Code the for loop with conditionals
  for (li in linkedin) {
    if (li > 10 ) {
      print("You're popular!")
    } else {
      print("Be more visible!")
    }
    print(li)
  }
  
  
  
  
  
  for (li in linkedin) {
    if (li > 10) {
      print("You're popular!")
    } else {
      print("Be more visible!")
    }
    
    # Add if statement with break
    if (li > 16) {
      print("This is ridiculous, I'm outta here!")
      break
    }
    
    # Add if statement with next
    if (li < 5) {
      print("This is too embarrassing!")
      next
    }
    
    print(li)
  }
  
  
  
  
  
  
  
  # Pre-defined variables
  rquote <- "r's internals are irrefutably intriguing"
  
  chars <- strsplit(rquote, split = "")[[1]]
  
  # Initialize rcount
  rcount <- 0
    
  # Finish the for loop
    for (char in chars) {
      
      if(char == "r"){
        rcount = rcount +1
      }
      
      if(char == "u"){
        break
      }
    }
  
  # Print out rcount
  print(rcount)
  

# FUNCIONES ---------------------------------------------------------------

# Desviacion estandar
  
  a <- c(1,5,6,7)
  
  my_sd <- sd(a)
  
  my_sd
  
  
  
  
  
  b <- c(1,5,6,NA)
  
  my_sda <- sd(b, TRUE)
  
  
  my_sda
  
  my_sdax <- sd(b, na.rm = TRUE)
  
  my_sdax
  
  
  
  

# Argumentos de una función ARGS() ----------------------------------------

args(sd)  
 -----------------------------------------------------------------------
# fin


  
  
  # Consult the documentation on the mean() function
  ?mean
  
  # Inspect the arguments of the mean() function
  args(mean)
  
  
  
  
  
  
  # The linkedin and facebook vectors have already been created for you
  linkedin <- c(16, 9, 13, 5, 2, 17, 14)
  facebook <- c(17, 7, 5, 16, 8, 13, 14)
  
  # Calculate average number of views
  avg_li  <- mean(linkedin)
  avg_li
  
  # Inspect avg_li and avg_fb
  avg_fb <- mean(facebook)
  avg_fb
  
  
  
  
  
  
  
  
  # The linkedin and facebook vectors have already been created for you
  linkedin <- c(16, 9, 13, 5, 2, 17, 14)
  facebook <- c(17, 7, 5, 16, 8, 13, 14)
  
  # Calculate the mean of the sum
  
  
  avg_sum <- mean(linkedin+facebook)
  
  
  # Calculate the trimmed mean of the sum
  avg_sum_trimmed <- mean (linkedin+facebook, trim = 0.2)
  
  
  
  # Inspect both new variables
  avg_sum
  avg_sum_trimmed
  
  
  
  
  
  # The linkedin and facebook vectors have already been created for you
  linkedin <- c(16, 9, 13, 5, NA, 17, 14)
  facebook <- c(17, NA, 5, 16, 8, 13, 14)
  
  # Basic average of linkedin
  mean(linkedin)
  
  # Advanced average of linkedin
  mean(linkedin, na.rm = TRUE)
  
  
  
  
  
  # The linkedin and facebook vectors have already been created for you
  linkedin <- c(16, 9, 13, 5, NA, 17, 14)
  facebook <- c(17, NA, 5, 16, 8, 13, 14)
  
  # Calculate the mean absolute deviation
  
  mean(abs(linkedin - facebook), na.rm = TRUE)
  
  
  
  ?read.table
  
  args(read.table)
  
  
  

# Crear mi función --------------------------------------------------------


  
  # my_fun <- function(arg1,arg2){
  # 
  #   body
  # }
  
  triple <- function(X){
    
    3*X
  }
  

  triple(6)

  
  

  math_magic <- function(a,b){

    a*b + a/b 
  }
  
  
  math_magic(4,2)
  
  
  
  
  math_magic1 <- function(a,b=1){
    
    a*b + a/b 
  }
  
  
  math_magic1(4)
  
  
  
  math_magic2 <- function(a,b=1){
    
    
    if(b == 0){
      return(0)
    }
    
    
    a*b + a/b 
  }
  
  math_magic2(4,0)
  
  
  
  
  
  
  
  # Create a function pow_two()
  pow_two <- function(arg1){

    arg1*arg1
  }
  
  
  # Use the function
  pow_two(12)
  
  
  # Create a function sum_abs()
  sum_abs <- function(a,b){
    
    abs(a) + abs(b)
  }
  
  
  # Use the function
  sum_abs(-2,3)
  
  

# función que nos dé el resultado aleatorio de lanzar un dado justo --------


  
  throw_die <- function() {
    number <- sample(1:6, size = 1)
    number
  }
  throw_die()
  
  
  # Define the function hello()
  
  hello <- function() {
    
    print("Hi there!")
    return(TRUE)
    
  }
  
  
  
  # Call the function hello()
  hello()
  
  
  
  
  
  
  # Finish the pow_two() function
  pow_two <- function(x, print_info=TRUE) {
    y <- x ^ 2
    
    if(print_info==TRUE)
    {      
    print(paste(x, "to the power two equals", y))
    }
    
    return(y)
  }
  
  pow_two(6)
  
  
  
  
  
  
  two_dice <- function() {
    possibilities <- 1:6
    dice1 <- sample(possibilities, size = 1)
    dice2 <- sample(possibilities, size = 1)
    dice1 + dice2
  }

  
  two_dice()
  
  
  
  
  
  
  triple <- function(x) {
    x <- 3*x
    x
  }
  a <- 5
  triple(a)
  a
  
  
  
  
  increment <- function(x, inc = 1) {
    x <- x + inc
    x
  }
  count <- 5
  a <- increment(count, 2)
  b <- increment(count)
  count <- increment(count, 2)
  
  
  
  
  
  # The linkedin and facebook vectors have already been created for you
  linkedin <- c(16, 9, 13, 5, 2, 17, 14)
  facebook <- c(17, 7, 5, 16, 8, 13, 14)
  
  
  # Define the interpret function
  interpret <- function(num_views) {
    if (num_views > 15) {
      print("You're popular!")
      return(num_views)
      
    } else {
      print("Try to be more visible!")
      return(0)
      
    }
  }
  
  # Call the interpret function twice
  interpret(linkedin[1])
  interpret(facebook[2])
  
  
  
  
  
  
  
  # The linkedin and facebook vectors have already been created for you
  linkedin <- c(16, 9, 13, 5, 2, 17, 14)
  facebook <- c(17, 7, 5, 16, 8, 13, 14)
  
  # The interpret() can be used inside interpret_all()
  interpret <- function(num_views) {
    if (num_views > 15) {
      print("You're popular!")
      return(num_views)
    } else {
      print("Try to be more visible!")
      return(0)
    }
  }
  
  # Define the interpret_all() function
  # views: vector with data to interpret
  # return_sum: return total number of views on popular days?
  interpret_all <- function(views, return_sum = TRUE) {
    count <- 0
    
    for (v in views) {
      count <- count + interpret(v)
    }
    
    if (return_sum == TRUE) {
      return(count)
    } else {
      return(NULL)
    }
  }
  
  
  
  # Call the interpret_all() function on both linkedin and facebook
  interpret_all(linkedin)
  interpret_all(facebook)
  
  
  
  

# R - Packages ------------------------------------------------------------

install.packages("ggvis")
  
search()

library("ggvis")

ggvis(mtcars, ~wt, ~hp)


# library("data.table")






# Load the ggplot2 package
library("ggplot2")


# Retry the qplot() function
qplot(mtcars$wt, mtcars$hp)

# Check out the currently attached packages again
search()  






# Chunk 1
library(data.table)
require(rjson)

# Chunk 2
library("data.table")
require(rjson)

# Chunk 3
library(data.table)
require(rjson, character.only = TRUE)

# Chunk 4
library(c("data.table", "rjson"))





# LAPPLY ------------------------------------------------------------------



nyc <- list(pop = 8405837,
            boroughs = c("Manhattan","Bronx","Brooklyn",
                         "Queens","Staten Island"),
            capital = FALSE)


for (info in nyc){
  
  print(class(info))
}


lapply(nyc,class)




cities <- c("New York", "Paris", "London", "Tokyo",
            "Rio de Janeiro", "Cape Town")



num_chars <- c()

for (i in 1:length(cities)) {
  
  num_chars[i] <- nchar(cities[i])
  
}


lapply (cities, nchar)

# convertir una list en un vector


unlist(lapply (cities, nchar))






oil_prices <- list (2.37, 2.49, 2.18, 2.22, 2.47, 2.32)



triple <- function(X){
  
  3*X
  
}


result <- lapply(oil_prices, triple)


str(result)

unlist(result)



multiply <- function(X, factor){
 
  X * factor
  
}


times3 <-  lapply(oil_prices, multiply , factor = 5)

unlist(times3)


times4 <-  lapply(oil_prices, multiply , factor = 4)

unlist(times4)





# The vector pioneers has already been created for you
pioneers <- c("GAUSS:1777", "BAYES:1702", "PASCAL:1623", "PEARSON:1857")

# Split names from birth year
split_math <- strsplit(pioneers, split = ":")

# Convert to lowercase strings: split_low
split_low = lapply(split_math,tolower)

# Take a look at the structure of split_low
str(split_low)






# Code from previous exercise:
pioneers <- c("GAUSS:1777", "BAYES:1702", "PASCAL:1623", "PEARSON:1857")
split <- strsplit(pioneers, split = ":")
split_low <- lapply(split, tolower)

# Write function select_first()
select_first <- function(x) {
  x[1]
}

# Apply select_first() over split_low: names
names = lapply(split_low,select_first)

# Write function select_second()
select_second <- function(x) {
  x[2]
}

# Apply select_second() over split_low: years
years = lapply(split_low,select_second)







# funcion anonima ---------------------------------------------------------


# Code from previous exercise:
pioneers <- c("GAUSS:1777", "BAYES:1702", "PASCAL:1623", "PEARSON:1857")
split <- strsplit(pioneers, split = ":")
split_low <- lapply(split, tolower)

# Write function select_first()

# Apply select_first() over split_low: names
names = lapply(split_low,function(x) {x[1]})

# Write function select_second()

# Apply select_second() over split_low: years
years = lapply(split_low, function(x) {x[2]})







# Definition of split_low
pioneers <- c("GAUSS:1777", "BAYES:1702", "PASCAL:1623", "PEARSON:1857")
split <- strsplit(pioneers, split = ":")
split_low <- lapply(split, tolower)

# Generic select function
select_el <- function(x, index) {
  x[index]
}

# Use lapply() twice on split_low: names and years
names <- lapply(split_low,select_el,index=1)
years <- lapply(split_low,select_el,index=2)






cities <- c("New York", "Paris", "London", "Tokyo",
            "Rio de Janeiro", "Cape Town")
unlist(lapply(cities, nchar))

sapply(cities, nchar)

sapply(cities, nchar, USE.NAMES =  FALSE)





fist_and_last <- function(name){
  
  name <- gsub(" ", "", name)
  letters <- strsplit(name,split = "")[[1]]
  c(first = min(letters), last = max(letters))
  
  
}


fist_and_last("New York")

sapply(cities, fist_and_last)







unique_letters <- function(name){
  
  name <- gsub(" ", "", name)
  letters <- strsplit(name,split = "")[[1]]
  unique(letters)
  
  
}



unique_letters("London")


lapply(cities, unique_letters)


sapply(cities, unique_letters)










temp <- list(c(3,7,9,6,-1), 
            c(6,9,12,13,-5),
            c(4,8,3,-1,-3),
            c(1,4,7,2,-2),
            c(5,7,9,4,2),
            c(-3,5,8,9,4),
            c(3,6,9,4,1))

# temp has already been defined in the workspace

# Use lapply() to find each day's minimum temperature
lapply(temp, min)

# Use sapply() to find each day's minimum temperature
sapply(temp, min)

# Use lapply() to find each day's maximum temperature
lapply(temp, max)

# Use sapply() to find each day's maximum temperature
sapply(temp, max)






# temp is already defined in the workspace

# Finish function definition of extremes_avg
extremes_avg <- function(x) {
  ( min(x) + max(x) ) / 2
}

# Apply extremes_avg() over temp using sapply()
sapply(temp, extremes_avg)

# Apply extremes_avg() over temp using lapply()
lapply(temp, extremes_avg)







# temp is already available in the workspace

# Create a function that returns min and max of a vector: extremes
extremes <- function(x) {
  c(min = min(x),max = max(x))
}

# Apply extremes() over temp with sapply()
sapply(temp, extremes)

# Apply extremes() over temp with lapply()
lapply(temp, extremes)







# temp is already prepared for you in the workspace

# Definition of below_zero()
below_zero <- function(x) {
  return(x[x < 0])
}

# Apply below_zero over temp using sapply(): freezing_s
freezing_s  <- sapply(temp, below_zero)

# Apply below_zero over temp using lapply(): freezing_l
freezing_l <- lapply(temp, below_zero)

# Are freezing_s and freezing_l identical?
identical(freezing_s,freezing_l)

# str(identical)
# args(identical)





# temp is already available in the workspace

# Definition of print_info()
print_info <- function(x) {
  cat("The average temperature is", mean(x), "\n")
}

# Apply print_info() over temp using sapply()
sapply(temp,print_info)

# Apply print_info() over temp using lapply()
lapply(temp,print_info)







cities <- c("New York", "Paris", "London", "Tokyo",
            "Rio de Janeiro", "Cape Town")

sapply(cities, nchar)


vapply(cities, nchar, numeric(1))




fist_and_last <- function(name){
  
  name <- gsub(" ", "", name)
  letters <- strsplit(name,split = "")[[1]]
  c(first = min(letters), last = max(letters))
  
  
}


sapply(cities,fist_and_last)

vapply(cities,fist_and_last, character(2))

# muestra error
vapply(cities,fist_and_last, nuneric[1])




unique_letters <- function(name){
  
  name <- gsub(" ", "", name)
  letters <- strsplit(name,split = "")[[1]]
  unique(letters)
  
}


sapply(cities,unique_letters)

# genera error por que tiene distintas entregas de catidad e caracteres

vapply(cities,unique_letters,character(7))





# temp is already available in the workspace

# Definition of basics()
basics <- function(x) {
  c(min = min(x), mean = mean(x), max = max(x))
}

# Apply basics() over temp using vapply()
vapply(temp,basics,numeric(3))





# temp is already available in the workspace

# Definition of the basics() function
basics <- function(x) {
  c(min = min(x), mean = mean(x), median = median(x), max = max(x))
}

# Fix the error:
vapply(temp, basics, numeric(4))





# temp is already defined in the workspace

# Convert to vapply() expression
vapply(temp, max, numeric(1))

# Convert to vapply() expression
vapply(temp, function(x, y) { mean(x) > y }, y = 5, logical(1))






v1 <- c(1.1, -7.1, 5.4, -2.7)
v2 <- c(-3.6, 4.1, 5.8, -8.0)

mean( c( sum( round(abs(v1)))  , sum( round(abs(v2)))  )    )





li <-  list ( log = TRUE,
              ch = "hello",
              int_vec = sort(  rep( seq(8,2, by= -2), times = 2 )))







is.list(li)

is.list(c(1,2,3))


li2 <- as.list(c(1,2,3))

is.list(li2)


unlist(li)


str (append(li,rev(li)))





# The errors vector has already been defined for you
errors <- c(1.9, -2.6, 4.0, -9.5, -3.4, 7.3)

# Sum of absolute rounded values of errors

sum(    round(  abs(  errors ) )    )







# Don't edit these two lines
vec1 <- c(1.5, 2.5, 8.4, 3.7, 6.3)
vec2 <- rev(vec1)

# Fix the error
mean(c(abs(vec1), abs(vec2)))

mean(1.1,1.2)



# Funciones matematicas ---------------------------------------------------

# abs(): Calcula el valor absoluto.
# sum(): Calcula la suma de todos los valores en una estructura de datos.
# mean(): Calcula la media aritmética.
# round(): Redondea los valores a 0 decimales de forma predeterminada. 
# Pruebe ?rounden la consola las variaciones round()y formas de cambiar
# el número de dígitos a redondear.



# funciones - estructura de datos -----------------------------------------


# R presenta un montón de funciones para hacer malabares con las estructuras de datos:
#   
# seq(): Generar secuencias, mediante la especificación de los from, to y by argumentos.
# rep(): Replica elementos de vectores y listas.
# sort(): Ordena un vector en orden ascendente. Funciona con números, pero también con cadenas de caracteres y lógicos.
# rev(): Invierte los elementos de una estructura de datos para los que se define la inversión.
# str(): Muestra la estructura de cualquier objeto R.
# append(): Fusiona vectores o listas.
# is.*(): Compruebe la clase de un objeto R.
# as.*(): Convierte un objeto R de una clase a otra.
# unlist(): Aplanar (posiblemente incrustadas) listas para producir un vector.
# 



# The linkedin and facebook lists have already been created for you
linkedin <- list(16, 9, 13, 5, 2, 17, 14)
facebook <- list(17, 7, 5, 16, 8, 13, 14)

# Convert linkedin and facebook to a vector: li_vec and fb_vec
li_vec <- unlist(linkedin)
fb_vec <- unlist(facebook)


# Append fb_vec to li_vec: social_vec
social_vec <-  append(li_vec,fb_vec)

# Sort social_vec
sort(social_vec, decreasing = TRUE)





# Fix me
rep(seq(1, 7, by = 2), times = 7)







# Create first sequence: seq1
seq1 <- seq(1,500, by = 3)

# Create second sequence: seq2
seq2 <- seq(1200,900, by = -7)

# Calculate total sum of the sequences
sum(c(seq1,seq2))





animals <- c("cat", "moose", "impala" , "ant" , "kiwi")

grepl(pattern = "a", x = animals)

 
grepl(pattern = "^a", x = animals)

grepl(pattern = "a$", x = animals)




grep(pattern = "a", x = animals)

which(grepl(pattern = "a", x = animals))


grep(pattern = "^a", x = animals)

grep(pattern = "a$", x = animals)



sub(pattern = "a",  replacement = "o" ,x = animals)


gsub(pattern = "a",  replacement = "o" ,x = animals)



gsub(pattern = "a|i",  replacement = "_" ,x = animals)


gsub(pattern = "a|i|o",  replacement = "_" ,x = animals)







# The emails vector has already been defined for you
emails <- c("john.doe@ivyleague.edu", "education@world.gov", "dalai.lama@peace.org",
            "invalid.edu", "quant@bigdatacollege.edu", "cookie.monster@sesame.tv")

# Use grepl() to match for "edu"
grepl(pattern = "edu", x = emails)

# Use grep() to match for "edu", save result to hits
hits = grep(pattern = "edu", x = emails)

# Subset emails using hits
emails[hits]

#' @, porque un correo electrónico válido debe contener un signo de arroba.
#' .*, que coincide con cualquier carácter (.) cero o más veces (*). Tanto el punto como el asterisco son metacaracteres. Puede usarlos para hacer coincidir cualquier carácter entre el signo arroba y la parte ".edu" de una dirección de correo electrónico.
#' \\.edu$, para que coincida con la parte ".edu" del correo electrónico al final de la cadena. La \\parte escapa al punto: le dice a R que quieres usar el .como un personaje real.
#' 


# The emails vector has already been defined for you
emails <- c("john.doe@ivyleague.edu", "education@world.gov", "dalai.lama@peace.org",
            "invalid.edu", "quant@bigdatacollege.edu", "cookie.monster@sesame.tv")

# Use grepl() to match for .edu addresses more robustly
grepl(pattern = "@.*\\.edu$", x = emails)

# Use grep() to match for .edu addresses more robustly, save result to hits
hits = grep(pattern = "@.*\\.edu$", x = emails)

# Subset emails using hits
emails[hits]








# The emails vector has already been defined for you
emails <- c("john.doe@ivyleague.edu", "education@world.gov", "global@peace.org",
            "invalid.edu", "quant@bigdatacollege.edu", "cookie.monster@sesame.tv")

# Use sub() to convert the email domains to datacamp.edu
sub(pattern = "@.*\\.edu$",  replacement = "@datacamp.edu" ,x = emails)




# .*: A usual suspect! It can be read as "any character that is matched zero or more times".
# \\s: Match a space. The "s" is normally a character, escaping it (\\) makes it a metacharacter.
# [0-9]+: Match the numbers 0 to 9, at least once (+).
# ([0-9]+): The parentheses are used to make parts of the matching string available to define the replacement. The \\1 in the replacement argument of sub() gets set to the string that is captured by the regular expression [0-9]+.
# 


awards <- c("Won 1 Oscar.",
            "Won 1 Oscar. Another 9 wins & 24 nominations.",
            "1 win and 2 nominations.",
            "2 wins & 3 nominations.",
            "Nominated for 2 Golden Globes. 1 more win & 2 nominations.",
            "4 wins & 1 nomination.")


sub(".*\\s([0-9]+)\\snomination.*$", "\\1", awards)







# FECHAS Y HORAS ----------------------------------------------------------



today <- Sys.Date()
today


# class(today)  devuekve tipo "Date"

now <- Sys.time()
now





my_date <-   as.Date("1971-05-14")
my_date

class(my_date)



my_date <- as.Date("1971-14-05")




my_date <- as.Date("1971-14-05",  format =  "%Y-%d-%m")
my_date


my_time <- as.POSIXct("1971-05-14 11:25:15")
my_time


my_date + 1


my_date2 <- as.Date("1998-09-29")

my_date2 - my_date

my_time + 1





my_time2 <- as.POSIXct("1974-07-14 21:11:55 CET")
my_time2 - my_time


# fecha
my_date
# unclass l convierte en numero de dias : DATE son numeros desdel 1 enero de 1970
unclass(my_date) 


# POSIXct  son numeros de segundos desde el 1 enero 1970
my_time
unclass(my_time)
# attr(,"tzone")

# paquetes de fechas
# 
# -lubridate
# -zoo
# -xts

# install.packages("lubridate")





# Get the current date: today
today <- Sys.Date()

# See what today looks like under the hood
unclass(today)

# Get the current time: now
now <- Sys.time()

# See what now looks like under the hood
unclass(now)





# Crear y formatear fechas
# 
# Para crear un Dateobjeto a partir de una cadena de caracteres simple en R, puede usar la as.Date()función. La cadena de caracteres debe obedecer a un formato que se puede definir mediante un conjunto de símbolos (los ejemplos corresponden al 13 de enero de 1982):
#   
#   %Y: Año de 4 dígitos (1982)
# %y: Año de 2 dígitos (82)
# %m: Mes de 2 dígitos (01)
# %d: Día del mes de 2 dígitos (13)
# %A: día laborable (miércoles)
# %a: día de la semana abreviado (miércoles)
# %B: mes (enero)
# %b: mes abreviado (enero)
# Los siguientes comandos de R crearán el mismo Dateobjeto para el día 13 de enero de 1982:
#   
#   as.Date("1982-01-13")
# as.Date("Jan-13-82", format = "%b-%d-%y")
# as.Date("13 January, 1982", format = "%d %B, %Y")
# Tenga en cuenta que la primera línea aquí no necesitaba un argumento de formato, porque de forma predeterminada R hace coincidir su cadena de caracteres con los formatos "%Y-%m-%d"o "%Y/%m/%d".
# 
# Además de crear fechas, también puede convertir fechas en cadenas de caracteres que utilizan una notación de fecha diferente. Para esto, usa la format()función. Pruebe las siguientes líneas de código:
#   
#   today <- Sys.Date()
# format(Sys.Date(), format = "%d %B, %Y")
# format(Sys.Date(), format = "Today is a %A!")



as.Date("1982-01-13")
as.Date("Jan-13-82", format = "%b-%d-%y")
as.Date("13 January, 1982", format = "%d %B, %Y")




today <- Sys.Date()
format(Sys.Date(), format = "%d %B, %Y")
format(Sys.Date(), format = "Today is a %A!")







# Definition of character strings representing dates
str1 <- "May 23, '96"
str2 <- "2012-03-15"
str3 <- "30/January/2006"

# Convert the strings to dates: date1, date2, date3
date1 <- as.Date(str1, format = "%b %d, '%y")
date2 <- as.Date(str2, format = "%Y-%m-%d")
date3 <- as.Date(str3, format = "%d/%B/%Y")



# Convert dates to formatted strings
format(date1, "%A")
format(date2, "%d")
format(date3, "%b %Y")


# Crear y formatear tiempos
# De manera similar a trabajar con fechas, puede utilizar as.POSIXct()para convertir de una cadena de caracteres a un POSIXctobjeto y format()para convertir de un POSIXctobjeto a una cadena de caracteres. Nuevamente, tiene una amplia variedad de símbolos:
#   
#   %H: horas como número decimal (00-23)
# %I: horas como número decimal (01-12)
# %M: minutos como número decimal
# %S: segundos como número decimal
# %T: notación abreviada para el formato típico %H:%M:%S
# %p: Indicador AM / PM
# Para obtener una lista completa de los símbolos de conversión, consulte la strptimedocumentación en la consola:
#   
#   ?strptime
# Nuevamente, as.POSIXct()usa un formato predeterminado para hacer coincidir cadenas de caracteres. En este caso, es %Y-%m-%d %H:%M:%S. En este ejercicio, la abstracción se realiza en diferentes zonas horarias.
# 


# Definition of character strings representing times
str1 <- "May 23, '96 hours:23 minutes:01 seconds:45"
str2 <- "2012-3-12 14:23:08"

# Convert the strings to POSIXct objects: time1, time2
time1 <- as.POSIXct(str1, format = "%B %d, '%y hours:%H minutes:%M seconds:%S")
time2 <- as.POSIXct(str2, format = "%Y-%m-%d %T")


# Convert times to formatted strings
format(time1, "%M")
format(time2, "%I:%M %p")







# day1, day2, day3, day4 and day5 are already available in the workspace


day1 <- as.Date("2021-04-09")
day2 <- as.Date("2021-04-11")
day3 <- as.Date("2021-04-16")
day4 <- as.Date("2021-04-22")
day5 <- as.Date("2021-04-27")



# Difference between last and first pizza day
day5 - day1

# Create vector pizza
pizza <- c(day1, day2, day3, day4, day5)

# Create differences between consecutive pizza days: day_diff
day_diff <- diff(pizza)
day_diff
# Average period between two consecutive pizza days
mean(day_diff)







logout = c("2021-04-13 10:56:29 UTC", "2021-04-18 09:14:52 UTC",
           "2021-04-18 12:35:48 UTC", "2021-04-18 13:17:22 UTC",
           "2021-04-20 22:08:47 UTC")

login = c("2021-04-13 10:18:04 UTC", "2021-04-18 09:14:18 UTC",
          "2021-04-18 12:21:51 UTC", "2021-04-18 12:37:24 UTC",
          "2021-04-20 21:37:55 UTC")

# login and logout are already defined in the workspace
# Calculate the difference between login and logout: time_online
time_online = logout - login

# Inspect the variable time_online
time_online

# Calculate the total time online
sum(time_online)

# Calculate the average time online
mean(time_online)





astro = c("20-Mar-2015","25-Jun-2015","23-Sep-2015","22-Dec-2015")
meteo = c("March 1, 15","June 1, 15","September 1, 15","December 1, 15")



# Convert astro to vector of Date objects: astro_dates
astro_dates <- as.Date(astro, format =  "%d-%b-%Y")

# Convert meteo to vector of Date objects: meteo_dates
meteo_dates <- as.Date(meteo, format =  "%B %d, %y")

# Calculate the maximum absolute difference between astro_dates and meteo_dates
max(abs(astro_dates-meteo_dates))



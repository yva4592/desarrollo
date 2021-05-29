


  # Poker and roulette winnings from Monday to Friday:
  poker_vector <- c(140, -50, 20, -120, 240)
  roulette_vector <- c(-24, -50, 100, -350, 10)
  days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
  names(poker_vector) <- days_vecmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmtor
  names(roulette_vector) <- days_vector

  # Total winnings with poker
  total_poker <- sum(poker_vector)

  # Total winnings with roulette
  total_roulette <-  sum(roulette_vector)

  # Total winnings overall
  total_week <- total_poker + total_roulette

  # Print out total_week
  total_week

  # Calculate total gains for poker and roulette
  total_poker <- sum(poker_vector)
  total_roulette <- sum(roulette_vector)

  # Check if you realized higher total gains in poker than in roulette
  # total_poker  > total_roulette

  # poker_wednesday <- poker_vector[3]


  poker_midweek <- poker_vector[c(2,3,4)]
  roulette_selection_vector <- roulette_vector[2:5]


  poker_start <- poker_vector[c("Monday","Tuesday","Wednesday")]

  # Calculate the average of the elements in poker_start
  mean(poker_start)



  # Which days did you make money on poker?
  selection_vector <- poker_vector > 0

  # Print out selection_vector
  selection_vector


  # Select from poker_vector these days
  poker_winning_days <- poker_vector[selection_vector]






  # Which days did you make money on roulette?
  selection_vectorr <-  roulette_vector > 0

  # Select from roulette_vector these days
  roulette_winning_days <- roulette_vector[selection_vectorr]

  roulette_winning_days



# matriz ------------------------------------------------------------------



  matrix(1:9, byrow = TRUE, nrow = 3)


  # Box office Star Wars (in millions!)

  new_hope <- c(460.998, 314.4)

  empire_strikes <- c(290.475, 247.900)

  return_jedi <- c(309.306, 165.8)


  # Create box_office

  box_office <-  c(new_hope, empire_strikes, return_jedi)



  # Construct matrix
  star_wars_matrix <- matrix(c(new_hope, empire_strikes, return_jedi), nrow = 3, byrow = TRUE)

  # Vectors region and titles, used for naming
  region <- c("US", "non-US")
  titles <- c("A New Hope", "The Empire Strikes Back", "Return of the Jedi")

  # Name the columns with region

   colnames(star_wars_matrix) <- region



  # Name the rows with titles
   rownames(star_wars_matrix) <- titles

  # Print out star_wars_matrix
   star_wars_matrix




  star_wars_matrix <- matrix(box_office,
                             nrow = 3, byrow = TRUE,
                             dimnames = list(titles, region))

  # The worldwide box office figures
  worldwide_vector <- rowSums(star_wars_matrix)

  # Bind the new variable worldwide_vector as a column to star_wars_matrix
  all_wars_matrix <-  cbind(star_wars_matrix, worldwide_vector)

  total_revenue_vector <- colSums(star_wars_matrix)

  all_wars_matrix2 <-  rbind(star_wars_matrix, total_revenue_vector)
  all_wars_matrix2




  # Select the non-US revenue for all movies
  non_us_all <- all_wars_matrix[,1]

  # Average non-US revenue
  mean(non_us_all)

  # Select the non-US revenue for first two movies
  non_us_some <- all_wars_matrix[1:2,2]

  # Average non-US revenue for first two movies
  mean(non_us_some)


  # Estimate the visitors
  visitors <- all_wars_matrix / 5

  # Print the estimate to the console
  visitors





# factor ------------------------------------------------------------------




  theory <- "factors"

  # Sex vector
  sex_vector <- c("Male", "Female", "Female", "Male", "Male")

  # Convert sex_vector to a factor
  factor_sex_vector <- factor(sex_vector)

  # Print out factor_sex_vector
  factor_sex_vector




  # Animals
  animals_vector <- c("Elephant", "Giraffe", "Donkey", "Horse")
  factor_animals_vector <- factor(animals_vector)
  factor_animals_vector

  # Temperature
  temperature_vector <- c("High", "Low", "High","Low", "Medium")
  factor_temperature_vector <- factor(temperature_vector, order = TRUE, levels = c("Low", "Medium", "High"))
  factor_temperature_vector



  # Code to build factor_survey_vector
  survey_vector <- c("M", "F", "F", "M", "M")
  factor_survey_vector <- factor(survey_vector)

  # Specify the levels of factor_survey_vector
  levels(factor_survey_vector) <-  c("Female", "Male")

  factor_survey_vector



  # Build factor_survey_vector with clean levels
  survey_vector <- c("M", "F", "F", "M", "M")
  factor_survey_vector <- factor(survey_vector)
  levels(factor_survey_vector) <- c("Female", "Male")
  factor_survey_vector

  # Generate summary for survey_vector
  summary(survey_vector)

  # Generate summary for factor_survey_vector
  summary(factor_survey_vector)


  # Create factor_speed_vector
  speed_vector <- c("medium", "slow", "slow", "medium", "fast")
  factor_speed_vector <- factor(speed_vector,
                                ordered = TRUE, levels = c("slow", "medium", "fast"))

  # Factor value for second data analyst
  da2 <- factor_speed_vector[2]

  # Factor value for fifth data analyst
  da5 <- factor_speed_vector[5]

  # Is data analyst 2 faster than data analyst 5?
    da2 > da5
  
  
  

# data frame --------------------------------------------------------------

# 
#   head(mtcars)
#   
# str(mtcars)
#   
  
  
# Definition of vectors
name <- c("Mercury", "Venus", "Earth", 
          "Mars", "Jupiter", "Saturn", 
          "Uranus", "Neptune")
type <- c("Terrestrial planet", 
          "Terrestrial planet", 
          "Terrestrial planet", 
          "Terrestrial planet", "Gas giant", 
          "Gas giant", "Gas giant", "Gas giant")
diameter <- c(0.382, 0.949, 1, 0.532, 
              11.209, 9.449, 4.007, 3.883)
rotation <- c(58.64, -243.02, 1, 1.03, 
              0.41, 0.43, -0.72, 0.67)
rings <- c(FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, TRUE)

# Create a data frame from the vectors
planets_df <-data.frame(name,type,diameter,rotation, rings)

str(planets_df)

# Print out diameter of Mercury (row 1, column 3)
planets_df[1,3]

# Print out data for Mars (entire fourth row)
planets_df[4,]


# The planets_df data frame from the previous exercise is pre-loaded
planets_df
# Select first 5 values of diameter column
planets_df[1:5,"diameter"]



# Select the rings variable from planets_df
rings_vector <- planets_df$rings

# Print out rings_vector
rings_vector




# planets_df and rings_vector are pre-loaded in your workspace
rings_vector


# Adapt the code to select all columns for planets with rings
planets_df[rings_vector,]

  
# planets_df is pre-loaded in your workspace
planets_df
# Select planets with diameter < 1
subset(planets_df, diameter < 1)




# CLASIFICACION -----------------------------------------------------------





a <- c(100, 10, 1000)
order(a)

a[order(a)]



# Use order() to create positions
positions <-  order(planets_df$diameter)

# Use positions to sort planets_df
planets_df[positions]


# Use order() to create positions
positions <-  order(planets_df$diameter)

# Use positions to sort planets_df
planets_df$diameter[positions]


vplanets_df[positions,]





# LISTA -------------------------------------------------------------------


# Vector with numerics from 1 up to 10
my_vector <- 1:10 

# Matrix with numerics from 1 up to 9
my_matrix <- matrix(1:9, ncol = 3)

# First 10 elements of the built-in data frame mtcars
my_df <- mtcars[1:10,]

# Construct list with these different elements:
my_list <- list(my_vector, my_matrix, my_df)

my_list




# First 10 elements of the built-in data frame mtcars
my_df <- mtcars[1:10,]

# Adapt list() call to give the components names
my_list <- list(my_vector, my_matrix, my_df)
names(my_list) <- c("vec", "mat", "df")
# Print out my_list
my_list

# Print out the vector representing the actors
shining_list

# Print the second element of the vector representing the actors
shining_list[[2]][2]



# Use the table from the exercise to define the comments and scores vectors
scores <- c(4.6, 
            5, 
            4.8, 
            5,
            4.2)

comments <- c("I would watch it again", 
              "Amazing!", 
              "I liked it", 
              "One of the best movies",
              "Fascinating plot")

# Save the average of the scores vector as avg_review  
movie_title
movie_actors
avg_review <-  mean(scores)


# Combine scores and comments into the reviews_df data frame
reviews_df <- data.frame(scores,comments)

# Create and print out a list, called departed_list
departed_list <- list(movie_title, movie_actors,reviews_df, avg_review)










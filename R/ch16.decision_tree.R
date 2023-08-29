# ---
# title: "ch16.decision_tree"
# author: "Seo"
# date: '2023 06 05'
# ---

#####################################################
# CART

# data loading
buy <- read.csv("https://www.dropbox.com/s/d4mycxz1t8srxpp/buy.csv?dl=1", stringsAsFactors = F)
buy$age <- as.factor(buy$age)
buy$income <- as.factor(buy$income)
buy$student <- as.factor(buy$student)
buy$credit_rating <- as.factor(buy$credit_rating)
buy$class_buys_computer <- as.factor(buy$class_buys_computer)

# classifier
library(rpart, warn.conflicts=F)
buy.dtree <- rpart(class_buys_computer~., data=buy, method="class")
buy.dtree

# visualizing
library(rpart.plot, warn.conflicts=F)
prp(buy.dtree, type=2, extra=104)

# tree pruning
buy.dtree <- rpart(class_buys_computer~., data=buy, method="class", maxdepth= 5, minsplit=2, minbucket = 1)
prp(buy.dtree, type=2, extra=104) # type 트리 그림 나타내는 방법 / extra중간 노드 나타낼 방법

# data loading - AHD
data <- read.csv("https://www.dropbox.com/s/lvkz0bzhy1pjoc2/heart.csv?dl=1", stringsAsFactors = F)
head(data)

data$Gender <- as.factor(data$Gender)
data$ChestPain <- as.factor(data$ChestPain)
data$Thal <- as.factor(data$Thal)
data$AHD <- as.factor(data$AHD)

# sampling : train and test
set.seed(321)
train <- sample(nrow(data), 0.7*nrow(data))
data.train <- data[train,]
data.test <- data[-train,]
table(data$AHD)
table(data.train$AHD)
table(data.test$AHD)


# classifier
data.dtree <- rpart(AHD~., data=data.train, method="class")

# visualizing
prp(data.dtree, type=2, extra=104)

# pruning information
printcp(data.dtree)

# pruning
data.dtree.pruned <- prune(data.dtree, cp=data.dtree$cptable[which.min(data.dtree$cptable[,"xerror"]),"CP"])
prp(data.dtree.pruned, type=2, extra=104)

# prediction
data.dtree.predicted <- predict(data.dtree.pruned, data.test, type="class")

library(caret, warn.conflicts=F)
confusionMatrix(data.dtree.predicted, data.test$AHD, positive = "Yes")


#####################################################
# Continuous Attribute 

# information gain
# info(D)
x <- c(2/7, 3/7, 2/7)
y <- log2(x)
info_d <- -sum(x * y)

# IG of elec750
x1 <- c(1/1); x2 <- c(3/6, 1/6, 2/6)
y1 <- log2(x1); y2 <- log2(x2)
info_elec750 <- sum(1/7*(-sum(x1 * y1)), 6/7*(-sum(x2 * y2)))

info_d - info_elec750

# IG of elec1350
x1 <- c(1/2, 1/2); x2 <- c(1/5, 2/5, 2/5)
y1 <- log2(x1); y2 <- log2(x2)
info_elec1350 <- sum(2/7*(-sum(x1 * y1)), 5/7*(-sum(x2 * y2)))

info_d - info_elec1350

# IG of elec2250
x1 <- c(2/4, 2/4); x2 <- c(1/3, 2/3)
y1 <- log2(x1); y2 <- log2(x2)
info_elec2250 <- sum(4/7*(-sum(x1 * y1)), 3/7*(-sum(x2 * y2)))

info_d - info_elec2250

# IG of elec4175
x1 <- c(2/5, 3/5); x2 <- c(2/2)
y1 <- log2(x1); y2 <- log2(x2)
info_elec4175 <- sum(5/7*(-sum(x1 * y1)), 2/7*(-sum(x2 * y2)))

info_d - info_elec4175

# IG of stream
x1 <- c(2/4, 1/4, 1/4); x2 <- c(2/3, 1/3)
y1 <- log2(x1); y2 <- log2(x2)
info_stream <- sum(4/7*(-sum(x1 * y1)), 3/7*(-sum(x2 * y2)))

info_d - info_stream

# IG of slope
x1 <- c(1/1); x2 <- c(3/5, 1/5, 1/5); x3 <- c(1/1)
y1 <- log2(x1); y2 <- log2(x2); y3 <- log2(x3)
info_slope <- sum(1/7*(-sum(x1 * y1)), 5/7*(-sum(x2 * y2)), 1/7*(-sum(x3 * y3)))

info_d - info_slope


#####################################################
# Regression Tree

# rental data : data loading
data <- read.csv("https://www.dropbox.com/s/kmgbaiv3we6gt8r/rentals.csv?dl=1", stringsAsFactors = F)
data$season <- as.factor(data$season)
data$work_day <- as.factor(data$work_day)

# information gain
# info(D)
info_d <- var(data$rentals)

n <- nrow(data)

# IG of season
var_spring <- var(data[data$season=="spring",]$rentals)
var_summer <- var(data[data$season=="summer",]$rentals)
var_autumn <- var(data[data$season=="autumn",]$rentals)
var_winter <- var(data[data$season=="winter",]$rentals)

info_season <- sum(nrow(data[data$season=="spring",])/n*var_spring, nrow(data[data$season=="summer",])/n*var_summer, nrow(data[data$season=="autumn",])/n*var_autumn, nrow(data[data$season=="winter",])/n*var_winter)

# IG of work_day
var_true <- var(data[data$work_day=="TRUE",]$rentals)
var_false <- var(data[data$work_day=="FALSE",]$rentals)

info_work_day <- sum(nrow(data[data$work_day=="TRUE",])/n*var_true, nrow(data[data$work_day=="FALSE",])/n*var_false)


############### iris
# sampling : train and test
set.seed(321)
train <- sample(nrow(iris), 0.7*nrow(iris))
data.train <- iris[train,]
data.test <- iris[-train,]
head(iris)
table(iris$Species)
table(data.train$Species)
table(data.test$Species)

# classifier
library(rpart)
data.dtree <- rpart(Sepal.Length~., data=data.train, method="anova")

# visualizing
library(rpart.plot)
prp(data.dtree, type=2, extra=101)

# pruning
printcp(data.dtree)
plotcp(data.dtree)
data.dtree.pruned <- prune(data.dtree, cp=data.dtree$cptable[which.min(data.dtree$cptable[,"xerror"]),"CP"])
prp(data.dtree.pruned, type=2, extra=101)

# prediction
yhat <- predict(data.dtree.pruned, data.test, method="anova")
mse <- mean((yhat - data.test[,"Sepal.Length"])^2)
mse

# linear regression
summary(data.lm <- lm(Sepal.Length~., data=data.train))
yhat <- predict(data.lm, data.test, method="anova")
mse <- mean((yhat - data.test[,"Sepal.Length"])^2)
mse

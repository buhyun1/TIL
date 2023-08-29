# ---
# title: "ch14.logistic_regression_analysis"
# author: "Seo"
# date: '2023 05 22'
# ---

#####################################################
# Logistic regression

# Binary Classification : pain
logistic <- read.csv("https://www.dropbox.com/s/68nc2w3xw6ajf8a/logistic.csv?dl=1", header=F, col.names=c("Treatment", "Age", "Duration", "Gender", "Pain"))

logistic$Gender <- as.factor(logistic$Gender)
logistic$Pain <- as.factor(logistic$Pain)

gl <- glm(Pain ~ ., data=logistic, family=binomial())
summary(gl)

# predict
gl.pred <- predict(gl, newdata=logistic, type="response")
gl.pred.tran <- factor(gl.pred > 0.5, levels=c(F, T), labels=c(0,1))

# performance measure (self)
library(caret, warn.conflicts=F)
confusionMatrix(gl.pred.tran, logistic$Pain)

# training & test data
set.seed(123)
train <- sample(nrow(logistic), 0.7*nrow(logistic))
logistic.train <- logistic[train,]
logistic.test <- logistic[-train,]
table(logistic.train$Pain)
table(logistic.test$Pain)

# model generation
gl <- glm(Pain ~ ., data=logistic.train, family=binomial())
summary(gl)

# test
# predict
gl.pred <- predict(gl, newdata=logistic.test, type="response")
gl.pred.tran <- factor(gl.pred > 0.5, levels=c(F, T), labels=c(0,1))

# performance measure (test)
confusionMatrix(gl.pred.tran, logistic.test$Pain)


# Binary Classification : exam score
data <- read.csv("https://www.dropbox.com/s/ym6i3mww1p7yafd/ex2data1.csv?dl=1")

data$admin <- as.factor(data$admin)

gl <- glm(admin ~ ., data=data, family=binomial())
summary(gl)

# predict
gl.pred <- predict(gl, newdata=data, type="response")
gl.pred.tran <- factor(gl.pred > 0.5, levels=c(F, T), labels=c(0,1))

# performance measure (self)
library(caret)
confusionMatrix(gl.pred.tran, data$admin)

# training & test data
set.seed(123)
train <- sample(nrow(data), 0.7*nrow(data))
data.train <- data[train,]
data.test <- data[-train,]
table(data.train$admin)
table(data.test$admin)

# model generation
gl <- glm(admin ~ ., data=data.train, family=binomial())
summary(gl)


# predict
gl.pred <- predict(gl, newdata=data.test, type="response")
gl.pred.tran <- factor(gl.pred > 0.5, levels=c(F, T), labels=c(0,1))

# performance measure (test)
confusionMatrix(gl.pred.tran, data.test$admin)


# Multiclass classification

# sampling : train and test
set.seed(123)
train <- sample(nrow(iris), 0.7*nrow(iris))
iris.train <- iris[train,]
iris.test <- iris[-train,]
table(iris.train$Species)
table(iris.test$Species)

# model generation
library(nnet)

ml <- multinom(Species ~ ., data=iris.train, family=multinomial())
summary(ml)
# predict
(ml.pred <- predict(ml, newdata = iris.test, type = "class"))

# performance measure (test)
confusionMatrix(ml.pred, iris.test$Species)

## ROC
## ROC

library(ROCR)

data <- read.csv("https://www.dropbox.com/s/ym6i3mww1p7yafd/ex2data1.csv?dl=1")
data$admin <- as.factor(data$admin)

# sampling : train and test
set.seed(123)
train <- sample(nrow(data), 0.7*nrow(data))
data.train <- data[train,]
data.test <- data[-train,]
table(data.train$admin)
table(data.test$admin)

# model generation
gl <- glm(admin ~ ., data=data.train, family=binomial())

# predict
gl.pred <- predict(gl, newdata=data.test, type="response")

# ROC
gl.pred.pr <- prediction(gl.pred, data.test$admin)
prf <- performance(gl.pred.pr, measure = "tpr", x.measure = "fpr") #y축은 tpr(recall), x축은 fpr
plot(prf)

# AUC
auc <- performance(gl.pred.pr, measure = "auc")
auc@y.values[[1]]



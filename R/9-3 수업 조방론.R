data <- read.csv("https://www.dropbox.com/s/n2oq8lv6qlk9xyb/mlr08.csv?dl=1")
view(data)
str(data) 

reg <- lm(X1 ~ ., data=data)
summary(reg)
# 유의하지 않은 애 제거 -> stepwise 실행

(step.reg <- step(reg, direction="both"))
# AIC 중 가장 작은 애 빼기

summary(step.reg)

#----------------- ex 1)

data <- read.csv("https://www.dropbox.com/s/mnvjou5u124citd/mlr09.csv?dl=1")
view(data)
str(data) 

reg <- lm(X1 ~ ., data=data)
summary(reg)
# 유의하지 않은 애 제거 -> stepwise 실행

(step.reg <- step(reg, direction="both"))
# AIC 중 가장 작은 애 빼기

summary(step.reg)
#------------------- ex 2)
# ---
# title: "ch09.regression_analysis"
# author: "Seo"
# date: '2023 04 10'
# ---

#####################################################
# Linear regression

# Simple linear regression
reg <- read.csv("https://www.dropbox.com/s/rmjklxf88q6tboo/regression.csv?dl=1", header=F, col.names=c("id", "키", "몸무게", "허리둘레"))
lm.reg <- lm(키 ~ 몸무게, data=reg)

summary(lm.reg) ; anova(lm.reg)
fitted(lm.reg)


# Multiple linear regression
lm.reg <- lm(키 ~ 몸무게+허리둘레, data=reg)
summary(lm.reg) ; anova(lm.reg)

step.reg <- step(lm.reg, direction="both")
summary(step.reg)


# Dummy
reg.dummy <- read.csv("https://www.dropbox.com/s/uy81kck2vlgv4va/regression_dummy.csv?dl=1", header=F, col.names=c("id","성별","키","몸무게"))
lm.reg.dummy <- lm(키 ~ factor(성별)+몸무게, data=reg.dummy)

summary(lm.reg.dummy)
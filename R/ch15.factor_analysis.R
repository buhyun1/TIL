# ---
# title: "ch15.factor_analysis"
# author: "Seo"
# date: '2023 05 29'
# ---

#####################################################
# Factor analysis

# data & test
banking <- read.csv("https://www.dropbox.com/s/rup12t11bfp3tt1/banking.csv?dl=1", header=F)

# Bartlett’s sphericity test
library(psych, warn.conflicts=F)
cortest.bartlett(cor(banking), n = nrow(banking))

# KMO test
KMO(cor(banking))

# PCA
pca <- prcomp(banking, scale=T)

summary(pca)


# factor loading
cor(banking, pca$x)

# eigen values
loading.df <- as.data.frame(cor(banking, pca$x)^2)
print(apply(loading.df, 2, sum))
print(sqrt(apply(loading.df, 2, sum)))


#Linear regression
banking.y <- read.csv("https://www.dropbox.com/s/q4bv3k3fafoz2t4/banking_y.csv?dl=1", header=F, col.names = c("y"))

banking.new <- cbind(pca$x, banking.y)

lm.reg <- lm(y ~ PC1+PC2+PC3+PC4+PC5+PC6+PC7, data=banking.new)

summary(lm.reg)

step.reg <- step(lm.reg, direction = "both")
summary(step.reg)


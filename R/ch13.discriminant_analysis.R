# ---
# title: "ch13.discriminant_analysis"
# author: "Seo"
# date: '2023 05 15'
# ---

#####################################################
# Discriminant analysis

library(MASS, warn.conflicts=F)

discriminant <- read.csv("https://www.dropbox.com/s/orbhm691f146awj/discriminant.csv?dl=1", header=F, col.name=c("유권자", "성실성", "개혁성", "후보자"))
discriminant$후보자 <- as.factor(discriminant$후보자)
(ld <- lda(후보자~성실성+개혁성, data=discriminant))

# prediction and confusionMatrix
(ld.predict <- predict(ld))

library(caret, warn.conflicts=F)
confusionMatrix(ld.predict$class, discriminant$후보자)

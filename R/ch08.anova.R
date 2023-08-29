# ---
# title: "ch08.anova"
# author: "Seo"
# date: '2023 04 03'
# ---

#####################################################
# Anova

# One way anova
restaurant <- read.csv("https://www.dropbox.com/s/fbyroeo28wgn1pz/restaurant.csv?dl=1", header=F, col.names=c("id", "name", "score"))
lm.restaurant <- lm(score ~ factor(name), data=restaurant)
anova(lm.restaurant)

aov.restaurant <- aov(score ~ factor(name), data=restaurant)
summary(aov.restaurant)

# post-hoc
library(agricolae, warn.conflicts=F)
l.test <- LSD.test(aov.restaurant, "factor(name)")
l.test
(scheffe.test(aov.restaurant, "factor(name)"))
(duncan.test(aov.restaurant, "factor(name)"))

# Two way anova
store.main <- read.csv("https://www.dropbox.com/s/oo0rrzhvmk3rmvm/store_main.csv?dl=1", header=F, col.names=c("size","loc","sales"))
lm.store.main <- lm(sales ~ factor(size)+factor(loc), data=store.main)
anova(lm.store.main)

aov.store.main <- aov(sales ~ factor(size)+factor(loc), data=store.main)
summary(aov.store.main)

# post-hoc
(LSD.test(aov.store.main, "factor(size)"))
(scheffe.test(aov.store.main, "factor(size)"))
(duncan.test(aov.store.main, "factor(size)"))

# Two way anova - interaction effect
store.inter <- read.csv("https://www.dropbox.com/s/wnnrtgyt6oqhoti/store_interaction.csv?dl=1", header=F, col.names=c("size","loc","sales"))
store.inter
lm.store.inter <- lm(sales ~ factor(size)*factor(loc), data=store.inter)
anova(lm.store.inter)

aov.store.inter <- aov(sales ~ factor(size)*factor(loc), data=store.inter)
summary(aov.store.inter)

# post-hoc
(LSD.test(aov.store.inter, "factor(size)"))
(scheffe.test(aov.store.inter, "factor(size)"))
(duncan.test(aov.store.inter, "factor(size)"))

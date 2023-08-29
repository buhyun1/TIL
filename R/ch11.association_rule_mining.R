# ---
# title: "ch11.association_rule_mining"
# author: "Seo"
# date: '2023 05 03'
# ---

#####################################################
# Association Rule Mining - Groceries dataset

# apriori package
library(arules, warn.conflicts=F)
library(arulesViz, warn.conflicts=F)

# data loading - Groceries
data(Groceries)
summary(Groceries)

# data visualizing}
inspect(Groceries[1:100])
crossTable(Groceries)[1:10, 1:10]

itemFrequencyPlot(Groceries, topN=10, type="absolute")
image(sample(Groceries, 300))

# frequent itemsets
gr.freqitem <- apriori(Groceries, parameter = list(target = "frequent itemsets", support = 0.005, maxlen = 10, minlen = 2))
summary(gr.freqitem)

# frequent itemset inspection
inspect(sort(gr.freqitem, by = "support")[1:10])

# rules generation
gr.rules <- apriori(Groceries, parameter = list(support = 0.005, confidence = 0.1, maxlen = 10, minlen = 2))
summary(gr.rules)

# rules inspection
inspect(sort(gr.rules, by = "confidence")[1:10])
inspect(sort(gr.rules, by = "lift")[1:10])

## values test
crossTable(Groceries)["ham", "white bread"]/crossTable(Groceries)["ham", "ham"] # conf
crossTable(Groceries)["ham", "white bread"]/(crossTable(Groceries)["ham", "ham"]*crossTable(Groceries)["white bread", "white bread"]) * nrow(Groceries) # lift

# rules visualizing
plot(sort(gr.rules, by = "confidence")[1:10], method="graph", measure="confidence", shading="lift")

# rules reduction - rhs limitating
wholemilk.gr.rules <- 
  apriori(Groceries, parameter = list(support = 0.005, confidence = 0.1, maxlen = 10, minlen = 2), 
          appearance = list(rhs="whole milk"))
summary(wholemilk.gr.rules)

# rules inspection
inspect(sort(wholemilk.gr.rules, by = "confidence")[1:10])
inspect(sort(wholemilk.gr.rules, by = "lift")[1:10])


#####################################################
# Association Rule Mining - Titanic dataset

# data loading
titanic_train <- read.csv("https://www.dropbox.com/s/90j42diqvp9mlgb/train.csv?dl=1", stringsAsFactors = F)

# data str
str(titanic_train)

# factor type def
titanic_train$Survived <- as.factor(titanic_train$Survived)
titanic_train$Pclass <- as.factor(titanic_train$Pclass)
titanic_train$Sex <- as.factor(titanic_train$Sex)
titanic_train$Embarked <- as.factor(titanic_train$Embarked)

str(titanic_train)

# data preprocessing
# data extraction
titanic_train <- subset(titanic_train, select = c(Survived, Pclass, Sex, Embarked))

# switching blank to NA 
titanic_train$Pclass <- replace(titanic_train$Pclass, titanic_train$Pclass=="", NA)
titanic_train$Sex <- replace(titanic_train$Sex, titanic_train$Sex=="", NA)
titanic_train$Embarked <- replace(titanic_train$Embarked, titanic_train$Embarked=="", NA)

colSums(is.na(titanic_train))

library(mice)
(miceresult <- mice(titanic_train, seed=1234, m=5))
titanic_train <- complete(miceresult, 1)
colSums(is.na(titanic_train))

# rules generation
tt.rules <- apriori(titanic_train, parameter = list(support = 0.01, confidence = 0.1, maxlen = 10, minlen = 2),
                    appearance = list(default="lhs", rhs=c("Survived=0", "Survived=1")))
summary(tt.rules)

# rules inspection
inspect(sort(tt.rules, by = "confidence"))
inspect(sort(tt.rules, by = "lift"))

# rules visualizing
plot(sort(tt.rules, by = "lift")[1:10], method="graph", measure="lift", shading="confidence")

# rules generation
tt.rules <- apriori(titanic_train, parameter = list(support = 0.01, confidence = 0.8, maxlen = 10, minlen = 2),
                    appearance = list(default="lhs", rhs=c("Survived=0", "Survived=1")))
summary(tt.rules)

# rule inspection
tt.rules.sorted <- sort(tt.rules, by="lift")
inspect(tt.rules.sorted)

# rules pruning
subsets <- which(colSums(is.subset(tt.rules.sorted, tt.rules.sorted)) > 1)
tt.rules.pruned <- tt.rules.sorted[-subsets]
summary(tt.rules.pruned)
inspect(tt.rules.pruned)


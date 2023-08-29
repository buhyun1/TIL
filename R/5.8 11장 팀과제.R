# library 호출
library(arules)
library(arulesViz)

# data 불러오기
data <- read.csv("https://www.dropbox.com/s/fxv5iapu3qfio5z/1.wearables_2011-2013_original.csv?dl=1",stringsAsFactors=F)
data


# transaction으로 변환
data.list <- split(data$class, data$patent)
data.tr <- as(data.list, "transactions")
summary(data.tr)

# data type 확인
str(data)

# int, chr을 factor로 변환
data$patent <-as.factor(data$patent)
data$class <- as.factor(data$class)


# freqitem
data.freqitem <- apriori(data.tr, parameter=list(target="frequent itemsets",support=0.05, minlen=1))
summary(data.freqitem)

# set of 42 itemsets
## 총 42개의 frequent itemsets 생성됨


# element (itemset/transaction) length distribution:sizes
# 1  2  3  4 
# 15 17  8  2 


## 모든 frequent itemsets 출력
inspect(data.freqitem)


# rule 생성
data.rules <- apriori(data.tr, parameter=list(support=0.05, confidence=0.05, minlen=2))
summary(data.rules)

# set of 66 rules
## 총 66개의 rules 생성됨

 
# rule length distribution (lhs + rhs):sizes
# 2  3  4 
# 34 24  8 

## Confidence 기준 상위 10개 및 lift 기준 상위 10개의 rule을 출력
inspect(sort(data.rules, by= "confidence")[1:10])
inspect(sort(data.rules, by= "lift")[1:10])


#  inspect(sort(data.rules, by= "confidence")[1:10])
# lhs                   rhs    support    confidence coverage   lift     count
# [1]  {G10K}             => {A61F} 0.18253968 1.0000000  0.18253968 2.930233 46   
# [2]  {G10K, H04R}       => {H03B} 0.05555556 1.0000000  0.05555556 5.040000 14   
# [3]  {G10K, H04R}       => {A61F} 0.05555556 1.0000000  0.05555556 2.930233 14   
# [4]  {G10K, H03B}       => {A61F} 0.17063492 1.0000000  0.17063492 2.930233 43   
# [5]  {G10K, H04B}       => {A61F} 0.06349206 1.0000000  0.06349206 2.930233 16   
# [6]  {G10K, H03B, H04R} => {A61F} 0.05555556 1.0000000  0.05555556 2.930233 14   
# [7]  {A61F, G10K, H04R} => {H03B} 0.05555556 1.0000000  0.05555556 5.040000 14   
# [8]  {G10K, H03B, H04B} => {A61F} 0.05555556 1.0000000  0.05555556 2.930233 14   
# [9]  {H03B}             => {A61F} 0.18650794 0.9400000  0.19841270 2.754419 47   
# [10] {G10K}             => {H03B} 0.17063492 0.9347826  0.18253968 4.711304 43   


# > inspect(sort(data.rules, by= "lift")[1:10])
# lhs                   rhs    support    confidence coverage   lift     count
# [1]  {A61F, H03B, H04R} => {G10K} 0.05555556 0.9333333  0.05952381 5.113043 14   
# [2]  {G10K, H04R}       => {H03B} 0.05555556 1.0000000  0.05555556 5.040000 14   
# [3]  {A61F, G10K, H04R} => {H03B} 0.05555556 1.0000000  0.05555556 5.040000 14   
# [4]  {A61F, H03B}       => {G10K} 0.17063492 0.9148936  0.18650794 5.012026 43   
# [5]  {A61F, H03B, H04B} => {G10K} 0.05555556 0.8750000  0.06349206 4.793478 14   
# [6]  {G10K}             => {H03B} 0.17063492 0.9347826  0.18253968 4.711304 43   
# [7]  {H03B}             => {G10K} 0.17063492 0.8600000  0.19841270 4.711304 43   
# [8]  {A61F, G10K}       => {H03B} 0.17063492 0.9347826  0.18253968 4.711304 43   
# [9]  {H03B, H04R}       => {G10K} 0.05555556 0.8235294  0.06746032 4.511509 14   
# [10] {G10K, H04B}       => {H03B} 0.05555556 0.8750000  0.06349206 4.410000 14   
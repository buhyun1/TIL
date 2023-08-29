# 데이터 불러오기
data <- read.csv("C:/Users/hwang/OneDrive/바탕 화면/transaction_data_v2.csv", header = T)

# 데이터 확인
head(data)
str(data)

library(arules, warn.conflicts=F)
library(arulesViz, warn.conflicts=F)

#factor 타입 변환
data$game <- as.factor(data$game)

write.csv(data, "data.csv", row.names = FALSE)

# transactions 생성
tran <- read.transactions("data.csv", format = "single", cols = c(3, 4), sep = ",", rm.duplicates = FALSE, header = TRUE)
inspect(tran)

# item 빈도수 확인
itemFrequencyPlot(tran, topN=5, type="absolute", xlab="game", ylab="빈도수", main="Absolute Item Frequency Plot")

# apriori algorithm
rules <- apriori(tran, parameter = list(support = 0.2, confidence = 0.5, maxlen = 10, minlen = 2))

#지지도 0.2 이유 -> 0.05 했을때 상위 50개가 confidence가 1로 다 동일하고, 롤과 서든으로만 연관성이 있는 결과가 많이 나왔기에 0.2로 설정
#confidence 0.5 설정 이유 -> 상위 50개 뽑았을때  0.6 이상이었기에 큰 의미 없다고 판단해 0.5로 설정했다. 

# rules inspection
inspect(sort(rules, by = "confidence")[1:50]) # <- 이거보고 결과 해석하면 됨******* 
# ex) 상위 1,2번째보고 메이플, 배그하면 서든 해 본 경험이 있거나 서든 할 확률이 높다 lift가 1 이상으로 연관성 높고,
# 1~ 50개 규칙들 보면 1등 서든, 2등 롤 순서로 결과를 도출해주는데
# 1둥 서든이 가장 많아 서든만 따로 뽑아 분석해보면 아래 서든만 추출하는 sudden.gr.rules 사용
inspect(sort(rules, by = "lift")[1:50])

# rules visualizing
plot(sort(rules, by = "confidence")[1:30], method="graph", measure="confidence", shading="lift")
plot(sort(rules, by = "lift")[1:30], method="graph", measure="confidence", shading="lift")


#하나의 게임으로만 분석하고 싶을때 사용 ex) 롤에 대한 결과만 보고 싶다
#롤 추출
lol.gr.rules <- apriori(tran, parameter = list(support = 0.2, confidence = 0.5, maxlen = 10, minlen = 2), 
                        appearance = list(rhs="롤"))
summary(lol.gr.rules)

maple.gr.rules <- apriori(tran, parameter = list(support = 0.2, confidence = 0.5, maxlen = 10, minlen = 2), 
                        appearance = list(rhs="메이플스토리"))
summary(maple.gr.rules)

bag.gr.rules <- apriori(tran, parameter = list(support = 0.2, confidence = 0.5, maxlen = 10, minlen = 2), 
                        appearance = list(rhs="배그"))
summary(bag.gr.rules)

fifa.gr.rules <- apriori(tran, parameter = list(support = 0.2, confidence = 0.5, maxlen = 10, minlen = 2), 
                        appearance = list(rhs="피파온라인4"))
summary(fifa.gr.rules)

over.gr.rules <- apriori(tran, parameter = list(support = 0.2, confidence = 0.5, maxlen = 10, minlen = 2), 
                        appearance = list(rhs="오버워치2"))
summary(over.gr.rules)

#서든 추출
sudden.gr.rules <- apriori(tran, parameter = list(support = 0.2, confidence = 0.5, maxlen = 10, minlen = 2), 
                           appearance = list(rhs="서든어택")) 
summary(sudden.gr.rules)


# rules inspection
inspect(sort(lol.gr.rules, by = "confidence")[1:10])
inspect(sort(maple.gr.rules, by = "confidence")[1:10])
inspect(sort(bag.gr.rules, by = "confidence")[1:10])
inspect(sort(fifa.gr.rules, by = "confidence")[1:5])
inspect(sort(over.gr.rules, by = "confidence")[1:5])
inspect(sort(sudden.gr.rules, by = "confidence")[1:10])# <- 이거보고 해석 10개중 많이 나온 장르로 해석해도 되고
#추가적인 게임 장르에 대한 분석으로 적어줘도 될듯? 장르에 따른 마케팅이나... 이런것들..?



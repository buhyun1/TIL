test <- read.csv("./카카오톡 받은 파일/result.csv", header= T)
test # csv 파일 불러오기 
var.test(test[test$mbti=="P",2],test[test$mbti=="J",2]) #등분산인지 먼저 검정
t.test(test[test$mbti=="P",2],test[test$mbti=="J",2], var.equal = T) #평균 가설 검정

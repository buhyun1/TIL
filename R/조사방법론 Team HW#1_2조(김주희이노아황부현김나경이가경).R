raw <- read.csv("https://www.dropbox.com/s/v0jojiktigfc0pg/hw1_data_1.csv?dl=1", header=T)
tab <- xtabs(~gender+pre_pro, data=raw)
tab

chisq.test(tab)

# 가설
# 귀무가설(H0): 신상품에 대한 선호도와 성별은 서로 독립이다.
# 연구가설(H1): 신상품에 대한 선호도와 성별은 서로 독립이 아니다.

# 결과
# p-value = 0.0001907이므로 유의수준 1%라고 가정하면, p-value가 유의수준 1%보다 낮으므로 귀무가설(H0)을 기각할 수 있다.
# 이는 연구가설(H1)을 채택하는 것이므로 신상품에 대한 선호도와 성별은 서로 독립이 아니라고 할 수 있다.


# 결과값
# Pearson's Chi-squared test
# data:  tab
# X-squared = 17.13, df = 2, p-value = 0.0001907
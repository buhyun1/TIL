# ---
# title: "ch07.association_analysis"
# author: "Seo"
# date: '2023 03 27'
# ---

#####################################################
# Correlation

# pearson correlation coefficient
# 키와 몸무게
height <- c(176, 172, 182, 160, 163, 165, 168, 163, 182, 148, 170, 166, 172, 160, 163, 170, 182, 174, 164, 160, 178, 175, 173, 188, 180, 170, 168, 166, 170, 182)
weight <- c(72, 72, 70, 43, 48, 54, 51, 52, 73, 45, 60, 62, 64, 47, 51, 74, 88, 64, 56, 56, 62, 70, 73, 82, 75, 65, 56, 65, 54, 77)

plot(height, weight)
cor.test(height, weight)

# partial correlation coefficient
# 마케팅과 시장성과
partial <- read.csv("https://www.dropbox.com/s/ang76vfzkn1na3d/partial.csv?dl=1", header=T, fileEncoding = "euc-kr")
cor.test(partial$마케팅, partial$성과)

rxy <- cor(partial$마케팅, partial$성과)
rxz <- cor(partial$마케팅, partial$시장성장)
ryz <- cor(partial$성과, partial$시장성장)
(rxy.z <- (rxy-rxz*ryz)/(sqrt(1-rxz^2)*sqrt(1-ryz^2)))

xres <- residuals(lm(partial$마케팅~partial$시장성장))
yres <- residuals(lm(partial$성과~partial$시장성장))
cor.test(xres, yres)

# rank correlation coefficient
rank_cor <- read.csv("https://www.dropbox.com/s/up48077y6mhk286/rank_cor.csv?dl=1", header=T, fileEncoding = "euc-kr")

plot(rank_cor$선호도, rank_cor$도덕성)

cor.test(rank_cor$선호도, rank_cor$도덕성, method="kendall")
cor.test(rank_cor$선호도, rank_cor$도덕성, method="spearman")



# chi-squared test
# 직무 만족도
raw <- read.csv("https://www.dropbox.com/s/xg6z6a7t3ktr646/cross_table_1.csv?dl=1", header=F, col.names=c("학력","직무만족","연령분류","보수","대인관계"))
tab <- xtabs(~학력+직무만족, data=raw)
sum.i <- apply(tab, 1, sum)
sum.j <- apply(tab, 2, sum)
eij <- outer(sum.i, sum.j)/sum(tab)

chisq <- sum((tab-eij)^2/eij)
ni <- length(sum.i); nj=length(sum.j)
pval <- pchisq(chisq, df=(ni-1)*(nj-1), lower.tail=F)

chisq ; pval

chisq.test(tab)

# 여론조사
정당 <- c(48,29,19,35,59,19,22,15,18)
tab <- matrix(정당,3,3,byrow=T)
colnames(tab) <- c("남편A","남편B","남편C")
rownames(tab) <- c("아내A","아내B","아내C")
tab
chisq.test(tab)
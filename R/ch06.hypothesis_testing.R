# ---
# title: "ch06.hypothesis_testing"
# author: "Seo"
# date: '2023 03 20'
# ---

#####################################################
# Hypothesis testing

# 철판경도
n <- 100; xbar <- 57.5; sigma <- sqrt(2.04); mu <- 58 
z0 <- (xbar-mu)/(sigma/sqrt(n))
pnorm(z0)                   #pnorm(z0,0,1)과 동일


# 슈퍼 건전지 수명
n <- 100; xbar <- 1420; sigma <- 200; mu <- 1500
t0 <- (xbar-mu)/(sigma/sqrt(n))
pt(t0, df=n-1)

# 통조림 무게1
n <- 100; xbar <- 294; sigma <- sqrt(400); mu <- 300
t0 <- (xbar-mu)/(sigma/sqrt(n))
pt(t0, df=n-1) + pt(-t0, df=n-1, lower.tail=F)

# 통조림 무게2
2*pt(t0, df=n-1)

# 통조림 무게3
x <- c(319, 242, 291, 276, 348, 299, 293, 304, 296, 286, 301, 324, 279, 323, 296, 271, 283, 302, 321, 322, 307, 300, 261, 274, 311, 320, 290, 269, 326, 287, 292, 294, 314, 303, 290, 292, 308, 308, 282, 285, 301, 327, 323, 320, 301, 288, 293, 310, 301, 303, 291, 304, 244, 305, 285, 316, 278, 290, 283, 261, 320, 293, 307, 307, 260, 318, 293, 309, 308, 306, 283, 300, 263, 311, 286, 272, 313, 312, 286, 282, 309, 284, 321, 251, 319, 311, 271, 295, 280, 293, 320, 256, 318, 281, 271, 283, 277, 289, 266, 309) 
t.test(x, mu=300)

# 통조림 무게4
t.test(x, mu=300, alt="less")

# 통조림 무게5
x <- read.csv("https://www.dropbox.com/s/gvvbp2n45r6wqyh/tuna_can.csv?dl=1", header=F, col.names = c("id", "weight"))
t.test(x$weight, mu=300)

# 통조림 무게6
t.test(x$weight, mu=300, alt="less")

# 된장 1
n <- 20; xdbar <- 2.55; sigmad <- 3.13
t0 <- xdbar/(sigmad/sqrt(n))
pt(t0, df=n-1, lower.tail=F)

# 된장2
before <- c(82, 54, 74, 75, 71, 76, 70, 62, 77, 75, 72, 83, 78, 74, 68, 76, 75, 75, 75, 71)
after <- c(75, 50, 74, 71, 69, 73, 68, 62, 68, 72, 70, 77, 71, 74, 67, 73, 77, 71, 76, 74)
t.test(before, after, paired=T, alt="greater")

# 된장3
diet <- read.csv("https://www.dropbox.com/s/anylurmkgmhqiuk/diet.csv?dl=1", header=F, col.names=c("id", "before", "after"))
t.test(diet$before, diet$after, paired=T, alt="greater")

# 타이어수명1
tire <- read.csv("https://www.dropbox.com/s/d40dcuescate4nq/tire.csv?dl=1", header=F, col.names=c("id", "span"))
var.test(tire[tire$id==1,2], tire[tire$id==2,2])

# 타이어수명2
t.test(tire[tire$id==1,2], tire[tire$id==2,2], var.equal=T)

# 화랑담배의 니코틴 함량
x <- c(11.5, 9.9, 12.3, 11.2, 13.8, 12.3, 11.9, 12.3, 12.9, 15.6, 13.2, 11.9, 12.8, 13.8, 11.5, 12.4, 12.3, 12.3, 10.5, 12.3, 12.7, 12.8, 13.2, 14.9, 9.8, 12.3, 14.2, 11.9, 14.2, 13.6)
n <- length(x); s <- sd(x); sigma0 <- sqrt(1.2)
chisq <- (n-1)*s^2/sigma0^2

chisq ; 2*(1-pchisq(chisq, df=n-1))

# 성적
score <- read.csv("https://www.dropbox.com/s/o8m92fqjhbudrb7/score.csv?dl=1", header=F, col.names=c("math", "eng"))
var.test(score$math, score$eng)


# 데이터 불러오기
library(MASS)
str(birthwt)

## 1) bwt를 종속변수로 하고, 나머지는 모두 독립변수로 활용
data <- birthwt
lm.data <- lm(bwt ~ age+lwt+factor(race)+factor(smoke)+ptl+factor(ht)+factor(ui), data=data)
summary(lm.data)

## 결과
# Call:
#  lm(formula = bwt ~ age + lwt + factor(race) + factor(smoke) + ptl + factor(ht) + factor(ui), data = data)

# Residuals:
#   Min      1Q  Median      3Q     Max 
# -1838.7  -454.5    57.6   465.1  1711.0 

# Coefficients:
#                 Estimate Std. Error t value Pr(>|t|)    
#  (Intercept)    2934.132    311.450   9.421  < 2e-16 ***
#  age              -4.093      9.440  -0.434 0.665091    
#  lwt               4.301      1.722   2.497 0.013416 *  
#  factor(race)2  -488.196    149.604  -3.263 0.001318 ** 
#  factor(race)3  -353.334    114.319  -3.091 0.002314 ** 
#  factor(smoke)1 -351.314    106.180  -3.309 0.001132 ** 
#  ptl             -47.423    101.663  -0.466 0.641443    
#  factor(ht)1    -586.836    200.841  -2.922 0.003925 ** 
#  factor(ui)1    -514.937    138.483  -3.718 0.000268 ***
#  ---
#  Signif. codes:  0 ‘***’ 0.001 ‘**’ 0.01 ‘*’ 0.05 ‘.’ 0.1 ‘ ’ 1

# Residual standard error: 648.7 on 180 degrees of freedom
# Multiple R-squared:  0.2424,	Adjusted R-squared:  0.2087 
# F-statistic: 7.197 on 8 and 180 DF,  p-value: 2.908e-08

## 해석
# 위의 결과를 통해 아래와 같은 결과를 얻을 수 있다.
# 회귀식: bwt = 2934.132 - 4.093*age + 4.301*lwt - 488.196*race2 - 353.334*race3 - 351.314*smoke1 - 47.423*ptl - 586.836*ht1 - 514.937*ui1
# Residual standard error = 648.7, R-squared = 0.2424, F값 = 7.197, p-value = 2.908e-08



## 2) bwt를 종속변수로 하고, 나머지는 중 단계선택방식(stepwise selection)에 따라 독립변수를 선택적으로 활용
lm.data <- lm(bwt ~ age+lwt+factor(race)+factor(smoke)+ptl+factor(ht)+factor(ui), data=data)
(step.lm.data <- step(lm.data, direction="both"))

summary(step.lm.data)

## 결과
# Start:  AIC=2456.3
# bwt ~ age + lwt + factor(race) + factor(smoke) + ptl + factor(ht) + factor(ui)

#                 Df Sum of Sq     RSS    AIC
# - age            1     79115 75820139 2454.5
# - ptl            1     91560 75832585 2454.5
# <none>                       75741025 2456.3
# - lwt            1   2623988 78365013 2460.7
# - factor(ht)     1   3592430 79333455 2463.1
# - factor(smoke)  1   4606425 80347449 2465.5
# - factor(race)   2   6552496 82293521 2468.0
# - factor(ui)     1   5817995 81559020 2468.3

# Step:  AIC=2454.5
# bwt ~ lwt + factor(race) + factor(smoke) + ptl + factor(ht) + factor(ui)

#                 Df Sum of Sq     RSS    AIC
# - ptl            1    117366 75937505 2452.8
# <none>                       75820139 2454.5
# + age            1     79115 75741025 2456.3
# - lwt            1   2545892 78366031 2458.7
# - factor(ht)     1   3546591 79366731 2461.1
# - factor(smoke)  1   4530009 80350149 2463.5
# - factor(race)   2   6571668 82391807 2466.2
# - factor(ui)     1   5751122 81571261 2466.3

# Step:  AIC=2452.79
# bwt ~ lwt + factor(race) + factor(smoke) + factor(ht) + factor(ui)

#                 Df Sum of Sq     RSS    AIC
# <none>                       75937505 2452.8
# + ptl            1    117366 75820139 2454.5
# + age            1    104920 75832585 2454.5
# - lwt            1   2674229 78611734 2457.3
# - factor(ht)     1   3584838 79522343 2459.5
# - factor(smoke)  1   4950633 80888138 2462.7
# - factor(race)   2   6630123 82567628 2464.6
# - factor(ui)     1   6353218 82290723 2466.0

# Call:
#  lm(formula = bwt ~ lwt + factor(race) + factor(smoke) + factor(ht) + factor(ui), data = data)

# Coefficients:
#  (Intercept)             lwt   factor(race)2   factor(race)3  factor(smoke)1     factor(ht)1     factor(ui)1  
#  2837.264           4.242        -475.058        -348.150        -356.321        -585.193        -525.524  




# Call:
#  lm(formula = bwt ~ lwt + factor(race) + factor(smoke) + factor(ht) + factor(ui), data = data)

# Residuals:
#   Min       1Q   Median       3Q      Max 
# -1842.14  -433.19    67.09   459.21  1631.03 

# Coefficients:
#                 Estimate Std. Error t value Pr(>|t|)    
#  (Intercept)    2837.264    243.676  11.644  < 2e-16 ***
#  lwt               4.242      1.675   2.532 0.012198 *  
#  factor(race)2  -475.058    145.603  -3.263 0.001318 ** 
#  factor(race)3  -348.150    112.361  -3.099 0.002254 ** 
#  factor(smoke)1 -356.321    103.444  -3.445 0.000710 ***
#  factor(ht)1    -585.193    199.644  -2.931 0.003810 ** 
#  factor(ui)1    -525.524    134.675  -3.902 0.000134 ***
#  ---
#  Signif. codes:  0 ‘***’ 0.001 ‘**’ 0.01 ‘*’ 0.05 ‘.’ 0.1 ‘ ’ 1

# Residual standard error: 645.9 on 182 degrees of freedom
# Multiple R-squared:  0.2404,	Adjusted R-squared:  0.2154 
# F-statistic:   9.6 on 6 and 182 DF,  p-value: 3.601e-09
 

## 해석
# 위의 문제는 stepwise selection을 이용해서 AIC를 비교, 제거하여 ptl, age 총 2개의 독립변수를 제거했다.
# 회귀식: bwt = 2837.264 + 4.242*lwt - 475.058*race2 - 348.150*race3 - 356.321*smoke1 - 585.193*ht1 - 525.524*ui1
# Residual standard error = 645.9, R-squared = 0.2404, F값 = 9.6, p-value = 3.601e-09 

# 첫번째 모델과 두번째 모델을 비교한 결과, 
# 두 모델 전부 R-squared 값이 비교적 낮은 값을 보이지만, 첫번째 모델이 값이 더 높으므로 그나마 좋은 모델이라고 할 수 있다.

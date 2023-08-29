# ---
# title: "ch10.cluster_analysis"
# author: "Seo"
# date: '2023 04 17'
# ---

#####################################################
# Hierarchical

cluster <- read.csv("https://www.dropbox.com/s/9gdabp5fvuad9vv/cluster.csv?dl=1", header=F, col.names=c("실내시설", "메뉴", "가격", "서비스", "편리성", "인지도", "위생", "숙박", "이용횟수", "레스토랑", "지불비용", "인원", "동반", "형태", "위치", "만족도", "재구매", "성별", "연령", "직업", "최종학력", "소득"))

cluster.scaled <- scale(cluster[1:8])
d <- dist(cluster.scaled)  		
hc.ward <- hclust(d, method="ward.D2")

plot(hc.ward, hang=-1)

# optimal number of clusters
library(NbClust, warn.conflicts=F)
nc <- NbClust(cluster.scaled, distance="euclidean", min.nc=3, max.nc=15, method="ward.D2")

# cutree
clusters <- cutree(hc.ward, k=3)
clusters; table(clusters)

# dendrogram
plot(hc.ward, hang=-1)
rect.hclust(hc.ward, k=3)

# mean value of each cluster
aggregate(cluster, by=list(cluster=clusters), mean)
aggregate(cluster.scaled, by=list(cluster=clusters), mean)


#####################################################
# K-means

set.seed(123)		# 실행할 때 마다 동일한 결과가 나올 수 있도록 지정(정수)
km <- kmeans(cluster.scaled, centers=3, nstart=10)	# nstart: iteration 횟수 제약

km$cluster; km$centers; km$size

# visualization
library(cluster, warn.conflicts=F)		# cluster package 설치 필요
clusplot(x=cluster, clus=km$cluster, color=T, shade=T, labels=2, lines=0)

# iris data set
iris2 <- iris[,-5]

# optimal number of clusters
nc <- NbClust(iris2, distance="euclidean", min.nc=3, max.nc=15, method="ward.D2")

# k-means clustering
set.seed(123)
km <- kmeans(iris2, centers=3, nstart=10)	# nstart: iteration 횟수 제약

km$cluster; km$size

# comparison
table(km$cluster, iris$Species)

# plotting
clusplot(x=iris2, clus=km$cluster, color=T, shade=T, labels=2, lines=0)


#####################################################
# K-medoids

set.seed(123)
kme <- pam(iris2, k=3)
kme$clustering; kme$clusinfo

# comparison
table(kme$clustering, iris$Species)

# plotting
clusplot(x=iris2, clus=kme$cluster, color=T, shade=T, labels=2, lines=0)


#####################################################
# Density-based

library(fpc, warn.conflicts=F)
dbs <- dbscan(iris2, eps = 0.42, MinPts = 5)

dbs$cluster; dbs

# comparison
table(dbs$cluster, iris$Species)

# plotting
clusplot(x=iris2, clus=dbs$cluster, color=T, shade=F, labels=2, lines=0)


#####################################################
# Fuzzy c-means

library(ppclust, warn.conflicts=F)

set.seed(123)
fcmeans <- fcm(iris2, centers=3)
summary(fcmeans)

# comparison
table(fcmeans$cluster, iris$Species)

# plotting
clusplot(x=iris2, clus=fcmeans$cluster, color=T, shade=T, labels=2, lines=0)


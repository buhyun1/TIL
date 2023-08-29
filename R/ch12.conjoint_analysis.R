# ---
# title: "ch12.conjoint_analysis"
# author: "Seo"
# date: '2023 05 15'
# ---

#####################################################
# Conjoint analysis

# tea
library(conjoint, warn.conflicts=F)

data(tea)

print(tlevn)
print(tprof)
head(tprefm)

# prof cor
print(round(cor(tprof), 5)) # cor == 0가 되도록 직교설계가 완성된 프로파일들들

# individual preferences}
caModel(y=tprefm[1,], x=tprof)
caUtilities(y=tprefm[1,], x=tprof, z=tlevn)
caPartUtilities(y=tpref, x=tprof, z=tlevn)

# total preferences - utility}
Conjoint(y=tpref, x=tprof, z=tlevn)
caImportance(y=tpref, x=tprof)


# ice cream
experiment <- expand.grid(
  flavor=c("chocolate","vanilla","strawberry"),
  price=c("$1.50","$2.00","$2.50"),
  container=c("cone","cup"),
  topping=c("yes","no"))

# Determining fractional, orthogonal factorial design with variable names and their levels for the needs of questionnaire construction

factdesign <- caFactorialDesign(data=experiment, type="orthogonal")
print(factdesign)

# encoding
# Encoding variable levels of the fractional design
prof <- caEncodedDesign(design=factdesign)
print(prof)

# verification - cov & cor
# Verification (using covariance and correlation matrix) of the fractional design quality
print(round(cov(prof),5))
print(round(cor(prof),5))

# data
# Loading from external files: data on empirical preferences, research design, variable names and their levels
pref <- read.csv2("https://www.dropbox.com/s/eay6bgqixxdjfsm/ice_preferences.csv?dl=1")
profiles <- read.csv2("https://www.dropbox.com/s/f8mff87s2e2kg5i/ice_profiles.csv?dl=1")
levelnames <- read.csv2("https://www.dropbox.com/s/56tgyivbqvt3d43/ice_levels.csv?dl=1")

print(pref)
print(profiles)
print(levelnames)

# rank(1,2,...) -> rating(9,8,...)
# Change of data format about preferences from rank ordering (so-called ranking) into importance assessments (so-called rating)
preferences <- caRankToScore(y.rank=pref)
print(preferences)

# individual preferences
caModel(y=preferences[1,], x=profiles)
caPartUtilities(y=preferences, x=profiles, z=levelnames)

# total preferences - utility
Conjoint(y=preferences, x=profiles, z=levelnames)
caImportance(y=preferences, x=profiles)



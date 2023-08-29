# a=[1,2,4,3,5]
# aa=a.sort()
# # aa=sorted(aa)
# print(a)

# import string
# input_string = '!hi. wh?at is the weat[h]er lik?e. !@##$%%^^&*)_+{}|?1111"'
# for character in string.punctuation:
#     input_string = input_string.replace(character, '')
# for character in string.ascii_letters:
#     input_string = input_string.replace(character, '')
# print(input_string)

# if __name__ == "__main__" :
#   ex = '!hi. wh?at is the weat[h]er lik?e. !@##$%%^^&*)_+{}|?"111'
#   result = solution(ex)
#   print(result)

# underscore_str = 'FDS'
# input = underscore_str.lower()
# print(input)

if '_' in input :
  `input = "__EXAMPLE__NAME____EXAMPLE__NAME__"
  input = input.strip('_')
  input = input.lower()
  input = input.split('_')
  input = ' '.join(input).split()

  cnt = 1
  input1 = input
  for i in range(1, len(input)) : 
    # print(len(input))
    input1 = input[cnt][0].upper()
    input[cnt] = input[cnt][1:] 
    input[cnt] = input1 + input[cnt] 
    cnt += 1
    
  input = ''.join(input)


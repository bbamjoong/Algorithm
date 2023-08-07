words = []
length = []
for i in range(5):
  word = input()
  words.append(word)
  length.append(len(word))

rst = ''
for i in range(max(length)):
  for j in range(5):
    if i < length[j]:
      rst += words[j][i]
print(rst)
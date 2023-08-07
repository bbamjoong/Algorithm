k = int(input())
sign_of_inequality = input()
insert_idx = 0
insert_num = 8
max = [9]
prev = sign_of_inequality[0]
for i in range(k):
  curr = sign_of_inequality[2*i]
  if(prev != curr):
    insert_idx = len(max)-1
  prev = curr
  if(curr == '<'):
    max.insert(insert_idx, insert_num)
  elif(curr == '>'):
    max.append(insert_num)
  insert_num -= 1
max = "".join(list(map(str, max)))
print(max)

insert_idx = 0
insert_num = 1
min = [0]
prev = sign_of_inequality[0]
for i in range(k):
  curr = sign_of_inequality[2*i]
  if(prev != curr):
    insert_idx = len(min)-1
  prev = curr
  if(curr == '<'):
    min.append(insert_num)
  elif(curr == '>'):
    min.insert(insert_idx, insert_num)
  insert_num += 1
min = "".join(list(map(str, min)))
print(min)
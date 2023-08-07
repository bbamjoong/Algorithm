rating = ['A+', 'A0', 'B+', 'B0', 'C+', 'C0', 'D+', 'D0', 'F']
grade = [4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0]

sum_r = 0
sum_grade = 0
for i in range(20):
  a, b, c = input().split()
  b = float(b)
  if c != 'P':
    sum_r += b
    sum_grade += b * grade[rating.index(c)]
print('%.6f' %(sum_grade/sum_r))
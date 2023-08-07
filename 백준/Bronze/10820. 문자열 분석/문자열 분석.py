while True:
    try:
        word = input()
        lower = sum(i.islower() for i in word)
        upper = sum(i.isupper() for i in word)
        num = sum(i.isdigit() for i in word)
        blank = sum(i.isspace() for i in word)
        print(lower,upper,num,blank)

    except:
        break
        
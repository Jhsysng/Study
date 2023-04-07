import sys

sumation = 0
grade_sum=0
for i in range(20):
    course,credit ,grade = sys.stdin.readline().split()

    credit = int(credit)
    sum+=credit
    if grade=="A+":
        sumation+=credit*4.5
    elif grade=="A0":
        sumation+=credit*4.0
    elif grade=="B+":
        sumation+=credit*3.5
    elif grade=="B0":
        sumation+=credit*3.0
    elif grade=="C+":
        sumation+=credit*2.5
    elif grade=="C0":
        sumation+=credit*2.0
    elif grade=="D+":
        sumation+=credit*1.5
    elif grade=="D0":
        sumation+=credit*1.0
    elif grade=="F":
        sumation+=credit*0
    else:
        grade_sum-=credit
    
print(sumation/grade_sum)
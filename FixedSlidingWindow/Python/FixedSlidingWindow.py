
arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

windowSize = 3
windowSum = 0
start = 0

for end in range(len(arr)):

    windowSum += arr[end]

    # window exeeded
    if end >= windowSize:
        windowSum -= arr[start]
        start += 1
    
    print("window sum is ", windowSum)
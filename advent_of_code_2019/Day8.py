from collections import defaultdict

digits = [int(x) for x in open(
    '/Users/mahesh/Documents/Contests/advent_of_code_2019/input/day8').read().strip()]
rows = 6
cols = 25
layers = int(len(digits) / (rows * cols))
image = defaultdict(lambda: defaultdict(int))
for l in range(layers):
    for i in range(rows * cols):
        image[l][i] += digits[l * rows * cols + i]

fewest_zeros = 25 * 6 + 1
fewest_zeros_layer = 0
for l in range(layers):
    zeros = 0
    for i in range(len(image[l])):
        if(image[l][i] == 0):
            zeros += 1
    if zeros < fewest_zeros:
        fewest_zeros = zeros
        fewest_zeros_layer = l

ones = 0
twos = 0
for i in range(len(image[fewest_zeros_layer])):
    if image[fewest_zeros_layer][i] == 1:
        ones += 1
    elif image[fewest_zeros_layer][i] == 2:
        twos += 1

values = ()

print(ones * twos)

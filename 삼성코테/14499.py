n, m, dice_x, dice_y, k = map(int, input().split())
maze = []
dice = [0, 0, 0, 0, 0, 0]
answer = []

for i in range(n):
    row = list(map(int, input().split()))
    maze.append(row)

direction = list(map(int, input().split()))

dir_y = [1, -1, 0, 0]
dir_x = [0, 0, -1, 1]
face = 0


def isOK(a, b, width, height):
    return 0 <= a < height and 0 <= b < width


for i in direction:
    def action(a, b, index, maps, dices):
        if maps[a][b] == 0:
            maps[a][b] = dices[index]
        else:
            dices[index] = maps[a][b]
            maps[a][b] = 0
        return [maps, dices]

    def east(dices):
        tmp = dices[2]
        dices[2] = dices[0]
        dices[0] = dices[1]
        dices[1] = dices[5]
        dices[5] = tmp
        return dices

    def west(dices):
        tmp = dices[5]
        dices[5] = dices[1]
        dices[1] = dices[0]
        dices[0] = dices[2]
        dices[2] = tmp
        return dices

    def north(dices):
        tmp = dices[5]
        dices[5] = dices[4]
        dices[4] = dices[0]
        dices[0] = dices[3]
        dices[3] = tmp
        return dices

    def south(dices):
        tmp = dices[3]
        dices[3] = dices[0]
        dices[0] = dices[4]
        dices[4] = dices[5]
        dices[5] = tmp
        return dices

    if i == 1:
        if isOK(dice_x + dir_x[0], dice_y + dir_y[0], m, n):
            dice_x += dir_x[0]
            dice_y += dir_y[0]
            dice = east(dice)
            result = action(dice_x, dice_y, 0, maze, dice)
            maze = result[0]
            dice = result[1]
            answer.append(dice[5])
    elif i == 2:
        if isOK(dice_x + dir_x[1], dice_y + dir_y[1], m, n):
            dice_x += dir_x[1]
            dice_y += dir_y[1]
            dice = west(dice)
            result = action(dice_x, dice_y, 0, maze, dice)
            maze = result[0]
            dice = result[1]
            answer.append(dice[5])
    elif i == 3:
        if isOK(dice_x + dir_x[2], dice_y + dir_y[2], m, n):
            dice_x += dir_x[2]
            dice_y += dir_y[2]
            dice = north(dice)
            result = action(dice_x, dice_y, 0, maze, dice)
            maze = result[0]
            dice = result[1]
            answer.append(dice[5])
    elif i == 4:
        if isOK(dice_x + dir_x[3], dice_y + dir_y[3], m, n):
            dice_x += dir_x[3]
            dice_y += dir_y[3]
            dice = south(dice)
            result = action(dice_x, dice_y, 0, maze, dice)
            maze = result[0]
            dice = result[1]
            answer.append(dice[5])

for k in answer:
    print(k)

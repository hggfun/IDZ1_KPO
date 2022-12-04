package org.example;

public class Playground {
    private final int[][] Matrix;
    public Playground() {
        Matrix = new int[8][8];
        Matrix[3][3] = Matrix[4][4] = 1;
        Matrix[3][4] = Matrix[4][3] = 2;
    }
    public int getByIndex(int x, int y, int player) {
        if (Matrix[x][y] > 0) {
            return Matrix[x][y];
        } else {
            if (check(player, x, y)) {
                return 0;
            } else {
                return 3;
            }
        }
    }
    public boolean check(int player, int x, int y) {
        if (Matrix[x][y] != 0) {
            return false;
        }
        if (checkRight(player, x, y)) {
            return true;
        }
        if (checkLeft(player, x, y)) {
            return true;
        }
        if (checkUp(player, x, y)) {
            return true;
        }
        if (checkDown(player, x, y)) {
            return true;
        }
        if (checkUpRight(player, x, y)) {
            return true;
        }
        if (checkUpLeft(player, x, y)) {
            return true;
        }
        if (checkDownRight(player, x, y)) {
            return true;
        }
        return checkDownLeft(player, x, y);
    }
    private boolean checkRight(int player, int x, int y) {
        if (y == 7) {
            return false;
        }
        int opponent = 3 - player;
        if (Matrix[x][++y] == opponent) {
            while (y < 7 && Matrix[x][y] == opponent) {
                y++;
            }
            return Matrix[x][y] == player;
        }
        return false;
    }
    private boolean checkLeft(int player, int x, int y) {
        if (y == 0) {
            return false;
        }
        int opponent = 3 - player;
        if (Matrix[x][--y] == opponent) {
            while (y > 0 && Matrix[x][y] == opponent) {
                y--;
            }
            return Matrix[x][y] == player;
        }
        return false;
    }
    private boolean checkDown(int player, int x, int y) {
        if (x == 7) {
            return false;
        }
        int opponent = 3 - player;
        if (Matrix[++x][y] == opponent) {
            while (x < 7 && Matrix[x][y] == opponent) {
                x++;
            }
            return Matrix[x][y] == player;
        }
        return false;
    }
    private boolean checkUp(int player, int x, int y) {
        if (x == 0) {
            return false;
        }
        int opponent = 3 - player;
        if (Matrix[--x][y] == opponent) {
            while (x > 0 && Matrix[x][y] == opponent) {
                x--;
            }
            return Matrix[x][y] == player;
        }
        return false;
    }
    private boolean checkUpRight(int player, int x, int y) {
        if (x == 0 || y == 7) {
            return false;
        }
        int opponent = 3 - player;
        if (Matrix[--x][++y] == opponent) {
            while (x > 0 && y < 7 && Matrix[x][y] == opponent) {
                x--;
                y++;
            }
            return Matrix[x][y] == player;
        }
        return false;
    }
    private boolean checkUpLeft(int player, int x, int y) {
        if (x == 0 || y == 0) {
            return false;
        }
        int opponent = 3 - player;
        if (Matrix[--x][--y] == opponent) {
            while (x > 0 && y > 0 && Matrix[x][y] == opponent) {
                x--;
                y--;
            }
            return Matrix[x][y] == player;
        }
        return false;
    }
    private boolean checkDownRight(int player, int x, int y) {
        if (x == 7 || y == 7) {
            return false;
        }
        int opponent = 3 - player;
        if (Matrix[++x][++y] == opponent) {
            while (x < 7 && y < 7 && Matrix[x][y] == opponent) {
                x++;
                y++;
            }
            return Matrix[x][y] == player;
        }
        return false;
    }
    private boolean checkDownLeft(int player, int x, int y) {
        if (x == 7 || y == 0) {
            return false;
        }
        int opponent = 3 - player;
        if (Matrix[++x][--y] == opponent) {
            while (x < 7 && y > 0 && Matrix[x][y] == opponent) {
                x++;
                y--;
            }
            return Matrix[x][y] == player;
        }
        return false;
    }

    public void change(int player, int x, int y) {
        int opponent = 3 - player;
        changeRight(player, opponent, x, y);
        changeLeft(player, opponent, x, y);
        changeUp(player, opponent, x, y);
        changeDown(player, opponent, x, y);
        changeDownLeft(player, opponent, x, y);
        changeDownRight(player, opponent, x, y);
        changeUpLeft(player, opponent, x, y);
        changeUpRight(player, opponent, x, y);
    }
    private void changeRight(int player, int opponent, int x, int y) {
        if (checkRight(player, x, y)) {
            Matrix[x][y] = player;
            y++;
            while (Matrix[x][y] == opponent) {
                Matrix[x][y] = player;
                y++;
            }
        }
    }
    private void changeLeft(int player, int opponent, int x, int y) {
        if (checkLeft(player, x, y)) {
            Matrix[x][y] = player;
            y--;
            while (Matrix[x][y] == opponent) {
                Matrix[x][y] = player;
                y--;
            }
        }
    }
    private void changeUp(int player, int opponent, int x, int y) {
        if (checkUp(player, x, y)) {
            Matrix[x][y] = player;
            x--;
            while (Matrix[x][y] == opponent) {
                Matrix[x][y] = player;
                x--;
            }
        }
    }
    private void changeDown(int player, int opponent, int x, int y) {
        if (checkDown(player, x, y)) {
            Matrix[x][y] = player;
            x++;
            while (Matrix[x][y] == opponent) {
                Matrix[x][y] = player;
                x++;
            }
        }
    }
    private void changeUpRight(int player, int opponent, int x, int y) {
        if (checkUpRight(player, x, y)) {
            Matrix[x][y] = player;
            x--;
            y++;
            while (Matrix[x][y] == opponent) {
                Matrix[x][y] = player;
                x--;
                y++;
            }
        }
    }
    private void changeUpLeft(int player, int opponent, int x, int y) {
        if (checkUpLeft(player, x, y)) {
            Matrix[x][y] = player;
            x--;
            y--;
            while (Matrix[x][y] == opponent) {
                Matrix[x][y] = player;
                x--;
                y--;
            }
        }
    }
    private void changeDownLeft(int player, int opponent, int x, int y) {
        if (checkDownLeft(player, x, y)) {
            Matrix[x][y] = player;
            x++;
            y--;
            while (Matrix[x][y] == opponent) {
                Matrix[x][y] = player;
                x++;
                y--;
            }
        }
    }
    private void changeDownRight(int player, int opponent, int x, int y) {
        if (checkDownRight(player, x, y)) {
            Matrix[x][y] = player;
            x++;
            y++;
            while (Matrix[x][y] == opponent) {
                Matrix[x][y] = player;
                x++;
                y++;
            }
        }
    }
}

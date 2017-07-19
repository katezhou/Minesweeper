package hu.ait.android.minesweeper.model;

import android.util.Pair;

import java.util.Random;

import hu.ait.android.minesweeper.MainActivity;

/**
 * Created by zhou_xiaoquan on 6/12/16.
 */
public class model {

    private static model instance = null;
    public static final short FLAG = -3;
    public static final short EMPTY = -2;
    public static final short MINE = -1;
    public static final short ON = -4;
    public static final short OFF = -5;
    public static short DIM = 10;
    public static short MINENUM = 8;
    public static short mode = OFF;
    public static Pair[] mines;
    public boolean gameOver = false;
    public static short[][] model = new short[DIM][DIM];
    public short flags = 0;

    public static model getInstance() {
        if (instance == null) {
            mines = new Pair[MINENUM];
            instance = new model();
            getInstance().resetModel();
        }
        return instance;
    }

    private model() {
    }

    public void setMines() {
        for (int i = 0; i < MINENUM; i++) {
            Pair result = RandomMines(i);
            for (int j = 0; j < i; j++) {
                while (result.first == mines[j].first && result.second == mines[j].second) {
                    result = RandomMines(i);
                }
            }
            mines[i] = result;
        }
    }

    private Pair RandomMines(int i) {
        Random random = new Random();
        int x = random.nextInt(DIM);
        int y = random.nextInt(DIM);
        Pair temp = new Pair(x, y);
        mines[i]  = temp;
        return temp;
    }


    public boolean checkMine(int x, int y) {
        Pair current = new Pair(x, y);
        // A bomb was found!
        for (int i = 0; i < MINENUM; i++) {
            if (mines[i].equals(current)) {
                return true;
            }
        }
        return false;
    }

    public void resetModel() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                model[i][j] = EMPTY;
            }
        }
        gameOver = false;
        flags = 0;
        setMines();
    }

    public short getFieldContent(int x, int y) {
        return model[x][y];
    }

    public short calculateMines(int x, int y) {
        int a = checkSet(x + 1, y) + checkSet(x - 1, y) + checkSet(x, y + 1)
                + checkSet(x, y - 1) + checkSet(x + 1, y + 1) + checkSet(x + 1, y - 1)
                + checkSet(x - 1, y + 1) + checkSet(x - 1, y - 1);
        return (short) a;
    }

    public short setFieldContent(int x, int y, boolean recursion) {
        if (mode == ON) {
            if (flags == MINENUM) {
                return -1;
            }
            flags++;
            model[x][y] = FLAG;
        } else if (checkMine(x, y) && !recursion) {
            model[x][y] = MINE;
        } else {
            short result = calculateMines(x, y);
            model[x][y] = result;
            if (result == 0) {
                RecursiveCheckSet(x + 1, y);
                RecursiveCheckSet(x - 1, y);
                RecursiveCheckSet(x, y + 1);
                RecursiveCheckSet(x, y - 1);
                RecursiveCheckSet(x + 1, y + 1);
                RecursiveCheckSet(x + 1, y - 1);
                RecursiveCheckSet(x - 1, y + 1);
                RecursiveCheckSet(x - 1, y - 1);
            }
        }
        return 1;

    }

    public boolean check(int x) {
        return (x < DIM && x >= 0);
    }

    private short checkSet(int x, int y) {
        if (check(x) && check(y) && checkMine(x, y) ) {
            return 1;
        }
        return 0;
    }

    private void RecursiveCheckSet(int x, int y) {
        if (check(x) && check(y) && model[x][y] == EMPTY) {
            setFieldContent(x, y, true);
        }
    }

    public void removeFlag(int x, int y) {
        model[x][y] = EMPTY;
    }


}


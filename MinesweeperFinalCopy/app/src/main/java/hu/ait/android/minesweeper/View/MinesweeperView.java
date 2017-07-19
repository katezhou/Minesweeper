package hu.ait.android.minesweeper.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import hu.ait.android.minesweeper.MainActivity;
import hu.ait.android.minesweeper.R;
import hu.ait.android.minesweeper.model.model;

/**
 * Created by zhou_xiaoquan on 6/12/16.
 */
public class MinesweeperView extends View {

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintCircle;
    private Paint paintText;
    private Paint paintShade;
    public static short DIM = 10;
    private final short BRUSH = 10;
    private Bitmap bitMapFlag;
    private Bitmap bitMapMine;

    public MinesweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paintBg = new Paint();
        paintBg.setColor(0xbe0080f9);
        paintBg.setStyle(Paint.Style.FILL);

        paintShade = new Paint();

        paintShade.setColor(0xe6014ae9);
        paintShade.setStyle(Paint.Style.FILL);

        paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(100);
        paintText.setStyle(Paint.Style.STROKE);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(BRUSH);

        paintCircle = new Paint();
        paintCircle.setColor(Color.RED);
        paintCircle.setStyle(Paint.Style.FILL);

        bitMapFlag = BitmapFactory.decodeResource(getResources(), R.drawable.flag);
        bitMapMine = BitmapFactory.decodeResource(getResources(), R.drawable.mine);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        // this is where we can draw
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

        // draw board
        drawBoard(canvas);

        // draw players
        drawMines(canvas);
    }

    private void drawBoard(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);

        // drawing horizontal lines
        for (int i = 1; i < DIM; i++) {
            canvas.drawLine(0, i * getHeight() / DIM, getWidth(), i * getHeight() / DIM, paintLine);
        }

        // drawing vertical lines
        for (int i = 1; i < DIM; i++) {
            canvas.drawLine(i * getWidth() / DIM, 0, i * getWidth() / DIM, getHeight(), paintLine);
        }
    }

    private void drawMines(Canvas canvas) {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                float centerX = i * getWidth() / DIM + getWidth() / (DIM * 2);
                float centerY = j * getHeight() / DIM + getHeight() / (DIM * 2);
                if (model.getInstance().getFieldContent(i, j) == model.MINE) {
                    int radius = 95;
                    canvas.drawBitmap(bitMapMine, (i) * (getWidth() / DIM), (j) * (getHeight() / DIM), null);
                } else if (model.getInstance().getFieldContent(i, j) == model.FLAG) {
                    canvas.drawBitmap(bitMapFlag, (i) * (getWidth() / DIM), (j) * (getHeight() / DIM), null);
                } else if (model.getInstance().getFieldContent(i, j) == 0) {
                    canvas.drawRect((i) * (getWidth() / DIM) + BRUSH/2, (j) * (getWidth() / DIM) + BRUSH/2,
                            (i + 1) * (getWidth() / DIM) - (BRUSH /2), (j + 1) * (getWidth() / DIM) - (BRUSH/2), paintShade);
                } else if (model.getInstance().getFieldContent(i, j) != model.EMPTY) {
                    canvas.drawRect((i) * (getWidth() / DIM) + BRUSH/2, (j) * (getWidth() / DIM) + BRUSH/2,
                            (i + 1) * ( getWidth() / DIM) - (BRUSH /2), (j + 1) * ((getWidth() / DIM)) - (BRUSH /2), paintShade);
                    canvas.drawText("" + model.getInstance().getFieldContent(i, j), centerX - 23, centerY + 23, paintText);
                }
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (model.getInstance().gameOver) {
            return false;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int tX = ((int) event.getX()) / (getWidth() / DIM);
            int tY = ((int) event.getY()) / (getWidth() / DIM);
            if (tX < DIM && tY < DIM && model.getInstance().getFieldContent(tX, tY) == model.EMPTY) {
                if (model.mode == model.OFF) {
                    if (model.getInstance().checkMine(tX, tY)) {
                        ((MainActivity) getContext()).showToastMessage(getContext().getString(R.string.over));
                        endGame();
                    }
                }
                if (model.getInstance().setFieldContent(tX, tY, false) == -1) {
                    ((MainActivity) getContext()).showToastMessage(getContext().getString(R.string.flags_max));
                }
            } else if (tX < DIM && tY < DIM && model.getInstance().getFieldContent(tX, tY) == model.FLAG) {
                model.getInstance().flags -= 1;
                model.getInstance().removeFlag(tX, tY);
            }
            invalidate();
            if (checkGame()) {
                ((MainActivity) getContext()).showToastMessage(getContext().getString(R.string.winning_message));
                model.getInstance().gameOver = true;
                ((MainActivity) getContext()).stopTime();
            }
        }
        return true;
    }

    public void clearGameArea() {
        model.getInstance().resetModel();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = (w == 0 ? h : (h == 0 ? w : (w < h ? w : h)));
        setMeasuredDimension(d, d);
    }

    public void endGame() {
        for (Pair mine : model.getInstance().mines) {
            if (model.getInstance().getFieldContent((int) mine.first, (int) mine.second) != model.FLAG) {
                    model.getInstance().setFieldContent((int) mine.first, (int) mine.second, false);
            }
        }
        model.getInstance().gameOver = true;
        ((MainActivity) getContext()).stopTime();
    }

    public boolean checkGame() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (model.getInstance().model[i][j] == model.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitMapFlag = Bitmap.createScaledBitmap(bitMapFlag,
                (getWidth() / DIM) - BRUSH, (getHeight() / DIM) - BRUSH, false);
        bitMapMine = Bitmap.createScaledBitmap(bitMapMine,
                (getWidth() / DIM) - BRUSH, (getHeight() / DIM) - BRUSH, false);
    }
}

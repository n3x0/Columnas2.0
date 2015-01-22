package com.estudioskelon.columnas;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.widget.ImageView;

public class Piece extends Drawable {

	int color;
	int type;
	int row;
	int col;
	int x, y;
	int width, height;
	Path face;
	Paint paint;
	boolean moving;
	View view;

	public Piece(View view) {
		this.view = view;
		moving = true;
		// x = view.getWidth() / 2 - width / 2;
		x = 180;
		y = 0;
		col = 2;
		row = 0;
		width = 60;
		height = 60;
		face = new Path();
		face.addRect(0, 0, 60, 60, Path.Direction.CW);
		paint = new Paint();
		color = Colorator.getColor();
		paint.setColor(color);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.FILL);
	}

	public int getColor() {
		return color;
	}

	public void draw(Canvas canvas) {
		int top, right, bottom, left;
		face.rewind();
		top = y - height / 2;
		right = x + width / 2;
		bottom = y + height / 2;
		left = x - height / 2;
		face.addRect(left, top, right, bottom, Path.Direction.CW);
		// System.out.println(left + " " + top + " " + right + " " + bottom);
		// Save the canvas
		canvas.save();
		canvas.drawPath(face, paint);
		// face.draw(canvas);
		// reset the canvas
		canvas.restore();
		// resetea la vista
		view.invalidate();
	}

	public void fall(int fall) {
		// if (moving)
		// y += fall;
		// if (y>1000)
		// y = 0;
		if (row < 15) {
			row++;
			y = 60 * row;
		}
	}

	public void right() {
		if (col < 5) {
			col++;
			x = x + width;
		}
	}

	public void left() {
		if (col > 0) {
			col--;
			x = x - width;
		}
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void place(int col, int row) {
		x = col;
		y = row;
	}

	public boolean isMoving() {
		return moving;
	}

	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub

	}
}

package com.estudioskelon.columnas;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class Field extends View {

	Canvas canvas;
	Piece piece = null;
	int fall = 10;
	Piece[][] field;
	int[] columns;
	int[] rows;

	public Field(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		createPiece();
		initializeColumnsRows();
		initializeField();
	}

	protected void setPiece(Piece piece, int col, int row) {
		field[col][row] = piece;
	}

	protected Piece getPiece(int col, int row) {
		if ((col >= 0) && (col <= 5) && (row >= 0) && (row <= 15)) {
			Piece piece = field[col][row];
			return piece;
		} else
			return null;
	}

	protected void initializeField() {
		field = new Piece[6][16];
		// for (int i = 0; i < columns.length; i++) {
		// for (int j = 0; j < rows.length; j++) {
		// Piece piece = new Piece(this);
		// piece.place(columns[i], rows[j]);
		// setPiece(piece, i, j);
		// }
		// }
	}

	protected void initializeColumnsRows() {
		int[] iniCol = { 60, 120, 180, 240, 300, 360 };
		columns = iniCol;
		int[] iniRow = { 60, 120, 180, 240, 300, 360, 420, 480, 540, 600, 660,
				720, 780, 840, 900, 960 };
		rows = iniRow;
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.canvas = canvas;
		drawField();
		if (!movePiece()) {
			while (checkField()) {
				//dropField();
			}
			createPiece();
		}
	}

	protected boolean checkField() {
		boolean result = false;
		for (int i = columns.length - 1; i >= 0; i--) {
			for (int j = rows.length - 1; j >= 0; j--) {
				Piece piece = getPiece(i, j);
				if (piece != null) {
					result = checkTop(piece);
					// checkTopleft(piece);
					// checkLeft(piece);
					// checkDownLeft(piece);
					// checkDown(piece);
				}
			}
		}
		if (result){
			System.out.println("HELLO");
		}
		return result;
	}

	protected boolean checkTop(Piece piece) {
		boolean result = false;
		Vector vector = new Vector<Piece>();
		vector.add(piece);
		vector.add(getPieceATop(piece));
		vector.add(getPieceATop(getPieceATop(piece)));
		if ((vector.get(0) != null) && (vector.get(1) != null) && (vector.get(2) != null))
			result = checkColor(vector);
		return result;
	}
	
	protected boolean checkColor(Vector<Piece> vector){
		boolean result = false;
		if ( (vector.get(0).getColor() == vector.get(1).getColor()) && (vector.get(0).getColor() == vector.get(2).getColor()) ){
			System.out.println("COINCIDENCIA con color " + vector.get(0).getColor());
			result = true;
		}
		return result;
	}

	protected Piece getPieceATop(Piece piece) {
		Piece result = null;
		if (piece == null){
			return null;
		}
		result =  getPiece(piece.getCol(), piece.getRow() - 1);
		return result;
	}

	protected void drawField() {
		for (int i = 0; i < columns.length; i++) {
			for (int j = 0; j < rows.length; j++) {
				Piece piece = getPiece(i, j);
				if (piece != null) {
					getPiece(i, j).draw(canvas);
				}
			}
		}
	}

	public void right() {
		if (piece.getCol() < 5)
			if (canRight()) {
				piece.right();
				System.out.println("Mover ficha a la derecha");
			}
	}

	public void left() {
		if (piece.getCol() > 0)
			if (canLeft()) {
				piece.left();
				System.out.println("Mover ficha a la izquierda");
			}
	}

	public void createPiece() {
		piece = new Piece(this);
		System.out.println("Nueva pieza");
	}

	public int normalizeCol(int x) {
		int result = 0;
		for (int i = 0; i < columns.length; i++) {
			if (columns[i] == x)
				result = i;
		}
		return result;
	}

	public int normalizeRow(int y) {
		int result = 0;
		for (int i = 0; i < rows.length; i++) {
			if (rows[i] == y)
				result = i;
		}
		return result;
	}

	public boolean movePiece() {
		piece.draw(canvas);
		// si puede caer cae
		if (canFall()) {
			piece.fall(fall);
			return true;
		} else {
			setPiece(piece, piece.getCol(), piece.getRow());
			// printPiece(piece);
		}
		return false;
	}

	protected boolean canFall() {
		boolean result = false;
		if (piece.getRow() == 15) {
			result = false;
		} else if (getPiece(piece.getCol(), piece.getRow() + 1) == null) {
			result = true;
		}
		return result;
	}

	protected boolean canRight() {
		boolean result = false;
		if (getPiece(piece.getCol() + 1, piece.getRow()) == null) {
			result = true;
		}
		return result;
	}

	protected boolean canLeft() {
		boolean result = false;
		if (getPiece(piece.getCol() - 1, piece.getRow()) == null) {
			result = true;
		}
		return result;
	}

	private void printPiece(Piece piece) {
		if (piece != null)
			System.out.println("Pieza " + piece.color + " colocada en "
					+ normalizeCol(piece.x) + ", " + normalizeRow(piece.y));
	}

	private void print() {
		for (int i = 0; i < columns.length; i++) {
			for (int j = 0; j < rows.length; j++) {
				Piece piece = getPiece(i, j);
				printPiece(piece);
			}
		}
	}
}

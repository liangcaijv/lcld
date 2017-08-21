package org.lanqiao.javafx.fir;

/**
 * 棋盘
 *
 * @author Mi Zhou
 */
public class ChessBoard {

	public static final int SIZE = 15;
	private final ChessPiece[][] pieces;
	private final int[][] intSymbols;

	public ChessBoard() {
		pieces = new ChessPiece[SIZE][SIZE];
		intSymbols = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				pieces[i][j] = ChessPiece.EMPTY;
				intSymbols[i][j] = ChessPiece.EMPTY.val();
			}
		}
	}

	public void set(int row, int col, ChessPiece piece) {
		pieces[row][col] = piece;
		intSymbols[row][col] = piece.val();
	}

	public ChessPiece get(int row, int col) {
		return pieces[row][col];
	}

	public int[][] asIntSybmols() {
		return intSymbols;
	}

	public void show() {
		System.out.print("   ");
		for (int j = 0; j < SIZE; ++j) {
			System.out.printf("%2d ", j + 1);
		}
		System.out.println();
		for (int i = 0; i < SIZE; ++i) {
			System.out.format("%2d ", i + 1);
			for (int j = 0; j < SIZE; ++j) {
				if (pieces[i][j] != ChessPiece.EMPTY) {
					System.out.printf("%2d ", pieces[i][j].val());
				} else {
					System.out.print("   ");
				}
			}
			System.out.println();
		}
	}

}

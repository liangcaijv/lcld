package org.lanqiao.javafx.fir;


import static org.lanqiao.javafx.fir.ChessPiece.EMPTY;

/**
 * 裁判
 *
 * @author Mi Zhou
 */
public class Referee {

	private ChessBoard board;

	public Referee(ChessBoard board) {
		this.board = board;
	}

	public void setBoard(ChessBoard board) {
		this.board = board;
	}

	public ChessPiece getWinner() {
		int size = ChessBoard.SIZE;

		ChessPiece winner = EMPTY;
		boolean finished = false;
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (i > 1 && j > 1 && i < size - 2 && j < size - 2
						&& board.get(i, j) != EMPTY && isWinner(i, j)) {
					winner = board.get(i, j);
					finished = true;
					break;
				}
			}
			if (finished) {
				break;
			}
		}
		return winner;
	}

	private boolean isWinner(int i, int j) {
		ChessPiece piece = board.get(i, j);
		return board.get(i, j - 1) == piece && board.get(i, j - 2) == piece
				&& board.get(i, j + 1) == piece && board.get(i, j + 2) == piece
				|| board.get(i - 1, j) == piece && board.get(i - 2, j) == piece
				&& board.get(i + 1, j) == piece && board.get(i + 2, j) == piece
				|| board.get(i - 1, j - 1) == piece && board.get(i - 2, j - 2) == piece
				&& board.get(i + 1, j + 1) == piece && board.get(i + 2, j + 2) == piece
				|| board.get(i - 1, j + 1) == piece && board.get(i - 2, j + 2) == piece
				&& board.get(i + 1, j - 1) == piece && board.get(i + 2, j - 2) == piece;
	}
}

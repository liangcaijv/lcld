package org.lanqiao.javafx.fir;

/**
 *
 * @author Mi Zhou
 */
public abstract class Player {

	protected final ChessBoard board;
	protected final ChessPiece symbol;

	public Player(ChessBoard board, ChessPiece symbol) {
		this.board = board;
		this.symbol = symbol;
	}

	public abstract Pair<Integer, Integer> next();

	public ChessPiece getSymbol() {
		return symbol;
	}

}

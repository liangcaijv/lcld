package org.mizhou.fir.chess;

/**
 * 棋子
 *
 * @author Mi Zhou
 */
public enum ChessPiece {

	EMPTY(0), WHITE(1), BLACK(2);

	private final int val;

	private ChessPiece(int val) {
		this.val = val;
	}

	public int val() {
		return val;
	}
}

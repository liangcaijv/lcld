package org.lanqiao.prj.fir;

/**
 * @author zhengwei
 *
 */
public class GameManager {
	// 棋盘边界
	final int BOUNDARY = 15;
	// 棋谱记录
	private int[][] pieces = new int[BOUNDARY][BOUNDARY];
	// 当前棋手
	private Player currentPlayer=Player.BLACK;
	// 最后一步的点
	private int[] lastPoint = new int[2];
	// 空白点
	private int blank = BOUNDARY*BOUNDARY;
	// 是否终止
	private boolean over = false;

	/**
	 * 构造器负责初始化
	 */
	public GameManager() {

	}
	

	public void changePlayer() {
		this.currentPlayer = this.currentPlayer==Player.BLACK?Player.WHITE:Player.BLACK;
	}

	public int[] getLastPoint() {
		return lastPoint;
	}

	public boolean setLastPoint(int[] lastPoint) {
		if (lastPoint[0]<0||lastPoint[1]>=BOUNDARY||lastPoint[1]<0||lastPoint[1]>=BOUNDARY) {
			return false;
    }
		if (pieces[lastPoint[0]][lastPoint[1]] != 0) {
			return false;
		}
		this.lastPoint = lastPoint;
		this.blank--;
		this.pieces[lastPoint[0]][lastPoint[1]] = this.currentPlayer == Player.BLACK ? 1
		    : -1;
		return true;
	}


	private boolean isFiveInRHT() {
		int n = 1;
		int x = lastPoint[0];
		int y = lastPoint[1];
		while (--x >= 0 && ++y < BOUNDARY
		    && pieces[x][y] == currentPlayer.getSymbol()) {
			n++;
			if (n == 5)
				return true;
		}
		x = lastPoint[0]; // 重置一下
		y = lastPoint[1];// 重置一下
		while (++x < BOUNDARY && --y >= 0
		    && pieces[x][y] == currentPlayer.getSymbol()) {
			n++;
			if (n == 5)
				return true;
		}
		return false;
	}

	private boolean isFiveInLHT() {
		int n = 1;
		int x = lastPoint[0];
		int y = lastPoint[1];
		while (--x >= 0 && --y >= 0 && pieces[x][y] == currentPlayer.getSymbol()) {
			n++;
			if (n == 5)
				return true;
		}
		x = lastPoint[0]; // 重置一下
		y = lastPoint[1];// 重置一下
		while (++x < BOUNDARY && ++y < BOUNDARY
		    && pieces[x][y] == currentPlayer.getSymbol()) {
			n++;
			if (n == 5)
				return true;
		}
		return false;
	}

	private boolean isFiveInVertical() {
		int n = 1;
		int x = lastPoint[0];
		int y = lastPoint[1];
		while (--x >= 0 && pieces[x][y] == currentPlayer.getSymbol()) {
			n++;
			if (n == 5)
				return true;
		}
		x = lastPoint[0]; // 重置一下
		while (++x < BOUNDARY && pieces[x][y] == currentPlayer.getSymbol()) {
			n++;
			if (n == 5)
				return true;
		}
		return false;
	}

	private boolean isFiveInHorizontal() {
		int n = 1;
		int x = lastPoint[0];
		int y = lastPoint[1];
		while (--y >= 0 && pieces[x][y] == currentPlayer.getSymbol()) {
			n++;
			if (n == 5)
				return true;
		}
		y = lastPoint[1]; // 重置一下
		while (++y < BOUNDARY && pieces[x][y] == currentPlayer.getSymbol()) {
			n++;
			if (n == 5)
				return true;
		}
		return false;
	}

	public int[][] getPieces() {
		return pieces;
	}

	public boolean isOver() {
		boolean fiveInHorizontal = isFiveInHorizontal();
		boolean fiveInVertical = isFiveInVertical();
		boolean fiveInLHT = isFiveInLHT();
		boolean fiveInRHT = isFiveInRHT();
		if (fiveInHorizontal || fiveInVertical || fiveInLHT || fiveInRHT) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isStalemate() {
		return blank==0;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

}

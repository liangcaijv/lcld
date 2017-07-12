package org.mizhou.fir.chess;

import static org.mizhou.fir.chess.ChessBoard.SIZE;

/**
 * 电脑
 * 
 * @author Mi Zhou
 */
public class Computer extends Player {

	private final int COMPUTER;
	private final int HUMAN;

	public Computer(ChessBoard board, ChessPiece symbol, ChessPiece anotherSymbol) {
		super(board, symbol);
		COMPUTER = symbol.val();
		HUMAN = anotherSymbol.val();
	}

	@Override
	public Pair<Integer, Integer> next() {
		return computeNextPosition();
	}

	private final int[] value = new int[SIZE];
	private final int[][] importance = new int[SIZE][SIZE]; // 记录每个空点的重要性

	// 计算出电脑要走的下一步棋
	private Pair<Integer, Integer> computeNextPosition() {
		for (int i = 0; i < SIZE; i++) {// 重要性数组清空
			for (int j = 0; j < SIZE; j++) {
				importance[i][j] = 0;
			}
		}
		// 分别计算每个空点对应电脑和玩家的重要性，取最关键点
		computeFourDirection(COMPUTER);
		computeFourDirection(HUMAN);
		int maxValue = 0;
		int x = 0, y = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (maxValue < importance[i][j]) {
					maxValue = importance[i][j];
					x = i;
					y = j;
				} // 重要性相等的点，随机取
				if (maxValue == importance[i][j]) {
					if ((int) (Math.random() * 100) % 3 == 0) {
						maxValue = importance[i][j];
						x = i;
						y = j;
					}
				}
			}
		}
		return Pair.of(x, y);
	}

	// 用于计算每点四个方向的权值，遇到自己的棋子加3，遇到空加1。
	private void computeFourDirection(int symbolVal) {
		int[][] chessBoard = board.asIntSybmols();
		for (int i = 0, tmp; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (chessBoard[i][j] == 0) { // 每个空点即可以落子的点，计算其重要性
					for (int k = 0; k < 4; k++) {
						value[k] = 0; // 统计数组每次清空
					}
					// 横向
					tmp = 0;
					for (int k = i - 1; k >= 0; k--) {// 从该点看，左右连续的情况
						if (chessBoard[k][j] == symbolVal) {
							tmp += 3;
						} else if (chessBoard[k][j] == 0) {
							tmp++;
							break;
						} else {
							break;
						}
					}
					for (int k = i + 1; k < SIZE; k++) {
						if (chessBoard[k][j] == symbolVal) {
							tmp += 3;
						} else if (chessBoard[k][j] == 0) {
							tmp++;
							break;
						} else {
							break;
						}
					}
					if (tmp == 3 || tmp == 6 || tmp == 9) {
						tmp = 0;// 由于数字3、6、9代表的情况没有连成五连珠的可能，即没有前途的，权值赋0
					}
					if (tmp > value[0]) {
						value[0] = tmp;
					}

					// 竖向
					tmp = 0;
					for (int k = j - 1; k >= 0; k--) {
						if (chessBoard[i][k] == symbolVal) {
							tmp += 3;
						} else if (chessBoard[i][k] == 0) {
							tmp++;
							break;
						} else {
							break;
						}
					}
					for (int k = j + 1; k < SIZE; k++) {
						if (chessBoard[i][k] == symbolVal) {
							tmp += 3;
						} else if (chessBoard[i][k] == 0) {
							tmp++;
							break;
						} else {
							break;
						}
					}
					if (tmp == 3 || tmp == 6 || tmp == 9) {
						tmp = 0;
					}
					if (tmp > value[1]) {
						value[1] = tmp;
					}

					// 左上->右下
					tmp = 0;
					for (int k = i - 1, l = j - 1; k >= 0 && l >= 0; k--, l--) {
						if (chessBoard[k][l] == symbolVal) {
							tmp += 3;
						} else if (chessBoard[k][l] == 0) {
							tmp++;
							break;
						} else {
							break;
						}
					}
					for (int k = i + 1, l = j + 1; k < SIZE && l < SIZE; k++, l++) {
						if (chessBoard[k][l] == symbolVal) {
							tmp += 3;
						} else if (chessBoard[k][l] == 0) {
							tmp++;
							break;
						} else {
							break;
						}
					}
					if (tmp == 3 || tmp == 6 || tmp == 9) {
						tmp = 0;
					}
					if (tmp > value[2]) {
						value[2] = tmp;
					}

					// 左下->右上
					tmp = 0;
					for (int k = i - 1, l = j + 1; k >= 0 && l < SIZE; k--, l++) {
						if (chessBoard[k][l] == symbolVal) {
							tmp += 3;
						} else if (chessBoard[k][l] == 0) {
							tmp++;
							break;
						} else {
							break;
						}
					}
					for (int k = i + 1, l = j - 1; k < SIZE && l >= 0; k++, l--) {
						if (chessBoard[k][l] == symbolVal) {
							tmp += 3;
						} else if (chessBoard[k][l] == 0) {
							tmp++;
							break;
						} else {
							break;
						}
					}
					if (tmp == 3 || tmp == 6 || tmp == 9) {
						tmp = 0;
					}
					if (tmp > value[3]) {
						value[3] = tmp;
					}

					// 计算出的四个权值进行综合计算提取出各种情况
					for (int k = 0; k < 2; k++) {
						int r = k;
						// 排序，找出四个方向中权值最大的两个
						for (int l = k + 1; l < 4; l++) {
							if (value[r] < value[l]) {
								r = l;
							}
						}
						tmp = value[k];
						value[k] = value[r];
						value[r] = tmp;
					}

					// 每种必胜或是防止对方必胜的情况按优先级重要考虑
					if (value[0] > 11) { // 由于此时电脑的棋子先于玩家的棋子，其优先级较高,同样的棋局数字大于玩家
						if (symbolVal == COMPUTER) {
							tmp = 1600;
						} else {
							tmp = 800; // 大于11代表的情况落一子就五连珠，优先级最高
						}
					} else if (value[0] == 11 || value[0] + value[1] == 20) {
						if (symbolVal == COMPUTER) {
							tmp = 400;
						} else {
							tmp = 200; // 11或者两个10代表的情况最多两棋就五连珠，优先级第二
						}
					} else if (value[0] + value[1] == 16 || value[0] + value[1] == 18) {
						if (symbolVal == COMPUTER) {
							tmp = 100;
						} else {
							tmp = 50; // 10+8或者8+8代表的情况最多三棋就五连珠，优先级第三
						}
					} else if (symbolVal == COMPUTER) { // 不是必胜的情况，四个方向权值简单相加，不可能超过上面的50，设计是合理的
						tmp = value[0] + value[1] + value[2] + value[3] + 2;
					} else {
						tmp = value[0] + value[1] + value[2] + value[3];
					}
					importance[i][j] += tmp;
				}
			}
		}
	}

}

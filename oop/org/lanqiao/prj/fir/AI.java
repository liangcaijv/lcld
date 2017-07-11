package org.lanqiao.prj.fir;

import java.util.Arrays;

public class AI {
	private GameManager gameManager;
	private int[][] importance;
	private Player currentPlayer;
	private Player anotherPlayer;

	public AI(GameManager gameManager) {
		this.gameManager = gameManager;
		importance = new int[gameManager.BOUNDARY][gameManager.BOUNDARY];
		currentPlayer = gameManager.getCurrentPlayer();
		anotherPlayer = currentPlayer == Player.BLACK ? Player.WHITE : Player.BLACK;
	}

	// 计算出电脑要走的下一步棋
	public int[] getBestPoint() {
		// 重要性数组清空
		clearImportance();
		// 计算每个空点对电脑的重要性
		saveImportanceOf(currentPlayer.getSymbol());
		// ArrayUtils.print(importance);
		// 计算每个空点对棋手的重要性
		saveImportanceOf(anotherPlayer.getSymbol());
		// ArrayUtils.print(importance);
		// 计算完后，得到一个完整的评分表，选择得分最高的点
		return getMostImportance();
	}

	private void clearImportance() {
		for (int i = 0; i < gameManager.BOUNDARY; i++) {
			for (int j = 0; j < gameManager.BOUNDARY; j++) {
				importance[i][j] = 0;
			}
		}
	}

	private int[] getMostImportance() {
		int maxValue = 0;
		int x = 0, y = 0; // 表示这个点
		for (int i = 0; i < gameManager.BOUNDARY; i++) {
			for (int j = 0; j < gameManager.BOUNDARY; j++) {
				if (maxValue < importance[i][j]) {
					maxValue = importance[i][j];
					x = i;
					y = j;
				}
				// 重要性相等的点，随机取
				if (maxValue == importance[i][j]) {
					if ((int) (Math.random() * 100) % 3 == 0) {
						maxValue = importance[i][j];
						x = i;
						y = j;
					}
				}
			}
		}
		return new int[] { x, y };
	}

	// 用于计算每点四个方向的分值，遇到自己的棋子加3，遇到空加1。
	private void saveImportanceOf(int symbolVal) {
		int[] values = new int[4];
		for (int i = 0; i < gameManager.BOUNDARY; i++) {
			for (int j = 0; j < gameManager.BOUNDARY; j++) {
				// 每个点都要计算四个方向分值，因此每下一个点，都要先清空values
				Arrays.fill(values, 0);
				// 每个空点即可以落子的点，计算其重要性
				if (gameManager.getPieces()[i][j] == 0) {
					// 四个方向有四个分值
					values[0] = scoreOfVertical(symbolVal, i, j);
					values[1] = scoreOforizontal(symbolVal, i, j);
					values[2] = scoreOfLHT(symbolVal, i, j);
					values[3] = scoreOfRHT(symbolVal, i, j);
					// 必胜清空单独处理
					int last = lastOf(values, symbolVal);
					if (last == 0) {
						if (symbolVal == currentPlayer.getSymbol()) {
							// 不是必胜的情况，四个方分值值简单相加，不可能超过上面的50，设计是合理的
							last = values[0] + values[1] + values[2] + values[3] + 2;
						} else {
							last = values[0] + values[1] + values[2] + values[3];
						}
					}
					importance[i][j] += last; // 注意这里是累加操作
				}
			}
		}
	}

	/**
	 * 处理必胜情况，自己的必胜或者对手的必胜
	 * 
	 * @param value
	 * @param symbolVal
	 * @return
	 */
	private int lastOf(int[] value, int symbolVal) {
		// 排序
		Arrays.sort(value);
		int tmp = 0;
		// -----每种必胜或是防止对方必胜的情况按优先级重要考虑----
		// 此点评估单个方向就12分，说明已经存在4个连续同色棋子，必胜
		if (value[3] > 11) {
			if (symbolVal == currentPlayer.getSymbol()) {
				tmp = 1600; // 电脑必胜点 评估为1600
			} else {
				tmp = 800; // 成五
			}
		} else if (value[3] == 11 || value[3] + value[2] == 20) {
			if (symbolVal == currentPlayer.getSymbol()) {
				tmp = 400;
			} else {
				tmp = 200; // 活三 或者 四四
			}
		} else if (value[3] + value[2] == 16 || value[3] + value[2] == 18) {
			if (symbolVal == currentPlayer.getSymbol()) {
				tmp = 100;
			} else {
				tmp = 50; // 双三 或者 四三
			}
		}
		return tmp;
	}

	private int scoreOfRHT(int symbolVal, int i, int j) {
		// 左下->右上
		int tmp = 0;
		for (int k = i - 1, l = j + 1; k >= 0 && l < gameManager.BOUNDARY; k--, l++) {
			if (gameManager.getPieces()[k][l] == symbolVal) {
				tmp += 3;
			} else if (gameManager.getPieces()[k][l] == 0) {
				tmp++;
				break;
			} else {
				break;
			}
		}
		for (int k = i + 1, l = j - 1; k < gameManager.BOUNDARY && l >= 0; k++, l--) {
			if (gameManager.getPieces()[k][l] == symbolVal) {
				tmp += 3;
			} else if (gameManager.getPieces()[k][l] == 0) {
				tmp++;
				break;
			} else {
				break;
			}
		}
		if (tmp == 3 || tmp == 6 || tmp == 9) {
			tmp = 0;
		}
		return tmp;
	}

	private int scoreOfLHT(int symbolVal, int i, int j) {
		// 左上->右下
		int tmp = 0;
		for (int k = i - 1, l = j - 1; k >= 0 && l >= 0; k--, l--) {
			if (gameManager.getPieces()[k][l] == symbolVal) {
				tmp += 3;
			} else if (gameManager.getPieces()[k][l] == 0) {
				tmp++;
				break;
			} else {
				break;
			}
		}
		for (int k = i + 1, l = j + 1; k < gameManager.BOUNDARY
		    && l < gameManager.BOUNDARY; k++, l++) {
			if (gameManager.getPieces()[k][l] == symbolVal) {
				tmp += 3;
			} else if (gameManager.getPieces()[k][l] == 0) {
				tmp++;
				break;
			} else {
				break;
			}
		}
		if (tmp == 3 || tmp == 6 || tmp == 9) {
			tmp = 0;
		}
		return tmp;

	}

	private int scoreOforizontal(int symbolVal, int i, int j) {
		// 横向
		int tmp = 0;
		for (int k = j - 1; k >= 0; k--) { // 向左看
			if (gameManager.getPieces()[i][k] == symbolVal) {
				tmp += 3;
			} else if (gameManager.getPieces()[i][k] == 0) {
				tmp++;
				break;
			} else {
				break;
			}
		}
		for (int k = j + 1; k < gameManager.BOUNDARY; k++) { // 向又看
			if (gameManager.getPieces()[i][k] == symbolVal) {
				tmp += 3;
			} else if (gameManager.getPieces()[i][k] == 0) {
				tmp++;
				break;
			} else {
				break;
			}
		}
		if (tmp == 3 || tmp == 6 || tmp == 9) {
			tmp = 0;
		}
		return tmp;
	}

	private int scoreOfVertical(int symbolVal, int i, int j) {
		int tmp = 0;
		// 纵向
		for (int k = i - 1; k >= 0; k--) {// 往上看
			if (gameManager.getPieces()[k][j] == symbolVal) { // 同色棋子+3
				tmp += 3;
			} else if (gameManager.getPieces()[k][j] == 0) { // 空白+1，终止
				tmp++;
				break;
			} else { // 不同色棋子
				break;
			}
		}
		for (int k = i + 1; k < gameManager.BOUNDARY; k++) { // 往下看
			if (gameManager.getPieces()[k][j] == symbolVal) {
				tmp += 3;
			} else if (gameManager.getPieces()[k][j] == 0) {
				tmp++;
				break;
			} else {
				break;
			}
		}
		// 纵向总得分
		if (tmp == 3 || tmp == 6 || tmp == 9) {
			tmp = 0;// 由于数字3、6、9代表的情况没有连成五连珠的可能，即没有前途的，权值赋0
		}
		return tmp;
	}
}

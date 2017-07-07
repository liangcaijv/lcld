import java.util.*;

/**
 * 五子棋 数组 dos版本 评分表算法版本
 * 
 * @author zhengwei lastmodified 2017年7月6日
 *
 */
public class FiveInRowAI {
	private static final int BOUNDARY = 15;
	// 棋子数组
	private static int[][] pieces = new int[BOUNDARY][BOUNDARY];
	// 评分数组
	private static int[][] importance = new int[BOUNDARY][BOUNDARY];
	private static final Scanner in = new Scanner(System.in);
	private static int AI_PLAYER = -1; // 预设电脑执白
	private static int ANOTHER_PLAYER = 1;

	public static void main(String[] args) {
		// 1.绘制棋盘
		// 2.开始棋局
		// 3.黑方落子
		// 3.1 判定落子是否合法
		// 3.2判定落子后是否胜利
		// 胜利：结束
		// 未胜利：继续
		// 4.白方落子
		// 4.1 判定落子是否合法
		// 4.2判定落子后是否胜利
		// 胜利：结束
		// 未胜利：继续

		int player = 1; // 黑方，变为0表示白方
		boolean hasWiner = false; // 是否有胜利者
		int blank = BOUNDARY * BOUNDARY;

		drawChessboard(null);
		while (!hasWiner && blank > 0) {
			int[] piece = getPiece(player);
			if (!isOk(piece)) {
				System.out.print("已有棋子，");
				continue;
			} else {
				pieces[piece[0]][piece[1]] = player;
				drawChessboard(piece);
			}
			hasWiner = judgeGame(player, piece);
			blank--;
			// 换手
			if (!hasWiner) {
				player = -player;
			}
		}
		if (hasWiner) {
			System.out.println(player == -1 ? "---白方胜!---" : "---黑方胜!---");
		} else {
			System.out.println("你们真行，棋盘填满都未分胜负");
		}

	}

	/**
	 * 绘制棋盘
	 * 
	 * @param piece
	 */
	private static void drawChessboard(int[] piece) {

		System.out.print("   ");
		for (int i = 0; i < BOUNDARY; i++) {
			System.out.print((i > 9 ? "" : " ") + i + " ");
		}
		System.out.println();
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((i > 9 ? "" : " ") + i + " ");
			for (int j = 0; j < pieces[i].length; j++) {
				if (null != piece && i == piece[0] && j == piece[1]) {
					System.out.print(pieces[i][j] == 0 ? "   "
					    : pieces[i][j] == 1 ? "(×)" : "(○)");
				} else {
					System.out.print(pieces[i][j] == 0 ? "   "
					    : pieces[i][j] == 1 ? " × " : " ○ ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * 接受用户输入
	 * 
	 * @param player
	 * @return
	 */
	private static int[] getPiece(int player) {
		int x;
		int y;
		// 人类棋手
		// if (player == ANOTHER_PLAYER) {
		// System.out.println("请人类棋手(" + player2Text(player) + ")落子：");
		// x = in.nextInt();
		// y = in.nextInt();
		// return new int[] { x, y };
		// } else {
		int[] nextPosition = getNextPosition();
		System.out.println("电脑(" + player2Text(player) + ")落子.");
		return nextPosition;
		// }
	}

	private static String player2Text(int player) {
		return player == 1 ? "黑方" : "白方";
	}

	/**
	 * 是否可以下子
	 * 
	 * @param piece
	 *          点位
	 * @return
	 */
	private static boolean isOk(int[] piece) {
		if (pieces[piece[0]][piece[1]] == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判定当前棋手是否胜利
	 * 
	 * @param player
	 *          棋手
	 * @param piece
	 *          点位
	 * @return
	 */
	private static boolean judgeGame(int player, int[] piece) {
		int x = piece[0];
		int y = piece[1];
		boolean fiveInHorizontal = isFiveInHorizontal(player, x, y);
		boolean fiveInVertical = isFiveInVertical(player, x, y);
		boolean fiveInLHT = isFiveInLHT(player, x, y);
		boolean fiveInRHT = isFiveInRHT(player, x, y);
		if (fiveInHorizontal || fiveInVertical
		    || fiveInLHT || fiveInRHT){
			System.out.println("水平"+fiveInHorizontal);
			System.out.println("垂直"+fiveInVertical);
			System.out.println("左手"+fiveInLHT);
			System.out.println("右手"+fiveInRHT);
			return true;
		}else {
			return false;
		}
	}

	private static boolean isFiveInRHT(int player, int x, int y) {
		int n = 1;
		int k = x-1;
		int j = y+1;
		while (k > 0 && j < BOUNDARY && pieces[k][j] == player) {
			n++;
			k--;
			j++;
			if (n == 5)
				return true;
		}
		k = x+1;
		j = y-1;
		while (k < BOUNDARY && j >= 0 && pieces[k][j] == player) {
			n++;
			k++;
			j--;
			if (n == 5)
				return true;
		}
		return false;
	}

	private static boolean isFiveInLHT(int player, int x, int y) {
		int n = 1;
		int k = x-1;
		int j = y-1;
		while (k >= 0 && j >= 0 && pieces[k][j] == player) {
			n++;
			k--;
			j--;
			if (n == 5)
				return true;
		}
		k = x+1;
		j = y+1;
		while (k < BOUNDARY && j < BOUNDARY && pieces[k][j] == player) {
			n++;
			k++;
			j++;
			if (n == 5)
				return true;
		}
		return false;
	}

	private static boolean isFiveInVertical(int player, int x, int y) {
		int n = 1;
		int k = x+1;
		while (k < BOUNDARY && pieces[k][y] == player) {
			k++;
			n++;
			if (n == 5)
				return true;
		}
		k = x-1;
		while (k >= 0 && pieces[k][y] == player) {
			k--;
			n++;
			if (n == 5){
				return true;				
			}
		}
		return false;
	}

	/**
	 * 水平足五
	 * 
	 * @param player
	 * @param x
	 * @param y
	 * @return
	 */
	private static boolean isFiveInHorizontal(int player, int x, int y) {
		int n = 1;
		int k = y-1;
		while (k >= 0 && pieces[x][k] == player) {
			n++;
			k--;
			if (n == 5)
				return true;
		}
		k = y+1;
		while (k < BOUNDARY && pieces[x][k] == player) {
			n++;
			k++;
			if (n == 5)
				return true;
		}
		return false;
	}

	// 计算出电脑要走的下一步棋
	private static int[] getNextPosition() {
		// 重要性数组清空
		clearImportance();
		// 计算每个空点对电脑的重要性
		saveImportanceOf(AI_PLAYER);
		// ArrayUtils.print(importance);
		// 计算每个空点对棋手的重要性
		saveImportanceOf(ANOTHER_PLAYER);
		// ArrayUtils.print(importance);
		// 计算完后，得到一个完整的评分表，选择得分最高的点
		return getMostImportance();
	}

	private static void clearImportance() {
		for (int i = 0; i < BOUNDARY; i++) {
			for (int j = 0; j < BOUNDARY; j++) {
				importance[i][j] = 0;
			}
		}
	}

	private static int[] getMostImportance() {
		int maxValue = 0;
		int x = 0, y = 0; // 表示这个点
		for (int i = 0; i < BOUNDARY; i++) {
			for (int j = 0; j < BOUNDARY; j++) {
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
	private static void saveImportanceOf(int symbolVal) {
		int[] values = new int[4];
		for (int i = 0; i < BOUNDARY; i++) {
			for (int j = 0; j < BOUNDARY; j++) {
				// 每个点都要计算四个方向分值，因此每下一个点，都要先清空values
				Arrays.fill(values, 0);
				// 每个空点即可以落子的点，计算其重要性
				if (pieces[i][j] == 0) {
					// 四个方向有四个分值
					values[0] = scoreOfVertical(symbolVal, i, j);
					values[1] = scoreOforizontal(symbolVal, i, j);
					values[2] = scoreOfLHT(symbolVal, i, j);
					values[3] = scoreOfRHT(symbolVal, i, j);
					// 必胜清空单独处理
					int last = lastOf(values, symbolVal);
					if (last == 0) {
						if (symbolVal == AI_PLAYER) {
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
	private static int lastOf(int[] value, int symbolVal) {
		// 排序
		Arrays.sort(value);
		int tmp = 0;
		// -----每种必胜或是防止对方必胜的情况按优先级重要考虑----
		// 此点评估单个方向就12分，说明已经存在4个连续同色棋子，必胜
		if (value[3] > 11) {
			if (symbolVal == AI_PLAYER) {
				tmp = 1600; // 电脑必胜点 评估为1600
			} else {
				tmp = 800; // 成五
			}
		} else if (value[3] == 11 || value[3] + value[2] == 20) {
			if (symbolVal == AI_PLAYER) {
				tmp = 400;
			} else {
				tmp = 200; // 活三 或者 四四
			}
		} else if (value[3] + value[2] == 16 || value[3] + value[2] == 18) {
			if (symbolVal == AI_PLAYER) {
				tmp = 100;
			} else {
				tmp = 50; // 双三 或者 四三
			}
		}
		return tmp;
	}

	private static int scoreOfRHT(int symbolVal, int i, int j) {
		// 左下->右上
		int tmp = 0;
		for (int k = i - 1, l = j + 1; k >= 0 && l < BOUNDARY; k--, l++) {
			if (pieces[k][l] == symbolVal) {
				tmp += 3;
			} else if (pieces[k][l] == 0) {
				tmp++;
				break;
			} else {
				break;
			}
		}
		for (int k = i + 1, l = j - 1; k < BOUNDARY && l >= 0; k++, l--) {
			if (pieces[k][l] == symbolVal) {
				tmp += 3;
			} else if (pieces[k][l] == 0) {
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

	private static int scoreOfLHT(int symbolVal, int i, int j) {
		// 左上->右下
		int tmp = 0;
		for (int k = i - 1, l = j - 1; k >= 0 && l >= 0; k--, l--) {
			if (pieces[k][l] == symbolVal) {
				tmp += 3;
			} else if (pieces[k][l] == 0) {
				tmp++;
				break;
			} else {
				break;
			}
		}
		for (int k = i + 1, l = j + 1; k < BOUNDARY && l < BOUNDARY; k++, l++) {
			if (pieces[k][l] == symbolVal) {
				tmp += 3;
			} else if (pieces[k][l] == 0) {
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

	private static int scoreOforizontal(int symbolVal, int i, int j) {
		// 横向
		int tmp = 0;
		for (int k = j - 1; k >= 0; k--) { // 向左看
			if (pieces[i][k] == symbolVal) {
				tmp += 3;
			} else if (pieces[i][k] == 0) {
				tmp++;
				break;
			} else {
				break;
			}
		}
		for (int k = j + 1; k < BOUNDARY; k++) { // 向又看
			if (pieces[i][k] == symbolVal) {
				tmp += 3;
			} else if (pieces[i][k] == 0) {
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

	private static int scoreOfVertical(int symbolVal, int i, int j) {
		int tmp = 0;
		// 纵向
		for (int k = i - 1; k >= 0; k--) {// 往上看
			if (pieces[k][j] == symbolVal) { // 同色棋子+3
				tmp += 3;
			} else if (pieces[k][j] == 0) { // 空白+1，终止
				tmp++;
				break;
			} else { // 不同色棋子
				break;
			}
		}
		for (int k = i + 1; k < BOUNDARY; k++) { // 往下看
			if (pieces[k][j] == symbolVal) {
				tmp += 3;
			} else if (pieces[k][j] == 0) {
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

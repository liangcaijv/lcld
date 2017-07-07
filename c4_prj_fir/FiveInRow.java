import java.util.Scanner;

/**
 * 五子棋 数组 dos版本
 * @author zhengwei lastmodified 2017年7月6日
 *
 */
public class FiveInRow {
  private static final int     BOUNDARY = 15;
  private static int[][]       pieces   = new int[BOUNDARY][BOUNDARY];
  private static final Scanner in       = new Scanner(System.in);

  public static void main(String[] args) {
    //    1.绘制棋盘
    //    2.开始棋局
    //    3.黑方落子
    //      3.1 判定落子是否合法
    //      3.2判定落子后是否胜利
    //          胜利：结束
    //          未胜利：继续
    //    4.白方落子
    //      4.1 判定落子是否合法
    //      4.2判定落子后是否胜利
    //          胜利：结束
    //          未胜利：继续

    int player = 1; // 黑方，变为0表示白方
    boolean hasWiner = false; // 是否有胜利者
    int blank = 81;

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
   * 判定当前棋手是否胜利
   * @param player 棋手
   * @param piece 点位
   * @return
   */
  private static boolean judgeGame(int player, int[] piece) {
    int x = piece[0];
    int y = piece[1];
    return (isFiveInHorizontal(player, x, y) || isFiveInVertical(player, x, y) || isFiveInLHT(player, x, y)
        || isFiveInRHT(player, x, y));
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

  /**
   * 是否可以下子
   * @param piece 点位
   * @return
   */
  private static boolean isOk(int[] piece) {
    if (pieces[piece[0]][piece[1]] == 0) {
      return true;
    }
    return false;
  }

  /**
   * 接受用户输入
   * @param player
   * @return
   */
  private static int[] getPiece(int player) {
    System.out.println("请" + player2Text(player) + "落子：");
    int x = in.nextInt();
    int y = in.nextInt();
    return new int[] { x, y };
  }

  private static String player2Text(int player) {
    return player == 1 ? "黑方" : "白方";
  }

  /**
   * 绘制棋盘
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
}

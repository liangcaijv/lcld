package org.lanqiao.prj.fir;

/**
 * 这是一个枚举类，因为只有两个棋手
 * @author zhengwei lastmodified 2017年7月11日
 *
 */
public enum Player {
  BLACK(1, false), WHITE(-1, false);
  //  数字符号
  private int     symbol;
  //  是否ai标志位
  private boolean ai;

  private Player(int symbol, boolean ai) {
    this.symbol = symbol;
    this.ai = ai;
  }

  public int getSymbol() {
    return symbol;
  }

  public String getColor() {
    return this.symbol == 1 ? "黑" : "白";
  }

  public boolean isAi() {
    return ai;
  }

  public void setAi(boolean ai) {
    this.ai = ai;
  }
}

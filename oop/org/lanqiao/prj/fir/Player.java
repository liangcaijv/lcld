package org.lanqiao.prj.fir;

public enum Player {
	BLACK(1,false), WHITE(-1,false);
	private int symbol;
	private boolean ai;
	private Player(int symbol,boolean ai) {
		this.symbol = symbol;
		this.ai = ai;
	}
	public int getSymbol() {
	  return symbol;
  }
	public String getColor(){
		return this.symbol==1?"黑":"白";
	}
	public boolean isAi() {
	  return ai;
  }
	public void setAi(boolean ai) {
	  this.ai = ai;
  }
}

package org.lanqiao.javase.oop.fir;

import java.util.Scanner;

public class GameUI {
	private static GameManager gameManager = new GameManager();
	private static final Scanner in = new Scanner(System.in);
	private static final AI ai = new AI(gameManager);

	public static void main(String[] args) {
		// 设置黑棋为AI
		Player.BLACK.setAi(true);
		// 设置白棋为AI
		Player.WHITE.setAi(true);
		drawChessboard();
		while (true) {
			int[] piece = getPiece(gameManager.getCurrentPlayer().isAi());
			boolean isOk = gameManager.setLastPoint(piece);
			if (!isOk) {
				System.out.print("位置有误，");
				continue;
			}
			drawChessboard();
			if (gameManager.isOver() || gameManager.isStalemate()) {
				break;
			} else {
				gameManager.changePlayer();
			}
		}
		if (gameManager.isOver()) {
			System.out.println("------" + gameManager.getCurrentPlayer().getColor()
			    + "方胜!------");
		} else {
			System.out.println("你们真行，棋盘填满都未分胜负");
		}
	}

	private static int[] getPiece(boolean isAi) {
		int x;
		int y;
		if (!isAi) {
			// 人类棋手
			System.out.println("请人类棋手(" + gameManager.getCurrentPlayer().getColor()
			    + ")落子：");
			x = in.nextInt();
			y = in.nextInt();
			return new int[] { x, y };
		} else {
			// AI棋手
			System.out.println("AI棋手(" + gameManager.getCurrentPlayer().getColor()
			    + ")落子：");
			return ai.getBestPoint();
		}
	}

	private static void drawChessboard() {
		System.out.print("   ");
		for (int i = 0; i < gameManager.BOUNDARY; i++) {
			System.out.print((i > 9 ? "" : " ") + i + " ");
		}
		System.out.println();
		int[][] pieces = gameManager.getPieces();
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((i > 9 ? "" : " ") + i + " ");
			for (int j = 0; j < pieces[i].length; j++) {
				int[] last = gameManager.getLastPoint();
				if (null != last && i == last[0] && j == last[1]) {
					System.out.print(pieces[i][j] == 0 ? "-┼-"
					    : pieces[i][j] == 1 ? "(×)" : "(○)");
				} else {
					System.out.print(pieces[i][j] == 0 ? "-┼-"
					    : pieces[i][j] == 1 ? " × " : " ○ ");
				}
			}
			System.out.println();
		}
	}
}

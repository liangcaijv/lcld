package org.mizhou.fir;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.mizhou.fir.chess.ChessBoard;
import org.mizhou.fir.chess.ChessPiece;
import org.mizhou.fir.chess.Computer;
import org.mizhou.fir.chess.Pair;
import org.mizhou.fir.chess.Player;
import org.mizhou.fir.chess.Referee;
import org.mizhou.fir.ui.FXDialogs;

/**
 * 主类
 *
 * @author Mi Zhou
 */
public class Main extends Application {

	// <editor-fold desc="属性">
	private final int XStart = 10;
	private final int YStart = 10;
	private final int SIZE = 600;
	private final int PADDING = 5;
	private final int GRID = 15;
	private final int UNIT = SIZE / GRID;
	private final int LINE_WIDTH = 2;

	private final Image BLACK_PIECE;
	private final Image WHITE_PIECE;

	private int blackScore = 0;
	private int whiteScore = 0;
	private ChessPiece winnerSymbol;
	private ChessBoard chessBoard;
	private Referee referee;
	private Player computer;

	private final Canvas boardCanvas;
	private final Text txtScore;
	private final LinkedBlockingQueue<Pair<Integer, Integer>> queue;
	// </editor-fold>

	public Main() {
		queue = new LinkedBlockingQueue<>(1);
		boardCanvas = new Canvas(620, 620);
		txtScore = new Text("Score 0 : 0");

		initScore();
		initGame();

		// 初始化棋子
		BLACK_PIECE = getChessPiece(UNIT - 2 * PADDING, Color.BLACK);
		WHITE_PIECE = getChessPiece(UNIT - 2 * PADDING, Color.WHITE);

	}

	@Override
	public void start(Stage stage) throws Exception {
		boardCanvas.addEventHandler(
				MouseEvent.MOUSE_CLICKED, new ChessBoardEventHandler(queue));

		ImageView ivRestartGame = new ImageView(
				ClassLoader.getSystemResource("images/restart.png").toExternalForm());
		ivRestartGame.addEventHandler(
				MouseEvent.MOUSE_CLICKED, new RestartGameEventHandler());
		ivRestartGame.setCursor(Cursor.HAND);

		VBox vBox = new VBox();
		VBox.setMargin(txtScore, new Insets(200, 0, 0, 5));
		VBox.setMargin(ivRestartGame, new Insets(60, 0, 0, 30));
		vBox.getChildren().addAll(txtScore, ivRestartGame);

		HBox hBox = new HBox();
		hBox.getChildren().addAll(boardCanvas, vBox);

		Group root = new Group();
		root.getChildren().add(hBox);

		Scene scene = new Scene(root);

		stage.setTitle("五子棋");
		stage.setResizable(false);
		stage.setWidth(900);
		stage.setHeight(648);
		stage.setScene(scene);
		stage.show();

		startGame();
	}

	private boolean isPlaying() {
		return winnerSymbol == ChessPiece.EMPTY;
	}

	private void initGame() {
		initChessBoard();
		chessBoard = new ChessBoard();
		referee = new Referee(chessBoard);
		computer = new Computer(chessBoard, ChessPiece.WHITE, ChessPiece.BLACK);
		winnerSymbol = ChessPiece.EMPTY;
		queue.clear();
	}

	private void initChessBoard() {
		boardCanvas.setCursor(Cursor.HAND);
		GraphicsContext g = boardCanvas.getGraphicsContext2D();
		g.setFill(Color.ORANGE);
		g.fillRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
		g.setStroke(Color.BLACK);
		g.setLineWidth(LINE_WIDTH);

		for (int i = 0; i <= GRID; ++i) {
			g.strokeLine(XStart + i * UNIT, YStart, XStart + i * UNIT, YStart + SIZE);
			g.strokeLine(XStart, YStart + i * UNIT, XStart + SIZE, YStart + i * UNIT);
		}
	}

	private void initScore() {
		Blend blend = new Blend();
		blend.setMode(BlendMode.MULTIPLY);

		DropShadow ds = new DropShadow();
		ds.setColor(Color.rgb(254, 235, 66, 0.3));
		ds.setOffsetX(5);
		ds.setOffsetY(5);
		ds.setRadius(5);
		ds.setSpread(0.2);

		blend.setBottomInput(ds);

		DropShadow ds1 = new DropShadow();
		ds1.setColor(Color.web("#f13a00"));
		ds1.setRadius(20);
		ds1.setSpread(0.2);

		Blend blend2 = new Blend();
		blend2.setMode(BlendMode.MULTIPLY);

		InnerShadow is = new InnerShadow();
		is.setColor(Color.web("#feeb42"));
		is.setRadius(9);
		is.setChoke(0.8);
		blend2.setBottomInput(is);

		InnerShadow is1 = new InnerShadow();
		is1.setColor(Color.web("#f13a00"));
		is1.setRadius(5);
		is1.setChoke(0.4);
		blend2.setTopInput(is1);

		Blend blend1 = new Blend();
		blend1.setMode(BlendMode.MULTIPLY);
		blend1.setBottomInput(ds1);
		blend1.setTopInput(blend2);

		blend.setTopInput(blend1);

		txtScore.setFont(Font.font("Microsoft YaHei", FontWeight.BOLD, 50));
		txtScore.setEffect(blend);
		txtScore.setFill(new Color(254.0 / 255, 239.0 / 255, 142.0 / 255, 1));

	}

	private void startGame() {
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Pair<Integer, Integer> next = queue.take();
					chessBoard.set(next.left(), next.right(), ChessPiece.BLACK);
					drawChessPiece(next.left(), next.right());
					winnerSymbol = referee.getWinner();
					if (winnerSymbol != ChessPiece.EMPTY) {
						break;
					}

					Thread.sleep(500);

					Pair<Integer, Integer> computerNext = computer.next();
					chessBoard.set(computerNext.left(), computerNext.right(), ChessPiece.WHITE);
					drawChessPiece(computerNext.left(), computerNext.right());

					winnerSymbol = referee.getWinner();
					if (winnerSymbol != ChessPiece.EMPTY) {
						break;
					}
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			afterCurrentGameOver();
		});
		thread.setDaemon(true);
		thread.start();
	}

	private void afterCurrentGameOver() {
		if (winnerSymbol == ChessPiece.BLACK) {
			blackScore++;
		} else {
			whiteScore++;
		}
		String winner = (winnerSymbol == ChessPiece.BLACK) ? "黑棋" : "白棋";
		Platform.runLater(() -> {
			FXDialogs.showInformation("提示", "胜利者是 " + winner);
			txtScore.setText(String.format("Score %d : %d", blackScore, whiteScore));
		});
	}

	private void drawChessPiece(int row, int col) {
		final double x = XStart + row * UNIT;
		final double y = YStart + col * UNIT;
		Platform.runLater(() -> {
			GraphicsContext g2d = boardCanvas.getGraphicsContext2D();
			if (chessBoard.get(row, col) == ChessPiece.BLACK) {
				g2d.drawImage(BLACK_PIECE, x, y);
			} else {
				g2d.drawImage(WHITE_PIECE, x, y);
			}
		});
	}

	private Image getChessPiece(int radius, Color color) {
		Canvas pieceCanvas = new Canvas(radius, radius);
		GraphicsContext g = pieceCanvas.getGraphicsContext2D();
		g.setFill(color);
		g.fillOval(0, 0, radius, radius);

		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(Color.TRANSPARENT);
		WritableImage image = pieceCanvas.snapshot(sp, null);
		ImageView imageView = new ImageView(image);

		DropShadow dropShadow = new DropShadow(PADDING, color);
		imageView.setEffect(dropShadow);

		return imageView.snapshot(sp, null);
	}

	class RestartGameEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			if (isPlaying()) {
				FXDialogs.showInformation("提示", "请先结束此局游戏");
			} else {
				initGame();
				startGame();
			}
		}
	}

	class ChessBoardEventHandler implements EventHandler<MouseEvent> {

		private final BlockingQueue<Pair<Integer, Integer>> queue;

		public ChessBoardEventHandler(BlockingQueue<Pair<Integer, Integer>> queue) {
			this.queue = queue;
		}

		@Override
		public void handle(MouseEvent event) {
			if (winnerSymbol != ChessPiece.EMPTY) {
				FXDialogs.showInformation("提示", "本次游戏已经结束");
				return;
			}
			int x = (int) (event.getX() - XStart) / UNIT;
			int y = (int) (event.getY() - YStart) / UNIT;
			if (chessBoard.get(x, y) == ChessPiece.EMPTY) {
				queue.offer(Pair.of(x, y));
			} else {
				FXDialogs.showInformation("提示", "这里已经有一个棋子");
			}
		}

	}
	public static void main(String[] args) {
		Application.launch(args);
	}

}

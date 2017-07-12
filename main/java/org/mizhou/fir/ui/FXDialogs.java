package org.mizhou.fir.ui;

import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

public class FXDialogs {

	public static void showInformation(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.DECORATED);
		alert.setTitle(title);
		alert.setHeaderText(message);
		//alert.setContentText(message);

		// 通过 CSS 改变字体
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(ClassLoader.getSystemResource("css/dialog.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");

		alert.showAndWait();
	}

	public static void showWarning(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("警告");
		alert.setHeaderText(title);
		alert.setContentText(message);

		alert.showAndWait();
	}

	public static void showError(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("错误");
		alert.setHeaderText(title);
		alert.setContentText(message);

		alert.showAndWait();
	}

	public static void showException(String title, String message, Exception exception) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("异常");
		alert.setHeaderText(title);
		alert.setContentText(message);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("细节:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}

	public static final String YES = "是";
	public static final String NO = "否";
	public static final String OK = "确定";
	public static final String CANCEL = "取消";

	public static String showConfirm(String title, String message, String... options) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("选择一个选项");
		alert.setHeaderText(title);
		alert.setContentText(message);

		alert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				event.consume();
				try {
					Robot r = new Robot();
					r.keyPress(java.awt.event.KeyEvent.VK_SPACE);
					r.keyRelease(java.awt.event.KeyEvent.VK_SPACE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		if (options == null || options.length == 0) {
			options = new String[]{OK, CANCEL};
		}

		List<ButtonType> buttons = new ArrayList<>();
		for (String option : options) {
			buttons.add(new ButtonType(option));
		}

		alert.getButtonTypes().setAll(buttons);

		Optional<ButtonType> result = alert.showAndWait();
		if (!result.isPresent()) {
			return CANCEL;
		} else {
			return result.get().getText();
		}
	}

	public static String showTextInput(String title, String message, String defaultValue) {
		TextInputDialog dialog = new TextInputDialog(defaultValue);
		dialog.initStyle(StageStyle.UTILITY);
		dialog.setTitle("输入");
		dialog.setHeaderText(title);
		dialog.setContentText(message);

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}

	}

}

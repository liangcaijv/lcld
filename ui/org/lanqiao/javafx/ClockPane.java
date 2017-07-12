package org.lanqiao.javafx;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class ClockPane extends Pane {
	private int			hour;
	private int			minute;
	private int			second;

	private double	w	= 250, h = 250;

	public ClockPane() {
		setCurrentTime();
	}

	public void setCurrentTime() {
		Calendar now = new GregorianCalendar();
		this.hour = now.get(Calendar.HOUR_OF_DAY);
		this.minute = now.get(Calendar.MINUTE);
		this.second = now.get(Calendar.SECOND);
		paintClock();
	}

	protected void paintClock() {
		// 画圆
		double radius = w * 0.8 * 0.5;
		double centerX = w / 2;
		double centerY = h / 2;
		Circle circle = new Circle(centerX, centerY, radius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		// 秒针
		double secondAngle = this.second * 2 * Math.PI / 60;
		double secondEndX = centerX + radius * 0.8 * Math.sin(secondAngle);
		double secondEndY = centerY - radius * 0.8 * Math.cos(secondAngle);
		Line sLine = new Line(centerX, centerY, secondEndX, secondEndY);
		sLine.setStroke(Color.RED);
		// 分针
		double minuteAngle = this.minute * (2 * Math.PI / 60);
		double minuteEndX = centerX + radius * 0.6 * Math.sin(minuteAngle);
		double minuteEndY = centerY - radius * 0.6 * Math.cos(minuteAngle);
		Line mLine = new Line(centerX, centerY, minuteEndX, minuteEndY);
		mLine.setStroke(Color.PURPLE);
		// 时针
		double hourAngle = (this.hour % 12 + this.minute / 60)*(2 * Math.PI   / 12);
		double hourEndX = centerX + radius * 0.4 * Math.sin(hourAngle);
		double hourEndY = centerY - radius * 0.4 * Math.cos(hourAngle);
		Line hLine = new Line(centerX, centerY, hourEndX, hourEndY);
		hLine.setStroke(Color.GREEN);
		
		getChildren().clear();
		getChildren().addAll(circle, sLine, mLine, hLine);
//		System.out.println("re................");
	}
}

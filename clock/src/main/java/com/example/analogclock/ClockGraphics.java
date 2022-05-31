package com.example.analogclock;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Text;

import java.time.LocalTime;

public class ClockGraphics {
    private final GraphicsContext graphicsContext;
    private final double UNIT_PERCENT = 2.5;
    private double canvasWidth;
    private double canvasHeight;
    private double zeroX;
    private double zeroY;
    private final String[] numberValues = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    public ClockGraphics(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw();
            }
        };
        timer.start();
    }

    public void start() {
        init();
    }

    private void init() {
        canvasWidth = graphicsContext.getCanvas().getWidth();
        canvasHeight = graphicsContext.getCanvas().getHeight();

        zeroX = canvasWidth / 2;
        zeroY = canvasHeight / 2;
        graphicsContext.translate(zeroX, zeroY);
    }

    private void draw() {
        clear();
        drawBackground();
        drawNumbers();
        TimeParams timeParams = TimeParams.getTimeParams();

        drawLines(timeParams.lineSecondPosition, timeParams.lineSecondSize, 60, timeParams.lineSecondWidth);
        drawLines(timeParams.lineHourPosition, timeParams.lineHourSize, 12, timeParams.lineHourWidth);

        drawHand(360.0 / 60 * LocalTime.now().getSecond(), Color.WHITE, timeParams.secondHandSize, timeParams.secondHandWidth);
        drawHand(360.0 / 60 * LocalTime.now().getMinute(), Color.WHITESMOKE, timeParams.minuteHandSize, timeParams.minuteHandWidth);
        drawHand(360.0 / 12 * LocalTime.now().getHour(), Color.MEDIUMTURQUOISE, timeParams.hourHandSize, timeParams.hourHandWidth);

        graphicsContext.setFill(Color.DARKSLATEGREY);
        graphicsContext.fillOval(-timeParams.centerCircleRadius, -timeParams.centerCircleRadius, timeParams.centerCircleRadius * 2, timeParams.centerCircleRadius * 2);

        graphicsContext.setStroke(Color.SEAGREEN);
        graphicsContext.strokeOval(-timeParams.borderCircleRadius, -timeParams.borderCircleRadius, timeParams.borderCircleRadius * 2, timeParams.borderCircleRadius * 2);
    }

    private void drawNumbers() {
        for (int i = 0; i < 12; i++) {
            double deg = (360.0 / 12 * i) - 60;
            double x = UNIT_PERCENT * 70 * Math.cos(Math.toRadians(deg));
            double y = UNIT_PERCENT * 70 * Math.sin(Math.toRadians(deg));

            Text number = new Text(numberValues[i]);

            double textWidth = number.getLayoutBounds().getWidth();
            double textHeight = number.getLayoutBounds().getHeight();

            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillText(number.getText(), x - textWidth / 2, y + textHeight / 2);
        }
    }

    private void drawLines(double radiusLocation, double size, int number, double width) {
        graphicsContext.setLineWidth(width);
        for (int i = 0; i < number; i++) {
            double deg = 360.0 / number * i;
            double pointX1 = radiusLocation * Math.cos(Math.toRadians(deg));
            double pointY1 = radiusLocation * Math.sin(Math.toRadians(deg));
            double pointX2 = (radiusLocation + size) * Math.cos(Math.toRadians(deg));
            double pointY2 = (radiusLocation + size) * Math.sin(Math.toRadians(deg));

            graphicsContext.setStroke(Color.WHITE);
            graphicsContext.strokeLine(pointX1, pointY1, pointX2, pointY2);
        }
    }

    private void drawBackground() {
        graphicsContext.setFill(Color.color(0.078, 0.078, 0.078, 0.9));
        double radius = 250.0;
        graphicsContext.fillOval(-radius, -radius, radius * 2, radius * 2);
    }

    public void drawHand(double deg, Color handColor, double handSize, double handWidth) {
        double pointX = handSize * Math.cos(Math.toRadians(deg - 90));
        double pointY = handSize * Math.sin(Math.toRadians(deg - 90));

        double minSizePercent = UNIT_PERCENT * 8;

        double minPointX = minSizePercent * Math.cos(Math.toRadians(deg - 270));
        double minPointY = minSizePercent * Math.sin(Math.toRadians(deg - 270));

        graphicsContext.setLineCap(StrokeLineCap.ROUND);
        graphicsContext.setLineJoin(StrokeLineJoin.ROUND);
        graphicsContext.setLineWidth(handWidth);
        graphicsContext.setStroke(handColor);
        graphicsContext.strokeLine(0, 0, (int) pointX, (int) pointY);

        graphicsContext.setLineWidth(handWidth * 1.5);
        graphicsContext.setStroke(handColor);
        graphicsContext.strokeLine(0, 0, (int) minPointX, (int) minPointY);
    }

    private void clear() {
        graphicsContext.clearRect(-zeroX, -zeroY, canvasWidth, canvasHeight);
    }

}

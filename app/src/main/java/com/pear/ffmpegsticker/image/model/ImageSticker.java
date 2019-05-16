package com.pear.ffmpegsticker.image.model;

public class ImageSticker {
    private String path;
    private int angle;
    private int x;
    private int y;
    private int width;
    private int height;

    public ImageSticker() {
    }

    public ImageSticker(String path, int angle, int x, int y, int width, int height) {
        this.path = path;
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

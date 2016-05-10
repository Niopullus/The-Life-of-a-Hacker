package com.niopullus.NioLib.scene.dynscene;

/**
 * Created by Owen on 4/10/2016.
 */
public class CameraNode extends Node {

    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;

    public CameraNode() {
        super("camera");
        this.xMin = 0;
        this.yMin = 0;
        this.xMax = 0;
        this.yMax = 0;
    }

    public void setBounds(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public void setX(int x) {
        if (x > this.xMax) {
            this.oSetX(this.xMax);
        } else if (x < this.xMin) {
            this.oSetX(this.xMin);
        } else {
            this.oSetX(x);
        }
    }

    public void setY(int y) {
        if (y > this.yMax) {
            this.oSetY(this.yMax);
        } else if (y < this.yMin) {
            this.oSetY(this.yMin);
        } else {
            this.oSetY(y);
        }
    }

}

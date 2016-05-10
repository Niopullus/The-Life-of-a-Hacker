package com.niopullus.NioLib.scene.dynscene;

import java.awt.image.BufferedImage;

/**
 * Created by Owen on 4/6/2016.
 */
public class DynamicTrigger extends DynamicImageNode implements Trigger {

    private int xRad;
    private int yRad;

    public DynamicTrigger(String name, BufferedImage image, int xRad, int yRad) {
        super(name, image);
    }

    public void trigger() {

    }

    public int getCx() {
        return this.getX() + this.getWidth() - this.xRad;
    }

    public int getCy() {
        return this.getY() + this.getHeight() - this.yRad;
    }

    public int getCwidth() {
        return -this.getWidth() + this.xRad * 2;
    }

    public int getCheight() {
        return -this.getHeight() + this.yRad * 2;
    }

}

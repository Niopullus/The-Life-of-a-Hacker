package com.niopullus.app.tiles;

import com.niopullus.NioLib.scene.dynscene.tile.MultiTile;
import com.niopullus.NioLib.scene.dynscene.tile.MultiTileReference;
import com.niopullus.app.bots.KeyBot;
import com.niopullus.app.scenes.LevelScene;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Owen on 4/18/2016.
 */
public class Door extends MultiTile {

    private boolean open;

    public Door() {
        super("door");
        this.open = true;
    }

    public BufferedImage getImage(int part) {
        if (this.open) {
            return ((MultiTileReference) this.getReference()).getImage(0, part);
        } else {
            return ((MultiTileReference) this.getReference()).getImage(1, part);
        }
    }

    public boolean getCollidable() {
        return !this.open;
    }

    public void clickedOn() {
        if (((LevelScene) this.getTilemap().getDynamicScene()).getFollowNode().distanceToW(new Point(this.getRwPos().x + this.getTilemap().getTileSize() / 2, this.getRwPos().y + this.getTilemap().getTileSize() / 2)) < 250 && ((LevelScene) this.getTilemap().getDynamicScene()).getFollowNode() instanceof KeyBot) {
            this.open = !this.open;
        }
    }

}

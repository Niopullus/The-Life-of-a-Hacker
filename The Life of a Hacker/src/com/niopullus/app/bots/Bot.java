package com.niopullus.app.bots;

import com.niopullus.NioLib.scene.dynscene.Collision;
import com.niopullus.NioLib.scene.dynscene.DynamicImageNode;
import com.niopullus.NioLib.scene.dynscene.HalfCollision;
import com.niopullus.app.scenes.HackScene;
import com.niopullus.app.scenes.LevelScene;
import com.niopullus.app.scenes.LevelSelectionScene;
import com.niopullus.app.tiles.Ladder;
import com.niopullus.app.tiles.LevelExit;

import java.awt.image.BufferedImage;

/**
 * Created by Owen on 4/18/2016.
 */
public class Bot extends DynamicImageNode {

    private BotState state;
    private int floorTimer;
    private int dir;
    private int ladderState;

    public Bot(String name, BufferedImage image) {
        super(name, image);
        this.state = BotState.EVIL;
        this.enablePhysics();
        this.enableGravity();
        this.setxSpeedLim(10);
        this.dir = 0;
        this.ladderState = 0;
        this.setColWest(new HalfCollision(100, null));
        this.setColEast(new HalfCollision(100, null));
    }

    public final void tick() {
        if (this.ladderState == 0) {
            this.enableGravity();
        } else {
            this.disableGravity();
        }
        if (this.state == BotState.EVIL) {
            if (this.dir == 0) {
                if (this.getColWest().getDist() > 5) {
                    this.accelerateX(-15);
                } else {
                    this.dir = 1;
                }
            } else if (this.dir == 1) {
                if (this.getColEast().getDist() > 5) {
                    this.accelerateX(15);
                } else {
                    this.dir = 0;
                }
            }
        }
    }

    public void setGood() {
        this.state = BotState.GOOD;
        this.switchImage(1);
    }

    public void setEvil() {
        this.state = BotState.EVIL;
        this.switchImage(2);
    }

    public int getFloorTimer() {
        return this.floorTimer;
    }

    public void setFloorTimer(int floorTimer) {
        this.floorTimer = floorTimer;
    }

    public void clickedOn() {
        if (this.state == BotState.EVIL) {
            this.getDynamicScene().addSubScene(new HackScene(this.getDynamicScene(), this));
        } else {
            ((LevelScene) this.getDynamicScene()).setFollowNode(this);
        }
    }

    public enum BotState {
        EVIL, GOOD;
    }

    public void victimCollision(Collision c) {
        if (this.state == BotState.EVIL && c.getCauser() instanceof Bot && ((Bot) c.getCauser()).state == BotState.GOOD) {
            ((Bot) c.getCauser()).removeFromParent();
        }
    }

    public void causerCollision(Collision c) {
        if (this.state == BotState.EVIL && c.getVictim() instanceof Bot && ((Bot) c.getVictim()).state == BotState.GOOD) {
            ((Bot) c.getVictim()).removeFromParent();
        } else if (c.getVictim() instanceof Ladder) {
            this.ladderState = 1;
        } else if (c.getVictim() instanceof LevelExit) {
            this.getDynamicScene().presentScene(new LevelSelectionScene());
        }
    }

    public int getLadderState() {
        return this.ladderState;
    }

    public void setLadderState(int state) {
        this.ladderState = state;
    }

}

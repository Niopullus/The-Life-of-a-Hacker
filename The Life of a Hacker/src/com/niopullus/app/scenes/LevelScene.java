package com.niopullus.app.scenes;

import com.niopullus.NioLib.Main;
import com.niopullus.NioLib.scene.DynamicImageBackground;
import com.niopullus.NioLib.scene.dynscene.CameraNode;
import com.niopullus.NioLib.scene.dynscene.DynamicScene;
import com.niopullus.NioLib.scene.dynscene.Node;
import com.niopullus.NioLib.scene.mapeditorscene.ExitMenu;
import com.niopullus.app.bots.Bot;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Owen on 4/18/2016.
 */
public class LevelScene extends DynamicScene {

    private CameraNode camera;
    private Node followNode;
    private boolean aheld;
    private boolean dheld;
    private boolean wheld;
    private boolean sheld;
    private DynamicImageBackground background;

    public LevelScene() {
        this.camera = new CameraNode();
        this.camera.disableCollisionIn();
        this.camera.disableCollisionOut();
        this.setFollowNode(this.camera);
        this.aheld = false;
        this.dheld = false;
        this.wheld = false;
        this.sheld = false;
        this.background = new DynamicImageBackground(0, 0, Main.Width(), Main.Height(), "background.jpeg");
        this.background.setWindow(new Rectangle(0, 0, 300, 168));
        this.addChildInWorld(this.camera);
        this.setBackground(this.background);
        this.enablePhysics();
        this.camera.enablePhysics();
        this.camera.disableGravity();
        this.camera.setBounds(-5000, -5000, 5000, 5000);
    }

    public void tock() {
        if (this.followNode instanceof Bot) {
            this.moveBot();
        } else if (this.followNode instanceof CameraNode) {
            this.moveCamera();
        }
        if (this.followNode != null) {
            this.background.setXShift(this.followNode.getWX() + 40);
            this.background.setYShift(this.followNode.getWY() + 40);
        }
    }

    private void moveCamera() {
        if (this.aheld) {
            this.followNode.moveX(-15);
            mouseMove();
        } else if (this.dheld) {
            this.followNode.moveX(15);
            mouseMove();
        }
        if (this.wheld) {
            this.followNode.moveY(15);
            mouseMove();
        } else if (this.sheld) {
            this.followNode.moveY(-15);
            mouseMove();
        }
    }

    private void moveBot() {
        Bot bot = (Bot) this.followNode;
        if (this.aheld) {
            if (this.followNode.getXv() - 4 >= -15) {
                this.followNode.accelerateX(-10);
            } else {
                this.followNode.setXv(-15);
            }
        } else if (this.dheld) {
            if (this.followNode.getXv() + 4 <= 15) {
                this.followNode.accelerateX(10);
            } else {
                this.followNode.setXv(15);
            }
        } else if (this.wheld) {
            if (bot.getLadderState() == 1) {
                if (this.followNode.getYv() + 3 <= 5) {
                    this.followNode.accelerateY(3);
                } else {
                    this.followNode.setYv(5);
                }
            }
        } else if (this.sheld) {
            if (bot.getLadderState() == 1) {
                if (this.followNode.getYv() - 3 >= -5) {
                    this.followNode.accelerateY(-3);
                } else {
                    this.followNode.setYv(-5);
                }
            }
        }
        if (((Bot) this.followNode).getFloorTimer() > 0) {
            ((Bot) this.followNode).setFloorTimer(((Bot) this.followNode).getFloorTimer() - 1);
        }
        bot.setLadderState(0);
    }

    public void setFollowNode(Node node) {
        this.followNode = node;
        this.setCamera(node);
    }

    public void keyPress(KeyEvent k) {
        int key = k.getKeyCode();
        if (key == KeyEvent.VK_D) {
            this.dheld = true;
        } else if (key == KeyEvent.VK_A) {
            this.aheld = true;
        } else if (key == KeyEvent.VK_W) {
            this.wheld = true;
        } else if (key == KeyEvent.VK_S) {
            this.sheld = true;
        } else if (key == KeyEvent.VK_ESCAPE) {
            addSubScene(new ExitMenu(this));
        } else if (key == KeyEvent.VK_J) {
            this.setFollowNode(this.camera);
        }
    }

    public final void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();
        if (key == KeyEvent.VK_A) {
            this.aheld = false;
        } else if (key == KeyEvent.VK_D) {
            this.dheld = false;
        } else if (key == KeyEvent.VK_W) {
            this.wheld = false;
        } else if (key == KeyEvent.VK_S) {
            this.sheld = false;
        }
    }

    public Node getFollowNode() {
        return this.followNode;
    }

}

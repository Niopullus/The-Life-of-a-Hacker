package com.niopullus.app.scenes;

import com.niopullus.NioLib.Main;
import com.niopullus.NioLib.scene.DynamicImageBackground;
import com.niopullus.NioLib.scene.Scene;
import com.niopullus.NioLib.scene.dynscene.World;
import com.niopullus.NioLib.scene.guiscene.*;
import com.niopullus.NioLib.scene.guiscene.Label;
import com.niopullus.app.InitScene;
import com.niopullus.app.levels.Level1;

import java.awt.*;
import java.awt.Button;

/**
 * Created by Owen on 4/18/2016.
 */
public class LevelSelectionScene extends GUIScene {

    private DynamicImageBackground background;
    private Label levelSelectLabel;
    private com.niopullus.NioLib.scene.guiscene.Button backButton;

    public LevelSelectionScene() {
        super();
        Theme theme = new Theme();
        theme.setBgColor(new Color(74, 255, 64));
        theme.setBorderColor(new Color(0, 0, 0));
        theme.setSelectedBgColor(new Color(74, 255, 64));
        theme.setSelectedBorderColor(new Color(61, 179, 255));
        background = new DynamicImageBackground(0, 0, Main.Width(), Main.Height(), "background.jpeg");
        background.setWindow(new Rectangle(0, 0, 300, 168));
        background.setxShiftSpeed(0.3);
        setBackground(background);
        levelSelectLabel = new com.niopullus.NioLib.scene.guiscene.Label("Level Selection", 0, 200, 600, 100);
        levelSelectLabel.setFontSize(70);
        levelSelectLabel.setTheme(theme);
        backButton = new com.niopullus.NioLib.scene.guiscene.Button("Back", -400, 200, 100, 40);
        backButton.setFontSize(20);
        backButton.setTheme(theme);
        addElement(levelSelectLabel);
        addElement(backButton);
        int levelNum = 1;
        for (int j = -1; j <= 1; j++) {
            for (int i = -1; i <= 1; i++) {
                com.niopullus.NioLib.scene.guiscene.Button button = new com.niopullus.NioLib.scene.guiscene.Button("" + levelNum, i * 120, -j * 60 - 50, 75, 50);
                button.setFontSize(20);
                button.setTheme(theme);
                button.setBorderWidth(5);
                addElement(button);
                levelNum++;
            }
        }
    }

    public void buttonActivate(int index) {
        LevelScene scene = new LevelScene();
        World world = World.loadWorld("level" + index + ".niolibworld", scene);
        scene.setWorld(world);
        presentScene(scene);
    }

}

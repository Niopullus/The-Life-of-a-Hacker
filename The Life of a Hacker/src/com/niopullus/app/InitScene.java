package com.niopullus.app;

import com.niopullus.NioLib.Main;
import com.niopullus.NioLib.scene.Background;
import com.niopullus.NioLib.scene.DynamicImageBackground;
import com.niopullus.NioLib.scene.ImageBackground;
import com.niopullus.NioLib.scene.guiscene.*;
import com.niopullus.NioLib.scene.guiscene.Button;
import com.niopullus.NioLib.scene.guiscene.Label;
import com.niopullus.NioLib.scene.mapeditorscene.MapEditorScene;
import com.niopullus.NioLib.scene.mapeditorscene.WorldPickScene;
import com.niopullus.app.scenes.HelpScene;
import com.niopullus.app.scenes.LevelSelectionScene;

import java.awt.*;

/**
 * Created by Owen on 4/3/2016.
 */
public class InitScene extends GUIScene {

    private DynamicImageBackground background;
    private Label title;
    private Button playButton;
    private Button helpButton;
    private Button levelEditor;

    public InitScene() {
        Theme theme = new Theme();
        theme.setBgColor(new Color(74, 255, 64));
        theme.setBorderColor(new Color(0, 0, 0));
        theme.setSelectedBgColor(new Color(74, 255, 64));
        theme.setSelectedBorderColor(new Color(61, 179, 255));
        background = new DynamicImageBackground(0, 0, Main.Width(), Main.Height(), "background.jpeg");
        background.setWindow(new Rectangle(0, 0, 300, 168));
        background.setxShiftSpeed(0.3);
        setBackground(background);
        title = new Label("The Life of a Hacker", 0, 200, 700, 120);
        title.setTheme(theme);
        title.setFontSize(70);
        addElement(title);
        playButton = new Button("Play", 0, -50, 400, 70);
        playButton.setTheme(theme);
        helpButton = new Button("Help", 0, -170, 400, 70);
        helpButton.setTheme(theme);
        addElement(helpButton);
        addElement(playButton);
        levelEditor = new Button("Level Editor", 0, -290, 400, 70);
        levelEditor.setTheme(theme);
        addElement(levelEditor);
    }

    public void buttonActivate(int index) {
        if (index == 0) {
            presentScene(new HelpScene());
        } else if (index == 1) {
            presentScene(new LevelSelectionScene());
        } else if (index == 2) {
            presentScene(new WorldPickScene());
        }
    }

}

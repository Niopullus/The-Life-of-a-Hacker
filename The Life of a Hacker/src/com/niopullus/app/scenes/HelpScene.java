package com.niopullus.app.scenes;

import com.niopullus.NioLib.Main;
import com.niopullus.NioLib.scene.DynamicImageBackground;
import com.niopullus.NioLib.scene.guiscene.GUIElement;
import com.niopullus.NioLib.scene.guiscene.GUIScene;
import com.niopullus.NioLib.scene.guiscene.Label;

import java.awt.*;

/**
 * Created by Owen on 4/26/2016.
 */
public class HelpScene extends GUIScene {

    private Label helpLabel;
    private DynamicImageBackground background;

    public HelpScene() {
        super();
        background = new DynamicImageBackground(0, 0, Main.Width(), Main.Height(), "space.png");
        background.setWindow(new Rectangle(0, 0, 448, 280));
        background.setxShiftSpeed(2);
        setBackground(background);
        helpLabel = new Label("Help", 0, 200, 400, 100);
        helpLabel.setFontSize(70);
        helpLabel.setColor(new Color(255, 74, 79));
        helpLabel.setBorderColor(new Color(255, 148, 65));
        addElement(helpLabel);
    }

}

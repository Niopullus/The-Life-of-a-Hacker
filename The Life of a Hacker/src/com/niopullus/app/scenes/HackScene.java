package com.niopullus.app.scenes;

import com.niopullus.NioLib.scene.Background;
import com.niopullus.NioLib.scene.ColorBackground;
import com.niopullus.NioLib.scene.Scene;
import com.niopullus.NioLib.scene.guiscene.*;
import com.niopullus.NioLib.scene.guiscene.Button;
import com.niopullus.NioLib.scene.guiscene.Label;
import com.niopullus.app.InitScene;
import com.niopullus.app.Question;
import com.niopullus.app.QuestionHandler;
import com.niopullus.app.bots.Bot;

import java.awt.*;

/**
 * Created by Owen on 4/28/2016.
 */
public class HackScene extends GUIScene {

    private Question question;
    private Bot bot;
    private LevelScene scene;

    public HackScene(Scene scene, Bot bot) {
        super(scene);
        Background background = new ColorBackground(this.getDx(), this.getDy(), this.getWidth(), this.getHeight(), new Color(61, 179, 255, 180));
        setBackground(background);
        this.bot = bot;
        this.question = QuestionHandler.getQuestion();
        this.scene = (LevelScene) scene;
        Label label = new Label("Hacking", 0, 200, 300, 100);
        Label qlabel = new Label(question.getQuestion(), 0, 0, 1200, 100);
        qlabel.setZ(500);
        for (int i = 0; i < question.getAnswers().size(); i++) {
            Button answerButton = new Button(question.getAnswers().get(i), 0, -300 + i * 70, 500, 50);
            answerButton.setZ(500);
            addElement(answerButton);
        }
        addElement(label);
        addElement(qlabel);
        background.setZ(490);
        label.setZ(500);
    }

    public void buttonActivate(int index) {
        if (index == this.question.getRightAnswerIndex()) {
            this.scene.setFollowNode(this.bot);
            this.bot.setGood();
            this.closeSubScene();
        } else {
            this.closeSubScene();
        }
    }

}

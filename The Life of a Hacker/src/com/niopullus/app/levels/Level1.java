package com.niopullus.app.levels;

import com.niopullus.NioLib.scene.dynscene.World;
import com.niopullus.app.bots.SecurityBot;
import com.niopullus.app.scenes.LevelScene;

/**
 * Created by Owen on 4/26/2016.
 */
public class Level1 extends LevelScene {

    public Level1() {
        super();
        setWorld(World.loadWorld("level1.niolibworld", this));
        SecurityBot bot = new SecurityBot();
        bot.setXScale(0.2);
        bot.setYScale(0.2);
        bot.setPosition(400, 400);
        //addChildInWorld(bot);
        //bot.enablePhysics();
    }

}

package com.niopullus.app;


import com.niopullus.NioLib.scene.dynscene.NodeReference;
import com.niopullus.NioLib.scene.dynscene.tile.MultiTileReference;
import com.niopullus.NioLib.scene.dynscene.tile.TileReference;
import com.niopullus.NioLib.utilities.Utilities;
import com.niopullus.app.bots.CableBot;
import com.niopullus.app.bots.KeyBot;
import com.niopullus.app.bots.SecurityBot;
import com.niopullus.app.tiles.Door;
import com.niopullus.app.tiles.Ladder;
import com.niopullus.app.tiles.LevelExit;
import com.niopullus.app.tiles.Wall;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Owen on 4/7/2016.
 */
public class Config {

    public static final String NIOLIBVERSION = "v0.02";
    public static final int IMGWIDTH = 1920;
    public static final int IMGHEIGHT = 1080;
    public static final double WINDOWSCALE = 0.5;
    public static final String WINDOWTITLE = "The Life of a Hacker";
    public static boolean WINDOWRESIZABLE = true;
    public static final int TILESIZE = 70;
    public static final int NODEPARTSIZE = 100;
    public static final int TILEMAPRAD = 100;
    public static final int NODEPARTRAD = 100;
    public static final int TILEREGIONSIZE = 100;
    public static final String DIRNAME = "The Life of a Hacker";

    public static void init() {
        TileReference.registerTile("wall", "wall.png", 0.3, 0, true, new Wall());
        NodeReference.registerNode("securitybot", 1.2, 1.2, new SecurityBot());
        NodeReference.registerNode("keybot", 1.2, 1.2, new KeyBot());
        NodeReference.registerNode("cablebot", 1.2, 1.2, new CableBot());
        ArrayList<BufferedImage> doorclosedSet = new ArrayList<>();
        doorclosedSet.add(Utilities.loadImage("doorclosed.png"));
        doorclosedSet.add(Utilities.loadImage("doorclosed.png"));
        TileReference.registerMultiTile("door", doorclosedSet, 0.3, 0, true, 1, 2, new Door());
        ArrayList<BufferedImage> doorOpenSet = new ArrayList<BufferedImage>();
        doorOpenSet.add(Utilities.loadImage("wall.png"));
        doorOpenSet.add(Utilities.loadImage("wall.png"));
        ((MultiTileReference) TileReference.getTileRef("door")).addSet(doorOpenSet);
        TileReference.registerTile("ladder", "ladder.png", 0.3, 0, false, new Ladder());
        TileReference.registerTile("levelexit", "levelexit.png", 0.3, 0, false, new LevelExit());
        QuestionHandler.compileQuestions();
    }

}

package com.niopullus.NioLib.scene.dynscene;

import com.niopullus.NioLib.DataPath;
import com.niopullus.NioLib.DataTree;
import com.niopullus.NioLib.scene.Background;
import com.niopullus.NioLib.scene.dynscene.tile.Tilemap;
import com.niopullus.app.Config;
import com.sun.javaws.Launcher;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Owen on 4/13/2016.
 */
public class World implements Serializable {

    private String name;
    private Node universe;
    private PhysicsHandler physicsHandler;
    private Background background;
    private Tilemap fgTilemap;
    private Tilemap bgTilemap;
    private Node camera;
    private int loadMode;

    public World() {

    }

    public String getName() {
        if (this.name == null) {
            return "Unnamed World";
        } else {
            return name;
        }
    }

    public int getLoadMode() {
        return this.loadMode;
    }

    public Node getUniverse() {
        return universe;
    }

    public PhysicsHandler getPhysicsHandler() {
        return physicsHandler;
    }

    public Background getBackground() {
        return background;
    }

    public Tilemap getFgTilemap() {
        return fgTilemap;
    }

    public Tilemap getBgTilemap() {
        return bgTilemap;
    }

    public Node getCamera() {
        return camera;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniverse(Node universe) {
        this.universe = universe;
    }

    public void setPhysicsHandler(PhysicsHandler physicsHandler) {
        this.physicsHandler = physicsHandler;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public void setFgTilemap(Tilemap fgTilemap) {
        this.fgTilemap = fgTilemap;
    }

    public void setBgTilemap(Tilemap bgTilemap) {
        this.bgTilemap = bgTilemap;
    }

    public void setCamera(Node camera) {
        this.camera = camera;
    }

    public static World loadWorld(String fileName) {
        return World.loadWorld(fileName, null);
    }

    public static World loadWorld(String fileName, DynamicScene scene) {
        World result = new World();
        String textData = "";
        String jarPathf = World.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        for (int i = jarPathf.length() - 1; i >= 0; i--) {
            if (jarPathf.charAt(i) == '/') {
                jarPathf = jarPathf.substring(1, i);
                break;
            }
        }
        jarPathf = jarPathf.replace('/', '\\');
        jarPathf = jarPathf.replace("%20", " ");
        File worldFile = new File(jarPathf + "\\" + Config.DIRNAME + "\\worlds\\" + fileName);
        System.out.println("looking at " + jarPathf + "\\" + Config.DIRNAME + "\\worlds\\" + fileName);
        if (!worldFile.exists()) {
            try {
                final String path = "builtinworlds";
                String jarPath = World.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                final File jarFile = new File(jarPath.substring(1));
                if (jarFile.isFile()) {  // Run with JAR file
                    InputStream in = (new Node("temp")).getClass().getClassLoader().getResourceAsStream(path + "/" + fileName);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        textData += line;
                    }
                    reader.close();
                } else { // Run with IDE
                    worldFile = new File(jarPathf + "\\" + Config.DIRNAME + "\\worlds\\" + fileName);
                    if (!worldFile.exists()) {
                        worldFile = new File("Resources\\" + path + "\\" + fileName);
                    }
                    BufferedReader reader = new BufferedReader(new FileReader(worldFile));
                    List<String> lines = Files.readAllLines(worldFile.toPath());
                    reader.close();
                    for (String s : lines) {
                        textData += s;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(worldFile));
                List<String> lines = Files.readAllLines(worldFile.toPath());
                reader.close();
                for (String s : lines) {
                    textData += s;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DataTree data = DataTree.decompress(textData);
        result.setFgTilemap(Tilemap.decompress(new DataTree((ArrayList) data.get(new DataPath(new int[]{0}))), Config.TILESIZE));
        result.setBgTilemap(Tilemap.decompress(new DataTree((ArrayList) data.get(new DataPath(new int[]{1}))), Config.TILESIZE));
        result.setUniverse(Node.decompress(new DataTree((ArrayList) data.get(new DataPath(new int[]{2}))), scene));
        return result;
    }

    public static void saveWorld(World world) {
        DataTree data = new DataTree();
        Tilemap fg = world.getFgTilemap();
        Tilemap bg = world.getBgTilemap();
        Node universe = world.getUniverse();
        data.addData((ArrayList) fg.compress().get());
        data.addData((ArrayList) bg.compress().get());
        data.addData((ArrayList) universe.compress().get());
        String textData = data.compress();
        BufferedWriter writer = null;
        try {
            String jarPath = World.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            for (int i = jarPath.length() - 1; i >= 0; i--) {
                if (jarPath.charAt(i) == '/') {
                    jarPath = jarPath.substring(1, i);
                    break;
                }
            }
            jarPath = jarPath.replace('/', '\\');
            jarPath = jarPath.replace("%20", " ");
            File worldFile = new File(jarPath + "\\" + Config.DIRNAME + "\\worlds\\" + world.getName() + ".niolibworld");
            writer = new BufferedWriter(new FileWriter(worldFile));
            writer.write(textData);
            writer.close();
            System.out.println("saved it:" + jarPath + "\\" + Config.DIRNAME + "\\worlds\\" + world.getName() + ".niolibworld");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

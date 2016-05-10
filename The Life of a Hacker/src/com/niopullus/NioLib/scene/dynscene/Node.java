package com.niopullus.NioLib.scene.dynscene;

import com.niopullus.NioLib.DataPath;
import com.niopullus.NioLib.DataTree;
import com.niopullus.NioLib.UUID;
import com.niopullus.NioLib.utilities.Utilities;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Owen on 3/5/2016.
 */
public class Node implements Comparable<Node>, CollideData, Serializable, Cloneable {

    private UUID id;
    private int x;
    private int y;
    private int z;
    private ArrayList<Node> children;
    private Node parent;
    private boolean isUniverse;
    private boolean isWorld;
    private DynamicScene dynamicScene;
    private PhysicsData physicsData;
    private int[] partRangeX;
    private int[] partRangeY;
    private int width;
    private int height;
    private double xScale;
    private double yScale;
    private int oWidth;
    private int oHeight;
    private int cx;
    private int cy;
    private int cwidth;
    private int cheight;
    private Point movePos;
    private int moveSpeed;
    private double angle;
    private NodeReference reference;
    private DataTree data;

    public Node() {
        this("unnamedNode");
    }

    public Node(String name) {
        this(name, 0, 0);
    }

    public Node(String name, int width, int height) {
        this.children = new ArrayList<Node>();
        this.id = new UUID(name);
        this.physicsData = new PhysicsData();
        this.width = width;
        this.height = height;
        this.partRangeX = new int[]{0,0};
        this.partRangeY = new int[]{0,0};
        this.cx = 0;
        this.cy = 0;
        this.cwidth = 0;
        this.cheight = 0;
        this.reference = Reference.getNodeRef(name);
        this.data = new DataTree();
    }

    public Node clone() {
        try {
            Node result = (Node) super.clone();
            result.id = new UUID(this.id.getName());
            ArrayList<Node> children = new ArrayList<Node>();
            for (Node child : this.children) {
                children.add(child.clone());
            }
            result.children = children;
            result.physicsData = this.physicsData.clone();
            result.data = this.data.clone();
            System.out.println(this.data == null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void tick() {
        for (Node node : this.children) {
            node.tick();
        }
        if (this.movePos != null) {
            double angle = Utilities.invtrig(this.movePos.getY() - this.y, this.movePos.getX() - this.x);
            int newX = (int) (this.x + this.moveSpeed * Math.cos(angle));
            int newY = (int) (this.y + this.moveSpeed * Math.sin(angle));
            if (newX < this.x && newX < this.movePos.getX() || newX > this.x && newX > this.movePos.getX()) {
                this.x = (int) this.movePos.getX();
            } else {
                this.x = newX;
            }
            if (newY < this.y && newY < this.movePos.getY() || newY > this.y && newY > this.movePos.getY()) {
                this.y = (int) this.movePos.getY();
            } else {
                this.y = newY;
            }
        }
        if (this.getPosition().equals(this.movePos)) {
            this.movePos = null;
        }
    }

    public int compareTo(Node node) {
        return this.getId().compareTo(node.getId());
    }

    public void addChild(Node node) {
        node.setParent(this);
        node.setDynamicScene(this.dynamicScene);
        this.children.add(node);
        Collections.sort(this.children);
    }

    public void setParent(Node node) {
        this.parent = node;
    }

    public UUID getId() {
        return this.id;
    }

    public Node getParent() {
        return this.parent;
    }

    public void removeChild(Node node) {
        int index = Collections.binarySearch(this.children, node);
        if (index >= 0) {
            this.children.remove(index);
        }
    }

    public void removeParent() {
        this.parent = null;
    }

    public void removeFromParent() {
        this.parent.removeChild(this);
        this.getDynamicScene().getPhysicsHandler().removePhysicsNode(this);
    }

    public int getChildrenSize() {
        return this.children.size();
    }

    public int getTX() {
        if (this.isUniverse) {
            return this.x;
        } else {
            //System.out.println(this.getName() + (this.parent == null) + "then" + (this.isWorld));
            return this.x + this.parent.getTX();
        }
    }

    public int getTY() {
        if (this.isUniverse) {
            return this.y;
        } else {
            return this.y + this.parent.getTY();
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void markUniverse() {
        this.isUniverse = true;
    }

    public void markWorld() {
        this.isWorld = true;
    }

    public boolean isUniverse() {
        return this.isUniverse;
    }

    public void setDynamicScene(DynamicScene dynamicScene) {
        this.dynamicScene = dynamicScene;
    }

    public DynamicScene getDynamicScene() {
        return this.dynamicScene;
    }

    public void enablePhysics() {
        if (this.dynamicScene != null) {
            this.dynamicScene.addPhysicsNode(this);
        }
        this.physicsData.setEnablePhysics(true);
    }

    public void disablePhysics() {
        this.dynamicScene.removePhysicsNode(this);
        this.physicsData.setEnablePhysics(false);
    }

    public final void drawNode() {
        //System.out.println("my name is " + this.getName() + " and I have " + this.getChildrenSize() + " children");
        //System.out.println("drawing " + this.getName());
        this.draw();
        for (Node node : this.children) {
            node.drawNode();
            //System.out.println("drawing child");
        }
    }

    public void setPosition(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public double getXv() {
        return this.physicsData.getXv();
    }

    public double getYv() {
        return this.physicsData.getYv();
    }

    public double getElasticity() {
        return this.physicsData.getElasticity();
    }

    public double getFriction() {
        return this.physicsData.getFriction();
    }

    public double getMass() {
        return this.physicsData.getMass();
    }

    public double getxStrength() {
        return this.physicsData.getxStrength();
    }

    public double getyStrength() {
        return this.physicsData.getyStrength();
    }

    public double getxSpeedLim() {
        return this.physicsData.getxSpeedLim();
    }

    public double getySpeedLim() {
        return this.physicsData.getySpeedLim();
    }

    public boolean getDoGravity() {
        return this.physicsData.getDoGravity();
    }

    public double getGravityCoefficient() {
        return this.physicsData.getGravityCoefficient();
    }

    public void setXv(double xv) {
        this.physicsData.setXv(xv);
    }

    public void setYv(double yv) {
        this.physicsData.setYv(yv);
    }

    public void setElasticity(double elasticity) {
        this.physicsData.setElasticity(elasticity);
    }

    public void setFriction(double friction) {
        this.physicsData.setFriction(friction);
    }

    public void setMass(double mass) {
        this.physicsData.setMass(mass);
    }

    public void setxStrength(double xStrength) {
        this.physicsData.setxStrength(xStrength);
    }

    public void setyStrength(double yStrength) {
        this.physicsData.setyStrength(yStrength);
    }

    public void setxSpeedLim(double xSpeedLim) {
        this.physicsData.setxSpeedLim(xSpeedLim);
    }

    public void setySpeedLim(double ySpeedLim) {
        this.physicsData.setySpeedLim(ySpeedLim);
    }

    public void disableGravity() {
        this.physicsData.disableGravity();
    }

    public void enableGravity() {
        this.physicsData.enableGravity();
    }

    public void setGravityCoefficient(double coefficient) {
        this.physicsData.setGravityCoefficient(coefficient);
    }

    public void accelerate(double xacc, double yacc) {
        this.physicsData.accelerate(xacc, yacc);
    }

    public void accelerateX(double acceleration) {
        this.physicsData.accelerateX(acceleration);
    }

    public void accelerateY(double acceleration) {
        this.physicsData.accelerateY(acceleration);
    }

    public void moveX(int deltaX) {
        this.setX(this.x + deltaX);
    }

    public void moveX() {
        this.setX(this.x + (int) this.physicsData.getXv());
    }

    public void moveY(int deltaY) {
        this.setY(this.y + deltaY);
    }

    public void moveY() {
        this.setY(this.y + (int) this.physicsData.getYv());
    }

    public void move(int deltaX, int deltaY) {
        this.moveX(deltaX);
        this.moveY(deltaY);
    }

    public void setColEast(HalfCollision halfCollision) {
        this.physicsData.setColEast(halfCollision);
    }

    public void setColWest(HalfCollision halfCollision) {
        this.physicsData.setColWest(halfCollision);
    }

    public void setColNorth(HalfCollision halfCollision) {
        this.physicsData.setColNorth(halfCollision);
    }

    public void setColSouth(HalfCollision halfCollision) {
        this.physicsData.setColSouth(halfCollision);
    }

    public HalfCollision getColEast() {
        return this.physicsData.getColEast();
    }

    public HalfCollision getColWest() {
        return this.physicsData.getColWest();
    }

    public HalfCollision getColNorth() {
        return this.physicsData.getColNorth();
    }

    public HalfCollision getColSouth() {
        return this.physicsData.getColSouth();
    }

    public int getMaxX() {
        return this.getX() + this.getWidth();
    }

    public int getMinX() {
        return this.getX();
    }

    public int getMaxY() {
        return this.getY() + this.getHeight();
    }

    public int getMinY() {
        return this.getY();
    }

    public int getMidX() {
        return this.getX() + this.getWidth() / 2;
    }

    public int getMidY() {
        return this.getY() + this.getHeight() / 2;
    }

    public int getTMinX() {
        return this.getTX();
    }

    public int getTMinY() {
        return this.getTY();
    }

    public int getTMaxX() {
        return this.getTX() + this.getWidth();
    }

    public int getTMaxY() {
        return this.getTY() + this.getHeight();
    }

    public int getTMidX() {
        return this.getTX() + this.getWidth() / 2;
    }

    public int getTMidY() {
        return this.getTY() + this.getHeight() / 2;
    }

    public void setPartRangeX(int[] rangeX) {
        this.partRangeX = rangeX;
    }

    public void setPartRangeY(int[] rangeY) {
        this.partRangeY = rangeY;
    }

    public int[] getPartRangeX() {
        return this.partRangeX;
    }

    public int[] getPartRangeY() {
        return this.partRangeY;
    }

    public boolean getCollidableIn() {
        return this.physicsData.getCollidableIn();
    }

    public void enableCollisionIn() {
        this.physicsData.enableCollisionIn();
    }

    public void disableCollisionIn() {
        this.physicsData.disableCollisionIn();
    }

    public boolean getCollidableOut() {
        return physicsData.getCollidableOut();
    }

    public void enableCollisionOut() {
        this.physicsData.enableCollisionOut();
    }

    public void disableCollisionOut() {
        this.physicsData.disableCollisionOut();
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setXScale(double xScale) {
        this.xScale = xScale;
        this.width = (int) (this.oWidth * xScale);
    }

    public void setYScale(double yScale) {
        this.yScale = yScale;
        this.height = (int) (this.oHeight * yScale);
    }

    public void csetXScale(double xScale) {
        this.xScale = xScale;
    }

    public void csetYScale(double yScale) {
        this.yScale = yScale;
    }

    public double getXScale() {
        return this.xScale;
    }

    public double getYScale() {
        return this.yScale;
    }

    public void setOWidth(int width) {
        this.oWidth = width;
    }

    public void setOHeight(int height) {
        this.oHeight = height;
    }

    public int getOWidth() {
        return this.oWidth;
    }

    public int getOHeight() {
        return this.oHeight;
    }

    public void draw() {

    }

    public Point getPosition() {
        return new Point(this.getX(), this.getY());
    }

    public Point getRPosition() {
        return new Point(this.x, this.y);
    }

    public String getName() {
        return this.id.getName();
    }

    public void setCX(int cx) {
        this.cx = cx;
    }

    public void setCY(int cy) {
        this.cy = cy;
    }

    public void setCWidth(int cwidth) {
        this.cwidth = cwidth;
    }

    public void setCHeight(int cheight) {
        this.cheight = cheight;
    }

    public void addCX(int delta) {
        this.cx += delta;
    }

    public void addCY(int delta) {
        this.cy += delta;
    }

    public void addCWidth(int delta) {
        this.cwidth += delta;
    }

    public void addCHeight(int delta) {
        this.cheight += delta;
    }

    public int getCx() {
        return this.getWX() + this.cx;
    }

    public int getCy() {
        return this.getWY() + this.cy;
    }

    public int getCwidth() {
        return this.width + this.cwidth;
    }

    public int getCheight() {
        return this.height + this.cheight;
    }

    public int getCMinX() {
        return this.getCx();
    }

    public int getCMinY() {
        return this.getCy();
    }

    public int getCMaxX() {
        return this.getCx() + this.getCwidth();
    }

    public int getCMaxY() {
        return this.getCy() + this.getCheight();
    }

    public int getCMidX() {
        return this.getCx() + this.getCwidth() / 2;
    }

    public int getCMidY() {
        return this.getCy() + this.getCheight() / 2;
    }

    public void oMoveTo(int x, int y, int speed) {
        this.movePos = new Point(x, y);
        this.moveSpeed = speed;
    }

    public void rotate(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return this.angle;
    }

    public final void oSetX(int x) {
        this.x = x;
    }

    public final void oSetY(int y) {
        this.y = y;
    }

    public int getWX() {
        if (this.isWorld || this.isUniverse()) {
            return 0;
        } else {
            return this.parent.getWX() + this.x;
        }
    }

    public int getWY() {
        if (this.isWorld || this.isUniverse()) {
            return 0;
        } else {
            return this.parent.getWY() + this.y;
        }
    }

    public Rectangle getRect() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public Rectangle getCRect() {
        return new Rectangle(getCx(), getCy(), getCwidth(), getCheight());
    }

    public Rectangle getWRect() {
        return new Rectangle(getWX(), getWY(), getWidth(), getHeight());
    }

    public void clickedOn() {

    }

    public double distanceTo(Point p) {
        return Math.sqrt(Math.pow(p.x - (this.x + this.width / 2), 2) + Math.pow(p.y - (this.y + this.height / 2), 2));
    }

    public double distanceToW(Point p) {
        return Math.sqrt(Math.pow(p.x - (this.getWX() + this.width / 2), 2) + Math.pow(p.y - (this.getWY() + this.height / 2), 2));
    }

    public double distanceToW(Node node) {
        return Math.sqrt(Math.pow(node.getWX() - (this.getWX() + this.width / 2), 2) + Math.pow(node.getWY() - (this.getWY() + this.height / 2), 2));
    }

    public void causerCollision(Collision collision) {

    }

    public void victimCollision(Collision collision) {

    }

    public DataTree compress() {
        System.out.println("compressing " + this.getName());
        DataTree result = new DataTree();
        if (this.reference != null) {
            result.addData(this.reference.getId());
        } else {
            result.addData(-1);
            System.out.println("gotta null reference!");
        }
        result.addData((ArrayList) this.data.get());
        result.addFolder();
        result.addData(this.x, new DataPath(new int[]{2}));
        result.addData(this.y, new DataPath(new int[]{2}));
        result.addData((ArrayList) (this.physicsData.compress()).get(), new DataPath(new int[]{2}));
        int toAdd;
        if (this.isUniverse()) {
            toAdd = 2;
        } else if (this.isWorld) {
            toAdd = 1;
        } else {
            toAdd = 0;
        }
        result.addData(toAdd, new DataPath(new int[]{2}));
        result.addFolder();
        for (Node node : this.children) {
            result.addData((ArrayList) node.compress().get(), new DataPath(new int[]{3}));
        }
        return result;
    }

    public static Node decompress(DataTree data) {
        return Node.decompress(data, null);
    }

    public void setName(String name) {
        this.id = new UUID(name);
    }

    public static Node decompress(DataTree data, DynamicScene scene) {
        int id = (Integer) data.get(new DataPath(new int[]{0}));
        DataTree nodeData = new DataTree((ArrayList) data.get(new DataPath(new int[]{1})));
        int x = (Integer) data.get(new DataPath(new int[]{2, 0}));
        int y = (Integer) data.get(new DataPath(new int[]{2, 1}));
        PhysicsData physicsData = PhysicsData.decompress(new DataTree((ArrayList) data.get(new DataPath(new int[]{2, 2}))));
        int state = (Integer) data.get(new DataPath(new int[]{2, 3}));
        ArrayList children = (ArrayList) data.get(new DataPath(new int[]{3}));
        ArrayList<Node> decompressedChildren = new ArrayList<Node>();
        for (Object child : children) {
            Node node = Node.decompress(new DataTree((ArrayList) child), scene);
            decompressedChildren.add(node);
            //System.out.println("found child: " + node.getName());
        }
        NodeReference ref = NodeReference.getNodeRef(id);
        Node node;
        if (ref == null) {
            node = new Node();
        } else {
            node = ref.getSample().clone();
            node.setXScale(ref.getDefaultXScale());
            node.setYScale(ref.getDefaultYScale());
        }
        node.data = nodeData;
        node.reference = NodeReference.getNodeRef(id);
        node.dynamicScene = scene;
        for (Node kid : decompressedChildren) {
            node.addChild(kid);
        }
        node.x = x;
        node.y = y;
        switch (state) {
            case 1: node.markWorld(); node.setName("world"); break;
            case 2: node.markUniverse(); node.setName("universe"); break;
        }
        PhysicsHandler physicsHandler = null;
        if (scene != null) {
             physicsHandler = scene.getPhysicsHandler();
        }
        System.out.println("decompressing " + node.getName() + " and I found it to have an ID of " + id + " and a pos of " + node.getPosition());
        if (physicsHandler != null && physicsData.doEnablePhysics()) {
            physicsHandler.addPhysicsNode(node);
        }
        return node;
    }

    public void setReference(NodeReference ref) {
        this.reference = ref;
    }

    public boolean hasReference() {
        return this.reference != null;
    }

    public Node getChild(int index) {
        return this.children.get(index);
    }

    public boolean getPhysicsEnabled() {
        return this.physicsData.doEnablePhysics();
    }

}

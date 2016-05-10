package com.niopullus.NioLib.scene.dynscene;

import com.niopullus.NioLib.DataPath;
import com.niopullus.NioLib.DataTree;
import com.niopullus.app.bots.Bot;

import java.io.Serializable;

/**
 * Created by Owen on 3/10/2016.
 */
public class PhysicsData implements Serializable, Cloneable {

    public static final double DEFAULT_ELASTICITY = 0.0;
    public static final double DEFAULT_FRICTION = 0.2;
    public static final double DEFAULT_MASS = 10;
    public static final double DEFAULT_XSTRENGTH = 10;
    public static final double DEFAULT_YSTRENGTH = 10;
    public static final double DEFAULT_XSPEEDLIMIT = 30;
    public static final double DEFAULT_YSPEEDLIMIT = 30;
    private double xv;
    private double yv;
    private double elasticity;
    private double friction;
    private double mass;
    private double xStrength;
    private double yStrength;
    private double xSpeedLim;
    private double ySpeedLim;
    private double gravityCoefficient;
    private boolean doGravity;
    private HalfCollision colEast;
    private HalfCollision colWest;
    private HalfCollision colNorth;
    private HalfCollision colSouth;
    private boolean collidablein;
    private boolean collidableout;
    private boolean enablePhysics;

    public PhysicsData() {
        this.xv = 0;
        this.yv = 0;
        this.elasticity = DEFAULT_ELASTICITY;
        this.friction = DEFAULT_FRICTION;
        this.mass = DEFAULT_MASS;
        this.xStrength = DEFAULT_XSTRENGTH;
        this.yStrength = DEFAULT_YSTRENGTH;
        this.xSpeedLim = DEFAULT_XSPEEDLIMIT;
        this.ySpeedLim = DEFAULT_YSPEEDLIMIT;
        this.gravityCoefficient = 1.0;
        this.doGravity = true;
        this.collidablein = true;
        this.collidableout = false;
        this.enablePhysics = false;
    }

    public double getXv() {
        return xv;
    }

    public double getYv() {
        return yv;
    }

    public double getElasticity() {
        return elasticity;
    }

    public double getFriction() {
        return friction;
    }

    public double getMass() {
        return mass;
    }

    public double getxStrength() {
        return xStrength;
    }

    public double getyStrength() {
        return yStrength;
    }

    public double getxSpeedLim() {
        return xSpeedLim;
    }

    public double getySpeedLim() {
        return ySpeedLim;
    }

    public boolean getDoGravity() {
        return this.doGravity;
    }

    public double getGravityCoefficient() {
        return this.gravityCoefficient;
    }

    public void setXv(double xv) {
        this.xv = xv;
    }

    public void setYv(double yv) {
        this.yv = yv;
    }

    public void setElasticity(double elasticity) {
        this.elasticity = elasticity;
    }

    public void setFriction(double friction) {
        this.friction = friction;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setxStrength(double xStrength) {
        this.xStrength = xStrength;
    }

    public void setyStrength(double yStrength) {
        this.yStrength = yStrength;
    }

    public void setxSpeedLim(double xSpeedLim) {
        this.xSpeedLim = xSpeedLim;
    }

    public void setySpeedLim(double ySpeedLim) {
        this.ySpeedLim = ySpeedLim;
    }

    public void enableGravity() {
        this.doGravity = true;
    }

    public void disableGravity() {
        this.doGravity = false;
    }

    public void setGravityCoefficient(double coefficient) {
        this.gravityCoefficient = coefficient;
    }

    public void accelerateX(double acceleration) {
        if (Math.abs(this.xv + acceleration) <= this.xSpeedLim) {
            this.xv += acceleration;
        } else {
            if (this.xv + acceleration > 0) {
                this.xv = this.xSpeedLim;
            } else {
                this.xv = -this.xSpeedLim;
            }
        }
    }

    public void accelerateY(double acceleration) {
        if (Math.abs(this.yv + acceleration) <= this.ySpeedLim) {
            this.yv += acceleration;
        } else {
            if (this.yv + acceleration > 0) {
                this.yv = this.ySpeedLim;
            } else {
                this.yv = -this.ySpeedLim;
            }
        }
    }

    public void accelerate(double xacc, double yacc) {
        this.accelerateX(xacc);
        this.accelerateY(yacc);
    }

    public void setColEast(HalfCollision halfCollision) {
        this.colEast = halfCollision;
    }

    public void setColWest(HalfCollision halfCollision) {
        this.colWest = halfCollision;
    }

    public void setColNorth(HalfCollision halfCollision) {
        this.colNorth = halfCollision;
    }

    public void setColSouth(HalfCollision halfCollision) {
        this.colSouth = halfCollision;
    }

    public HalfCollision getColEast() {
        return this.colEast;
    }

    public HalfCollision getColWest() {
        return this.colWest;
    }

    public HalfCollision getColNorth() {
        return this.colNorth;
    }

    public HalfCollision getColSouth() {
        return this.colSouth;
    }

    public boolean getCollidableIn() {
        return this.collidablein;
    }

    public void enableCollisionIn() {
        this.collidablein = true;
    }

    public void disableCollisionIn() {
        this.collidablein = false;
    }

    public boolean getCollidableOut() {
        return this.collidableout;
    }

    public void enableCollisionOut() {
        this.collidableout = true;
    }

    public void disableCollisionOut() {
        this.collidableout = false;
    }

    public boolean doEnablePhysics() {
        return this.enablePhysics;
    }

    public void setEnablePhysics(boolean enablePhysics) {
        this.enablePhysics = enablePhysics;
    }

    public void setDoGravity(boolean doGravity) {
        this.doGravity = doGravity;
    }

    public void setCollidablein(boolean collidablein) {
        this.collidablein = collidablein;
    }

    public void setCollidableout(boolean collidableout) {
        this.collidableout = collidableout;
    }

    public DataTree compress() {
        DataTree data = new DataTree();
        data.addData(this.enablePhysics);
        data.addData(this.xv);
        data.addData(this.yv);
        data.addData(this.elasticity);
        data.addData(this.friction);
        data.addData(this.mass);
        data.addData(this.xStrength);
        data.addData(this.yStrength);
        data.addData(this.xSpeedLim);
        data.addData(this.ySpeedLim);
        data.addData(this.gravityCoefficient);
        data.addData(this.doGravity);
        data.addData(this.collidablein);
        data.addData(this.collidableout);
        return data;
    }

    public static PhysicsData decompress(DataTree data) {
        PhysicsData result = new PhysicsData();
        result.setEnablePhysics((Boolean) data.get(new DataPath(new int[]{0})));
        result.setXv((Double) data.get(new DataPath(new int[]{1})));
        result.setYv((Double) data.get(new DataPath(new int[]{2})));
        result.setElasticity((Double) data.get(new DataPath(new int[]{3})));
        result.setFriction((Double) data.get(new DataPath(new int[]{4})));
        result.setMass((Double) data.get(new DataPath(new int[]{5})));
        result.setxStrength((Double) data.get(new DataPath(new int[]{6})));
        result.setyStrength((Double) data.get(new DataPath(new int[]{7})));
        result.setxSpeedLim((Double) data.get(new DataPath(new int[]{8})));
        result.setySpeedLim((Double) data.get(new DataPath(new int[]{9})));
        result.setGravityCoefficient((Double) data.get(new DataPath(new int[]{10})));
        result.setDoGravity((Boolean) data.get(new DataPath(new int[]{11})));
        result.setCollidablein((Boolean) data.get(new DataPath(new int[]{12})));
        result.setCollidableout((Boolean) data.get(new DataPath(new int[]{13})));
        return result;
    }

    public PhysicsData clone() {
        try {
            return (PhysicsData) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

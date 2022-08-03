package Physics;

import Game.Rigidbody;

import java.awt.*;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.Vector;

public class MathVector extends Vector<Double> {

    public MathVector(Double x, Double y){
        super();
        add(x);
        add(y);
    }

    public MathVector(Double x, Double y, int initialCapacity, int capacityIncrement){
        super(initialCapacity, capacityIncrement);
        addElement(x);
        addElement(y);
    }

    public MathVector(){super();}


    public MathVector add(MathVector v2){
        if (v2.size() != this.size()){
            throw new InvalidParameterException("MathVector addition must have the same size");
        }

        MathVector result  = new MathVector();
        for (int i=0; i<this.size(); i++){
            result.addElement(this.elementAt(i)+v2.elementAt(i));
        }
        return result;
    }

    public MathVector add(Dimension v2){
        if (this.size() != 2){
            throw new InvalidParameterException("MathVector addition must have the same size as Dimension");
        }
        return new MathVector(this.getX()+v2.width, this.getY()+v2.height);
    }

    public MathVector sub(MathVector v2){
        if (v2.size() != this.size()){
            throw new InvalidParameterException("MathVector subtraction must have the same size");
        }
        MathVector result  = new MathVector();
        v2 = v2.mult((Double)(-1.0));
        for (int i=0; i<this.size(); i++){
            result.addElement(this.elementAt(i)-v2.elementAt(i));
        }
        return result;
    }

    public double dotDouble(MathVector v2){
        if (v2.size() != this.size()){
            throw new InvalidParameterException("MathVector addition must have the same size");
        }
        double result = 0;
        for (int i = 0; i < size();  i++){
            result += this.elementAt(i)*v2.elementAt(i);
        }
        return result;
    }

    public int dotInt(MathVector v2){
        if (v2.size() != this.size()){
            throw new InvalidParameterException("MathVector addition must have the same size");
        }
        int result = 0;
        for (int i = 0; i < size();  i++){
            result += this.elementAt(i).intValue()*v2.elementAt(i).intValue();
        }
        return result;
    }

    public MathVector mult(Double val){
        MathVector result = new MathVector();
        for (Double i : this){
            result.addElement((i*val));
        }
        return result;
    }

    public MathVector div(Double val){
        MathVector result = new MathVector();
        for (Double i : this){
            result.addElement((i/val));
        }
        return result;
    }

    public MathVector negative(){
        MathVector result = new MathVector();
        for (int i=0;  i<this.size(); i++){
            result.addElement(-this.elementAt(i));
        }
        return result;
    }

    // Setters and Getters

    public void setX(Double newx){this.setElementAt(newx, 0);}
    public void setY(Double newy){this.setElementAt(newy, 1);}
    public void setZ(Double newz){this.setElementAt(newz, 2);}
    public void setW(Double neww){this.setElementAt(neww, 3);}

    public Double getX(){return this.elementAt(0);}
    public Double getY(){return this.elementAt(1);}
    public Double getZ(){return this.elementAt(2);}
    public Double getW(){return this.elementAt(3);}

    // Swaps the elements at index with another MathVector, takes advantage of java pass by reference
    public void swap(MathVector v2, int index){
        Double buffer = this.get(index);
        this.set(index, v2.elementAt(index));
        v2.set(index, buffer);
    }

    public void swap(MathVector v2, int index1, int index2){
        Double buffer = this.get(index1);
        this.set(index1, v2.elementAt(index2));
        v2.set(index2, buffer);
    }

}
package Physics;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.Vector;

public class MathVector extends Vector<Integer> {

    public MathVector(Integer x, Integer y){
        super();
        add(x);
        add(y);
    }

    public MathVector(Integer x, Integer y, int initialCapacity, int capacityIncrement){
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

    public MathVector sub(MathVector v2){
        if (v2.size() != this.size()){
            throw new InvalidParameterException("MathVector subtraction must have the same size");
        }
        MathVector result  = new MathVector();
        v2 = v2.mult((Integer)(Integer)(-1));
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
            result += this.elementAt(i).doubleValue()*v2.elementAt(i).doubleValue();
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

    public MathVector mult(Integer val){
        MathVector result = new MathVector();
        for (int i=0; i<size(); i++){
            result.addElement((this.elementAt(i)*val));
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

    public void setX(Integer newx){this.setElementAt(newx, 0);}
    public void setY(Integer newy){this.setElementAt(newy, 1);}
    public void setZ(Integer newz){this.setElementAt(newz, 2);}
    public void setW(Integer neww){this.setElementAt(neww, 3);}

    public Integer getX(){return this.elementAt(0);}
    public Integer getY(){return this.elementAt(1);}
    public Integer getZ(){return this.elementAt(2);}
    public Integer getW(){return this.elementAt(3);}

}
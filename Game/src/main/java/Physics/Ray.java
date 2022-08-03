package Physics;

public class Ray {
    public MathVector origin;
    public MathVector direction;


    // Constructors
    public Ray(MathVector origin, MathVector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray() {
        origin = new MathVector();
        direction = new MathVector(1.0, 0.0);
    }

    // Setters and Getters
    public MathVector getOrigin() {
        return origin;
    }
    public void setOrigin(MathVector origin) {
        this.origin = origin;
    }
    public MathVector getDirection() {
        return direction;
    }
    public void setDirection(MathVector direction) {
        this.direction = direction;
    }


    @Override
    public String toString() {
        return "Ray{" +
                "origin=" + origin +
                ", direction=" + direction +
                '}';
    }


    // Gets position at "time" t
    MathVector getPositionAtT(double t){
        return origin.add(direction.mult(t));
    }
}

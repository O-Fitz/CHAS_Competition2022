package Game;
import Physics.MathVector;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Entity {
    
    public Enemy() {
        super();
    }

    public Enemy(int maxHealth){
        super(maxHealth);
    }

    public Enemy(Dimension size, int maxHealth) {
        super(size, maxHealth);
    }

    public Enemy(Dimension size, MathVector position, int maxHealth) {
        super(size, position, maxHealth);
    }

    public Enemy(Dimension size, MathVector position, MathVector velocity, int maxHealth) {
        super(size, position, velocity, maxHealth);
    }

}

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends GameObject {

    private int speed = 200;
    
    public Player(double x, double y){
        super(x, y, 50, 50, "assets\\fish.png");
    }

    @Override
    public void move(double deltaTime){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            setX(getX() - (speed * deltaTime));
        } if (Gdx.input.isKeyPressed(Input.Keys.D)){
            setX(getX() + (speed * deltaTime));
        } if (Gdx.input.isKeyPressed(Input.Keys.W)){
            setY(getY() + (speed * deltaTime));
        } if (Gdx.input.isKeyPressed(Input.Keys.S)){
            setY(getY() - (speed * deltaTime));
        }

    }

}

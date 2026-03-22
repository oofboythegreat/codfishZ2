import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Enemy extends GameObject {

    private double speed = 100;
    private int IQ = 1;
    private boolean agro = false;
    private int time = 0;

    public Enemy(int x, int y, int width, int height, String imagePath){
        super(x, y, width, height, imagePath);
    }

    
    public void setAgro(boolean agro){
        this.agro = agro;
    }

    @Override
    public boolean getAgro(){
        return agro;
    }

    public int getTime(){
        return time;
    }
    public void setTime(int t){
        time = t;
    }

    @Override
    public void move(double deltaTime, double targetX, double targetY, GameObject player){
        //double distance = Math.sqrt(Math.pow(Math.abs(targetY-getY()), 2.0)+Math.pow(Math.abs(targetX-getX()), 2.0));
        if(getDistance(player) < 200*IQ || agro){
            agro = true;
            setImage("assets\\\\red-dot.png");
            double smart = Math.random()*10*IQ;
            if(smart > 5){
                if(getX()<targetX){
                    setX(getX() + (speed/2 * deltaTime));
                }
                if(getY()<targetY){
                    setY(getY() + (speed/2 * deltaTime));
                }
                if(getX()>targetX){
                    setX(getX() - (speed/2 * deltaTime));
                }
                if(getY()>targetY){
                    setY(getY() - (speed/2 * deltaTime));
                }
            }
        }else{
            agro = false;
        }
        double randX = Math.random()*speed*2 - speed;
        double randY = Math.random()*speed*2 - speed;
        if(agro){
            setX(getX() + randX* deltaTime);
            setY(getY() - randY* deltaTime);
        }
    }
}

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MyGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private ArrayList<GameObject> activeObjects;
    private Player player;
    private Texture china;

    @Override
    public void create() {
        batch = new SpriteBatch();
        activeObjects = new ArrayList<GameObject>();

        // TODO 3: Instantiate your Player subclass and add it to activeObjects.

        player = new Player(0, 0);
        activeObjects.add(player);
        china = new Texture("assets\\china.png");
        

        // TODO 4: Write a for-loop to instantiate 5 Enemy objects at different 
        //         starting Y-coordinates and add them to activeObjects.
        int startingY = 100;
        for(int i = 0; i < 5; i++){
            activeObjects.add(new Enemy((int)(Math.random()*800), (int)(Math.random()*800), 50, 50, "assets\\dot.png"));
        }
    }

    //render() is the game loop, called approx 60 times per second

    @Override
    public void render() {
    
        // Boilerplate: Clear the screen to black each frame
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // --- AP REVIEW: CASTING ---
        // Gdx.graphics.getDeltaTime() returns a float. 
        // We cast it to a double to stay strictly within the AP CSA Java standards.
        double deltaTime = (double) Gdx.graphics.getDeltaTime();

        // --- AP REVIEW: POLYMORPHISM ---
        // TODO 5: Write a standard or enhanced for-loop to iterate through activeObjects.
        // For each object, call its move() method.

        for(GameObject game : activeObjects){
            game.move(deltaTime);
        }

        
        //Note: Anything drawn must be between .begin() and .end()
        batch.begin();
        for(int i = 0; i<800;i+=200){
            for(int j = 0; j<800;j+=200){
                batch.draw(china, i, j);
            }
        }
        

        // TODO 6: Write a loop to iterate through activeObjects and call draw(batch).
        for(GameObject game : activeObjects){
            game.draw(batch);
        }


        batch.end();

        // --- AP REVIEW: ARRAYLIST TRAVERSAL & REMOVAL ---
        // TODO 7: Write collision logic. 
        // You must iterate through the list to check if the player overlaps with enemies.
        // See the cheat sheet for the overlap method!
        // NOTE: If you are removing items from an ArrayList, how must you structure 
        // your for-loop to avoid skipping elements?

        for(int i = activeObjects.size() - 1; i >= 0; i--){
            if(activeObjects.get(i) instanceof Enemy){
                activeObjects.get(i).move(deltaTime, player.getX(), player.getY(), player);
                for(int b = activeObjects.size() - 1; b >= 0; b--){
                    if(activeObjects.get(b) instanceof Enemy){
                        if(activeObjects.get(i).getDistance(activeObjects.get(b))<200){
                            if(activeObjects.get(b).getAgro()){
                                activeObjects.get(i).setTime(activeObjects.get(i).getTime()+1);
                                if(activeObjects.get(i).getTime() == 120 ){
                                    activeObjects.get(i).setAgro(true);
                                    activeObjects.get(i).setTime(0);
                                }
                            }
                        }
                    }
                }
                if(player.getHibox().overlaps(activeObjects.get(i).getHibox())){
                    activeObjects.remove(i);
                    activeObjects.add(new Enemy((int)(Math.random()*800), (int)(Math.random()*800), 50, 50, "assets\\dot.png"));
                } 
            }
        }

    }
    
    @Override
    public void dispose() {
        batch.dispose();
    }
}
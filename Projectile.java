import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
public class Projectile extends Obstacle
{
    private final int velocity = 2;
    private int xSpeed, ySpeed, damage;

    public Projectile(Rectangle shape, int direction, int damage)
    {
        super();
        setCollision(false);
        this.damage = damage;
        xLoc = (int) shape.getX();
        yLoc = (int) shape.getY();
        bounds.add(shape);
        if (direction == Constants.NORTH)
            ySpeed = -velocity;
        if (direction == Constants.SOUTH)
            ySpeed = velocity;
        if (direction == Constants.EAST)
            xSpeed = velocity;
        if (direction == Constants.WEST)
            xSpeed = -velocity;
    }

    public void doPlayerEffect(Player p)
    {
        p.addHealth(-damage);
    }

    public void updateGameState(ArrayList<GamePiece> entities)
    {
        for (int i = entities.size() - 1; i >= 0; i--)
        {
            GamePiece g = entities.get(i);
            if (g == this)
            {
                continue;
            }
            if (collide(g) || Utility.outOfScreen(xLoc, yLoc))
            {
                if (g instanceof Player)
                {
                    doPlayerEffect((Player) g);
                }
                System.out.println("removed");
                entities.remove(this);
            }
        }
        xLoc += xSpeed;
        yLoc += ySpeed;
        updateBoundingBox(xSpeed, ySpeed);	
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        super.drawBounds(g);
    }
}
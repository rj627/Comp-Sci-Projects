
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
//import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;
import info.gridworld.grid.Location;
import java.util.Random;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BugRunner
{
    public static void main(String[] args)
    {
    	Grid<Actor> grid = new BoundedGrid<Actor>(50,50);
    	Random randomNum = new Random();
        ActorWorld world = new ActorWorld(grid);
        Location loc = new Location(randomNum.nextInt(50), randomNum.nextInt(50));   
        int turns[] = new int[278];
        for (int i = 0; i < turns.length; i++)
        {
        	turns[i] = randomNum.nextInt(8);
        }
        world.add(loc, new DancingBug(turns));
        loc = new Location(randomNum.nextInt(50), randomNum.nextInt(50));
        world.add(loc,new Rock());
        world.show();
        System.out.println(world.getGrid().getOccupiedLocations());
        System.out.println(turns.toString());
    }
}

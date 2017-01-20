package attack;

import battlecode.common.*;

import static attack.ArchonBot.*;
import static attack.RobotPlayer.*;

/**
 * Created by nisar on 11-01-2017.
 */
public class GardenerBot {

    static Direction[] directions={Direction.getEast(),Direction.getNorth(),Direction.getWest(),Direction.getSouth()};
    static void runGardener() throws GameActionException {
        System.out.println("I'm a gardener!");

        // The code you want your robot to perform every round should be in this loop
        while (true) {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {

                // Listen for home archon's location
              //  int prev = rc.readBroadcast(GARDENER_CHANNEL);
              //  rc.broadcast(GARDENER_CHANNEL, prev + 1);
                int xPos = rc.readBroadcast(0);
                int yPos = rc.readBroadcast(1);
                MapLocation archonLoc = new MapLocation(xPos, yPos);

                // Generate a random direction
                Direction dir = randomDirection();
//
//                if (rc.getLocation().distanceSquaredTo(archonLoc)< 10) {
//                    while (rc.getLocation().distanceSquaredTo(archonLoc)<= 10)
//                        tryMove(randomDirection());
//                }
//                // Randomly attempt to build a soldier or lumberjack in this direction
//                else {
//                    for (Direction d : directions) {
//                        if (rc.canPlantTree(d)) {
//                            rc.plantTree(d);
//                        }
//                    }
//                    TreeInfo[] trees = rc.senseNearbyTrees();
//                    for (TreeInfo t : trees) {
//                        if (rc.canWater(t.location)) {
//                            rc.water(t.location);
//                        }
//                        t.getContainedBullets();
//                        t.getContainedRobot();
//                    }
                // if (rc.getRoundNum() < 3000) {
                int prev = rc.readBroadcast(GARDENER_CHANNEL);
                int prevNumSol=rc.readBroadcast(SOLDIER_CHANNEL);
                rc.broadcast(GARDENER_CHANNEL, prev+1);
              //  Direction dir = randomDirection();
                if(rc.getLocation().isWithinDistance(archonLoc,8)){
                    while(rc.getLocation().isWithinDistance(archonLoc,8))
                        if(rc.canMove(rc.getLocation().directionTo(archonLoc).opposite()))
                            tryMove(rc.getLocation().directionTo(archonLoc).opposite());
                        else if(rc.canMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90)))
                        {tryMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90));}
                        else
                            tryMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90));
                }
                else if (rc.getRoundNum() < 3000) {
                    int prevNumGard = rc.readBroadcast(LUMBERJACK_CHANNEL);
                    if (prevNumGard <= LUMBERJACK_MAX && rc.canBuildRobot(RobotType.LUMBERJACK, dir)) {
                        rc.buildRobot(RobotType.LUMBERJACK, dir);
                        rc.broadcast(LUMBERJACK_CHANNEL, prevNumGard + 1);
                    }
                    else  if (prevNumSol<=SOLDIER_MAX ) {
                        if(rc.canBuildRobot(RobotType.SOLDIER, rc.getLocation().directionTo(archonLoc).opposite()))  {rc.buildRobot(RobotType.SOLDIER, rc.getLocation().directionTo(archonLoc).opposite());
                            rc.broadcast(SOLDIER_CHANNEL,prevNumSol+1);
                        }
                      else if(rc.canBuildRobot(RobotType.SOLDIER, Direction.getEast()))  {rc.buildRobot(RobotType.SOLDIER, Direction.getEast());
                          rc.broadcast(SOLDIER_CHANNEL,prevNumSol+1);
                      }
                    }

                    else
                    {   TreeInfo[] trees=rc.senseNearbyTrees(RobotType.GARDENER.bodyRadius+2f);
                        if(rc.canPlantTree(Direction.getSouth()))
                        {
                            rc.plantTree(Direction.getSouth());
                        }
                        else if(rc.canPlantTree(Direction.getNorth()))
                        {
                            rc.plantTree(Direction.getNorth());
                        }
                        else if(rc.canPlantTree(Direction.getWest()))
                        {
                            rc.plantTree(Direction.getWest());
                        }
                        else if(rc.canPlantTree(Direction.getEast()))
                        {
                            rc.plantTree(Direction.getEast());
                        }
                        else
                        {
                            if(rc.canWater(trees[0].getLocation()))rc.water(trees[0].getLocation());
                        }
                }
                }
                    //               }
                    // Move randomly
                 //   tryMove(randomDirection());
                    // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                    Clock.yield();
                } catch(Exception e){
                    System.out.println("Gardener Exception");
                    e.printStackTrace();


        }
        }
    }

}

package attack;

import battlecode.common.*;
import static attack.RobotPlayer.randomDirection;
import static attack.RobotPlayer.rc;
import static attack.RobotPlayer.tryMove;

/**
 * Created by nisar on 11-01-2017.
 */
public class LumberjackBot {
    static void runLumberjack() throws GameActionException {
        System.out.println("I'm a lumberjack!");
        Team enemy = rc.getTeam().opponent();

        // The code you want your robot to perform every round should be in this loop
        while (true) {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {

                // See if there are any enemy robots within striking range (distance 1 from lumberjack's radius)
                RobotInfo[] robots = rc.senseNearbyRobots(RobotType.LUMBERJACK.bodyRadius+ GameConstants.LUMBERJACK_STRIKE_RADIUS, enemy);
                    TreeInfo[] trees=rc.senseNearbyTrees();
                    int xPos = rc.readBroadcast(10);
                    int yPos = rc.readBroadcast(11);
                    MapLocation enemy1 = new MapLocation(xPos, yPos);
                    MapLocation myLocation = rc.getLocation();
                    if (robots.length > 0 && !rc.hasAttacked()) {
                        // Use strike() to hit all nearby robots!
                        rc.strike();
                    } else {
                        // No close robots, so search for robots within sight radius
                        robots = rc.senseNearbyRobots(10, enemy);

                        // If there is a robot, move towards it
                        if (robots.length > 0) {

                            MapLocation enemyLocation = robots[0].getLocation();
                            Direction toEnemy = myLocation.directionTo(enemyLocation);
                            rc.broadcast(10, (int) robots[0].getLocation().x);
                            rc.broadcast(11, (int) robots[0].getLocation().y);
                            tryMove(myLocation.directionTo(enemyLocation));
                        }
                        else if(trees.length>0)
                        { int i=0;
                            while(i<trees.length) {
                                if (trees[i].getTeam() != rc.getTeam() && rc.canChop(trees[i].location))
                                { rc.chop(trees[i].location); break;}
                                else i++;//tryMove(rc.getLocation().directionTo(trees[].location));
                            }
                        }
//                        else if(xPos!=0 && yPos!=0){
//                            tryMove(rc.getLocation().directionTo(enemy1));
//                        }
                        else
                            tryMove(randomDirection());
                    }
//                    else{
//                    // Move Randomly
//                   tryMove(myLocation.directionTo(enemy1));
//
//                }

                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                Clock.yield();

            } catch (Exception e) {
                System.out.println("Lumberjack Exception");
                e.printStackTrace();
            }
        }
    }
}

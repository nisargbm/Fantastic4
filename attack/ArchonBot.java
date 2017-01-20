package attack;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

import static attack.RobotPlayer.randomDirection;
import static attack.RobotPlayer.rc;

/**
 * Created by nisar on 11-01-2017.
 */
public class ArchonBot {
    static int GARDENER_CHANNEL = 5;
    static int LUMBERJACK_CHANNEL = 6;
static int SOLDIER_CHANNEL=7;
    // Keep important numbers here
    static int GARDENER_MAX = 4;
    static int LUMBERJACK_MAX = 10;
    static int SOLDIER_MAX=10;
    static void runArchon() throws GameActionException {
        System.out.println("I'm an archon!");

        // The code you want your robot to perform every round should be in this loop
        while (true) {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {

                // Generate a random direction
                Direction dir = randomDirection();
                int prevNumGard = rc.readBroadcast(GARDENER_CHANNEL);
                rc.broadcast(GARDENER_CHANNEL, 0);
                if (prevNumGard < GARDENER_MAX && rc.canHireGardener(dir)) {
                    rc.hireGardener(dir);
                    rc.broadcast(GARDENER_CHANNEL, prevNumGard + 1);
                }
                    // Randomly attempt to build a gardener in this direction
//                if (rc.canHireGardener(dir) && Math.random() < .01) {
//                    rc.hireGardener(dir);
//                }

                    // Move randomly
                    // tryMove(randomDirection());

                    // Broadcast archon's location for other robots on the team to know
                    MapLocation myLocation = rc.getLocation();
                    rc.broadcast(0, (int) myLocation.x);
                    rc.broadcast(1, (int) myLocation.y);

                    // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                    Clock.yield();

                } catch(Exception e){
                    System.out.println("Archon Exception");
                    e.printStackTrace();
                }
            }

    }

}

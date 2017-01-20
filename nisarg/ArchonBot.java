package nisarg;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

import static nisarg.RobotPlayer.randomDirection;
import static nisarg.RobotPlayer.rc;
import static nisarg.RobotPlayer.tryMove;

/**
 * Created by nisar on 11-01-2017.
 */
public class ArchonBot {
    static int SCOUT_CHANNEL=4;
    static int GARDENER_CHANNEL = 3;
    static int LUMBERJACK_CHANNEL = 2;
    static int SOLDIER_CHANNEL=90;
    // Keep important numbers here
    static int GARDENER_MAX = 2;
    static int LUMBERJACK_MAX = 3;
    static int SCOUT_MAX=2;
    static int SOLDIER_MAX=2;
    static int numGard=0;
    //neutral tree channel
    static int NEUTRAL_TREE_X_CHANNEL=99;
    static int NEUTRAL_TREE_Y_CHANNEL=98;
    //Scout Loc Broadcast
    static int XPOS_SCOUT_CHANNEL=97;
    static int YPOS_SCOUT_CHANNEL=96;
    static void runArchon() throws GameActionException {
        System.out.println("I'm an archon!");

        // The code you want your robot to perform every round should be in this loop
        while (true) {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {

                // Generate a random direction
                Direction dir = randomDirection();
                int prevNumGard = rc.readBroadcast(GARDENER_CHANNEL);
                if (prevNumGard < GARDENER_MAX && rc.canHireGardener(dir)) {
                    rc.hireGardener(dir);
                    rc.broadcast(GARDENER_CHANNEL, prevNumGard + 1);
                   // tryMove(rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam().opponent())[0]).opposite(), 30, 12);
                    // Broadcast archon's location for other robots on the team to know
                    MapLocation myLocation = rc.getLocation();
                    rc.broadcast(0, (int) myLocation.x);
                    rc.broadcast(1, (int) myLocation.y);
//                if(rc.getTeamBullets()>=1000)
//                    rc.donate(500);
                    // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                    Clock.yield();
                }
            } catch(Exception e){
                System.out.println("Archon Exception");
                e.printStackTrace();
            }
        }

    }

}

package nisarg;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.HashSet;
import static nisarg.ArchonBot.*;
import static nisarg.RobotPlayer.*;

/**
 * Created by nisar on 11-01-2017.
 */
public class GardenerBot {

	static Direction[] directions={Direction.getEast(),Direction.getNorth(),Direction.getWest(),Direction.getSouth()};
	static void runGardener() throws GameActionException {
		System.out.println("I'm a gardener!");
    int prevScoutNum=0;
    int  prevNumLum=0;
    int prevNumSol=0;
		// The code you want your robot to perform every round should be in this loop
		while (true) {

			// Try/catch blocks stop unhandled exceptions, which cause your robot to explode
			try {

				if (rc.getHealth() < RobotType.GARDENER.maxHealth / 10 &&
						rc.senseNearbyRobots(-1, rc.getTeam().opponent()).length > 0)
					rc.broadcast(GARDENER_CHANNEL, rc.readBroadcast(GARDENER_CHANNEL) - 1);
				int i = 0;
				// Listen for home archon's location
				//  int prev = rc.readBroadcast(GARDENER_CHANNEL);
				//  rc.broadcast(GARDENER_CHANNEL, prev + 1);
				int xPos = rc.readBroadcast(0);
				int yPos = rc.readBroadcast(1);
				MapLocation archonLoc = new MapLocation(xPos, yPos);
				// Generate a random direction
				Direction dir = randomDirection();
				int prev = rc.readBroadcast(GARDENER_CHANNEL);
                prevNumLum = rc.readBroadcast(LUMBERJACK_CHANNEL);
                 prevScoutNum=rc.readBroadcast(SCOUT_CHANNEL);
				 prevNumSol = rc.readBroadcast(SOLDIER_CHANNEL);
				//rc.broadcast(GARDENER_CHANNEL, prev+1);
				TreeInfo[] trees = rc.senseNearbyTrees(-1,Team.NEUTRAL);
				TreeInfo[] myTrees=rc.senseNearbyTrees(-1,rc.getTeam());
				RobotInfo[] bots= rc.senseNearbyRobots(2f);
				//  Direction dir = randomDirection();
		 //       Direction dir1 = rc.getLocation().directionTo(rc.getInitialArchonLocations(rc.getTeam())[0]);
//                if(rc.getLocation().isWithinDistance(archonLoc,6)){
//                    while(rc.getLocation().isWithinDistance(archonLoc,6))
//                        if(rc.canMove(rc.getLocation().directionTo(archonLoc).opposite()))
//                            tryMove(rc.getLocation().directionTo(archonLoc).opposite());
//                        else if(rc.canMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90)))
//                        {tryMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90));}
//                        else
//                            tryMove(rc.getLocation().directionTo(archonLoc).rotateRightDegrees(90));
//                }


//				for (int j = 0; j < 180; j++) {
//			if (rc.canPlantTree(dir)) {
//						rc.plantTree(dir);
//					   // Clock.yield();
//					}
//					dir.rotateLeftDegrees(2);
//					j++;
//				}

//
////                    }
//                tryPlant(dir,30,12);
//
				if(rc.getRoundNum()<600) {

					 if (prevNumLum <= LUMBERJACK_MAX)
                    { if (trySpawn(dir, 30, 12, RobotType.LUMBERJACK))
						rc.broadcast(LUMBERJACK_CHANNEL, prevNumLum + 1);
                    }
                    else if (prevScoutNum <= SCOUT_MAX){
                        if( trySpawn(dir, 30, 12, RobotType.SCOUT))
                        {rc.broadcast(SCOUT_CHANNEL, prevScoutNum + 1);
                        }
                    }
				}
//				else if(trees.length==0) {
//					tryPlant(randomDirection(), 60, 6);
//
//				}
				else
					{
					if(prevNumSol<=SOLDIER_MAX && trySpawn(dir,30,12,RobotType.SOLDIER))rc.broadcast(SOLDIER_CHANNEL,prevNumSol+1);
					else if (prevNumLum <= LUMBERJACK_MAX && trySpawn(dir, 30, 12, RobotType.LUMBERJACK))
						rc.broadcast(LUMBERJACK_CHANNEL, prevNumLum + 1);
					else {
						if (trees.length == 0 && bots.length==0 ) {
							tryPlant(dir, 60, 5);
							if (myTrees.length > 0) {
								while (i < myTrees.length) {
									if (rc.canShake()) rc.shake(myTrees[i].ID);
									if (myTrees[i].getTeam() == rc.getTeam() && rc.canWater(myTrees[i].ID))
										rc.water(myTrees[i].ID);
									Clock.yield();
									i++;
								}
							}
						}
						else tryMove(randomDirection(),60,6);
					}
				}
			    Clock.yield();
			} catch(Exception e) {
				System.out.println("Gardener Exception");
				e.printStackTrace();
			}
		}
	}
	static boolean tryPlant(Direction dir, float degreeOffset, int checksPerSide) throws GameActionException {
		int currentCheck = 1;
		while(currentCheck<=checksPerSide) {
			// Try the offset of the left side
			if(rc.canPlantTree(dir))
			{
				rc.plantTree(dir);
				return true;
			}
			dir.rotateLeftDegrees(degreeOffset*currentCheck);
			currentCheck++;
		}
		// A move never happened, so return false.
		return false;
	}
    static boolean trySpawn(Direction dir, float degreeOffset, int checksPerSide,RobotType robotType) throws GameActionException {
        int currentCheck = 1;
        while(currentCheck<=checksPerSide) {
            // Try the offset of the left side
            if(rc.canBuildRobot(robotType,dir))
            {
                rc.buildRobot(robotType,dir);

                return true;
            }
            dir.rotateLeftDegrees(degreeOffset*currentCheck);
            currentCheck++;
        }
        // A move never happened, so return false.
        return false;
    }
}

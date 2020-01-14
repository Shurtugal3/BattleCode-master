package samaritan;
import battlecode.common.*;
import java.util.ArrayList;

/**
 * contains local form of map -- ONLY 32x32 right now
 * Robot initial location will be set to the middle of the 2d table
 * Map Legend:
 * -1 - current location
 * -2 - HQ
 * positive number - soup
 */

public class localMap {
    mapLocation[][] map;
    /*
    MapLocation currentLocation;
    ArrayList soupAmount = new ArrayList();
    ArrayList soupLocation = new ArrayList();
    */

    /**
     * Create a new localMap object
     * @param inInitLocation mapLocation object containing the location information to be written
     * @param locType object type that is being stored on the map, should be a robot or building at this point
     * @param sizeX size of the map along the x-axis
     * @param sizeY size of the map along the y-axis
     */
    public localMap(MapLocation inInitLocation, char locType, int sizeX, int sizeY) {
        int x = inInitLocation.x;
        int y = inInitLocation.y;
        this.map = new mapLocation[sizeX][sizeY];
        this.map[inInitLocation.x][inInitLocation.y] = new mapLocation(x, y, locType);
    }

    //move location from one place to the next
    public void updateLocation(MapLocation inNewLocation) {
        this.currentLocation = inNewLocation;
    }

    /**
     * Add soup to the local map
     * @param inX x location of soup
     * @param inY y location of soup
     * @param inElevation the elevation of the tile
     */
    public void addTile(int inX, int inY, int inElevation) {
        //map[locX][locY] = elevation;
        map[inX][inY] = new mapLocation(inX, inY, inElevation);
    }

    public void addSoup(MapLocation location, int soup) {
        /*
        soupAmount.add(soup);
        soupLocation.add(location);
        */
        int x = location.x;
        int y = location.y;
        this.map[x][y] = new mapLocation(x, y, 'C', soup);
    }

    /*
    public void removeSoup(MapLocation location) {
        for(int i = 0; i < soupLocation.size(); i++) {
            if(((MapLocation) soupLocation.get(i)).equals(location)) {
                soupLocation.remove(i);
                soupAmount.remove(i);
            }
        }
    }
    */
    public void removeSoup(MapLocation location){
        int x = location.x;
        int y = location.y;
        int elevation = this.map[x][y].getElevation();
        this.map[x][y] = new mapLocation(x, y, elevation); //creates empty map location
    }

    public MapLocation closestSoup(MapLocation myLocation) {
        int distance;
        int closest = 100000;
        MapLocation location = null;
        for(int i = 0; i < soupLocation.size(); i++) {
            distance = myLocation.distanceSquaredTo((MapLocation) soupLocation.get(i));
            if(distance < closest) {
                location = (MapLocation) soupLocation.get(i);
                closest = distance;
            }
        }
        return location;
    }

    public int totalSoup(MapLocation myLocation, int radius) {
        int soup = 0;
        for(int i = 0; i < soupLocation.size(); i++) {
             if(myLocation.distanceSquaredTo((MapLocation) soupLocation.get(i)) <= radius)
                 soup += (int) soupAmount.get(i);
        }
        return soup;
    }

}

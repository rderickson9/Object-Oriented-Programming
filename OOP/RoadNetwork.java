import java.util.LinkedList;

/** Roads are one-way streets linking intersections
 *  @see Intersection
 */
class Road {
        float travelTime;         //measured in seconds
        Intersection destination; //where the road goes
        Intersection source;      //where the comes from
        // name of road is source-destination
}

/** Intersections join roads
 *  @see Road
 */
class Intersection {
        String name;
        LinkedList <Road> outgoing = new LinkedList <Road> ();
        LinkedList <Road> incoming = new LinkedList <Road> ();
        // Bug: multiple types of intersections (uncontrolled, stoplight)
}
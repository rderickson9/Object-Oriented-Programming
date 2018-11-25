//SimulationOutput.java

import java.util.Scanner;

/** Generate simulation output for the neuron network simulator
 *
 *  @author: Andy W.M. Arthur
 *  @author: Douglas W. Jones
 *  @version: May 4, 2016
 *  @see ScanSupport
 *  @see Simulator
 *  @see Neuron
 *  @see NeuronNetwork
 *
 * This code is extracted from the April 6, 2016 version of 
 * NeuronNetwork.java, which, in turn was based on the non-alternative 
 * solution to MP3, as well as a simulation framework from the March 11 
 * lecture notes. This code is further modified to meet the requirements 
 * of MP5.
 */

public class SimulationOutput {

    private static float interval;         // time between output reports
    private static float timeRemaining;    // time remaining in simulation
    
    /** Initialize for printing the simulation output
     *  This scans the output interval and simulation duration,
     *  and then it schedules initial output events.
     *  @param sc the scanner from which the interval and duration are gotten.
     */
    static void setOutput( Scanner sc ) {
        interval = ScanSupport.nextFloat( 
            sc,
            () -> "output interval error "
        );
        timeRemaining = ScanSupport.nextFloat( 
            sc,
            () -> "output duration error "
        );
        Simulator.schedule( 0.0f, (float t) -> printHeaders() );
        Simulator.schedule( interval, (float t) -> printState(t) );
    }

    /** Print the header for the simulation output.
     *  For each neuron, print the first 5 letters of its name.
     *  Truncated names are separated by single spaces.
     *  Code cribbed from D.W. Jones' Fall '15 solution to MP4.
     */
    static void printHeaders() {
        for( Neuron neu: NeuronNetwork.neurons) {
            int t = (5 <= neu.name.length()) ? 5 : neu.name.length();
            String n = neu.name.substring( 0, t );
            System.out.append( n );
            System.out.append( "      ".substring( 0, 6 - n.length()) );
        }
        System.out.println();
    }

    /** Report the state of each neuron in the simulation.
     *  @param time the time of this output report.
     *  After reporting the state of each neuron, schedules the next
     *  output event until the simulation duration has passed.
     */
    static void printState( float time ) {

        timeRemaining -= interval;
        if (timeRemaining < 0.0f) System.exit(0);

        for( Neuron neu: NeuronNetwork.neurons) {
            switch( neu.getCount() ) {
                case 0 : System.out.append("  |   "); break;
                case 1 : System.out.append("  |-  "); break;
                default: System.out.append("  |=  "); break;
            }
        }
        System.out.println();

        if (timeRemaining >= 0.0f) {
            Simulator.schedule( time + interval, (float t) -> printState(t) );
        }
    }
}

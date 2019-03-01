
package utils;

import javax.persistence.Persistence;

/**
 *
 * @author RasmusFriis
 */
public class Main
{

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Persistence.generateSchema("pu", null);
    }
}

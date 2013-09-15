
public class LOG
{
    /*
     * LOGFILE Style:
     *      16.09.2013-01:20:21 |   | Startup
     *      16.09.2013-01:20:22 |   | Logevent
     *      16.09.2013-01:20:26 | X | Failure
     *      
     */

    /**
     * Date-Time + ' | ' +FAILSYMBOL+ ' | ' +LINE
     */
    public static void write(String line)
    {
        write(line,false);
    }
    public static void write(String line, boolean fail)
    {
        ;
    }
    
    /**
     * clear content or create ./log/events and
     */
    public static void clearlog(int y)
    {
        ;
    }
}

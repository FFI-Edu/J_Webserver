
public class MAIN
{
    SETTINGS set;
    SERVER server;
    
    
    /**
     * Constructor for objects of class MAIN
     */
    public MAIN(String args)
    {
        LOG.write("entering main tread with params: "+args.toString() );
        //do something with start params
        ;
        try{
            //create object for the settings management class and (re-)reading the cfg/ini
            set=new SETTINGS();
        
            //create a thread of main socket with param: settings clss
            server=new SERVER(set);
        }catch (Exception e){
            //catch exceptions somathing may have an error->dump informations in ./log/crash
            
        }
        finally{
            
            ;
        }
    }

    /**
     * program entry
     */
    public static void main(String args)
    {
        MAIN main = new MAIN(args);
    }
}

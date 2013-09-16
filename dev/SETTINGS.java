
public class SETTINGS
{
    SETTINGSTICKER ticker;
    String lastchange="";
    
    public SETTINGS()
    {
        ticker = new SETTINGSTICKER (this, 750, true);
        refresh();
    }
    
    public void refresh()
    {
        //
        LOG.write("Settings refreshed");
    }

    public void tickerevent()
    {
        //check if 
        refresh();
    }
}

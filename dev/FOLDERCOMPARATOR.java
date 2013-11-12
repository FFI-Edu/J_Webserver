import java.util.*;

public class FOLDERCOMPARATOR implements Comparator<FOLDER>
{
    String order=null;
    boolean asc=true;
    public FOLDERCOMPARATOR(String order)
    {
        this.order=order.toLowerCase();
        if( this.order.contains("desc") )
            asc=false;
    }

    public int compare(FOLDER b1, FOLDER b2) {
        int r=1;
        if( order.contains("size") ){
            r = ( (int) (b1.getSize()-b2.getSize()) );
            if( r!=0 )
                r=r/Math.abs(r);
        }
        
        if( order.contains("date") ){
            r = ( (int) (b1.getDate()-b2.getDate()) );
            if( r!=0 )
                r=r/Math.abs(r);
        }
        
        if( order.contains("name") ){
            r = b1.getName().toLowerCase().compareTo( b2.getName().toLowerCase() );
        }
        
        if( !asc )
            r=-r;
        return r;
    }
}

import java.util.*;

public class FILECOMPARATOR implements Comparator<FILE>
{
    String order=null;
    boolean asc=true;
    public FILECOMPARATOR(String order)
    {
        this.order=order.toLowerCase();
        if( this.order.contains("desc") )
            asc=false;
    }

    @Override
    public int compare(FILE b1, FILE b2) {
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

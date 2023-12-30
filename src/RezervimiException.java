public class RezervimiException extends Exception{
    RezervimiException(String message){
        super(message);
    }
     protected static void checkErr(int n) throws RezervimiException{
        if(n < 0)
            throw new RezervimiException("Negative values are not allowed.");
    }
    protected static void checkErr(double n) throws RezervimiException{
        if(n < 0)
            throw new RezervimiException("Negative values are not allowed.");
    }
    protected static void checkErr(Object n) throws RezervimiException{
        if(n == null)
            throw new RezervimiException("Null references are not allowed.");
        if (n instanceof String)
        if(((String)n).trim().isEmpty())
            throw new RezervimiException("Empty values are not allowed.");
    }
}

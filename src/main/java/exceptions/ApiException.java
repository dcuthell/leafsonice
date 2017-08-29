package exceptions;

/**
 * Created by Guest on 8/29/17.
 */
public class ApiException extends RuntimeException{
    private final int statusCode;

    public ApiException (int statusCode, String msg){
        super(msg);
        this.statusCode = statusCode;
    }

    public int getStatusCode(){
        return statusCode;
    }
}

package models;

public class Result {
    private ResponseType responseType;
    private Object response;

    public Result(ResponseType responseType,Object response){
        this.responseType=responseType;
        this.response=response;
    }

    public ResponseType getResponseType(){
        return responseType;
    }

    public void setResponseType(ResponseType responseType){
        this.responseType=responseType;
    }

    public Object getResponse(){
        return response;
    }

    public void setResponse(Object response){
        this.response=response;
    }

    public static Result build(ResponseType responseType,Object response){
        return new Result(responseType,response);
    }
}

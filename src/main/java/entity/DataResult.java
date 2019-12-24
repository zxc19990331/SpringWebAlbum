package entity;

public class DataResult {
    private int status;
    private String msg = "";
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static DataResult fail(String msg){
        DataResult dataResult = new DataResult();
        dataResult.setMsg(msg);
        dataResult.setStatus(-1);
        return dataResult;
    }

    public static DataResult success(String msg,Object data){
        DataResult dataResult = new DataResult();
        dataResult.setMsg(msg);
        dataResult.setData(data);
        dataResult.setStatus(0);
        return dataResult;
    }
}

package uz.project.utilds;

public class CustomResponse {

    private int status;

    private String message;

    private Long time;

    public CustomResponse() {
        this.time= System.currentTimeMillis();
    }

    public CustomResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.time= System.currentTimeMillis();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}

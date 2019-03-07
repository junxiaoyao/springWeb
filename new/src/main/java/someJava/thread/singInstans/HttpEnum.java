package someJava.thread.singInstans;

public enum HttpEnum {
    HTTP_200(200, "success"),HTTP_500(500, "error");
    private Integer code;
    private String msg;

    HttpEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

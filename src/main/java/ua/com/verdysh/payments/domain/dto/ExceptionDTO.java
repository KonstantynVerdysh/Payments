package ua.com.verdysh.payments.domain.dto;

public class ExceptionDTO {

    private Integer code;
    private String text;

    public ExceptionDTO() { }

    public ExceptionDTO(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

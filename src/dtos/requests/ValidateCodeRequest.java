package dtos.requests;


import lombok.Data;

@Data
public class ValidateCodeRequest {

    private String code;
    private String codeType;
}

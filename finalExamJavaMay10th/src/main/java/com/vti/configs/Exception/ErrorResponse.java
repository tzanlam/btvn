package com.vti.configs.Exception;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String detailsMessage;
    private String errors;
    private int code;
    private String moreInfor;
}

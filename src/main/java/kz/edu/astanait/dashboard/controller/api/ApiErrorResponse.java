package kz.edu.astanait.dashboard.controller.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiErrorResponse extends ApiResponse {

    public ApiErrorResponse(String msg) {
        setSuccess(false);
        setMsg(msg);
    }

    public static ApiErrorResponse create (String msg) {
        return new ApiErrorResponse(msg);
    }
}

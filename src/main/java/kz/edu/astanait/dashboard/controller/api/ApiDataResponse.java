package kz.edu.astanait.dashboard.controller.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiDataResponse<T> extends ApiResponse {
    private T data;

    public ApiDataResponse(T data) {
        this.data = data;
        setSuccess(true);
    }

    public static <T> ApiDataResponse<T> create(T data) {
        return new ApiDataResponse<>(data);
    }

    public static <T> ApiDataResponse<T> failed(String msg) {
        ApiDataResponse<T> response = new ApiDataResponse<>();
        response.setSuccess(false);
        response.setMsg(msg);
        return response;
    }
}

package kz.edu.astanait.dashboard.controller.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiListResponse<T> extends ApiResponse {
    private List<T> list;
    private int totalSize;

    public static <T> ApiListResponse<T> create(List<T> list) {
        return create(list, list.size());
    }

    public static <T> ApiListResponse<T> create(List<T> list, int totalSize) {
        var response = new ApiListResponse<T>();
        response.setSuccess(true);
        response.setList(list);
        response.setTotalSize(totalSize);
        return response;
    }

    public static <T> ApiListResponse<T> failed(String msg) {
        var response = new ApiListResponse<T>();
        response.setSuccess(false);
        response.setMsg(msg);
        return response;
    }
}

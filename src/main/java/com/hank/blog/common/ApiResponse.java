package com.hank.blog.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
    String status,
    HttpStatusCode code,
    @Nullable String message,
    T data,
    @Nullable List<ErrorDetail> errors
) {
  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<T>(
        "success", HttpStatus.OK, null, data, null
    );
  }

  public static <T> ApiResponse<T> badRequest(String message, @Nullable List<ErrorDetail> errors) {
    return new ApiResponse<T>(
        "failure", HttpStatus.BAD_REQUEST, message, null, errors
    );
  }


  public record ErrorDetail(
      String field,
      String message
  ) {
  }
}

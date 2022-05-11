package com.apitesting.models.common;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorList {

  private List<Error> errors;

  public static ErrorList getExpectedError(ErrorType errorType) {
    return getExpectedErrors(List.of(errorType));
  }

  public static ErrorList getExpectedErrors(List<ErrorType> errorTypeList) {
    List<Error> errorList = new ArrayList<>();
    errorTypeList.forEach(
        errorType ->
            errorList.add(
                new Error(errorType.getField(), errorType.getMessage())));
    return ErrorList.builder().errors(errorList).build();
  }
}

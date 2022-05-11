package com.apitesting.requests.user;

import com.apitesting.constants.Endpoints;
import com.apitesting.models.users.UserRequestModel;
import com.apitesting.requests.ApiRequest;
import io.restassured.response.Response;

public class UserRequest extends ApiRequest {

  public Response getUserById(int userId) {
    return setBasePath(Endpoints.USERS_BY_ID_ENDPOINT)
        .setPathParam("userId", String.valueOf(userId))
        .get();
  }

  public Response postUser(UserRequestModel user) {
    return setBasePath(Endpoints.USERS_ENDPOINT)
        .post(user);
  }

  public Response patchUser(int userId, UserRequestModel user) {
    return setBasePath(Endpoints.USERS_BY_ID_ENDPOINT)
        .setPathParam("userId", String.valueOf(userId))
        .patch(user);
  }

  public Response updateUser(int userId, UserRequestModel user) {
    return setBasePath(Endpoints.USERS_BY_ID_ENDPOINT)
        .setPathParam("userId", String.valueOf(userId))
        .put(user);
  }

  public Response deleteUser(int userId) {
    return setBasePath(Endpoints.USERS_BY_ID_ENDPOINT)
        .setPathParam("userId", String.valueOf(userId))
        .delete();
  }
}

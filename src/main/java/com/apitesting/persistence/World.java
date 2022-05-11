package com.apitesting.persistence;

import com.apitesting.models.common.ErrorList;
import com.apitesting.models.users.UserRequestModel;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class World {

  private static ThreadLocal<World> instance;

  private String userId;
  private Response response;
  private UserRequestModel userRequest;
  private ErrorList errorList;

  public static synchronized World getWorld() {

    if (instance == null) {
      instance = ThreadLocal.withInitial(World::new);
    }
    return instance.get();
  }

}

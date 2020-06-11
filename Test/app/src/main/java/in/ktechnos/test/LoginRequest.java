package in.ktechnos.test;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRequest {

    @POST("login")
    Call<Root> loginUser(@Body LoginUser loginUser);
}

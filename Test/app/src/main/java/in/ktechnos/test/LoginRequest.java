package in.ktechnos.test;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginRequest {

    @POST("login")
    Call<Root> loginUser(@Body LoginUser loginUser);

    @GET("about")
    Call<Root> getUserDetails();
}

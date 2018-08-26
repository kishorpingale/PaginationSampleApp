package co.arrk.test.webservices;

import co.arrk.test.models.People;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SWApi {

    @GET("/api/people/?")
    Call<People> fetchPeopleDetails(
            @Query("page") int page);
}

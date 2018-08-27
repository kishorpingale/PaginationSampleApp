package co.kishor.sample.paging.rest;

import co.kishor.sample.paging.models.People;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SWApi {

    @GET("/api/people/?")
    Call<People> fetchPeopleDetails(
            @Query("page") int page);
}

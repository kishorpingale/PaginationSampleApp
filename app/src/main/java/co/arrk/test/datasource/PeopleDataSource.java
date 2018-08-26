package co.arrk.test.datasource;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import co.arrk.test.models.People;
import co.arrk.test.models.Result;
import co.arrk.test.webservices.SWApiFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleDataSource extends PageKeyedDataSource<Integer, Result> {

    private final String mLOGTAG = PeopleDataSource.class.getSimpleName();
    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;
    //the size of a page that we want
    public static final int PAGE_SIZE = 9;


    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Result> callback) {
        SWApiFactory.getInstance().getSWApi().fetchPeopleDetails(FIRST_PAGE)
                .enqueue(new Callback<People>() {
                    @Override
                    public void onResponse(Call<People> call, Response<People> response) {
                        if(response.body() != null) {
                            callback.onResult(response.body().getResults(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<People> call, Throwable t) {

                    }
                });
    }


    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params,
                           @NonNull final LoadCallback<Integer, Result> callback) {
        SWApiFactory.getInstance().getSWApi().fetchPeopleDetails(params.key)
                .enqueue(new Callback<People>() {

                    @Override
                    public void onResponse(Call<People> call, Response<People> response) {
                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null && response.body().getPrevious() != null) {

                            //passing the loaded data
                            //and the previous page key
                            callback.onResult(response.body().getResults(), adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<People> call, Throwable t) {

                    }
                });

    }


    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, Result> callback) {

        SWApiFactory.getInstance().getSWApi().fetchPeopleDetails(params.key)
                .enqueue(new Callback<People>() {
                    @Override
                    public void onResponse(Call<People> call, Response<People> response) {
                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number

                            if(response.body().getNext() != null) {
                                Integer key = params.key + 1;

                                //passing the loaded data and next page value
                                callback.onResult(response.body().getResults(), key);
                            } else {
                                Log.e(mLOGTAG, "loadAfter next is null");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<People> call, Throwable t) {

                    }
                });

    }
}

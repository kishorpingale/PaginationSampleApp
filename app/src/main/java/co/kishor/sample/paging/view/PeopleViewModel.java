package co.kishor.sample.paging.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import co.kishor.sample.paging.datasource.PeopleDataSource;
import co.kishor.sample.paging.datasource.PeopleDataSourceFactory;
import co.kishor.sample.paging.models.Result;

public class PeopleViewModel extends ViewModel {

    //creating Live data for PagedList and PagedKeyDataSource
    LiveData<PagedList<Result>> peopleListData;
    LiveData<PageKeyedDataSource<Integer, Result>> liveDataSource;

    //Constructor
    public PeopleViewModel() {
        //getting our data source factory
        PeopleDataSourceFactory peopleDataSourceFactory = new PeopleDataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = peopleDataSourceFactory.getPeopleMutableLiveData();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(PeopleDataSource.PAGE_SIZE).build();

        //Building the paged list
        peopleListData = (new LivePagedListBuilder(peopleDataSourceFactory, pagedListConfig))
                .build();
    }
}

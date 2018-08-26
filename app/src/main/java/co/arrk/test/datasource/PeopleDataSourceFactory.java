package co.arrk.test.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import co.arrk.test.models.Result;

public class PeopleDataSourceFactory extends DataSource.Factory<Integer, Result> {

    private MutableLiveData<PageKeyedDataSource<Integer, Result>> peopleMutableLiveData
            = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Result> create() {
        //getting our data source object
        PeopleDataSource peopleDataSource = new PeopleDataSource();

        //posting the datasource to get the values
        peopleMutableLiveData.postValue(peopleDataSource);

        //returning the datasource
        return peopleDataSource;
    }

    //getter for peopleMutableLiveDataSource

    public MutableLiveData<PageKeyedDataSource<Integer, Result>> getPeopleMutableLiveData() {
        return peopleMutableLiveData;
    }
}

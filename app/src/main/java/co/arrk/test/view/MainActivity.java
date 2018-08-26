package co.arrk.test.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import co.arrk.test.R;
import co.arrk.test.models.Result;

public class MainActivity extends BaseActivity{

    public final String mLOGTAG = MainActivity.class.getSimpleName();
    //getting recyclerview
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //setting up recyclerview
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //getting our ItemViewModel
        PeopleViewModel peopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);

        //creating the Adapter
        final PeopleListAdapter adapter = new PeopleListAdapter(this);

        //observing the itemPagedList from view model
        peopleViewModel.peopleListData.observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(@Nullable PagedList<Result> items) {

                //in case of any changes
                //submitting the items to adapter
                adapter.submitList(items);
            }
        });

        //setting the adapter
        recyclerView.setAdapter(adapter);
    }
}

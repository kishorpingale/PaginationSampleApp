package co.kishor.sample.paging.view;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import co.kishor.sample.paging.R;
import co.kishor.sample.paging.listeners.RecyclerViewClickListener;
import co.kishor.sample.paging.models.Result;
import co.kishor.sample.paging.util.Constant;

public class MainActivity extends BaseActivity{

//    public final String mLOGTAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recyclerview initialize
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getCharacterList();
    }

    private void getCharacterList() {
        if(isOnline()) {
            //Get PeopleViewModel
            PeopleViewModel peopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);

            RecyclerViewClickListener listener = new RecyclerViewClickListener() {
                @Override
                public void onClick(View view, int position, Result result) {
//                    String characterName = ((TextView) view.findViewById(R.id.textViewName)).getText().toString();
//                    Toast.makeText(MainActivity.this, "Position " + position
//                            + " Character Name " + characterName, Toast.LENGTH_SHORT).show();

                    Intent characterDetails = new Intent(MainActivity.this, CharacterDetailsActivity.class);
//                    characterDetails.putExtra(Constant.SELECTED_CHARACTER_POSITION, position);
                    characterDetails.putExtra(Constant.SELECTED_CHARACTER_POSITION, result);
                    startActivity(characterDetails);
                }
            };

            //Creating Adapter
            final PeopleListAdapter adapter = new PeopleListAdapter(this, listener);

            //Observing PeopleListData from PeopleViewModel
            peopleViewModel.peopleListData.observe(this, new Observer<PagedList<Result>>() {
                @Override
                public void onChanged(@Nullable PagedList<Result> items) {

                    //in case of any changes
                    //submitting the items to adapter
                    adapter.submitList(items);
                }
            });

            //Set adapter to the RecyclerView
            recyclerView.setAdapter(adapter);
        } else {
            showAlertDialog();
        }
    }

    private void showAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Information");
        alertDialog.setMessage("No Internet connection");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Try Again",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getCharacterList();
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}

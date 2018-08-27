package co.kishor.sample.paging.listeners;

import android.view.View;

import co.kishor.sample.paging.models.Result;

public interface RecyclerViewClickListener {

    void onClick(View view, int position, Result result);
}

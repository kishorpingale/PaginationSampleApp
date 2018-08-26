package co.arrk.test.view;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.arrk.test.R;
import co.arrk.test.models.Result;

public class PeopleListAdapter extends PagedListAdapter<Result, PeopleListAdapter.PeopleViewHolder> {

    private final String mLOGTAG = PeopleListAdapter.class.getSimpleName();
    private Context mContext;

    public PeopleListAdapter(Context paraContext) {
        super(DIFF_CALLBACK);
        mContext = paraContext;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_people, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        Result result = getItem(position);

        if(result != null) {
            holder.textView.setText(result.getName());
        } else {
            Log.e(mLOGTAG, "People is null");
        }
    }

    private static DiffUtil.ItemCallback<Result> DIFF_CALLBACK = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(Result oldItem, Result newItem) {
            return oldItem.getName() == newItem.getName();
        }

        @Override
        public boolean areContentsTheSame(Result oldItem, Result newItem) {
            return oldItem.equals(newItem);
        }
    };

    public class PeopleViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public PeopleViewHolder(View peopleView) {
            super(peopleView);

            textView = (TextView) peopleView.findViewById(R.id.textViewName);
        }
    }
}

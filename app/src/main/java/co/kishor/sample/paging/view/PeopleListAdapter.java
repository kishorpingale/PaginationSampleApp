package co.kishor.sample.paging.view;

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

import co.kishor.sample.paging.R;
import co.kishor.sample.paging.listeners.RecyclerViewClickListener;
import co.kishor.sample.paging.models.Result;

public class PeopleListAdapter extends PagedListAdapter<Result, PeopleListAdapter.PeopleViewHolder> {

    private final String mLOGTAG = PeopleListAdapter.class.getSimpleName();
    private Context mContext;
    private RecyclerViewClickListener mViewClickListener;

    public PeopleListAdapter(Context paraContext, RecyclerViewClickListener viewClickListener) {
        super(DIFF_CALLBACK);
        mContext = paraContext;
        mViewClickListener = viewClickListener;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_people, parent, false);
        return new PeopleViewHolder(view, mViewClickListener);
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

    public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        RecyclerViewClickListener mViewClickListener;

        public PeopleViewHolder(View peopleView, RecyclerViewClickListener viewClickListener) {
            super(peopleView);
            mViewClickListener = viewClickListener;
            textView = peopleView.findViewById(R.id.textViewName);
            peopleView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION) {
                Result result = getItem(position);
                mViewClickListener.onClick(v, position, result);
            }
        }
    }
}

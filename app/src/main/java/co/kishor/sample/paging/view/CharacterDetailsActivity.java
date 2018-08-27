package co.kishor.sample.paging.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.kishor.sample.paging.R;
import co.kishor.sample.paging.models.Result;
import co.kishor.sample.paging.util.Constant;

public class CharacterDetailsActivity extends BaseActivity {

    private final String mLOGTAG = CharacterDetailsActivity.class.getSimpleName();
//    private int selectedItemPos = -1;
    private TextView nameTextView, heightTextView, massTextView, createdTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_details);

        nameTextView = findViewById(R.id.textView_acd_char_name);
        heightTextView = findViewById(R.id.textView_acd_char_height);
        massTextView = findViewById(R.id.textView_acd_char_mass);
        createdTextView = findViewById(R.id.textView_acd_char_createdDate);

        if(getIntent() != null && getIntent().hasExtra(Constant.SELECTED_CHARACTER_POSITION)) {
            Bundle bundle = getIntent().getExtras();
            Result result = bundle.getParcelable(Constant.SELECTED_CHARACTER_POSITION);
//            Log.e(mLOGTAG, Constant.SELECTED_CHARACTER_POSITION + " " + selectedItemPos);

            if(result != null) {
                nameTextView.setText(result.getName());
                heightTextView.setText(result.getHeight());
                massTextView.setText(result.getMass());
                if (!result.getCreated().isEmpty()) {
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String createDate = result.getCreated();
                        if(createDate.contains("T")) {
                            createDate = createDate.substring(0, createDate.indexOf("T"));
                            Date date = formatter.parse(createDate);
                            formatter = new SimpleDateFormat("dd/MM/yyyy");
                            String strDate= formatter.format(date);
                            Log.e(mLOGTAG, " Created date is " + strDate);
                            createdTextView.setText(strDate);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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
}

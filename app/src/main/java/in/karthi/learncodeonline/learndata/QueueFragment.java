package in.karthi.learncodeonline.learndata;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class QueueFragment extends Fragment {

    private ReturnTypeFragment.OnButtonClickListener mOnButtonClickListener;

    interface OnButtonClickListener{
        void onButtonClicked(View view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnButtonClickListener = (ReturnTypeFragment.OnButtonClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(((Activity)context).getLocalClassName()+"must Implement OnButtonClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_queue, container,
                false);

        Button button = rootView.findViewById(R.id.move1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnButtonClickListener.onButtonClicked(v);
            }
        });
        return rootView;
    }
}

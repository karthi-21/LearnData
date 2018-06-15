package in.karthi.learncodeonline.learndata;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ReturnTypeFragment extends Fragment {
    private OnButtonClickListener mOnButtonClickListener;

    interface OnButtonClickListener{
        void onButtonClicked(View view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnButtonClickListener = (OnButtonClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(((Activity)context).getLocalClassName()+"must Implement OnButtonClickListener");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_retype, container,
                false);

        String code1="<font color='yellow'>void</font> <font color='#ffffff'>main() {</font> <br>&#160 &#160 <font color='#ffffff'>a=b+c;</font><br> <font color='#ffffff'>}</font>";
        TextView tv = rootView.findViewById(R.id.code1);
        tv.setText(Html.fromHtml(code1), TextView.BufferType.SPANNABLE);

        String codeint="<font color='yellow'>int</font> <font color='#ffffff'>main() {</font> <br>&#160 &#160 <font color='#ffffff'>a=b+c;</font><br>&#160 &#160<font color='#ffffff'>return c;</font><br> <font color='#ffffff'>}</font>";
        TextView tvint = rootView.findViewById(R.id.codeint);
        tvint.setText(Html.fromHtml(codeint), TextView.BufferType.SPANNABLE);

        Button button = rootView.findViewById(R.id.move);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ViewPager.setCurrentItem(2, true); //getItem(-1) for previous
                mOnButtonClickListener.onButtonClicked(v);
            }
        });

        return rootView;
    }

}
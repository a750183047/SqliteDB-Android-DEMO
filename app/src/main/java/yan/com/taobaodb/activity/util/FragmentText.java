package yan.com.taobaodb.activity.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * FragmentText 类
 * Created by yan on 2015/10/22.
 */
public class FragmentText extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(container.getContext());
        textView.setText("Fragment content000");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

}

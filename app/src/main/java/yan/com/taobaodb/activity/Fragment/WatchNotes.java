package yan.com.taobaodb.activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yan.com.taobaodb.R;
import yan.com.taobaodb.databsse.TBDataBase;
import yan.com.taobaodb.model.Merchants;

/**
 *
 * Created by yan on 2015/10/30.
 */
public class WatchNotes extends Fragment {

    private TBDataBase tbDataBase;
    private String MerchantsId;
    private Merchants merchants;
    private TextView merchantsLevel;
    private TextView merchantsNotes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_merchants_notes, container, false);
        tbDataBase = TBDataBase.getInstance(getActivity());
        tbDataBase.openDB(getActivity());
        //获取商店信息
      //  merchants = tbDataBase.loadAMerchant(MerchantsId);
        //控件实例化
        merchantsLevel = (TextView) view.findViewById(R.id.my_merchants_level);
        merchantsNotes = (TextView) view.findViewById(R.id.my_merchants_notes);

        //设置信息
        merchantsLevel.setText(String.valueOf(merchants.getMLevel()));
        merchantsNotes.setText(merchants.getMNote());

        return view;

    }

    public void setMerchantsId(String MerchantsId) {
        this.MerchantsId = MerchantsId;
    }

    public void getMerchantsObj(Merchants merchants){
        this.merchants = merchants;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // tbDataBase.closeDB();
    }
}

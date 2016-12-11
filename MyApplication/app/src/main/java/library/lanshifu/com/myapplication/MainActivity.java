package library.lanshifu.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import library.lanshifu.com.lsf_library.utils.Dictitem;
import library.lanshifu.com.lsf_library.utils.T;
import library.lanshifu.com.lsf_library.widget.dialog.AbsSingleChoicePopup;
import library.lanshifu.com.myapplication.multList.MultListActivity;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.btn_single)
    Button btnSingle;
    @Bind(R.id.btn_base)
    Button btnBase;
    @Bind(R.id.btn_mult)
    Button btnMult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_single, R.id.btn_base})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_single:
                String[] str = new String[]{"1", "2", "1", "2", "1", "2", "1", "2"};
                AbsSingleChoicePopup popup = new AbsSingleChoicePopup(this, "提示", str);
                popup.setOnSingleChoice(new AbsSingleChoicePopup.onSingleChoice() {
                    @Override
                    public void onCancelClick() {

                    }

                    @Override
                    public void onEnsureClick(Dictitem bean, int selectPos) {
                        T.showShort("点击了" + bean.getDictname());
                    }
                });
                popup.show();


                break;
            case R.id.btn_base:
                startActivity(new Intent(this, Main2Activity.class));
                break;
        }
    }

    @OnClick(R.id.btn_mult)
    public void onClick() {
        startActivity(new Intent(this,MultListActivity.class));
    }
}

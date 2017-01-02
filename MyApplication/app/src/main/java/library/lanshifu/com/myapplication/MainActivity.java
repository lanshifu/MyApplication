package library.lanshifu.com.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import library.lanshifu.com.lsf_library.utils.T;
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


    @OnClick({R.id.btn_single, R.id.btn_base,R.id.btn_multi,R.id.toolbar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_single:
                String[] str = new String[]{"1", "2", "1", "2", "1", "2", "1", "2"};
                new  AlertDialog.Builder(this)
                .setTitle("单选框")
                .setSingleChoiceItems(str, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        T.showShort("点击了"+which);
                        dialog.dismiss();
                    }
                })
                .show();


                break;
            case R.id.btn_multi:
                String[] str2 = new String[]{"1", "2", "3", "4"};
                boolean[] select = new boolean[]{false,false,false,true};
                new  AlertDialog.Builder(this)
                        .setTitle("多选框")
                        .setMultiChoiceItems(str2, select, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                T.showShort("点击了"+which+"，isChecked="+isChecked);
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
                break;



              case R.id.btn_base:
                startActivity(new Intent(this, Main2Activity.class));
                break;
              case R.id.toolbar:
                startActivity(new Intent(this, ScrollingActivity.class));
                break;
        }
    }

    @OnClick(R.id.btn_mult)
    public void onClick() {
        startActivity(new Intent(this,MultListActivity.class));
    }
}

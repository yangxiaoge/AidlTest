package com.yjn.aidltest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.yjn.aidlserver.ServiceAidl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestAIDL extends AppCompatActivity {

    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.editText3)
    EditText editText3;
    @Bind(R.id.button)
    Button button;

    private ServiceAidl mBinder;

    private int a;
    private int b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = ServiceAidl.Stub.asInterface(service);
            try {
                editText3.setText(mBinder.add(a, b)+"");

                unbindService(connection);

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @OnClick(R.id.button)
    public void onClick() {
//        Intent intent = new Intent("com.yjn.aidlserver.RemoteService");
//        bindService(intent, connection, BIND_AUTO_CREATE);

        //解决 Android 5.0中出现的警告：Service Intent must be explicit
        Intent mIntent = new Intent();
        mIntent.setAction("com.yjn.aidlserver.RemoteService");//你定义的service的action
        mIntent.setPackage("com.yjn.aidlserver");//这里你需要设置你应用的包名
        bindService(mIntent, connection, BIND_AUTO_CREATE);

        a = Integer.parseInt(editText.getText().toString());
        b = Integer.parseInt(editText2.getText().toString());

    }

}

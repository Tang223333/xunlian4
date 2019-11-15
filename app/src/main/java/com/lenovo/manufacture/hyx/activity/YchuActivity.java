package com.lenovo.manufacture.hyx.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;

import com.lenovo.manufacture.R;

public class YchuActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ychu);
        initView();

        MenuBuilder menuBuilder = new MenuBuilder(this);
        menuBuilder.setOptionalIconsVisible(true);

    }
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("溢出菜单");
        setSupportActionBar(toolbar);

    }



    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
//    设置溢出菜单布局
        getMenuInflater().inflate(R.menu.toolbarmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:

                break;
            case R.id.menu2:
                Toast.makeText(this, "这是溢出菜单的演示demo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}

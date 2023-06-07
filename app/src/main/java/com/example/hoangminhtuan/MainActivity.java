package com.example.hoangminhtuan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Taxi_hoangminhtuan> listUser=new ArrayList<>();
    private List<Taxi_hoangminhtuan> listUserCopy=new ArrayList<>();
    private Button them;
    private ListView listView;
    private Adapter_201200391 listAdapter;
    private Sqlite_201200391 db;
    private int viTri, count=0;
    ConnectionReceiver connectionReceiver;
    IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        //thêm dữ liệu vào sqlite
        db=new Sqlite_201200391(this,"Taxi_HoangTuan",null,1);
        //db.addUser(new Taxi_hoangminhtuan("4",20.5,2000,5));
       // db.addUser(new Taxi_hoangminhtuan("2",12.5,1000,5));
        //add từ sqlite vào list
        db.getAllUser().forEach(i-> System.out.println(i));
        db.getAllUser().forEach(i->listUser.add(i));

        /*----------sắp xếp giảm dần theo quang duong---------------------*/
        listUser.sort((o1,o2)->(int)(o2.getQuangDuong()-o1.getQuangDuong()));
        listAdapter=new Adapter_201200391(listUser,MainActivity.this);
        listView.setAdapter(listAdapter);

        /*-------------Tìm kiếm-------------------- */
        EditText edSearch=findViewById(R.id.edSearch);
        /*--------------Đếm--------------*/
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                viTri=position;
                System.out.println("tuan"+viTri);
                return false;
            }
        });
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    listAdapter.getFilter().filter(s.toString());
                    listAdapter.notifyDataSetChanged();
               // listView.setAdapter(listAdapter);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Toast.makeText(this, ""+count, Toast.LENGTH_SHORT).show();
        /*Kiểm tra bằng kết nối mạng*/
        connectionReceiver=new ConnectionReceiver();
        intentFilter=new IntentFilter("com.example.adapterview.SOME_ACTION");
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(connectionReceiver,intentFilter);

        /*---------Menu------------*/
        registerForContextMenu(listView);
    }
    /*-------Tạo menu con khi click vào listview--------------*/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuaction, menu);
        menu.setHeaderTitle("Select Option");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.idSua:
                Intent in =new Intent(MainActivity.this,MainActivity2.class);
                Bundle b=new Bundle();
                b.putString("soXe",listUser.get(viTri).getSoXe());
                b.putDouble("quangDuong",listUser.get(viTri).getQuangDuong());
                b.putInt("donGia",listUser.get(viTri).getDonGia());
                b.putInt("khuyenMai",listUser.get(viTri).getKhuyenMai());
                in.putExtras(b);
                startActivityForResult(in, 200);
                break;
            case R.id.idXoa:
                listUser.remove(listUser.get(viTri));
                break;

        }
        return super.onContextItemSelected(item);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        Bundle b = data.getExtras();
//        int id = b.getInt("Id");
//        String name = b.getString("Name");
//        String phone = b.getString("Phone");
//        Taxi_hoangminhtuan newContact = new Taxi_hoangminhtuan(id, "Images", name, phone);
//        if (requestCode == 100 && resultCode == 150) {
//            listUser.add(newContact);
//            listAdapter = new Adapter_201200391(listUser, this);
//            listView.setAdapter(listAdapter);
//        }
//        // Sua
//        else if (requestCode == 200 && resultCode == 150) {
//            for (Taxi_hoangminhtuan t : listUser) {
//                if (t.getSoXe() == "") {
//                   // contact.setName(name);
//                    //contact.setPhone(phone);
//                    break;
//                }
//            }
//            listAdapter = new Adapter_201200391(listUser, this);
//            listView.setAdapter(listAdapter);
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
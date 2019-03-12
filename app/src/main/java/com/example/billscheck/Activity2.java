package com.example.billscheck;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {
    //store the phonebook
    int index=1;
    ArrayList<String> arraylist;
    ListView listView ;
    ArrayAdapter<String> adapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        listView =(ListView) findViewById(R.id.listview);


        System.out.println("here!!!!!");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent,View view , int position, long id ){
                Toast.makeText(Activity2.this, index+"Selected", Toast.LENGTH_SHORT).show();
                index++;
            }
        });
 //       arraylist = new ArrayList<String>();

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M
            &&checkSelfPermission(Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS,},1
            );
        }
        else {
                getcontact();;
            }


    }

    public void getcontact(){
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while(cursor.moveToNext()){
           String  n = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
           String  moblie = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            adapter.add(n + " Phone_Number: "+ moblie);

        }

    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(requestCode == 1 )
        {
            if(grantResults[0]  == PackageManager.PERMISSION_GRANTED){
                getcontact();
            }
        }
    }


//    private class mylist extends ArrayAdapter<String> {
//        private int layout ;
//        private mylist(Context context, int resource, List<String> objects){
//            super(context,resource,objects);
//            layout = resource;
//        }
//        public View getView(int position, View convertView , ViewGroup parent){
//            ViewHolder mainView = null;
//            if(convertView == null) {
//                LayoutInflater inflater = LayoutInflater.from(getContext());
//                convertView = inflater.inflate(layout, parent, false);
//                ViewHolder viewHolder = new ViewHolder();
//                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_phonebook);
//                viewHolder.button = (Button) convertView.findViewById(R.id.button3);
//                convertView.setTag(viewHolder);
//
//
//            }
//            else {
//                mainView = (ViewHolder) convertView.getTag();
//                mainView.title.setText(getItem(position));
//
//            }
//            return convertView;
//        }
//
//    }
//
//
//    public class ViewHolder{
//        TextView title;
//        Button button;
//    }



}

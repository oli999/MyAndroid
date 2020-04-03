package com.gura.step21hellokotlin

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener,
        AdapterView.OnItemLongClickListener{
    // null 로 property 를 초기화 하기
    //var inputMsg:EditText?=null

    // 나중에 초기화 하겠다는 keyword  lateinit
    lateinit var adapter:ArrayAdapter<String>
    lateinit var msgList:MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Button 에 리스너 등록
        addBtn.setOnClickListener(this)
        clearBtn.setOnClickListener(this)

        //MutableList 객체의 참조값을 얻어내서 property 에 저장하기
        msgList=mutableListOf()
        //ArrayAdapter 객체의 참조값을 얻어내서 property 에 저장하기
        adapter= ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                msgList)
        //ListView 에 아답타 연결하기
        // id 를 이용해서 View 의 참조값 바로 사용 가능
        myListView.adapter=adapter
        // 아이템 롱 클릭 리스너 등록하기
        myListView.setOnItemLongClickListener(this)

    }
    //추가, 모두 삭제 버튼을 누르면 호출되는 함수
    override fun onClick(v: View?) {
        // v 는 눌러진 버튼의 참조값이 전달된다.
        when(v?.id){
            R.id.addBtn -> {
                //입력한 문자열 읽어오기
                val msg=inputMsg.text.toString()
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
                //입력한 문자열을 모델에 추가하고
                msgList.add(msg)
                //ListView 가 업데이트 되도록 아답타에 알린다.
                adapter.notifyDataSetChanged()
            }
            R.id.clearBtn -> {
                msgList.clear()
                adapter.notifyDataSetChanged()
            }
        }

    }

    override fun onItemLongClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long): Boolean {
        var activity=this

        //클릭한 곳의 item
        val item:String = msgList.get(position)

        AlertDialog.Builder(this)
                .setMessage("자세히 보기로 이동 하겠습니까?")
                .setPositiveButton("네", object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        //액티비티 이동
                        //val intent=Intent(activity, DetailActivity::class.java)
                        val intent=Intent(this@MainActivity, DetailActivity::class.java)
                        //Intent 객체에 item 이라는 키값으로 String type 담기
                        intent.putExtra("item", item)
                        startActivity(intent)
                    }
                })
                .setNegativeButton("아니요", null)
                .create()
                .show();

        return false;
    }
}









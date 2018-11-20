package c.gingdev.saveandgetjson

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{


    lateinit var Rc: RecyclerView
    lateinit var AddBtn: Button
    lateinit var GetBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewInit()
    }

    private fun ViewInit() {
        Rc = RcView
        AddBtn = Add
        GetBtn = Get

        AddBtn.setOnClickListener(this)
        GetBtn.setOnClickListener(this)
    }

    /**OnClickListener**/
    override fun onClick(v: View?) {
        when(v!!.id) {
            Add.id -> {
                val i = Intent(this, SaveJson::class.java)
                startActivity(i)
            }
            Get.id -> {

            }
        }
    }
}

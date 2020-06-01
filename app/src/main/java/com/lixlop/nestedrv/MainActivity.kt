package com.lixlop.nestedrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenResumed {
            //测试数据 start
            val list = ArrayList<NestedModel>()
            withContext(Dispatchers.IO) {
                for (i in 0..20) {
                    val model = NestedModel()
                    model.num = i
                    list.add(model)
                }
                val model = NestedModel()
                model.bottom = true
                list.add(model)
            }
            //测试数据 end
            val adapter: NestedAdapter? = NestedAdapter(list)
            recycler_view.adapter = adapter
            recycler_view.layoutManager = LinearLayoutManager(baseContext)
        }
    }
}

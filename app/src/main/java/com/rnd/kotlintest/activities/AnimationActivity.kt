package com.rnd.kotlintest.activities
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.rnd.kotlintest.R
import kotlinx.android.synthetic.main.activity_animation.*

/**
 * Created by Devrepublic-14 on 11/3/2017.
 */
class AnimationActivity : BaseActivity() {

    internal var animations = arrayOf("Fade In", "Fade Out", "Zoom In", "Zoom Out", "Blink", "Rotate", "Move", "Slide Up", "Slide Down", "Bounce")
    internal var animationIDs = intArrayOf(R.anim.fade_in, R.anim.fade_out, R.anim.zoom_in, R.anim.zoom_out, R.anim.blink, R.anim.rotate, R.anim.move, R.anim.slide_up, R.anim.slide_down, R.anim.bounce)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        try {
            initCoponets()
           } catch (e: Exception) {
         }
    }

    override fun initCoponets() {
        super.initCoponets()
        lv.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, animations)

        lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val animation = AnimationUtils.loadAnimation(this@AnimationActivity, animationIDs[position])
            tv.startAnimation(animation)
          }

       }
 }
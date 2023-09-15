package com.sumin.hospital

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.sumin.hospital.databinding.ActivityMainBinding
import com.sumin.navigation.Navigator
import com.sumin.navigation.NavigatorMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    private val binding: ActivityMainBinding get() = requireNotNull(_binding)
    private var _binding: ActivityMainBinding? = null

    private var backPressedTime = 0L
    private val backPressedCallback = object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if(System.currentTimeMillis() - backPressedTime >= 2000L) {
                backPressedTime = System.currentTimeMillis()
                Toast.makeText(this@MainActivity, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()

            } else {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        onBackPressedDispatcher.addCallback(backPressedCallback)
    }
}
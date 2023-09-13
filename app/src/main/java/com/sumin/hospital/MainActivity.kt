package com.sumin.hospital

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.sumin.hospital.databinding.ActivityMainBinding
import com.sumin.navigation.Navigatable
import com.sumin.navigation.NavigatorMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding get() = requireNotNull(_binding)
    private var _binding: ActivityMainBinding? = null

    private val navigatorHolder = NavControllerHolder()
    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navHostFragment.navController
    }

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

    override fun onStart() {
        super.onStart()
        NavigatorMediator.connect(navigatorHolder)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavController(navController)
    }

    override fun onPause() {
        navigatorHolder.removeNavController()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        NavigatorMediator.disconnect()
    }
}
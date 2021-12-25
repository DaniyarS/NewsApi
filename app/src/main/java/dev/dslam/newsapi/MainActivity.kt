package dev.dslam.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.dslam.newsapi.adapters.FragmentAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var adapter: FragmentAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FragmentAdapter(supportFragmentManager)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
        tabLayout.setupWithViewPager(viewPager)
    }
}
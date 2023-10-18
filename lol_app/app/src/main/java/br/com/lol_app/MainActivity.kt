package br.com.lol_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.com.lol_app.databinding.ActivityMainBinding
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onInitViews()
    }

    private fun onInitViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment

        navController = navHostFragment.navController

        binding.navBottom.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
        binding.navBottom.add(MeowBottomNavigation.Model(2, R.drawable.ic_stats))
        binding.navBottom.add(MeowBottomNavigation.Model(3, R.drawable.ic_settings))

        binding.navBottom.setOnClickMenuListener {
            when (it.id) {
                1 -> navController.navigate(R.id.home_nav_graph)
                2 -> navController.navigate(R.id.stats_nav_graph)
                3 -> navController.navigate(R.id.settings_nav_graph)
            }
        }
    }
}
package br.com.lol_app.screen.host

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.com.lol_app.R
import br.com.lol_app.databinding.ActivityMainBinding
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onInitViews()
        onInitObservers()
    }

    private fun onInitViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment

        navController = navHostFragment.navController

        binding.navBottom.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.menuHome -> {
                    navController.navigate(R.id.home_nav_graph)
                    true
                }

                R.id.menuStats -> {
                    navController.navigate(R.id.stats_nav_graph)
                    true
                }

                R.id.menuSettings -> {
                    navController.navigate(R.id.settings_nav_graph)
                    true
                }

                else -> false
            }
        }
    }

    private fun onInitObservers() {
        mainViewModel.navGraphId.observe(this) { navGraphResource ->
            navController.navigate(navGraphResource)
            binding.navBottom.selectedItemId = mainViewModel.getDestinationItemId()
        }

        mainViewModel.showNavBottom.observe(this) {
            binding.navBottom.isVisible = it
        }
    }
}
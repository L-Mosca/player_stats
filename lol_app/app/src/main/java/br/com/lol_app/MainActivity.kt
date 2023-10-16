package br.com.lol_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.lol_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.bind(findViewById(R.id.navHostContainer))
    }
}
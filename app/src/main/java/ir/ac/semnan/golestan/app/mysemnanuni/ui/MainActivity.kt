package ir.ac.semnan.golestan.app.mysemnanuni.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import ir.ac.semnan.golestan.app.mysemnanuni.R
import ir.ac.semnan.golestan.app.mysemnanuni.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var Listener:NavController.OnDestinationChangedListener

    private lateinit var navController:NavController
    private lateinit var appBarConfig:AppBarConfiguration
    private lateinit var navHostFragment:NavHostFragment


    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.navigateToolbar)
        navHostFragment=
            supportFragmentManager.findFragmentById(R.id.fragment_container_view)as NavHostFragment
        navController=navHostFragment.navController
        appBarConfig= AppBarConfiguration(navController.graph)
        binding.bottomNavView.setupWithNavController(navController)
        Listener=
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.main_Home_Fragment) {
                    binding.navigateToolbar.title = "main"
                }
                if (destination.id == R.id.dashboardFragment) {
                    binding.navigateToolbar.title = "person"
                }
                if (destination.id == R.id.notifictionFragment) {
                    binding.navigateToolbar.title = "notification"
                }
            }

    }

    override fun onPause() {
        super.onPause()
        navController.addOnDestinationChangedListener(Listener)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(Listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController( R.id.fragment_container_view)
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }
}
package com.example.login10.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.login10.R
import com.example.login10.databinding.MainActivityBinding
import com.example.login10.view.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController
    private var homeSubNavController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupNavHost()
        setupDestinationChangedListener()
    }

    // SRP: Configura la Toolbar en un método separado
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    // SRP: Configura el NavHost en un método separado
    private fun setupNavHost() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_Fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    // SRP: Listener para cambios en la navegación
    private fun setupDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            handleDestinationChange(destination.id)
        }
    }

    // OCP: Método abierto a la extensión para manejar cambios en la navegación
    private fun handleDestinationChange(destinationId: Int) {
        when (destinationId) {
            R.id.loginFragment -> configureActionBar("Infocal", false)
            R.id.registerFragment -> configureActionBar("Registro", true)
            R.id.homeFragment -> handleHomeFragment()
            else -> configureActionBar(null, false)
        }
    }

    // SRP: Configuración de ActionBar
    private fun configureActionBar(title: String?, displayHomeAsUp: Boolean) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUp)
    }

    // SRP: Maneja la lógica específica del HomeFragment
    private fun handleHomeFragment() {
        configureActionBar("HomeApp", false)
        binding.root.post {
            setupHomeFragmentSubNavigation()
        }
    }

    // Lógica de subnavegación del HomeFragment
    private fun setupHomeFragmentSubNavigation() {
        val homeFragment = findHomeFragment()

        if (homeFragment != null) {
            setupHomeNavController(homeFragment)
        } else {
            Log.e("MainActivity", "HomeFragment not found")
        }
    }

    private fun findHomeFragment(): HomeFragment? {
        return supportFragmentManager.findFragmentById(R.id.nav_host_Fragment)
            ?.childFragmentManager
            ?.fragments
            ?.firstOrNull { it is HomeFragment } as? HomeFragment
    }

    private fun setupHomeNavController(homeFragment: HomeFragment) {
        val homeNavHostFragment = homeFragment.childFragmentManager
            .findFragmentById(R.id.home_nav_host_fragment) as? NavHostFragment

        if (homeNavHostFragment != null) {
            homeSubNavController = homeNavHostFragment.navController
            homeSubNavController?.addOnDestinationChangedListener { _, destination, _ ->
                handleSubNavigationChange(destination.id)
            }
        } else {
            Log.e("MainActivity", "NavHostFragment not found with ID R.id.home_nav_host_fragment")
        }
    }

    private fun handleSubNavigationChange(destinationId: Int) {
        when (destinationId) {
            R.id.productFragment -> configureActionBar("Producto", false)
            R.id.detailFragment -> configureActionBar("Detalle", true)
            R.id.welcomeFragment -> configureActionBar("Bienvenido", false)
            R.id.graphicsFragment -> configureActionBar("Gráficos", false)
        }
    }

    // Manejo de opciones del menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "Settings ...", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_logout -> {
                navController.navigate(R.id.action_homeFragment_to_loginFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)
        val logoutItem = menu?.findItem(R.id.action_logout)
        logoutItem?.isVisible = navController.currentDestination?.id == R.id.homeFragment
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return homeSubNavController?.navigateUp() ?: navController.navigateUp() || super.onSupportNavigateUp()
    }
}

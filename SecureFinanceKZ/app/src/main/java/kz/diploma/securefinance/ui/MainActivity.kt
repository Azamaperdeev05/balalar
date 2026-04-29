package kz.diploma.securefinance.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.R
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.ui.auth.AuthActivity
import kz.diploma.securefinance.util.enableFlagSecure

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val lockHandler = Handler(Looper.getMainLooper())
    private val lockChecker = object : Runnable {
        override fun run() {
            if (SessionManager.shouldLock()) { lockApp(); return }
            lockHandler.postDelayed(this, 5_000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Process death немесе session жоқ → Fragment жүктелмей тұрып AuthActivity-ге
        if (!SessionManager.isUnlocked || (application as FinanceApp).repository == null) {
            redirectToAuth()
            return   // setContentView() шақырылмайды → Fragment-тар ешқашан inflate болмайды
        }

        enableFlagSecure()
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHost.navController
        findViewById<BottomNavigationView>(R.id.bottomNavigation).setupWithNavController(navController)

        val fab = findViewById<FloatingActionButton>(R.id.fabAddTransaction)
        fab.setOnClickListener { navController.navigate(R.id.addEditTransactionFragment) }

        navController.addOnDestinationChangedListener { _, dest, _ ->
            fab.visibility = when (dest.id) {
                R.id.dashboardFragment, R.id.transactionsFragment -> android.view.View.VISIBLE
                else -> android.view.View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Егер redirect басталып қойса (isFinishing) — қайтадан шақырмаймыз
        if (isFinishing) return
        if (!SessionManager.isUnlocked || (application as FinanceApp).repository == null) {
            lockApp()
            return
        }
        SessionManager.touch()
        lockHandler.postDelayed(lockChecker, 5_000)
    }

    override fun onPause() {
        super.onPause()
        lockHandler.removeCallbacks(lockChecker)
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        SessionManager.touch()
    }

    private fun lockApp() {
        SessionManager.lock()
        (application as FinanceApp).closeDatabase()
        redirectToAuth()
    }

    private fun redirectToAuth() {
        val app = application as FinanceApp
        val dest = if (app.cryptoManager.isSetup)
            AuthActivity::class.java else kz.diploma.securefinance.ui.auth.SetupActivity::class.java
        startActivity(Intent(this, dest).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }
}

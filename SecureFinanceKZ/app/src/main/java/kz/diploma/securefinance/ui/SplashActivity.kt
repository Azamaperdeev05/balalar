package kz.diploma.securefinance.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.ui.auth.AuthActivity
import kz.diploma.securefinance.ui.auth.SetupActivity
import kz.diploma.securefinance.util.enableFlagSecure

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableFlagSecure()

        val app = application as FinanceApp

        val next = when {
            SessionManager.isUnlocked -> {
                Intent(this, MainActivity::class.java)
            }
            app.cryptoManager.isSetup -> {
                Intent(this, AuthActivity::class.java)
            }
            else -> {
                Intent(this, SetupActivity::class.java)
            }
        }
        startActivity(next)
        finish()
    }
}

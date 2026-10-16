package cat.institutmarianao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {

    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Login successful
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ClientsActivity::class.java))
            } else {
                // Login failed or cancelled
                Toast.makeText(this, "Login cancelled or failed", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val accessFirebaseUiButton = findViewById<Button>(R.id.accessFirebaseUiButton)

        accessFirebaseUiButton.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser != null) {
                // User already signed in, launch ClientsActivity
                startActivity(Intent(this, ClientsActivity::class.java))
            } else {
                // Not signed in, show firebase login dialog
                launchFirebaseAuthUI()
            }
        }
    }

    private fun launchFirebaseAuthUI() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder()
                .setAllowNewAccounts(false) // Avoid FirebaseUI to register
                .build()
        )

        val signInIntent =
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build()

        signInLauncher.launch(signInIntent)
    }
}

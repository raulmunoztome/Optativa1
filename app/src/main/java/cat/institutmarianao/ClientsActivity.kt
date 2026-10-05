package cat.institutmarianao

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class ClientsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clients)
        val bundle = intent.extras
        val usuari = bundle?.getString("email")

        val info = findViewById<TextView>(R.id.info1)
        val volver = findViewById<Button>(R.id.boton2)

        info.text = "Bienvenido $usuari"

        volver.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            finish()
        }

    }
}
package cat.institutmarianao

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase

class ClientsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clients)
        val bundle = intent.extras
        val usuari = bundle?.getString("email")

        val info = findViewById<TextView>(R.id.info1)
        val volver = findViewById<Button>(R.id.boton2)
        val botonUsuario = findViewById<Button>(R.id.boton3)

        info.text = "Bienvenido $usuari"

        volver.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            finish()
        }
        botonUsuario.setOnClickListener {
            val gestionUsers = Intent(this, GestionUsuarios::class.java)
            startActivity(gestionUsers)
        }

    }
}
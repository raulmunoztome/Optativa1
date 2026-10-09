package cat.institutmarianao

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class NuevoUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_usuario)

        val infoNom = findViewById<EditText>(R.id.usuarioNom)
        val infoMail = findViewById<EditText>(R.id.usuarioEmail)
        val infoEdad = findViewById<EditText>(R.id.usuarioEdad)
        val botonConfirma = findViewById<Button>(R.id.botonCrearUsuario)
        val botonSalir = findViewById<Button>(R.id.salirCrear)

        botonConfirma.setOnClickListener {
            if (infoNom != null && infoEdad != null && infoMail != null) {
                val db = Firebase.firestore
                val client = hashMapOf(
                    "name" to infoNom.text.toString(),
                    "email" to infoMail.text.toString(),
                    "age" to infoEdad.text.toString().toIntOrNull()
                )
                db.collection("clients")
                    .document(infoMail.text.toString()) // Fem servir l'email com a ID del document
                    .set(client)
                    .addOnSuccessListener { documentReference ->
                        Log.d("Firestore", "Document saved with ID: ${infoMail.text}")
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firestore", "Error adding document", e)
                    }

            }
        }

        botonSalir.setOnClickListener {
            finish()
        }

    }
}
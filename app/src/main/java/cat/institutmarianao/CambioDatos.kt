package cat.institutmarianao

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CambioDatos : AppCompatActivity() {
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cambio_datos)
        val botonNom = findViewById<Button>(R.id.cambiarNom)
        val inEdad = findViewById<EditText>(R.id.inputEdad)
        val inNom = findViewById<EditText>(R.id.inputNom)
        val botonEdad = findViewById<Button>(R.id.cambiarEdad)
        val atras = findViewById<Button>(R.id.buttonBack)
        val finalizar = findViewById<Button>(R.id.Confirmar)
        val correo = findViewById<EditText>(R.id.EmailAddressCambios)

        botonNom.setOnClickListener {
                inNom.visibility = View.VISIBLE
                inEdad.visibility = View.GONE
                botonEdad.visibility = View.GONE
                finalizar.visibility = View.VISIBLE

        }
        botonEdad.setOnClickListener {
                inNom.visibility = View.GONE
                inEdad.visibility = View.VISIBLE
                botonNom.visibility = View.GONE
                finalizar.visibility = View.VISIBLE
        }
        atras.setOnClickListener {
            finish()
        }
        finalizar.setOnClickListener {
            if(!correo.text.toString().isEmpty()){
                val userRef = db.collection("clients").document(correo.text.toString().trim())
                if(botonNom.visibility == View.GONE) {
                    userRef.update("age", inEdad.text.toString().toIntOrNull())
                        .addOnSuccessListener {
                            Log.d("Firestore", "Client updated successfully!")
                            finish()
                        }
                }
                else{
                    userRef.update("name", inNom.text.toString().trim())
                        .addOnSuccessListener {
                            Log.d("Firestore", "Client ${correo.text.toString().trim()} updated successfully!")
                            finish()
                        }
                }

            }
        }

    }
}
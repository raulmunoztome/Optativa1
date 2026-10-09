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
import androidx.core.widget.addTextChangedListener
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class EliminarCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_eliminar_cliente)
        val db = Firebase.firestore
        val volverAtras = findViewById<Button>(R.id.volvX)
        val confirEliminar = findViewById<Button>(R.id.confirEliminar)
        val correoEliminar = findViewById<EditText>(R.id.emailEliminar)

        correoEliminar.addTextChangedListener{
            confirEliminar.visibility = View.VISIBLE
        }
        volverAtras.setOnClickListener {
            finish()
        }
        confirEliminar.setOnClickListener {
            if(!correoEliminar.text.toString().isEmpty()){
                db.collection("clients").document(correoEliminar.text.toString().trim())
                    .delete()
                    .addOnSuccessListener {
                        Log.d("Firestore", "Document eliminat correctament!")
                        finish()
                    }
            }
        }

    }
}
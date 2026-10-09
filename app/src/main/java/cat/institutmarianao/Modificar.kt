package cat.institutmarianao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Modificar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_modificar)

        val eliminar = findViewById<Button>(R.id.botonBorrar)
        val modificar = findViewById<Button>(R.id.botonModi)
        val atrasB = findViewById<Button>(R.id.buttonVolver)

        eliminar.setOnClickListener {
            val intento = Intent(this, EliminarCliente::class.java)
            startActivity(intento)
        }

        modificar.setOnClickListener {
            val intento = Intent(this, CambioDatos::class.java)
            startActivity(intento)
        }
        atrasB.setOnClickListener {
            finish()
        }


    }
}
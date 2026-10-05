package cat.institutmarianao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GestionUsuarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gestion_usuarios)

        val crear = findViewById<Button>(R.id.botonCrear)
        val modificar = findViewById<Button>(R.id.botonMod)
        val listar = findViewById<Button>(R.id.botonLista)
        val volver = findViewById<Button>(R.id.botonVolver)

        crear.setOnClickListener {
            val crearCliente = Intent(this, NuevoUsuario::class.java)
            startActivity(crearCliente)
        }

        modificar.setOnClickListener {
            val modificarCliente = Intent(this, Modificar::class.java)
            startActivity(modificarCliente)
        }

        listar.setOnClickListener {
            val listarCliente = Intent(this,Listado::class.java)
            startActivity(listarCliente)
        }

        volver.setOnClickListener {
            finish()
        }

    }
}
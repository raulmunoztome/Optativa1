package cat.institutmarianao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import android.util.Log
import android.widget.Button

data class Client(
    val name: String = "",
    val email: String = "",
    val age: Int? = null
)
class Listado : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var clientAdapter: ClientAdapter
    private val clientList = mutableListOf<Client>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)

        // Set up RecyclerView
        recyclerView = findViewById(R.id.clientsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // The ClientAdapter get each element from the clientList and place it in the element layout (item_client)
        clientAdapter = ClientAdapter(clientList)
        recyclerView.adapter = clientAdapter

        // Load data from Firestore
        loadClientsFromFirestore()

        val volver = findViewById<Button>(R.id.botonVolver)
        volver.setOnClickListener {
            finish()
        }
    }
    private fun loadClientsFromFirestore() {
        // * Firestore get all documents from collection * //
        db.collection("clients").get().addOnSuccessListener { result ->
            clientList.clear() // clear list before get new data
            for (document in result) {
                val client = document.toObject(Client::class.java)
                clientList.add(client)
            }
            clientAdapter.notifyDataSetChanged() // refresh UI
        }.addOnFailureListener { exception ->
            Log.w("Firestore", "Error getting documents.", exception)
        }
    }
}
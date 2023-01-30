package com.example.pia11firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import layout.FruitAdapter

class MainActivity : AppCompatActivity() {

    var fadapter = FruitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val personRecview = findViewById<RecyclerView>(R.id.fruitRV)

        personRecview.adapter = fadapter
        personRecview.layoutManager = LinearLayoutManager(this)

        loadfruits()

        findViewById<Button>(R.id.addFruitButton).setOnClickListener {
            var addfruitname = findViewById<EditText>(R.id.addFruitET).text.toString()

            val database = Firebase.database
            val fruitsallad = database.getReference("fruidsallad")
            val somefruit = Fruit(addfruitname, "")
            fruitsallad.push().setValue(somefruit)

            loadfruits()
        }

        val database = Firebase.database
       // val myRef = database.getReference("message")

      //  myRef.setValue("Hello, World!")

        //Spara frukt
        /*val banan = Fruit("Banan", "gul")
        val fruitRef = database.getReference("androidfruits")
        fruitRef.setValue(banan)
        */
      // Hämta data
        /*
        val myRef = database.getReference("androidfruits")
        myRef.get().addOnSuccessListener {
            val thefruit = it.getValue<Fruit>()
            Log.i("pia11debug", thefruit!!.fruitname!!)
        }*/

        val fruitsallad = database.getReference("fruidsallad")
     // Spara med autoid
        /*
        val somefruit = Fruit("Kiwi", "grön")

        fruitsallad.push().setValue(somefruit)
        */
     // Hämta list med objekt
        /*
        fruitsallad.get().addOnSuccessListener {
            val allfruits = mutableListOf<Fruit>()
            it.children.forEach{ childsnap ->
                allfruits.add(childsnap.getValue<Fruit>()!!)

            }
            for ( fruit in allfruits!!){
                Log.i("pia11debug", fruit!!.fruitname!!)
            }
        } */


    }


    fun loadfruits() {

        val database = Firebase.database

        val fruitsallad = database.getReference("fruidsallad")

        fruitsallad.get().addOnSuccessListener {
            val fbfruits = mutableListOf<Fruit>()
            it.children.forEach{ childsnap ->
                fbfruits.add(childsnap.getValue<Fruit>()!!)

            }
            fadapter.allfruit = fbfruits
            fadapter.notifyDataSetChanged()
        }
    }
}

data class Fruit(val fruitname : String? = null, val fruitcolor : String? = null){

}
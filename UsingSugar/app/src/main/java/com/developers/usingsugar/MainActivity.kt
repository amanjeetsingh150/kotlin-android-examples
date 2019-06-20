package com.developers.usingsugar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import com.orm.SugarRecord
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Duration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //insert operation by save() func
        val pokemon1 = Pokemon("Pikachu", "Ash", "Electric")
        pokemon1.save()
        val pokemon2 = Pokemon("Onyx", "Brock", "Rock")
        pokemon2.save()
        val pokemon3 = Pokemon("StarFish", "Misty", "Water")
        pokemon3.save()
        val pokemonList = SugarRecord.listAll(Pokemon::class.java)
        for (pokemon in pokemonList) {
            pokemon_desc_textview.append(pokemon.name + " of " + pokemon.trainerName + " which is "
                    + pokemon.pokemonType + " type.\n")
        }
        //Delete function
        if (pokemon3.delete()) {
            toast("DELETED POKEMON 3 "+pokemon3.name)
        }
    }

    private fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}

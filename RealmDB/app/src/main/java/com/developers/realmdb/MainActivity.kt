package com.developers.realmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.delete
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var realm: Realm
    lateinit var list: Sequence<Pokemon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getDefaultInstance()
        insert_button.setOnClickListener { view ->
            realm.beginTransaction()
            val pokemon = realm.createObject<Pokemon>()
            pokemon.name = pokemon_name_edittext.text.toString()
            pokemon.type = pokemon_type_edittext.text.toString()
            realm.commitTransaction()
            Snackbar.make(view, "Inserted", Snackbar.LENGTH_SHORT).show()
        }
        delete_all_button.setOnClickListener { view ->
            realm.beginTransaction()
            realm.delete<Pokemon>()
            realm.commitTransaction()
            Snackbar.make(view, getString(R.string.deleted_all), Snackbar.LENGTH_SHORT).show()
        }
        view_button.setOnClickListener {
            list = realm.where<Pokemon>()
                    .findAllAsync()
                    .asSequence()
            if (list.count() > 0) {
                for (pokemon in list) {
                    pokemon_text_view.append(pokemon.name + " is " + pokemon.type + " type.\n")
                }
            } else {
                pokemon_text_view.text = getString(R.string.no_value_string)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //Imp
        realm.close()
    }
}

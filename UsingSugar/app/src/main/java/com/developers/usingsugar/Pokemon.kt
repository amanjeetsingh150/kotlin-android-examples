package com.developers.usingsugar

import com.orm.SugarRecord

/**
 * Created by Amanjeet Singh on 17/11/17.
 */
data class Pokemon(val name: String = "",
              val trainerName: String = "",
              val pokemonType: String = "") : SugarRecord() {

}

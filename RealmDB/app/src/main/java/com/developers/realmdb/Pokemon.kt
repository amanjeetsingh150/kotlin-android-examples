package com.developers.realmdb

import io.realm.RealmObject

/**
 * Created by Amanjeet Singh on 8/2/18.
 */
open class Pokemon : RealmObject() {

    var name: String? = null
    var type: String? = null
}
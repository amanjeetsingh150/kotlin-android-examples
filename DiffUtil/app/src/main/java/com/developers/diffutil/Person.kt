package com.developers.diffutil

/**
 * Created by Amanjeet Singh on 17/1/18.
 */
data class Person(var name: String, var status: String) : Comparable<Person> {


    override fun compareTo(other: Person): Int {
        if (other.name == (this.name) && other.status == this.status) {
            return 0
        }
        return 1
    }


}
package com.developers.diffutil

/**
 * Created by Amanjeet Singh on 15/1/18.
 */
class DataSource {


    fun getData(): MutableList<Person> {
        val personOne = Person("Jasmeet Singh",
                "Love is in the air lets tie a mask.")
        val personTwo = Person("Jeeveshu", "I don't want to eat man.")
        val personThree = Person("Jaspreet Singh", "I want to fly high.")
        val personFour = Person("Undertaker", "In love with WWE.")
        return mutableListOf<Person>(personOne, personTwo, personThree, personFour)
    }

    fun getUpdatedData(): MutableList<Person> {
        val listOfPerson = getData()
        listOfPerson[0].name = "Jasmeet Singh"
        listOfPerson[0].status = "I have dependency on it. Let's take dependency injector."
        print(listOfPerson)
        return listOfPerson
    }
}
package com.developers.listadapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developers.listadapter.model.Language

class MainViewModel : ViewModel() {

    private val initialData = listOf(
        Language(1, "Kotlin", "Kotlin is a rockstar language"),
        Language(2, "Java", "Java is also superstar in its own terms"),
        Language(3, "Swift", "A swift a day binds apple with you")
    )
    private val languageList = MutableLiveData<List<Language>>()

    init {
        languageList.postValue(initialData)
    }

    fun getLanguageList() = languageList

    fun refreshList() {
        val languageListData = initialData.toMutableList()
        languageListData.add(
            Language(4, "Ktor", "Backend lovers this is for you")

        )
        languageListData.add(Language(5, "Kitura", "Swift backend lovers"))
        languageList.postValue(languageListData)
    }
}
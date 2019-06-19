package com.developers.diffutil

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import java.util.logging.Logger

/**
 * Created by Amanjeet Singh on 17/1/18.
 */
class ContactDiffUtilCallBack(val newList: List<Person>,
                              val oldList: List<Person>) : DiffUtil.Callback() {


    companion object {
        val log = Logger.getLogger("ContactDiffUtilCallBack")
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val result = newList[newItemPosition].compareTo(oldList[oldItemPosition])
        if (result == 0) {
            return true
        }
        return false
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val newPerson = newList[newItemPosition]
        val oldPerson = oldList[oldItemPosition]
        val bundle = Bundle()
        if (!(newPerson.name == oldPerson.name)) {
            log.info("Changes put")
            bundle.putString("name", newPerson.name)
        }
        if (!(newPerson.status == oldPerson.status)) {
            log.info("Changes put to status")
            bundle.putString("status", newPerson.status)
        }
        return bundle
    }
}
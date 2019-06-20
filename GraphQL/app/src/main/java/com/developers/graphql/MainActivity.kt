package com.developers.graphql

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.repo_layout.*
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {

    private lateinit var gitApi: ApiInterface
    private lateinit var disposable:Disposable

    companion object {
        val Log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_find.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            val repoName = repo_name_edittext.text
            val ownerName = owner_name_edittext.text
            val query = "query {" +
                    "repository(owner:\"$ownerName\", name:\"$repoName\") {" +
                    "name" +
                    " description" +
                    " forkCount" +
                    " url" +
                    "  }" +
                    "}"

            /*
            query for getting a repository name->"butterknife" with owner name->
            "jakewharton" query would be like:

            query{
             repository(owner:"jakewharton", name:"butterknife"){
                name
                description
                forkCount
                url
              }
            }

            This query will bring up the name,description,forkCount and url of
            the corresponding repo and owner.
             */

            val queryBody = Query()
            queryBody.query = query
            Log.info("Query: " + Gson().toJson(queryBody))

            if (repo_name_edittext.text.isEmpty() || owner_name_edittext.text.isEmpty()) {
                Snackbar.make(coordinator_layout, getString(R.string.error_blank),
                        Snackbar.LENGTH_SHORT).show()
                progress_bar.visibility = View.GONE
            } else {

                gitApi = ApiInterface.create()
                disposable = gitApi.getInfo(queryBody)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ result ->
                            Log.info("Result: " + result.data.repository.name)

                            val nameSpannableBuilder = SpannableStringBuilder()
                            val spanString = SpannableString(getString(R.string.name_text))
                            spanString.setSpan(ForegroundColorSpan(Color
                                    .parseColor("#303F9F")), 0,
                                    5, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                            nameSpannableBuilder.append(spanString)
                            val name = " " + result.data.repository.name
                            val nameSpannable = SpannableString(name)
                            nameSpannable.setSpan(ForegroundColorSpan(Color.BLACK),
                                    0, name.length, 0)
                            nameSpannableBuilder.append(nameSpannable)
                            name_text_view.setText(nameSpannableBuilder, TextView.BufferType.SPANNABLE)


                            val descriptionSpannableBuider = SpannableStringBuilder()
                            val descString = SpannableString(getString(R.string.description_text))
                            descString.setSpan(ForegroundColorSpan(Color
                                    .parseColor("#303F9F")), 0, 12,
                                    Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                            descriptionSpannableBuider.append(descString)
                            val description = " " + result.data.repository.description
                            val descSpannable = SpannableString(description)
                            descSpannable.setSpan(ForegroundColorSpan(Color.BLACK),
                                    0, description.length, 0)
                            descriptionSpannableBuider.append(descSpannable)
                            description_text_view.setText(descriptionSpannableBuider,
                                    TextView.BufferType.SPANNABLE)

                            val forksSpannableBuilder = SpannableStringBuilder()
                            val forkString = SpannableString(getString(R.string.fork_text))
                            forkString.setSpan(ForegroundColorSpan(Color.parseColor(
                                    "#303F9F")), 0,
                                    6, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                            forksSpannableBuilder.append(forkString)
                            val forkCount = " " + result.data.repository.forkCount.toString()
                            val forkSpan = SpannableString(forkCount)
                            forkSpan.setSpan(ForegroundColorSpan(Color.BLACK),
                                    0, forkCount.length, 0)
                            forksSpannableBuilder.append(forkSpan)
                            forks_text_view.setText(forksSpannableBuilder,
                                    TextView.BufferType.SPANNABLE)

                            val urlSpannableBuilder = SpannableStringBuilder()
                            val urlString = SpannableString(getString(R.string.url_text))
                            urlString.setSpan(ForegroundColorSpan(Color.parseColor(
                                    "#303F9F")), 0,
                                    4, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                            urlSpannableBuilder.append(urlString)
                            val url = " " + result.data.repository.url
                            val urlSpan = SpannableString(url)
                            urlSpan.setSpan(ForegroundColorSpan(Color.BLACK),
                                    0, url.length, 0)
                            urlSpannableBuilder.append(urlSpan)
                            url_text_view.setText(urlSpannableBuilder,
                                    TextView.BufferType.SPANNABLE)
                            progress_bar.visibility = View.GONE
                        }, { e ->
                            e.printStackTrace()
                        })
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if(!(disposable.isDisposed)){
            disposable.dispose()
        }
    }
}

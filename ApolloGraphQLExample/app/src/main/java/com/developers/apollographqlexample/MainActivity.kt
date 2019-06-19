package com.developers.apollographqlexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import kotlinx.android.synthetic.main.repo_layout.*
import okhttp3.OkHttpClient
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var client: ApolloClient

    companion object {
        val Log = Logger.getLogger(MainActivity::class.java.name)
        private const val BASE_URL = "https://api.github.com/graphql"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        client = setupApollo()
        button_find.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            client.query(FindQuery
                    .builder()
                    .name(repo_name_edittext.text.toString())
                    .owner(owner_name_edittext.text.toString())
                    .build())
                    .enqueue(object : ApolloCall.Callback<FindQuery.Data>() {

                        override fun onFailure(e: ApolloException) {
                            Log.info(e.message.toString())
                        }

                        override fun onResponse(response: Response<FindQuery.Data>) {
                            Log.info(" " + response.data()?.repository())
                            runOnUiThread {
                                progress_bar.visibility = View.GONE
                                name_text_view.text = String.format(getString(R.string.name_text),
                                        response.data()?.repository()?.name())
                                description_text_view.text = String.format(getString(R.string.description_text),
                                        response.data()?.repository()?.description())
                                forks_text_view.text = String.format(getString(R.string.fork_count_text),
                                        response.data()?.repository()?.forkCount().toString())
                                url_text_view.text = String.format(getString(R.string.url_count_text),
                                        response.data()?.repository()?.url().toString())
                            }
                        }

                    })
        }

    }

    private fun setupApollo(): ApolloClient {
        val okHttp = OkHttpClient
                .Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val builder = original.newBuilder().method(original.method(),
                            original.body())
                    builder.addHeader("Authorization"
                            , "Bearer " + BuildConfig.AUTH_TOKEN)
                    chain.proceed(builder.build())
                }
                .build()
        return ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttp)
                .build()
    }
}

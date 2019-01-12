# CoroutineAdapter
Coroutines are used to simplify asynchronous programming. They are very light and can be sometimes used in place of RxJava for performing async operations with less code. Coroutine Adapter is a call Adapter around Retrofit for Http calls developed by Jake Wharton.
<br><br>
<b>Docs: </b> https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter
<br><br>
In this example we asynchronously hit 2 endpoints 1st is from where we get id and 2nd is to get the list of crew. We use coroutine Adapter of retrofit to asynchronously hit the 2 end points. 
<br><br>
To integrate the adapter integrate following dependency:
<br>
<pre>
implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
</pre> 
<br><br>
<b>Screenshots:</b>
<br><br>
<img src="coroutineAdapter.gif"/>

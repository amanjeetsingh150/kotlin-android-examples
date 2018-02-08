# CoroutineAdapter
Coroutines are used to simplify asynchronous programming. They are very light and can be sometimes used in place of RxJava for performing async operations with less code. Coroutine Adapter is a call Adapter around Retrofit for Http calls developed by Jake Wharton.
<br>
<b>Docs: </b> https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter
<br><br>
In this example we asynchronously hit 2 endpoints 1st is from where we get id and 2nd is to get the list of crew. We use coroutine Adapter of retrofit to asynchronously hit the 2 end points. 
<br><br>
PS: Coroutines are in a experimental version. They will soon be stable.
<br><br>
To integrate the adapter integrate following dependency:
<br>
<pre>
compile 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:1.0.0'
</pre> 
<br><br>
Screenshots:
<br><br>
<img src="coroutineAdapter.gif"/>

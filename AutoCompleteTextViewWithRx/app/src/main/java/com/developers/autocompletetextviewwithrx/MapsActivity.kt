package com.developers.autocompletetextviewwithrx

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.developers.autocompletetextviewwithrx.model.AutoCompleteResult
import com.developers.autocompletetextviewwithrx.model.PlaceModel
import com.developers.autocompletetextviewwithrx.utils.ApiInterface
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jakewharton.rxbinding2.widget.textChangeEvents
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.concurrent.TimeUnit


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var composite: CompositeDisposable
    private lateinit var apiDisposable: Disposable
    private lateinit var apiCall: ApiInterface
    private lateinit var arrayAdapter: ArrayAdapter<String>
    val placeList: ArrayList<PlaceModel> = arrayListOf()
    private val placeNameList: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        apiCall = ApiInterface.create()
        composite = CompositeDisposable()
        addObserverOnAutoComplete(auto_complete_places)
    }

    private fun addObserverOnAutoComplete(autoCompleteTextView: AutoCompleteTextView) {
        //Extension function for autocomplete from RxBindings
        val autocompleteResponseObservable: Observable<AutoCompleteResult> = autoCompleteTextView.textChangeEvents()
                .debounce(500, TimeUnit.MILLISECONDS)
                .map { textViewTextChangeEvent -> textViewTextChangeEvent.text().toString() }
                .filter { s -> s.length >= 2 }
                .observeOn(Schedulers.io())
                //"establishment" instructs the Place Autocomplete service to return only business results
                .switchMap { s ->
                    apiCall.getResults(s, "establishment",
                            BuildConfig.PLACE_KEY)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .retry()

        composite.add(autocompleteResponseObservable.subscribe({ placeAutocompleteResult ->
            placeAutocompleteResult.predictions.forEach {
                placeList.add(PlaceModel(it.description, it.placeId))
                placeNameList.add(it.description)
            }
            arrayAdapter = ArrayAdapter(applicationContext,
                    R.layout.list_row, placeNameList)
            autoCompleteTextView.setAdapter(arrayAdapter)
            if (placeNameList.size > 1 &&
                    placeList[0].description.contains(autoCompleteTextView.text.toString())) {
                autoCompleteTextView.showDropDown()
            }
        }, { e -> e.message }))

        autoCompleteTextView.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                if (autoCompleteTextView.isPopupShowing) {
                    autoCompleteTextView.dismissDropDown()
                }
                //make call for LatLng by using placeId of item at this position
                makeCallForDetails(placeList[position].placeId)
            }
        }

    }

    private fun makeCallForDetails(placeId: String) {
        apiDisposable = apiCall.getDetails(placeId, BuildConfig.PLACE_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val location = result.result.geometry.location
                    val loc = LatLng(location.lat, location.lng)
                    hideKeyboard()
                    mMap.addMarker(MarkerOptions().position(loc).title("Marker Selected"))
                    //Set zoom according to your need
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 5f))
                }, { e -> toast(e.message.toString()) })
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}

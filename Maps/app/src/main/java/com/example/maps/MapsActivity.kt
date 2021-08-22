package com.example.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    val locationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        requestAccessFineLocation()
        super.onStart()
        when {
            isFineLocationGranted() -> {
                when {
                    isLocationEnabled() -> setUpLocationListener()
                    else -> showGPSNotEnableDialog()
                }
            }
            else -> requestAccessFineLocation()
        }
    }

    private fun isFineLocationGranted(): Boolean {
        return checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            999 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                when {
                    isLocationEnabled() -> setUpLocationListener()
                    else -> showGPSNotEnableDialog()
                }
            } else {
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun setUpLocationListener() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val locationRequest = LocationRequest.create().apply {
            interval = 2000
            fastestInterval = 2000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            smallestDisplacement = 1f
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {
                        val current = LatLng(location.latitude, location.longitude)
                        if (::mMap.isInitialized) {
                            mMap.addMarker(
                                MarkerOptions().position(current).title("Marker in Current Area")
                            )
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(current))
                        }
                    }
                }

            },
            Looper.myLooper()
        )
    }

//    @SuppressLint("MissingPermission")
//    private fun setUpLocationListener() {
//        val providers = locationManager.getProviders(true)
//        var l: Location? = null
//        for (i in providers.indices.reversed()) {
//            l = locationManager.getLastKnownLocation(providers[i])
//            if (l != null) break
//        }
//        l?.let {
//            if (::mMap.isInitialized) {
//                val current = LatLng(it.latitude, it.longitude)
//                mMap.addMarker(MarkerOptions().position(current).title("Marker in Current Area"))
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(current))
//            }
//        }
//    }


    private fun requestAccessFineLocation() {
        this.requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            999
        )
    }

    private fun isLocationEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
                locationManager.isProviderEnabled((LocationManager.NETWORK_PROVIDER))
    }

    private fun showGPSNotEnableDialog() {
        AlertDialog.Builder(this)
            .setTitle("Enable GPS")
            .setMessage("GPS is required for Google Maps")
            .setCancelable(false)
            .setPositiveButton("Enable Now") { dialogInterface: DialogInterface, i: Int ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                dialogInterface.dismiss()
            }.show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = true
            isCompassEnabled = true
            isMyLocationButtonEnabled = true
            isScrollGesturesEnabledDuringRotateOrZoom = true
        }
        mMap.setMaxZoomPreference(22f)
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.addPolyline(
            PolylineOptions().add(sydney, LatLng(20.0, 78.0))
                .color(ContextCompat.getColor(baseContext, android.R.color.holo_purple))
                .width(3f)
        )
    }
}
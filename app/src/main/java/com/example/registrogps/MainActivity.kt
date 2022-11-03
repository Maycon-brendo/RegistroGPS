package com.example.registrogps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.registrogps.databinding.ActivityMainBinding
import com.example.registrogps.fragments.ListaRegistroFragment
import com.example.registrogps.utils.nav
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setup()
    }

    val sdf = SimpleDateFormat("dd-M-yyyy-hh-mm")
    val currentDate = sdf.format(Date())
    val registros = mutableListOf<String>()
    var estaGravando = false

    private fun setup() {
        setupClickListeners()
        setupconfig()
    }

    private fun setupconfig() {

    }

    private fun setupClickListeners() {

        binding.btnGravar.setOnClickListener {
            callAccessLocation()
            estaGravando = true

        }

        binding.btnEncerrar.setOnClickListener {
            callWriteOnExternalStorage()
            estaGravando = false
        }

        binding.btnTelaLista.setOnClickListener {
            val intent = Intent(this, ListaRegistroFragment::class.java)
            startActivity(intent)

        }

        binding.fabConfigs.setOnClickListener {
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)

        }

    }



    fun callAccessLocation() {
        val permissionAFL = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val permissionACL = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (permissionAFL != PackageManager.PERMISSION_GRANTED &&
            permissionACL != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                callDialog(
                    "É preciso liberar ACCESS_FINE_LOCATION",
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                )
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSIONS_CODE
                )
            }
        } else {
            readMyCurrentCoordinates()
        }


    }

    private fun readMyCurrentCoordinates() {
        val locationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val isGPSEnabled = locationManager.isProviderEnabled(
            LocationManager.GPS_PROVIDER
        )

        val isNetworkEnabled = locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )

        if (!isGPSEnabled && !isNetworkEnabled) {
            Toast.makeText(this, "Habilite o GPS e Rede!", Toast.LENGTH_LONG).show()
            Log.d("Permissao", "Ative os serviços necessários")
        } else {
            if (isGPSEnabled) {
                try {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        2000L, 0f, locationListener
                    )
                } catch (ex: SecurityException) {
                    Log.d("Permissao", "Security Exception")
                }
            } else if (isNetworkEnabled) {
                try {
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        2000L, 0f, locationListener
                    )
                } catch (ex: SecurityException) {
                    Log.d("Permissao", "Security Exception")
                }
            }

        }

    }

    private val locationListener =
        object : LocationListener {
            override fun onLocationChanged(location: Location) {
                if(estaGravando != false){
                Toast.makeText(
                    applicationContext,
                    "Lat: $location.latitude | Long: $location.longitude",
                    Toast.LENGTH_SHORT
                ).show()
                registros.add("Lat: $location.latitude | Long: $location.longitude \n ")
            }}

            override fun onStatusChanged(
                provider: String, status: Int, extras: Bundle
            ) {
            }

            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}

        }

    //////////////// gravar //////////

    fun callWriteOnExternalStorage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                callDialog(
                    "É preciso liberar WRITE_EXTERNAL_STORAGE",
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE
                )
            }
        } else {
            criarFile()
        }
    }

    private val REQUEST_PERMISSIONS_CODE = 12800

    private fun callDialog(messagem: String, permissions: Array<String>) {
        var mDialog = AlertDialog.Builder(this)
            .setTitle("Permissão")
            .setMessage(messagem)
            .setPositiveButton("OK") { dialog, id ->
                ActivityCompat.requestPermissions(
                    this@MainActivity, permissions,
                    REQUEST_PERMISSIONS_CODE
                )
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                dialog.dismiss()
            }
        mDialog.show()
    }

    private fun criarFile() {
        val file = File(getExternalFilesDir(null), "${currentDate}.crd")
        try {
            val os: OutputStream = FileOutputStream(file)
            os.write("\n ${registros} \n".toByteArray())
            os.close()
        } catch (e: IOException) {
            Log.d("Permissao", "Erro de escrita em arquivo")
        }

    }
}
package com.example.sig


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView


class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private var map: MapView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        val ctx: Context = applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's
        //tile servers will get you banned based on this string

        //inflate and create the map
        setContentView(R.layout.activity_main)
        map = findViewById<View>(R.id.map) as MapView
        map!!.setTileSource(TileSourceFactory.MAPNIK)


    }

    public override fun onResume() {
        super.onResume()
        //this will refresh the osmdroid configuration on resuming.
        map?.onResume() //needed for compass, my location overlays, v6.0.0 and up
    }

    public override fun onPause() {
        super.onPause()
        //this will refresh the osmdroid configuration on resuming.
        map?.onPause() //needed for compass, my location overlays, v6.0.0 and up
    }

}
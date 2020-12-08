package com.example.sig

import DemoCollectionAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.OverlayItem

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.widget.TextView
import androidx.lifecycle.*
import com.example.sig.models.EtatRoute
import com.example.sig.models.PARC
import com.example.sig.models.result
import com.example.sig.retrofit.ParcRepository
import com.example.sig.retrofit.ParcService
import com.example.sig.retrofit.RetrofitSingleton
import retrofit2.*

class MainActivity : AppCompatActivity() {

    private lateinit var tv: TextView
    private var resultParcComplet: result? = null
    private var parc: PARC? = null
    private var retourRoute: EtatRoute? = null

    private var mViewModel = MainViewModel()


    private lateinit var demoCollectionAdapter: DemoCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.tv)
        mViewModel.parcList.observe(this, Observer {
            tv.text = it[0].PARC_POINT[0].POI_ID
        })
        mViewModel.getEtatRoute(1, 103).observe(this, Observer {
            tv.text = tv.text.toString() + "     " +  it[0].delai
        })




        //demoCollectionAdapter = DemoCollectionAdapter(supportFragmentManager.findFragmentById())
        viewPager = findViewById(R.id.activity_main_viewPager)
        //viewPager.adapter = demoCollectionAdapter
        /*val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()*/
        map = findViewById<View>(R.id.map) as MapView
        map!!.setTileSource(TileSourceFactory.MAPNIK)

        //zoom on the map
        map!!.zoomController.setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        map!!.setMultiTouchControls(true);

        //default view point
        val mapController = map!!.controller
        mapController.setZoom(9.5)
        val startPoint = GeoPoint(45.750000, 4.850000)
        mapController.setCenter(startPoint)

        /**
         * Gets the current location through location permission
         * TODO: Find a way to declare the current location among the "points de passage"
         */
//        this.mLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), mMapView)
//        this.mLocationOverlay.enableMyLocation()
//        mMapView.getOverlays().add(this.mLocationOverlay)


        /**
         * Enables icons placing with clicklisteners
         * TODO: Get the coordinates of the "points de passage"
         */
//        //your items
//        var items: ArrayList<OverlayItem?>? = ArrayList<OverlayItem>()!!
//        items!!.add(
//            OverlayItem(
//                "Title",
//                "Description",
//                GeoPoint(0.0, 0.0)
//            )
//        ) // Lat/Lon decimal degrees
//
//        //the overlay
//        val mOverlay = ItemizedOverlayWithFocus(
//            items,
//            object : OnItemGestureListener<OverlayItem?> {
//                override fun onItemSingleTapUp(index: Int, item: OverlayItem?): Boolean {
//                    //do something
//                    return true
//                }
//
//                override fun onItemLongPress(index: Int, item: OverlayItem?): Boolean {
//                    return false
//                }
//            }, context
//        )
//        mOverlay.setFocusItemsOnTap(true)
//
//        mMapView.getOverlays().add(mOverlay)

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
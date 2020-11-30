package com.example.sig


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


class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private var map: MapView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflate and create the map
        setContentView(R.layout.activity_main)
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
package com.example.sig

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.sig.dijkstra.DijkstraAlgorithm
import com.example.sig.dijkstra.model.Edge
import com.example.sig.dijkstra.model.Graph
import com.example.sig.dijkstra.model.Vertex
import com.example.sig.models.EtatRoute
import com.example.sig.models.PARC
import com.example.sig.models.PARCPOINT
import com.example.sig.models.result
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import retrofit2.*


class MainActivity : AppCompatActivity() {

    private lateinit var tv: TextView
    private var resultParcComplet: result? = null
    private lateinit var parc: PARC
    private var retourRoute: EtatRoute? = null

    private var mViewModel = MainViewModel()

    private var map: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel.parcList.observe(this, Observer {
            parc = it[0]
            parc.PARC_ROUTE = it[1].PARC_ROUTE
            val startPoint = GeoPoint(
                parc.PARC_POINT[0].POI_Y.toDouble(),
                parc.PARC_POINT[0].POI_X.toDouble()
            )

            var marquer: Marker = Marker(map)
            //marquer.position = startPoint
            for (point in parc.PARC_POINT) {
                marquer = Marker(map)
                marquer.position = GeoPoint(point.POI_Y.toDouble(), point.POI_X.toDouble())
                marquer.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marquer.title = point.POI_NOM
                map!!.overlays.add(marquer)
            }
            map!!.controller.setCenter(startPoint)

            val vertexFrom: Vertex<PARCPOINT> = Vertex(parc.PARC_POINT[0])
            val vertexTo: Vertex<PARCPOINT> = Vertex(parc.PARC_POINT[parc.PARC_POINT.size - 1])

            var edges: ArrayList<Edge> = ArrayList()
            var vertexDepRoute: Vertex<PARCPOINT?>? = null
            var vertexFinRoute: Vertex<PARCPOINT?>? = null

            for (route in parc.PARC_ROUTE){

                vertexDepRoute = Vertex(parc.getPARCPOINTByID(route.ROU_POI_ID_DEB.toInt()))
                vertexFinRoute = Vertex(parc.getPARCPOINTByID(route.ROU_POI_ID_FIN.toInt()))

                edges.add(Edge(vertexDepRoute,vertexFinRoute,parc.getInteretRoute(route)))

            }

            val pathLength = DijkstraAlgorithm(Graph(edges)).execute(vertexFrom).getShortestPathLength(vertexFrom, vertexTo)
            Log.d("DEBUG",pathLength.toString())

        })

        /*mViewModel.getEtatRoute(1, 103).observe(this, Observer {
            tv.text = tv.text.toString() + "     " +  it[0].delai
        })*/

        map = findViewById<View>(R.id.map) as MapView
        map!!.setTileSource(TileSourceFactory.MAPNIK)

        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID

        //zoom on the map
        map!!.zoomController.setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        map!!.setMultiTouchControls(true);

        map!!.controller.setZoom(18.0)

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
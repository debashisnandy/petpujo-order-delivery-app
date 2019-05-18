package com.example.petpujoorderdelivery.deliverorder

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.petpujoorderdelivery.R
import com.example.petpujoorderdelivery.model.ChefOrders
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.team.nebula.petpujodelivery.adapter.OrderAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OfflineFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [OfflineFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OfflineFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    val db = FirebaseFirestore.getInstance()
    lateinit var arrList:ArrayList<ChefOrders>
    var dc: ListenerRegistration? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView= inflater.inflate(R.layout.fragment_offline, container, false)
        val docRef: CollectionReference = db.collection("chef-cafeteria")
        var date = Date();
        val formatter = SimpleDateFormat("ddMMYYYY")
        val answer: String = formatter.format(date)
        arrList = ArrayList()
        val orderAdapter= OrderAdapter(arrList,rootView.context,docRef)
        recyclerView = rootView.findViewById(R.id.currentRview) as RecyclerView
        recyclerView.adapter = orderAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        dc = docRef.orderBy("time", Query.Direction.ASCENDING)
            .whereEqualTo("orderStatus","complete")
            .whereEqualTo("orderType","offline").whereEqualTo("date",answer.toInt()).addSnapshotListener(EventListener<QuerySnapshot> { snapshots, e ->

                if (e != null) {
                    Log.w("Errr", "listen:error", e)
                    return@EventListener
                }


                for (dc in snapshots!!.documentChanges) {

                    when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            val chefHis = dc.document.toObject(ChefOrders::class.java)
                            arrList.add(0,chefHis)
                            recyclerView.scrollToPosition(0)
                            //orderAdapter.notifyItemInserted(0)

                        }
                        DocumentChange.Type.MODIFIED -> {
                            val chefHis = dc.document.toObject(ChefOrders::class.java)

                            Log.d("Modify","********************************************"+arrList.indexOf(chefHis))
                        }


                        else ->{

                        }
                    }


                }

            })


        return rootView
    }



}

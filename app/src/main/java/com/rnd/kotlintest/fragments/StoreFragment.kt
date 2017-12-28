package com.rnd.kotlintest.fragments

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rnd.kotlintest.R
import com.rnd.kotlintest.controller.APIController
import com.rnd.kotlintest.models.Movie
import com.rnd.kotlintest.services.ServiceVolley
import com.rnd.kotlintest.utils.Util
import kotlinx.android.synthetic.main.fragment_store.view.*
import kotlinx.android.synthetic.main.store_item_row.view.*
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Devrepublic-14 on 12/26/2017.
 */
class StoreFragment : BaseFragment() {

    private val TAG = StoreFragment::class.java.simpleName

    // url to fetch shopping items
    private val URL = "https://api.androidhive.info/json/movies_2017.json"

    private var recyclerView: RecyclerView? = null
    val itemsList = ArrayList<Movie>()
    private var mAdapter: StoreAdapter? = null

    fun newInstance(param1: String, param2: String): StoreFragment {
        val fragment = StoreFragment()
        val args = Bundle()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_store, container, false)
        recyclerView = view.recycler_view
        callwsHereToGetData()

        return view
    }

    private fun callwsHereToGetData() {
        //call ws here
        val service = ServiceVolley()
        val apiController = APIController(service)
        val params = JSONObject()
        val meMap = HashMap<String, String>()
        val path = Util.mapToQueryString(meMap)
        apiController.getStringResult(URL, "") { response ->
            // Parse the result
            var jsonArray = JSONArray(response)
            for (i in 0..jsonArray.length() - 1) {
                var jsonObj: JSONObject = jsonArray.getJSONObject(i)
                val title: String = jsonObj.getString("title")
                val image: String = jsonObj.getString("image")
                val price: String = jsonObj.getString("price")
                var movi: Movie = Movie(title, image, price)
                itemsList.add(movi)
            }
            mAdapter = StoreAdapter(activity, itemsList)
            val mLayoutManager = GridLayoutManager(activity, 3)
            recyclerView?.layoutManager = mLayoutManager
            recyclerView?.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(8), true))
            recyclerView?.itemAnimator = DefaultItemAnimator()
            recyclerView?.adapter = mAdapter
            recyclerView?.isNestedScrollingEnabled = true
        }
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }


    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }


    internal inner class StoreAdapter(private val context: Context, private val movieList: List<Movie>) : RecyclerView.Adapter<StoreAdapter.MyViewHolder>() {
        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var name: TextView? = null
            var price: TextView? = null
            var thumbnail: ImageView? = null

            init {
                name = view.title
                price = view.price
                thumbnail = view.thumbnail
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.store_item_row, parent, false)
            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val movie = movieList[position]
            holder.name?.text = movie.title
            holder.price?.text = movie.price
            Glide.with(context)
                    .load(movie.image)
                    .into(holder.thumbnail)
        }

        override fun getItemCount(): Int {
            return movieList.size
        }
    }

}
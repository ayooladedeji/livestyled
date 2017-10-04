package com.livestyledtask.ui.module.articleselection.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import com.livestyledtask.*
import com.livestyledtask.ui.module.articleselection.IEvents
import com.livestyledtask.datamodel.Event
import com.livestyledtask.ui.module.articleselection.viewmodel.MainViewModel
import com.livestyledtask.ui.module.articleselection.view.adapters.EventListAdapter
import com.livestyledtask.ui.dialogs.SimpleDialog
import kotlinx.android.synthetic.main.activity_events.*
import android.view.View
import android.widget.EditText
import java.lang.ref.WeakReference

class EventsActivity : AppCompatActivity(), IEvents.View, EventListAdapter.IOnItemClickListener {

    private lateinit var viewModel: IEvents.ViewModel
    private lateinit var adapter: EventListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.viewReference = WeakReference(this)
        viewModel.loadEventsList("")
        subscribeViews()

    }

    override fun updateRecyclerView(items: List<Any>) {
        adapter = EventListAdapter(items, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun subscribeViews() {
        search_button.setOnClickListener { showSearchDialog() }
        swipe_refresh.setOnRefreshListener { swipe_refresh.isRefreshing = false; viewModel.loadEventsList("") }
        viewModel
                .eventListValue
                .subscribe { updateRecyclerView(it) }
    }

    override fun showError() = SimpleDialog.show(this, getString(R.string.error_text), getString(R.string.error_message), true)

    override fun onItemClick(position: Int) {
        val item = adapter.getItemAt(position)
        if (item is Event) {
            viewModel.eventIsFavourite(item.id)
        }

    }

    override fun showSearchDialog() {
        val searchDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        val searchBox = EditText(this)
        searchBox.hint = getString(R.string.search_dialog_hint)
        searchDialog.setView(searchBox)
        searchDialog
                .setPositiveButton(getString(R.string.search_text)) { dialog, _ ->
                    viewModel.loadEventsList(searchBox.text.toString())
                    dialog.dismiss() }
        searchDialog.setNegativeButton(getString(R.string.cancel_text)) { dialog, _ -> dialog.dismiss() }
        searchDialog.show()
    }

    override fun isLoading(show: Boolean) {
        progress_bar.visibility = if (show) View.VISIBLE else View.GONE
    }

}

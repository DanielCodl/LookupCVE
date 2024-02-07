package cz.utb.fai.lookupcve.ui.notifications

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cz.utb.fai.lookupcve.database.CveViewModel
import cz.utb.fai.lookupcve.databinding.FragmentNotificationsBinding
import androidx.lifecycle.Observer
import cz.utb.fai.lookupcve.R

class NotificationsFragment : Fragment() {

    private lateinit var mCveViewModel: CveViewModel

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Adding Menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.menu_delete -> {
                        // clearCompletedTasks()

                        val builder = AlertDialog.Builder(requireContext())
                        builder.setPositiveButton("Yes"){_,_->
                            mCveViewModel.deleteAllData()
                            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_SHORT).show()
                        }
                        builder.setNegativeButton("No"){_,_ ->}
                        builder.setTitle("Delete everything?")
                        builder.setMessage("Are you sure you want to delete everything?")
                        builder.create().show()
                        true
                    }
                    R.id.menu_delete -> {
                        // loadTasks(true)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerview //findViewById<RecyclerView>(R.id.recyclerview1)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // CveViewModel
        mCveViewModel = ViewModelProvider(this).get(CveViewModel::class.java)
        mCveViewModel.readAllData.observe(viewLifecycleOwner, Observer { cve-> adapter.setData(cve)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
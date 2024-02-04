package cz.utb.fai.lookupcve.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cz.utb.fai.lookupcve.database.CveViewModel
import cz.utb.fai.lookupcve.databinding.FragmentNotificationsBinding
import androidx.lifecycle.Observer

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
package cz.utb.fai.lookupcve.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import cz.utb.fai.lookupcve.databinding.FragmentDashboardBinding
import retrofit2.Response
import java.lang.NullPointerException
import androidx.lifecycle.Observer
import cz.utb.fai.lookupcve.api.ApiServices
import cz.utb.fai.lookupcve.api.RetrofitInstance
import cz.utb.fai.lookupcve.database.CveDTO
import cz.utb.fai.lookupcve.database.CveViewModel
import cz.utb.fai.lookupcve.model.CveJson


class DashboardFragment : Fragment() {

    private lateinit var mCveViewModel: CveViewModel
    private lateinit var dashboardViewModel:DashboardViewModel

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mCveViewModel =ViewModelProvider(this).get(CveViewModel::class.java)


        // Observing Widgets on Fragment page using values from ViewModel and MutableLiveData
        val tvl_id_txt: TextView = binding.idTxt
        dashboardViewModel.id_txt.observe(viewLifecycleOwner) {
            tvl_id_txt.text = it
        }
        val tvl_baseScore_txt: TextView = binding.baseScoreTxt
        dashboardViewModel.baseScore_txt.observe(viewLifecycleOwner) {
            tvl_baseScore_txt.text = it
        }
        val tvl_baseSeverity_txt: TextView = binding.baseSeverityTxt
        dashboardViewModel.baseSeverity_txt.observe(viewLifecycleOwner) {
            tvl_baseSeverity_txt.text = it
        }
        val tvl_vectorString_txt: TextView = binding.vectorStringTxt
        dashboardViewModel.vectorString_txt.observe(viewLifecycleOwner) {
            tvl_vectorString_txt.text = it
        }
        val tvl_published_txt: TextView = binding.publishedTxt
        dashboardViewModel.published_txt.observe(viewLifecycleOwner) {
            tvl_published_txt.text = it
        }
        val tvl_description_txt: TextView = binding.descriptionTxt
        dashboardViewModel.description_txt.observe(viewLifecycleOwner) {
            tvl_description_txt.text = it
        }

        val pb_progressBar: ProgressBar = binding.progressBar
        dashboardViewModel.ProgressBarVisible.observe(viewLifecycleOwner) {
            pb_progressBar.visibility = it
        }

        // Submit CVE number to REST API call - to fetch the data from Internet
        val bt_submit_cve_button = binding.btSubmitCve

        bt_submit_cve_button.setOnClickListener(){
            if (binding.evCveNum.text.isEmpty()){
                Toast.makeText(requireContext(), "CVE number cannot be empty!", Toast.LENGTH_SHORT).show()
            } else{
                // Beginning to fetch the data from Internet - Set the Progress Bar indicator ON
                dashboardViewModel.ProgressBarVisible.value=View.VISIBLE
                val retrofitService =
                    RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)

                // Retrofit call - REST API
                val responseLiveData: LiveData<Response<CveJson>> =
                    liveData {
                        val response = retrofitService.getCveJson(binding.evCveNum.text.toString())
                        emit(response)
                    }

                // Response received, now we need to process it
                responseLiveData.observe(viewLifecycleOwner, Observer {
                    // Handle API call errors - output is NULL (eg. when wrong CVE or mismatch input is provided)
                    if (it.body() != null) {

                        // Json response to Data Object mapping
                        val cveid: String = it.body()?.vulnerabilities!![0].cve.id
                        val published: String = it.body()?.vulnerabilities!![0].cve.published
                        val description: String = it.body()?.vulnerabilities!![0].cve.descriptions[0].value
                        dashboardViewModel.id_txt.value=cveid
                        dashboardViewModel.published_txt.value=published
                        dashboardViewModel.description_txt.value=description

                        // Additional check for NulPointer needed in case of missing cvssMetricV31 data (for old CVEs)
                        var baseScore: Double = 0.0
                        var baseSeverity: String = "N/A"
                        var vectorString: String = "N/A"

                        try {
                            baseScore = it.body()?.vulnerabilities!![0].cve.metrics.cvssMetricV31[0].cvssData.baseScore
                            baseSeverity = it.body()?.vulnerabilities!![0].cve.metrics.cvssMetricV31[0].cvssData.baseSeverity
                            vectorString = it.body()?.vulnerabilities!![0].cve.metrics.cvssMetricV31[0].cvssData.vectorString
                        } catch (e: NullPointerException) {
                            // Fetch older cvssMetricV2 data (for older CVEs) if it exists
                        }

                        dashboardViewModel.vectorString_txt.value=vectorString
                        dashboardViewModel.baseSeverity_txt.value=baseSeverity
                        dashboardViewModel.baseScore_txt.value=baseScore.toString()

                        val record = CveDTO(0, cveid, published, description, baseScore, baseSeverity, vectorString)

                        // Store data to Room database
                        mCveViewModel.addCve(record)

                        Toast.makeText(requireContext(), "Success!", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(requireContext(), "Error fetching data from REST API - CVE \""+binding.evCveNum.text.toString()+"\" does NOT exist!", Toast.LENGTH_LONG).show()
                    }
                    binding.evCveNum.text.clear()
                    // Finished to fetch the data from Internet - Set the Progress Bar indicator back OFF
                    dashboardViewModel.ProgressBarVisible.value=View.INVISIBLE
                })

            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
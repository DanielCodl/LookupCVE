package cz.utb.fai.lookupcve.ui.dashboard

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _id_txt = MutableLiveData<String>().apply {
        value = ""// "xCVE-2019-1010218"
    }
    private val _baseScore_txt = MutableLiveData<String>().apply {
        value = ""// "x7.5"
    }
    private val _baseSeverity_txt = MutableLiveData<String>().apply {
        value = ""// "xHIGH"
    }
    private val _vectorString_txt = MutableLiveData<String>().apply {
        value = ""// "xCVSS:3.1/AV:N/AC:L/PR:N/UI:N/S:U/C:N/I:N/A:H"
    }
    private val _published_txt = MutableLiveData<String>().apply {
        value = ""// "x2019-07-22T18:15:10.917"
    }
    private val _description_txt = MutableLiveData<String>().apply {
        value = ""// "xCherokee Webserver Latest Cherokee Web server Upto Version 1.2.103 (Current stable) is affected by: Buffer Overflow - CWE-120. The impact is: Crash. The component is: Main cherokee command. The attack vector is: Overwrite argv[0] to an insane length with execl. The fixed version is: There's no fix yet."
    }

    private val _ProgressBarVisible = MutableLiveData<Int>().apply {
        value = View.INVISIBLE
    }

    val id_txt: MutableLiveData<String> = _id_txt
    val baseScore_txt: MutableLiveData<String> = _baseScore_txt
    val baseSeverity_txt: MutableLiveData<String> = _baseSeverity_txt
    val vectorString_txt: MutableLiveData<String> = _vectorString_txt
    val published_txt: MutableLiveData<String> = _published_txt
    val description_txt: MutableLiveData<String> = _description_txt

    val ProgressBarVisible: MutableLiveData<Int> =_ProgressBarVisible

}



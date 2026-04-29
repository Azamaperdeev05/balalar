package kz.diploma.securefinance.ui.settings

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import kz.diploma.securefinance.BuildConfig
import kz.diploma.securefinance.R

class AboutFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tvVersion).text = BuildConfig.VERSION_NAME
        view.findViewById<TextView>(R.id.tvDescription).text = getString(R.string.about_description)
        view.findViewById<TextView>(R.id.tvTechnology).text = getString(R.string.about_technology)
    }
}

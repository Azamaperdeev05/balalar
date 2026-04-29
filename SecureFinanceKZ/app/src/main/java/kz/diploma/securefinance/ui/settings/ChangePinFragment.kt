package kz.diploma.securefinance.ui.settings

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChangePinFragment : Fragment() {

    private lateinit var tilOld: TextInputLayout
    private lateinit var tilNew: TextInputLayout
    private lateinit var tilConfirm: TextInputLayout
    private lateinit var etOld: TextInputEditText
    private lateinit var etNew: TextInputEditText
    private lateinit var etConfirm: TextInputEditText
    private lateinit var btnChange: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_change_pin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tilOld = view.findViewById(R.id.tilOldPin)
        tilNew = view.findViewById(R.id.tilNewPin)
        tilConfirm = view.findViewById(R.id.tilConfirmPin)
        etOld = view.findViewById(R.id.etOldPin)
        etNew = view.findViewById(R.id.etNewPin)
        etConfirm = view.findViewById(R.id.etConfirmPin)
        btnChange = view.findViewById(R.id.btnChangePin)
        progressBar = view.findViewById(R.id.progressBar)

        btnChange.setOnClickListener { attemptChange() }
    }

    private fun attemptChange() {
        val oldPin = etOld.text?.toString() ?: ""
        val newPin = etNew.text?.toString() ?: ""
        val confirmPin = etConfirm.text?.toString() ?: ""

        tilOld.error = null
        tilNew.error = null
        tilConfirm.error = null

        if (oldPin.length < 4) { tilOld.error = getString(R.string.setup_pin_too_short); return }
        if (newPin.length < 4) { tilNew.error = getString(R.string.setup_pin_too_short); return }
        if (newPin != confirmPin) { tilConfirm.error = getString(R.string.setup_pin_mismatch); return }

        setLoading(true)
        val app = requireActivity().application as FinanceApp
        val oldChars = oldPin.toCharArray()
        val newChars = newPin.toCharArray()

        lifecycleScope.launch {
            val success = withContext(Dispatchers.Default) {
                app.cryptoManager.changePin(oldChars, newChars)
            }
            oldChars.fill('0')
            newChars.fill('0')
            setLoading(false)

            if (success) {
                Snackbar.make(requireView(), getString(R.string.change_pin_success), Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                tilOld.error = getString(R.string.change_pin_wrong_old)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        btnChange.isEnabled = !loading
    }
}

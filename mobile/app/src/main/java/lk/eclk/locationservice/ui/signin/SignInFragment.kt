package lk.eclk.locationservice.ui.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.sign_in_fragment.*

import lk.eclk.locationservice.R
import lk.eclk.locationservice.internal.eventexecutor.MessageEvents
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SignInFragment : Fragment(), KodeinAware, MessageEvents {

    override val kodein: Kodein by closestKodein()
    private lateinit var viewModel: SignInViewModel
    private val viewModelFactory: SignInViewModelFactory by instance()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)
        bindUI()
    }

    private fun bindUI() {
        viewModel.liveMessageEvent.setEventReceiver(this, this)
        viewModel.signingIn.observe(viewLifecycleOwner, Observer { state ->
            progress_horizontal.visibility = if (state) View.VISIBLE else View.INVISIBLE
            btn_login.isEnabled = !state
        })

        btn_login.setOnClickListener {
            val username = et_username.text.toString()
            val password = et_password.text.toString()

            viewModel.signIn(username, password)
        }
    }

    override fun navigateUp(): Boolean {
        // don't need to override.no fragment before the sign in in stack
        return false
    }

    override fun navigate(action: Int) {
        navController.navigate(action)
    }

    override fun showSnackBar(s: String) {
        if (view != null) Snackbar.make(view!!, s, Snackbar.LENGTH_SHORT).show()
    }
}

package ru.spb.iac.kotlin_mobile_template.activitities.login.presenter

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.broadcast
import retrofit2.Response
import ru.spb.iac.kotlin_mobile_template.activitities.login.presenter.di.DaggerLoginPresenterComponent
import ru.spb.iac.kotlin_mobile_template.activitities.login.presenter.di.LoginPresenterComponent
import ru.spb.iac.kotlin_mobile_template.activitities.login.view.LoginView
import ru.spb.iac.kotlin_mobile_template.activitities.main.model.api.AuthorizationApiEntity
import ru.spb.iac.kotlin_mobile_template.activitities.model.source.api.AuthorizationApiSource
import ru.spb.iac.kotlin_mobile_template.architecture.presenter.AbstractPresenter
import ru.spb.iac.kotlin_mobile_template.databinding.ActivityLoginBinding
import java.io.IOError
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

/**
 *   Created by vladislav on 2/26/20.
 */

class LoginPresenter(view : LoginView, val binding: ActivityLoginBinding) : AbstractPresenter<LoginView>(view) {

    @Inject lateinit var apiSource : AuthorizationApiSource

    init {

        val dependencyComponent = initDagger()
        dependencyComponent.inject(this)
        val email = binding.mailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        var result: Response<AuthorizationApiEntity>? = null
        var crScope: Job = Job()
        binding.submitButton.setOnClickListener {
            if (view.validate()) {
//                view.showProgressBar()
//                sendRequest(apiResponseHandler.prepareSubscribtion (apiSource.getAuthorization(email, password), {
//                    Log.e(TAG, "Request successed")
//                }).doFinally {
//                    view.hideProgressBar()
//                })



            }
        }



        binding.awaiteBackgorund.setOnClickListener {
            crScope.cancel("Error cancelations")
        }

        binding.sendBackground.setOnClickListener {
            crScope = CoroutineScope(Dispatchers.IO).launch {
                for(i in 1..10) {
                    println("$i")
                    delay(1000)
                }
//                result = apiSource.getAuthorization(email, password)
//                result?.apply {
//                    if(isSuccessful) {
//                        println("is Success $isSuccessful")
//                    }
//                }
            }
        }

        binding.resultBackgorund.setOnClickListener {
            println("Active ${crScope.isActive}")
            println("Canceled ${crScope.isCancelled}")
            println("Completed ${crScope.isCompleted}")
        }
    }

    override fun onStart() {
    }

    override fun onDestroyed() {
        subscription.dispose()
    }

    override fun startActivityForResult(intent: Intent?, resposeCode: Int) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return view.actionBar?.onCreateOptionsMenu(menu) ?: false
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean
            = view.actionBar?.onOptionsItemSelected(menuItem) ?: false


    private fun initDagger() : LoginPresenterComponent = DaggerLoginPresenterComponent.create()
}
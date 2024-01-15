package com.raphael.lemon.ui.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.couchbase.lite.MutableDocument
import com.raphael.lemon.data.DBManager
import com.raphael.lemon.ui.utils.Validator
import com.raphael.lemon.ui.utils.ViewEvent

class RegisterViewModel : ViewModel(), ViewEvent<RegisterViewEvent> {

    private val TAG = RegisterViewModel::class.simpleName

    val registerViewState = mutableStateOf(RegisterViewState())

    override fun onEvent(event: RegisterViewEvent) {
        when (event) {

            is RegisterViewEvent.SelectedFirstNameEvent -> {
                registerViewState.value = registerViewState.value.copy(
                    firstName = event.firstName,
                    isFirstNameValid = Validator.validateFirstName(event.firstName)
                )
            }

            is RegisterViewEvent.SelectedLastNameEvent -> {
                registerViewState.value = registerViewState.value.copy(
                    lastName = event.lastName,
                    isLastNameValid = Validator.validateLastName(event.lastName)
                )
            }

            is RegisterViewEvent.SelectedEmailEvent -> {
                registerViewState.value = registerViewState.value.copy(
                    email = event.email,
                    isEmailValid = Validator.validateEmail(event.email)
                )
            }

            is RegisterViewEvent.SelectedPasswordEvent -> {
                registerViewState.value = registerViewState.value.copy(
                    password = event.password,
                    isPasswordValid = Validator.validatePassword(event.password)
                )
            }

            is RegisterViewEvent.CheckedTermOfUse -> {
                registerViewState.value = registerViewState.value.copy(
                    isPrivacyPolicyAccepted = event.isChecked
                )
            }

            is RegisterViewEvent.RegisterEvent -> {
                if (isValid()) {
                    Log.d(TAG, "Registering user")
                    val database = DBManager.getInstance()?.get("Offices")
                    val collection = database?.createCollection("Users")
                    Log.d(TAG, "Database state : ${database?.collections}")

                    val mutableDocument = MutableDocument()
                    mutableDocument.setString("firstName", registerViewState.value.firstName)
                    mutableDocument.setString("lastName", registerViewState.value.lastName)
                    mutableDocument.setString("email", registerViewState.value.email)
                    mutableDocument.setString("password", registerViewState.value.password)

                    val save = collection?.save(/* document = */ mutableDocument)
                    val document = collection?.getDocument(mutableDocument.id)

                    Log.d(
                        TAG,
                        "The document stored is ${document?.keys} and ${document?.getString("email")}"
                    )
                    Log.d(TAG, "There is ${collection?.count} documents in this collection")

                }
            }
        }

        Log.d(TAG, registerViewState.value.toString())
    }

    public fun isValid(): Boolean {
        return registerViewState.value.isFirstNameValid
                && registerViewState.value.isLastNameValid
                && registerViewState.value.isEmailValid
                && registerViewState.value.isPasswordValid
                && registerViewState.value.isPrivacyPolicyAccepted
    }

}
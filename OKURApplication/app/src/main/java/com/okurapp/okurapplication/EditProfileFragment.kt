package com.okurapp.okurapplication
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class EditProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_edit_profile_fragment, container, false)

        val etCurrentPassword = view.findViewById<EditText>(R.id.etCurrentPassword)
        val etNewPassword = view.findViewById<EditText>(R.id.etNewPassword)
        val btnChangePassword = view.findViewById<Button>(R.id.btnChangePassword)

        btnChangePassword.setOnClickListener {
            val currentPassword = etCurrentPassword.text.toString()
            val newPassword = etNewPassword.text.toString()

            // Şifre değiştirme işlemlerini gerçekleştir

            val user = FirebaseAuth.getInstance().currentUser
            val credential = EmailAuthProvider.getCredential(user?.email!!, currentPassword)

            user?.reauthenticate(credential)?.addOnCompleteListener { reAuthTask ->
                if (reAuthTask.isSuccessful) {
                    user.updatePassword(newPassword).addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            // Şifre değiştirme başarılı,
                        } else {
                            // Şifre değiştirme başarısız,
                        }
                    }
                } else {
                    // Mevcut şifre yanlış,
                }
            }
        }

        return view
    }

}
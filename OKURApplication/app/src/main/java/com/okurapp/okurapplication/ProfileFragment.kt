package com.okurapp.okurapplication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.okurapp.okurapplication.R
import android.widget.TextView
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.okurapp.okurapplication.BookMarkFragment
import com.okurapp.okurapplication.EditProfileFragment




class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_profile_fragment, container, false)

        val usernameTextView = view.findViewById<TextView>(R.id.tvUsername)
        val bookmarkButton = view.findViewById<Button>(R.id.button_bookmark)
        val editProfileButton = view.findViewById<Button>(R.id.button_edit_profile)

        val user = FirebaseAuth.getInstance().currentUser
        val username = user?.displayName
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        tvUsername.text = username



        bookmarkButton.setOnClickListener {
            // Beğenilen Kitaplar sayfasını açmak için gerekli kodu buraya ekleyin
            // Örneğin, yeni bir Fragment oluşturup bu Fragment'i açabilirsiniz
            val bookmarkFragment = BookMarkFragment() // Beğenilen Kitaplar sayfasını temsil eden yeni bir Fragment oluşturun
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, bookmarkFragment) // fragment_container adlı bir container'a yeni Fragment'i yerleştirin
                .addToBackStack(null) // Geri düğmesine basıldığında önceki Fragment'e dönmesini sağlamak için geri yığına ekleyin
                .commit()
        }

        editProfileButton.setOnClickListener {
            // Profili Düzenle sayfasını açmak için gerekli kodu buraya ekleyin
            // Örneğin, yeni bir Fragment oluşturup bu Fragment'i açabilirsiniz
            val editProfileFragment = EditProfileFragment() // Profili Düzenle sayfasını temsil eden yeni bir Fragment oluşturun
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, editProfileFragment) // fragment_container adlı bir container'a yeni Fragment'i yerleştirin
                .addToBackStack(null) // Geri düğmesine basıldığında önceki Fragment'e dönmesini sağlamak için geri yığına ekleyin
                .commit()
        }

        return view
    }
}
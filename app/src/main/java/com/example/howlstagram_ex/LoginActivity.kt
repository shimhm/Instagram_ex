package com.example.howlstagram_ex

import android.content.ComponentCallbacks2
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        email_login_btn.setOnClickListener {
            email_login()
        }

    }

    override fun onStart() {
        super.onStart()
        moveMainPage(auth?.currentUser)
    }


    fun email_login(){
        if(email_edt.text.toString().isNullOrEmpty() || password_edt.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "이메일 혹은 비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show()
        }else{
            signinAndsignup()
        }
    }

    fun signinAndsignup(){
        auth.createUserWithEmailAndPassword(email_edt.text.toString(), password_edt.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    // email로 성공적으로 계정을 만들었을 경우
                    moveMainPage(task.result?.user)
                }else if(task.exception?.message.isNullOrEmpty()){
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }else{
                    // email의 계정이 이미 존재하는 경우
                    signinEmail()
                }
            }
    }

    fun signinEmail(){
        auth.signInWithEmailAndPassword(email_edt.text.toString(), password_edt.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    // login 성공
                    moveMainPage(task.result?.user)
                }else{
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun moveMainPage(user : FirebaseUser?){
        if(user != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}

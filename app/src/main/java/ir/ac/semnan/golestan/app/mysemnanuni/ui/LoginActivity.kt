package ir.ac.semnan.golestan.app.mysemnanuni.ui

import android.app.Dialog
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import ir.ac.semnan.golestan.app.mysemnanuni.database.DatabaseHelper
import ir.ac.semnan.golestan.app.mysemnanuni.databinding.ActivityLoginBinding
import ir.ac.semnan.golestan.app.mysemnanuni.databinding.ChangePasswordDialogBinding
import ir.ac.semnan.golestan.app.mysemnanuni.utils.showSnack

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    private lateinit var user_perefrences:SharedPreferences
    private lateinit var user_edit:SharedPreferences.Editor

    private  var isPassChange:Boolean=false



    private var cancelationsignal: CancellationSignal?=null
    private val AutenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    showSnack(binding.root,"error $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    val intent= Intent(this@LoginActivity, MainActivity::class.java)
                    showSnack(binding.root,"welcome!!", Snackbar.LENGTH_LONG)
                    startActivity(intent)
                }

            }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user_perefrences=getSharedPreferences("student_info",0)
        user_edit=user_perefrences.edit()
        isPassChange=user_perefrences.getBoolean("isPassChange",false)
        if (!isPassChange){
            makeUserData()
        }
        CheckedBiometricsupport()
        doLogin()
        changePassword()

        
    }

    private fun CheckedBiometricsupport() : Boolean {
        val keyguardManager: KeyguardManager =getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isDeviceSecure){
            showSnack(binding.root,"your device dose not have finger print")
            return false
        }
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.USE_BIOMETRIC)
            != PackageManager.PERMISSION_GRANTED){
            showSnack(binding.root,"finger print is not enable")
            return false
        }
        return if(packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            true
        } else true
    }

    private fun getcancelationsignal(): CancellationSignal {
        cancelationsignal= CancellationSignal()
        cancelationsignal?.setOnCancelListener {
            showSnack(binding.root,"your fingerprint is canceld by user")
        }
        return cancelationsignal as CancellationSignal
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun makeUserFinger(){
        val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(this)
            .setTitle("FingerPrint")
            .setTitle("your security is needed")
            .setDescription("this app is use the finger print")
            .setNegativeButton(
                "cancel",
                this.mainExecutor,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    showSnack(binding.root,"Cancel")
                }).build()
        biometricPrompt.authenticate(getcancelationsignal(),mainExecutor,AutenticationCallback)
    }

    private fun makeUserData(){

        user_edit.putString("username","9811126038")
        user_edit.putString("password","12341234")
        user_edit.putBoolean("isPassChange",false)
        user_edit.apply()
    }

    private fun changePassword(){
        binding.tvChangePassword.setOnClickListener {

            val dialogBinding:ChangePasswordDialogBinding= ChangePasswordDialogBinding.inflate(layoutInflater)
            val dialog=Dialog(this)
            .apply {
                setContentView(dialogBinding.root)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialogBinding.btnLogin.setOnClickListener {
                    if (dialogBinding.inputStudentUsername.text.isEmpty() || dialogBinding.inputStudentPassword.text.isEmpty()) {
                        showSnack(dialogBinding.root, "لطفا کددانشجویی و رمزعبور جدید واردشود")

                    } else {
                        if(dialogBinding.inputStudentPassword.text.length>8){
                            if (user_perefrences.getString("username","notfound")==dialogBinding.inputStudentUsername.text.toString()){
                                user_edit.putString("password",dialogBinding.inputStudentPassword.text.toString())
                                user_edit.putBoolean("isPassChange",true)
                                user_edit.apply()
                                dismiss()
                            }
                            else{
                                showSnack(dialogBinding.root,"کد داشنجویی اشتباه است")
                            }
                        }else{
                            showSnack(dialogBinding.root,"رمز عبور جدید بیشتر از 8 تا باشد")
                        }

                    }
                    }
                show()
            }


        }


    }

    private  fun doLogin(){
        binding.btnLogin.setOnClickListener {
            if(binding.inputStudentUsername.text.isEmpty()||binding.inputStudentPassword.text.isEmpty()){
                showSnack(binding.root,"کد دانشجویی یارمز عبور راواردکنید")
            }
            else if (binding.inputStudentUsername.text.toString()!=user_perefrences.getString("username","notFound")||
                binding.inputStudentPassword.text.toString()!=user_perefrences.getString("password","notFound")){
                showSnack(binding.root,"رمز یا کددانشجویی اشتباه است")
            }
            else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        binding.ivBtnUseFinger.setOnClickListener {
            makeUserFinger()
        }
    }
}
package me.santiagobrito.unabstore

import android.util.Patterns
import org.intellij.lang.annotations.Pattern

fun validateEmail(email:String): Pair<Boolean, String>{
    return when{
        email.isEmpty() -> Pair(false, "El correo es requerido.")
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Pair (false, "El correo es invalido.")
        !email.endsWith("@test.com") -> Pair(false, "Ese email no es de la Universidad.")
        else -> Pair(true,"")
    }

}

fun validatePassword(password:String): Pair<Boolean, String> {

    return when{
        password.isEmpty() -> Pair(false, "La contraseña es requerida.")
        password.length < 8 ->Pair(false, "La contraseña debe tener almenos 8 caracteres.")
        !password.any{ it.isDigit() } -> Pair(false, "La contraseña debe tener almenos un numero.")
        else -> Pair(true,"")
    }
}

fun validateName (name: String): Pair<Boolean, String>{
    return  when{
        name.isEmpty() -> Pair(false, "El nombre es requerido.")
        name.length < 3 -> Pair(false, "El nombre debe tener almenos tres caracteres.")
        else -> Pair(true, "")
    }
}

fun validateConfirmPassword(password: String, confirmPassword: String): Pair <Boolean, String>{
    return when{
        confirmPassword.isEmpty() -> Pair(false, "La contraseña es requerida.")
        confirmPassword != password -> Pair(false, "La contraseña no coinciden")
        else -> Pair(true,"")
    }
}
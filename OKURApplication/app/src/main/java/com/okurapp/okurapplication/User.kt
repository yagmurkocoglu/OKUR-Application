package com.okurapp.okurapplication

class User {
    var createUserName: String?= null
    var createPassword: String? = null

    constructor() {
        // Parametresiz yapıcı
    }

    constructor(createUserName: String?,createPassword: String?){
        this.createUserName= createUserName
        this.createPassword= createPassword
    }
}
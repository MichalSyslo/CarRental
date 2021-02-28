function validate() {
    var login = document.forms["register-form"]["login"].value;
    var pass = document.getElementById("pass").value;
    var pass2 = document.getElementById("pass2").value;
    var name = document.getElementById("name").value;
    var surname = document.getElementById("surname").value;

    var regex = new RegExp(/[A-Za-z0-9]{5}.*/);
    var regex2 = new RegExp("[A-Za-z]{3}.*");
    var flag = true;


    if(!regex.test(login)){
        document.getElementById("login").style.backgroundColor= 'RED';
        flag = false;
    } else{
        document.getElementById("login").style.backgroundColor = 'WHITE';

    }

    if(!regex.test(pass)){
        document.getElementById("pass").style.backgroundColor= 'RED';
        document.getElementById("pass2").style.backgroundColor= 'RED';
        flag = false;
    } else{
        document.getElementById("pass").style.backgroundColor = 'WHITE';
        document.getElementById("pass2").style.backgroundColor= 'WHITE';
    }

    if(pass !== pass2){
        document.getElementById("pass").style.backgroundColor= 'RED';
        document.getElementById("pass2").style.backgroundColor= 'RED';
        flag = false;
    } else if (flag){
        document.getElementById("pass").style.backgroundColor = 'WHITE';
        document.getElementById("pass2").style.backgroundColor= 'WHITE';
    }

    if(!regex2.test(name)){
        document.getElementById("name").style.backgroundColor= 'RED';
        flag = false;
    }else{
        document.getElementById("name").style.backgroundColor = 'WHITE';
    }

    if(!regex2.test(surname)){
        document.getElementById("surname").style.backgroundColor= 'RED';
        flag = false;
    }else{
        document.getElementById("surname").style.backgroundColor = 'WHITE';
    }

    return flag;
}
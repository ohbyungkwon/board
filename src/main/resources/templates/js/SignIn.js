window.onload=function() {
    //////////////////////////////회원가입
    var EmailDuplicate = document.getElementById("EmailDuplicate");
    EmailDuplicate.onclick = function () {
        $.ajax({
            url: '/checkEmail',
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify({"userEmail": $("#InputEmail").val()}),
            success: function (data){
                console.log(data);
                $("#InputEmail").attr('disabled',true);
                $("#EmailDuplicate").attr('disabled',true);
                $("#EmailChange").attr('disabled',false);
            },
            error: function(data){
                alert(data.responseText);
                console.log(data);
            }
        });
        return false;
    };

    $("#InputPWD").keyup(function(){
        var data = {
            "password" : $("#InputPWD").val()
        };

        $.ajax({
            url: '/checkPwd',
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data),
            success: function (data){
                console.log(data);
                $("#passwordComment").text(data.responseText);
            },
            error: function(data){
                console.log(data);
                $("#passwordComment").text(data.responseText);
            }
        });
        return false;

    });

    $("#ReInputPWD").keyup(function(){
        var password = $("#InputPWD").val();
        var rePassword = $("#ReInputPWD").val();

        if(password === rePassword){
            $("#duplicateComment").text("패스워드 일치");
        }else {
            $("#duplicateComment").text("패스워드 불일치");
        }
    });


    var SendLoginPage = document.getElementById("SendLoginPage");
    SendLoginPage.onclick = function () {
        var temp = {
            "username": $("#InputEmail").val(),
            "password": $("#InputPWD").val()
        };

        $.ajax({
            url: '/users',
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(temp),
            success:function(data){
                location.replace("/register");
            },
            error: function(){
                alert("회원가입 실패");
            }
        });
        return false;
    }

    $("#EmailChange").onclick = function(){
        $("#EmailChange").attr("disabled", false);
        $("#EmailDuplicate").attr("disabled", true);
        $("#InputEmail").attr("disabled", true);
    }
}
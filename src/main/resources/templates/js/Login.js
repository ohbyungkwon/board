window.onload= function() {
    var btn = document.getElementsByClassName('inputBtn');

    var index = btn.length;
    var url;

    console.log(index);

    for(var i = 0; i < index; i++) {
        (function(m){
            document.getElementById(btn[m].id).addEventListener("click",function() {
                if(btn[m].id == 1){
                    url = "/signIn";
                }

                location.replace(url);
                console.log(url);
            });
        })(i);
    }//button click
}
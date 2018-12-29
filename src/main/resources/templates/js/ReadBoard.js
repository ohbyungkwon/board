window.onload = function () {
    var update_bno;
    //댓
    $("button[name='replyOutBtn']").click(function () {
        var bno = this.value;
        var data;
        var action;
        var url;

        if($("button[name='replyOutBtn']").text() === 'Update'){
            url = "/comment/"+update_bno;
            data ={
                content: $("#outterText").val(),
                bno: bno,
                writer : $("#writer_"+update_bno+"").text()
            };
            action='PUT';
        }
        else {
            url = "/comment";
            data = {
                content: $("#outterText").val(),
                bno: bno
            };
            action = 'POST';
        }
        console.log(action);

        $.ajax({
            url: url,
            method: action,
            dataType:'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data),
            success: function (data) {
                alert(data.data);
                location.reload();
            }
        });
        return false;
    });

    var count = 0;
    var reply_bno;

    $("a[name='replyAgain']").click(function () {
        count++;
        reply_bno = this.id;

        var innerText = document.getElementById("InnerText_"+reply_bno);
        var replyBtn = document.getElementById("replyBtn_"+reply_bno);

        if(count%2 == 1){
            innerText.style.display="block";
            replyBtn.style.display="block";
        }
        else {
            innerText.style.display = "none";
            replyBtn.style.display = "none";
        }
    });

    $("a[name='replyUpdate']").click(function () {
        update_bno = this.id;

        // console.log("1. "+$("#replyContent_"+update_bno+"").text());
        // console.log("2. "+$("#replyContent_"+update_bno+"").val());

        $('#outterText').text($("#replyContent_"+update_bno+"").text());
        $("button[name='replyOutBtn']").text("Update");
    });

    $("a[name='replyDel']").click(function () {
        reply_bno = this.id;

        $.ajax({
            url: '/comment/'+reply_bno,
            method: 'delete',
            dataType:'json',
            contentType: 'application/json; charset=UTF-8',
            success: function(data){
                alert(data.data);
                location.reload();
            }
        });
    });
};
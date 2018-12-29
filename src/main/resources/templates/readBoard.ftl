<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Board</title>
    <link rel="stylesheet" type="text/css" href="/dist/js/total.css">
    <link rel="stylesheet" type="text/css" href="/dist/js/semantic.css">
    <link rel="stylesheet" type="text/css" href="/dist/js/bootstrap.css">

    <script src="/dist/js/vendor.js"></script>
    <script src="/dist/js/readBoard.js"></script>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
    <div class="header_c">
        <h1 class="centerHeader">게시글 상세</h1>
        <hr>
    </div>
    <div class="content_c">
        <h2>제목 : ${board.getTitle()}</h2>
        <span class="metaFont">작성자 : ${board.getWriter()}</span>
        <p class="metaFont">작성날짜 : ${board.getRegisterDate()}</p
        </br>
        <div class="ui attached segment">
            <div class="meta">
            </div>
            </br>
            <div class="description">
                <p>${board.getContent()}</p>
            </div>
        </div>
        <a href="/board/page=1">목록으로 이동</a>
    </div>

    <div class="ui center aligned basic segment">
        <div class="ui horizontal divider">
            Comments
        </div>
        <form class="ui reply form">
            <div class="fields">
                <div class="fourteen wide field">
                    <textarea id="outterText" rows="1"></textarea>
                </div>
                <div class="two wide field">
                    <button class="ui blue button" id="outterBtn" name="replyOutBtn" value="${board.getSeq()}">ADD Reply</button>
                </div>
            </div>
        </form>
    </div>
    <div class="content_c">
        <#list comments as comment_each>
        <div class="commentDto">
            <div class="content">
                <div class="text">
                    <strong id="writer_${comment_each.getSeq()}">${comment_each.getUser().getUsername()}</strong>
                    <strong id="replyContent_${comment_each.getSeq()}">${comment_each.getContent()}</strong>
                </div>
                <div class="actions">
                    <a id="${comment_each.getSeq()}" name="replyUpdate" class="reply">Update</a> /
                    <a id="${comment_each.getSeq()}" name="replyAgain" class="reply">Reply</a> /
                    <a id="${comment_each.getSeq()}" name="replyDel" class="reply">Delete</a>
                </div>
                <textarea style="display: none; margin: 0px; width:100%; height: 150px" id="InnerText_${comment_each.getSeq()}"></textarea>
                <button style="display: none" id="replyBtn_${comment_each.getSeq()}" class="ui yellow button">답글</button>
            </div>
        </div>
        <br/>
        </#list>
    </div>
</body>
</html>
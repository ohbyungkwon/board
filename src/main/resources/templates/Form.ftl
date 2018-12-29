<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <title>Login Test</title>
    <#-- 외부 css는 css로 묶고 페이지에 해당하는 css파일은 js로 변환해 관리했다.-->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="/dist/js/total.css">
    <link rel="stylesheet" type="text/css" href="/dist/js/semantic.css">
    <link rel="stylesheet" type="text/css" href="/dist/js/bootstrap.css">

    <script src="/dist/js/vendor.js"></script>
    <script src="/dist/js/login.js"></script>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
    <div class ="layout-small">
        <form role="form" method="post" action="j_spring_security_check" id="center-form-small">
            <h2>Login</h2>
            <div class="ui inverted segment">
                <div class="ui inverted form">
                    <div class="three fields">
                        <div class="field">
                            <label>ID 입력</label>
                            <input id="id" type="text" name="j_username" placeholder="Enter your username">
                        </div>
                        <div class="field">
                            <label>PW 입력</label>
                            <input id="pwd" type="password" name="j_password" placeholder="Enter your password">
                        </div>
                        <div class="field">
                            <input type="submit" id="0" name="LOGIN"  value="LOGIN" class="inputBtn"/>
                            <input type="button" id="1" name="SIGNIN" value="SIGNIN" class="inputBtn"/>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</body>

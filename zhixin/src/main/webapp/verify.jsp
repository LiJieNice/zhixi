<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Document</title>

    <script type="text/javascript">
        $(function() {
            changeImg();
        });
        function changeImg(){
            var img = document.getElementById("img");
            img.src = "${RootPath}pfUserBase/web/code?date=" + new Date();
        }
        $(function() {
            $("#myform").Validform({
                btnSubmit:".ok",
                btnReset:".cancel",
                ajaxPost:true,
                tiptype : function(message, o, cssctl) {
                    var objtip = $("#message");
                    cssctl(objtip, o.type);
                    objtip.text("提示：" + message);
                },
                callback:function(data){
                    alert(data.message);
                    window.location.href = "${RootPath}"+data.data;
                }
            });
        });
    </script>
    <form id="myform" action="${RootPath}pfUserBase/web/login" method="POST">
        用户名: <input type="text" name="loginName"/> <br/><br/>
        密码: <input type="password" name="userpwd"/><br/><br/>
        验证码： <input type="text" name="verCode" id="vercode"/><br/><br/>
        <img id="img" src="" />
        <a href='javascript:void(0);' onclick="javascript:changeImg()" style="color:white;"><label style="color:blue;">看不清</label></a><br/><br/>
        <div class="button-box">
            <a href="javascript:;" class="ok">提交</a>
            <a href="javascript:;" class="cancel">取消</a>
        </div>
        <div id="message" style="color:red;"></div>
    </form>
</head>
<body>

</body>
</html>

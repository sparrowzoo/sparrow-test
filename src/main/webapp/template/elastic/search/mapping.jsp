
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript"
            src="https://raw.githack.com/sparrowzoo/sparrow-js/master/src/main/webapp/assets/scripts-all/sparrow.js"/>

    <script type="text/javascript" language="JavaScript">
        function submit() {
            var postString = "index=" + $("name.index").value() + "&type=" + $("name.type").value();
            ajax
                    .json(
                            $.url.root + "/elastic/search/mapping.json",
                            postString,
                            function (result) {
                                $("#.txtResult").value($.format(result));
                            });
        }
    </script>
</head>
<body>
<form method="post" action="/elastic/search/mapping.json">
    index:<input name="index" type="text"/> <br/>
    type:<input name="type" type="text"/> </br>
    <input type="submit" onclick="submit"/>

    <textarea id="txtResult">

    </textarea>
</form>
</body>
</html>

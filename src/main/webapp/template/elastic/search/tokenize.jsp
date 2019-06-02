<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
https://www.elastic.co/guide/en/elasticsearch/reference/5.6/configuring-analyzers.html
<form method="post" action="/elastic/search/tokenize.json">
    analyzer:<select name="tokenizer" type="text">
    <option value="standard">standard</option>
    <option value="english">english</option>
    <option value="simple">simple </option>
    <option value="stop">stop </option>
    <option value="keyword">keyword </option>
    <option value="pattern">pattern </option>
    <option value="whitespace">whitespace </option>
    <option value="ik_max_word">Ikanalyzer </option>

</select> <br/>
    text:<textarea rows="5" cols="100" name="text" type="text">here is beijing and i love beijing tiananmen
我爱北京天安门，天安门上太阳升</textarea> </br>
    <input type="submit"/>
</form>
</body>
</html>

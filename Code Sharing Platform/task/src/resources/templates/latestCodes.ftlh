<html>
<head>
    <meta charset="utf-8">
    <title>Latest</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <style>
        table {
            color: black;
            background-color: antiquewhite;
        }
        pre {
            border: 2px inset gray;
            background-color: gray;
            color: lightslategrey;
        }

        span {
            color: lightgreen;
        }
    </style>
</head>
<body>
<h1>Latest codes</h1>
<table>
    <tr>
        <th>Code</th>
        <th>Date</th>
    </tr>
    <#list codes as code>
        <tr>
            <pre id="code_snippet"><code>${code.code}</code></pre>
            <span id="load_date">${code.date}</span>
        </tr>
    </#list>
</table>
</body>
</html>
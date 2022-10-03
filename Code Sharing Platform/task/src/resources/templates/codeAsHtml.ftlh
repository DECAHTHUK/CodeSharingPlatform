<html>
<head>
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <style>
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
    <#if code.startingTime != 0>
        <span id="time_restriction">${code.time}</span>
    </#if>
    <#if code.isViewsInit()>
        <span id="views_restriction">${code.views}</span>
    </#if>
    <span id="load_date">${code.date}</span>
    <pre id="code_snippet"><code>${code.code}</code></pre>
</body>
</html>
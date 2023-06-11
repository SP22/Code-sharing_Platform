<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8"/>
        <link href="/main.css" rel="stylesheet"/>
        <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
        <title>Code</title>
    </head>
    <body>
        <span id="load_date">${code.date}</span>
        <#if (code.time > 0)>
        <span id="time_restriction">The code will be available for ${code.getTime()} seconds</span>
        </#if>
        <#if code.isViewRestricted()>
        <span id="views_restriction">${code.getViews()} more views allowed</span><br>
        </#if>
        <pre id="code_snippet"><code>${code.code}</code></pre>
    </body>
</html>
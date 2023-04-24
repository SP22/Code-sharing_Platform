<!DOCTYPE html>

<html>
    <head>
        <meta charset=UTF-8"/>
        <link href="/main.css" rel="stylesheet"/>
        <title>Latest</title>
    </head>
    <body>
        <table>
            <#list latest as code>
                <tr>
                    <td>
                        <span id="load_date">${code.date}</span>
                        <pre id="code_snippet"><code>${code.code}</code></pre>
                    </td>
                </tr>
            </#list>
        </table>
    </body>
</html>
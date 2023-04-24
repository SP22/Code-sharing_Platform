<!DOCTYPE html>

<html>
    <head>
        <meta charset=UTF-8"/>
        <link href="/main.css" rel="stylesheet"/>
        <title>Create</title>
    </head>
    <body>
        <textarea id="code_snippet">// write your code here</textarea>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>

        <script type="text/javascript">
            function send() {
                let object = {
                    "code": document.getElementById("code_snippet").value
                };

                let json = JSON.stringify(object);

                let xhr = new XMLHttpRequest();
                xhr.open("POST", '/api/code/new', false)
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send(json);

                if (xhr.status === 200) {
                    alert("Success!");
                }
            }
        </script>
    </body>
</html>
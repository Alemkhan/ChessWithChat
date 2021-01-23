
<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="style/chat.css">
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous">
    </script>

    <style>
        *{
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
           
             }
        body {
            height: 100vh;
            width: 100vw;
             background: url("http://avatars.mds.yandex.net/get-pdb/1906603/d648866b-ad7f-43ac-9caf-10b551dabd0a/s1200" ) no-repeat center center fixed;
             
            
        }
        .main{
            width:100%;
            display:flex;
            flex-direction: row;
            margin-left: 20.5em;
           
        }
        .chat{
            width:30%;
            height: 60vh;
            display:flex;
            flex-direction: column;
            padding:20px;
            color:#2C302E;
            background-color: lightgray;
        }
        .messageContainer{
            width:100%;
            height: 60vh;
            overflow-y:scroll;
            display:flex;
            flex-direction: column;
            padding:20px;
        }
        .user1{
            width:100%;
            float:left;
            margin-bottom: 10px;
        }
        .user2{
            width:100%;
            text-align: right;
            margin-bottom: 10px;
        }
        .pill1{
            min-width: 20%;
            padding: 5px 10px;
            float:left;
            background-color: mintcream;
           
            
        }
        .pill2{
            
            min-width: 20%;
            padding: 5px 10px;
            float:right;
            background-color: lightblue;
        }
        .form{
            margin-top: 10px;
            padding:10px;
            display:flex;
            justify-content: center;
            align-items: center;
            background-color:#128c7e50;
            box-shadow: 5px 5px 5px  #0b52495e;
        }
        .form>input[type='text']{
            width:50%;
            border:none;
            padding:5px;
            border-radius: 15px 0px 0 15px;
        }
        .sender{
            padding:5px;
            width:20%;
            font-weight: 600;
            background-color: #B3FFB3;
            border:none;
            border-radius: 0 15px 15px 0;
        }
        #messageinput {
            color: darkgreen;
        }
    </style>


</head>
<body>
<div class="main">

    <div class="chat">
        <div class="messageContainer">
           
        </div>


        <div class="form">
            <input type="text" name="message" id="messageinput">
            <button class="sender">Send</button>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $('.sender').click(function () {
                if (!$('#messageinput').val()) {
                    alert("You cannot send empty message");
                }
                if ($('#messageinput').val()) {
                    var message = $('#messageinput').val();
                    $.post('ChatServlet', {
                        message: message
                    },
                        function (responseText) {
                            $('.messageContainer').append("" +
                                "<div class='user2'>" +
                                "   <div class='pill2'>" +
                                "       <h3>You</h3>" +
                                "       <p class='user2Message'>" +
                                responseText + "" +
                                "       </p>" +
                                "   </div>" +
                                "   </div>")
                        })
                };
            });
            setInterval(function () {
                $.post('ChatBuilderServlet', function (response) {
                    if (response !== 'Error') {
                        $('.messageContainer').append("<div class='user1'><div class='pill1'><h3>" + response.username + "</h3><p class='user1Message'>" +
                            response.message + "</p></div></div>")
                    }
                });
            }, 1000);
        })
    </script>
</div>
</body>

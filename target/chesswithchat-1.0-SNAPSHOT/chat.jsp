<%--
  Created by IntelliJ IDEA.
  User: alemh
  Date: 17.01.2021
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        .main{
            width:100%;
            display:flex;
            flex-direction: row;

        }

        .chat{
            width:30%;
            height: 60vh;
            display:flex;
            flex-direction: column;
            padding:20px;
            color:#2C302E;
            background-color: #ECE5DD;
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
            border-radius: 15px;
            padding: 5px 10px;
            float:left;
            background-color: rgb(179, 236, 255);
            box-shadow: -5px 5px 5px #0b52495e;
        }

        .pill2{
            border-radius: 15px;
            min-width: 20%;
            padding: 5px 10px;
            float:right;
            background-color: #DCF8C6;
            box-shadow: 5px 5px 5px  #0b52495e;
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

    </style>

    <script>
        $(document).ready(function() {
            $('.sender').click(function(){
                if(!$('#messageinput').val()){
                    alert("You cannot send empty message");
                }
                if($('#messageinput').val()){

                    var message = $('#messageinput').val();
                    $.post('ChatServlet', {

                            message: message
                        },

                        function(responseText){

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

            setInterval(function() {

                $.post('ChatBuilderServlet', function (response) {

                    if (response !== 'Error') {
                        $('.messageContainer').append("<div class='user1'><div class='pill1'><h3>" + response.username + "</h3><p class='user1Message'>" +
                            response.message + "</p></div></div>")
                    }

                });

            }, 1000);

        })

    </script>
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

</div>
</body>

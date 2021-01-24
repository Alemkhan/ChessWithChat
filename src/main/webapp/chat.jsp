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
            font-family: Arial;
        }
        .main{
            width:100%;
            display:flex;
            flex-direction: row;

        }

        .chat{
            width:80%;
            height: 80%;
            display:flex;
            flex-direction: column;
            padding:20px;
            color:#2C302E;
            background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('https://images8.alphacoders.com/102/1026626.png');
            background-size: cover;
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


        .pill2{
            min-width: 20%;
            padding: 5px 10px;
            float:right;
            color: #101010;
            background-color: #99ddff;
        }

        .form{
            margin-top: 10px;
            padding:10px;
            display:flex;
            justify-content: center;
            align-items: center;
            background-color:#101010;
        }
        .form>input[type='text']{
            width:50%;
            border:none;
            padding:10px;
        }
        .sender{
            font-family: Arial;
            padding: 9px;
            width:5%;
            background-color: rgb(255, 51, 51);
            border:none;
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
            <button class="sender"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right-circle" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"/>
            </svg></button>
        </div>
    </div>
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
</div>
</body>

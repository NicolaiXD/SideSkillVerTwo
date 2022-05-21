
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset=UTF-8">

        
        
        <link rel="stylesheet" href="csstwo/register.css"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type = "text/javascript">
            function validate() {

                if (document.myForm.username.value == "") {
                    alert("Please provide your name!");
                    document.myForm.username.focus();
                    return false;
                }
                if (document.myForm.email.value == "") {
                    alert("Please provide your Email!");
                    document.myForm.email.focus();
                    return false;
                }

                if (document.myForm.password.value == "") {
                    alert("Please provide your password!");
                    document.myForm.password.focus();
                    return false;
                }
                return(true);
            }

        </script> 
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    </head>
    <body>

        <div class ="regpage">
            <div class="container"> 

                <div class="title">Registration</div>

                <form name= "myForm" method="post" action="registerservlet" onsubmit="return validate()">

                    <div class="user-details">
                        <div class ="input-box">
                            <br>
                            <span class="details">Username:</span>
                            <input type="text" id="username"  name="username" placeholder="Username"/>
                            <br>
                            <span class="details">Password:</span>
                            <input type="password" name="password" id="password" placeholder="Password"/> 
                            <br>
                            <span class="details">Confirm Password:</span>
                            <input type="password" name="cpass" id="cpass" placeholder="Confirm Password"/> 

                        </div> 
                        <br><br>
                        <div class="role-choice">

                            <span class="details" > Role </span>
                            <select name="role" id="role">
                                <option> Admin </option>
                                <option> Teacher </option>
                                <option> Student </option>
                            </select>
                            
                        </div>
                        <br><br>
                        <div class="captcha">
                            <img src="<%= request.getContextPath()%>/simple">
                            <input type="text" name="rcaptcha" placeholder="What do you see?" required>
                            <br>
                        </div>

                        <div class="button"> 
                            <input type="submit" name="btn_login" value="submit"/>
                        </div> 
                    </div>
                    <br><br>
                </form>
            </div>
        </div>

    </form>

</body>
</html>
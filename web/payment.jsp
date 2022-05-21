<%-- 
    Document   : payment
    Created on : May 20, 2022, 11:20:23 PM
    Author     : LENAR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
        <link rel="stylesheet" href="csstwo/paymentstyle.css">

    </head>

    <body>

        <div class="pricingcontainer">

            <section class ="pricing">
                <div class="pricingplan">
                    <h1 class="pricingtitle">Basic Plan</h1>
                    <h2 class="pricingsummary">For starters</h2>
                </div>

                <div class="pricingdescription">
                    <ul class="pricelist">
                        <li class="pricingfeature">Lorem Ipsum #1</li>
                        <li class="pricingfeature">Lorem Ipsum #2</li>
                        <li class="pricingfeature">Lorem Ipsum #3</li>
                    </ul>
                </div>

                <div class="pricingactions">
                    <p class="pricecost">P199</p>
                    <p class="pricetext">per month</p>
                    
                    <div class="paymentchoice">
                    
                    <a href="#" class="gcashbutton">Gcash</a>
                    <a href="#" class="paymayabutton">Maya</a>
                    
                    </div>
                    
                    <a href="home.jsp" class="pricebutton">Proceed</a>
                    <p class="pricetext">please proceed while admin checks for you</p>
                </div>
            </section>

        </div>
        
        <!--modal practice-->
<!--        <div class="paymentmodal">
            <div class="gcashscreen">
                <img src="gcash.jpg">
                
                <form action="">
                    <input type="text" placeholder="Username">
                    <input type="text" placeholder="Reference No.">
                    <a href="#" class="button">Submit</a>
                </form>
                
            </div>
        </div>-->

    </body>
</html>
